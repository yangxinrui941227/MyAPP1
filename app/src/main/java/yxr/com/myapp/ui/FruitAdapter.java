package yxr.com.myapp.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import yxr.com.myapp.R;
import yxr.com.myapp.entity.Fruit;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId ;
    public FruitAdapter( Context context, int resource,  List<Fruit> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        Fruit item = getItem(position);
        View inflate ;
        ViewHolder vh;
        if (convertView == null){
            inflate = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            vh = new ViewHolder();
            vh.iv = (ImageView) inflate.findViewById(R.id.fruit_img);
            vh.tv = (TextView) inflate.findViewById(R.id.fruit_name);
            inflate.setTag(vh);
        }else{
            inflate = convertView;
            vh = (ViewHolder) inflate.getTag();
        }
        vh.tv.setText(item.getName());
        vh.iv.setImageResource(item.getImageId());
        return inflate;
    }
    class ViewHolder{
        ImageView iv;
        TextView tv;
    }
}
