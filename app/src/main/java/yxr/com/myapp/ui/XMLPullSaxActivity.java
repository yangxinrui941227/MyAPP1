package yxr.com.myapp.ui;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import yxr.com.myapp.R;
import yxr.com.myapp.entity.TheEntity;
import yxr.com.myapp.util.Logger;
import yxr.com.myapp.util.OkHttpUtil;

public class XMLPullSaxActivity extends AppCompatActivity {

    private String response;
    private TextView textView;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
           switch (msg.what){
               case 1:
               textView.setText(textView.getText()+msg.getData().getString("key"));
           }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xmlpull_sax);


        Button btn1 = findViewById(R.id.pull);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response = OkHttpUtil.getResponse("http://192.168.0.3:8080/docs/get_data.xml", XMLPullSaxActivity.this);
                parseXMLWithPull(response);
            }
        });
        Button btn2 = findViewById(R.id.sax);
         textView = findViewById(R.id.tv);
        Button btn_json_obj = findViewById(R.id.json_obj);
        Button btn_gson = findViewById(R.id.gson);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response = OkHttpUtil.getResponse("http://192.168.0.3:8080/docs/get_data.xml", XMLPullSaxActivity.this);
                parseXMLWithSax(response);
            }
        });
        btn_json_obj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response = OkHttpUtil.getResponse("http://192.168.0.3:8080/docs/get_data1.json", XMLPullSaxActivity.this);
                parseJSONByJSONObject(response);
            }
        });
        btn_gson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                response = OkHttpUtil.getResponse("http://192.168.0.3:8080/docs/get_data1.json", XMLPullSaxActivity.this);
                OkHttpUtil.getRespose2("http://192.168.0.3:8080/docs/get_data1.json", XMLPullSaxActivity.this, new okhttp3.Callback() {
                    @Override
                    public void onFailure(Call call, final IOException e) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(e.getMessage());
                            }
                        });
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        System.out.println("onResponse@@@@@@@@@@@@@@@@@@@@@");
                        parseJSONByGson(response.body().string());
                    }
                });

                parseJSONByGson(response);
            }
        });
    }

    private void parseJSONByGson(String response) {
        Gson gson = new Gson();
        List<TheEntity> o = gson.fromJson(response, new TypeToken<List<TheEntity>>() {
        }.getType());
        if(o != null && o.size() >0){
            String str = "";
            for (TheEntity theEntity : o) {
                str += theEntity.getId()+","+theEntity.getName()+","+theEntity.getVersion()+";";
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("key",str);
                message.setData(bundle);
                message.what = 1;
                handler.sendMessage(message);
             }
        }

    }

    private void parseJSONByJSONObject(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i=0;i<jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                textView.setText(textView.getText().toString()+";"+id+","+name+","+version);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithSax(String response) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            XMLReader xmlReader = factory.newSAXParser().getXMLReader();
            ContentHandler contentHandler = new ContentHandler();
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(new InputSource(new StringReader(response)));
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseXMLWithPull(String response) {
        try {
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(response));
            int eventType = xmlPullParser.getEventType();
            String id ="";
            String name ="";
            String version ="";
            while (eventType != xmlPullParser.END_DOCUMENT) {
                String nodename = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if ("id".equals(nodename)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodename)) {
                           name = xmlPullParser.nextText();
                        } else if ("version".equals(nodename)) {
                          version =  xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG:{
                        if ("app".equals(name)){
                            Logger.log("id:",id);
                            Logger.log("name:",name);
                            Logger.log("version:",version);
                        }
                        break;
                    }

                }
                eventType = xmlPullParser.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
