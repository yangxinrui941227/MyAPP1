package yxr.com.myapp.util;

import android.content.Context;
import android.widget.Toast;

public class AlterMessage {
    private static Toast toast = null;
    public static void toast(Context context,String msg){
        if (toast == null){
            toast = Toast.makeText(context,msg,Toast.LENGTH_LONG);
        }else{
            toast.setText(msg);
        }

        toast.show();
    }

}
