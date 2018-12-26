package yxr.com.myapp.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;

import yxr.com.myapp.R;
import yxr.com.myapp.entity.Fruit;

public class RecycleListActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycl_list);
        RecyclerView recyclerView = findViewById(R.id.recycle_1);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        FruitAdapter2 fruitAdapter2 = new FruitAdapter2(initList());
        recyclerView.setAdapter(fruitAdapter2);

    }

    private ArrayList<Fruit> initList() {
        ArrayList<Fruit> list = new ArrayList<>();
        for (int i=0;i<10;i++){
            list.add(new Fruit("tu1",R.drawable.a));
            list.add(new Fruit("tu2",R.drawable.b));
        }
        return list;
    }
}
