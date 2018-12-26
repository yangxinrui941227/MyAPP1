package yxr.com.myapp.frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yxr.com.myapp.R;
import yxr.com.myapp.entity.News;
import yxr.com.myapp.ui.NewsActivity;

public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView re = (RecyclerView) view.findViewById(R.id.news_title_recycle_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        re.setLayoutManager(layoutManager);
        NewsAdapter newsAdapter = new NewsAdapter(getNews());
        re.setAdapter(newsAdapter);

        return view;
    }

    private List<News> getNews() {
        List<News> l = new ArrayList<>();
        for (int i=0;i<10;i++){
            News n = new News();
            n.setTitle("this is title"+i);
            n.setContent("this is content "+i);
            l.add(n);
        }
        return l;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout) != null)
        {
            isTwoPane = true;
        }else {
            isTwoPane = false;
        }
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> list;
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_item, viewGroup, false);
            final ViewHolder viewHolder = new ViewHolder(inflate);
            viewGroup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news = list.get(viewHolder.getAdapterPosition());
                    if (isTwoPane){
                        News_frag fragmentById = (News_frag) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        fragmentById.refresh(news.getTitle(),news.getContent());
                    }else {
                        NewsActivity.actionStart(getActivity(),news.getTitle(),news.getContent());
                    }
                }
            });
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            News news = list.get(i);
            viewHolder.textView.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView textView;
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textView = (TextView)itemView.findViewById(R.id.news_title);
            }
        }
        public NewsAdapter(List<News> list){
            this.list = list;
        }

    }
}
