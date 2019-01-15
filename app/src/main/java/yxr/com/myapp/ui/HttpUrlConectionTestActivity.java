package yxr.com.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import yxr.com.myapp.R;
import yxr.com.myapp.util.AlterMessage;

public class HttpUrlConectionTestActivity extends AppCompatActivity {
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_conection_test);
        Button btn1 = findViewById(R.id.btn_view);
        final EditText editText = findViewById(R.id.url_text);
        editText.setText("https://www.baidu.com");
        text = findViewById(R.id.text_view1);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               /* new Thread(new Runnable() {
                    @Override
                    public void run() {
                        BufferedReader bufferedReader = null;
                        try {
                            URL url = new URL(editText.getText().toString());
                            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                            httpURLConnection.setRequestMethod("GET");
                            httpURLConnection.setConnectTimeout(8000);
                            httpURLConnection.setReadTimeout(8000);
                            InputStream inputStream = httpURLConnection.getInputStream();
                            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                            final StringBuilder sb = new StringBuilder();
                            String line;
                            while((line=bufferedReader.readLine()) != null){
                                sb.append(line);
                            }
                            httpURLConnection.disconnect();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    text.setText(sb.toString());
                                }
                            });
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (ProtocolException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            try {
                                if (bufferedReader != null){
                                    bufferedReader.close();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();*/
                callOkHttp(editText.getText().toString());
            }
        });
    }
    public void callOkHttp(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                try {
                    Response execute = client.newCall(request).execute();
                    String string = execute.body().string();
                    showResponse(string);
                } catch (IOException e) {
                    AlterMessage.toast(HttpUrlConectionTestActivity.this,e.getMessage());
                }
            }
        }).start();



    }
    public void showResponse(final String str){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                text.setText(str);
            }
        });
    }
}
