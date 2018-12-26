package yxr.com.myapp.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.Future;

import yxr.com.myapp.R;
import yxr.com.myapp.entity.Fruit;

public class FruitAdapter2 extends RecyclerView.Adapter<FruitAdapter2.ViewHolder> {
    private List<Fruit> list;

    public FruitAdapter2(List<Fruit> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fruit_item_1,viewGroup,false);
        final ViewHolder vh = new ViewHolder(view);
        vh.tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = vh.getAdapterPosition();
                Fruit fruit = list.get(adapterPosition);
                Toast.makeText(v.getContext(),"click"+adapterPosition+fruit.getName(),Toast.LENGTH_LONG).show();
            }
        });
        vh.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPosition = vh.getAdapterPosition();
                Fruit fruit = list.get(adapterPosition);
                Toast.makeText(v.getContext(),"click"+adapterPosition+fruit.getImageId(),Toast.LENGTH_LONG).show();
            }
        });
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Fruit f = list.get(i);
        viewHolder.iv.setImageResource(f.getImageId());
        viewHolder.tv.setText(f.getName());
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv = (ImageView)itemView.findViewById(R.id.fruit_img);
            tv = (TextView) itemView.findViewById(R.id.fruit_name);
        }
    }
}
