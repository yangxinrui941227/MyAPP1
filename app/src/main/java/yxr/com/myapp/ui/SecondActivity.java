package yxr.com.myapp.ui;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import yxr.com.myapp.R;
import yxr.com.myapp.entity.Fruit;

public class SecondActivity extends AppCompatActivity {

    private ArrayList<Fruit> a = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        TextView tv = (TextView) findViewById(R.id.text_view2);
        String str = getIntent().getStringExtra("who");
        tv.setText(tv.getText()+","+str);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("http://www.baidu.com"));
                startActivity(i);*/
                Toast.makeText(SecondActivity.this,"傻子点",Toast.LENGTH_SHORT).show();
                Intent i = new Intent();
                i.putExtra("key","fox");
                setResult(RESULT_OK,i);
                finish();
            }
        });
        String [] data = {"1","2"};
        ListView listView = (ListView) findViewById(R.id.list_view);
        a = initList();
        FruitAdapter fa =  new FruitAdapter(SecondActivity.this,R.layout.fruit_item_1,a);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<String>(SecondActivity.this,android.R.layout.simple_list_item_1,data);
        listView.setAdapter(fa);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = a.get(position);
                Toast.makeText(SecondActivity.this,fruit.getName(),Toast.LENGTH_LONG).show();
            }

        });

    }

    private ArrayList<Fruit> initList() {
        ArrayList<Fruit> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new Fruit("tu1",R.drawable.a));
            list.add(new Fruit("tu2",R.drawable.b));
        }
        return list;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent();
        i.putExtra("key","fox");
        setResult(RESULT_OK,i);
        finish();
    }
}
