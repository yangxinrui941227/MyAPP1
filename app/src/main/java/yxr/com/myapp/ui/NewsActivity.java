package yxr.com.myapp.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import yxr.com.myapp.R;
import yxr.com.myapp.frag.News_frag;

public class NewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        News_frag fragmentById = (News_frag)getSupportFragmentManager().findFragmentById(R.id.news_title_fragment);
        fragmentById.refresh(title,content);

     }
    public static void actionStart(Context context,String title, String content){
        Intent intent = new Intent(context, NewsActivity.class);
        intent.putExtra("title",title);
        intent.putExtra("content",content);
        context.startActivity(intent);
    }

}
