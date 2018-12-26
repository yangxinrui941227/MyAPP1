package yxr.com.myapp.ui;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import yxr.com.myapp.R;

public class TitleLayout extends LinearLayout {
    public TitleLayout(Context context,  AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button viewById = (Button) findViewById(R.id.title_back);
        viewById.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ((Activity)getContext()).finish();
            }
        });
    }
}
