package yxr.com.myapp.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import yxr.com.myapp.R;

public class News_frag extends Fragment {

    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }
    public void refresh(String title,String content){
        View viewById = view.findViewById(R.id.visibility_layout);
        viewById.setVisibility(View.VISIBLE);
        TextView titleView = (TextView)view.findViewById(R.id.news_title);
        TextView contentView = (TextView)view.findViewById(R.id.news_content);
        titleView.setText(title);
        contentView.setText(content);

    }
}
