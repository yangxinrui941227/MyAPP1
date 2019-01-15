package yxr.com.myapp.util;

import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpUtil {
    private static String str = "";
    public static String getResponse(final String url, final Activity activity){
        new Thread(new Runnable() {
            @Override
            public void run() {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url(url).build();
                try {
                    Response execute = client.newCall(request).execute();
                    str = execute.body().string();
                } catch (IOException e) {
                    AlterMessage.toast(activity,e.getMessage());
                }
            }
        }).start();
       return str;
    }

    public static void getRespose2(final String url, final Activity activity,okhttp3.Callback callback){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(callback);
    }
    public static void showText(final Activity activity, final TextView view,final String text){
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                view.setText(text);
            }
        });
    }
}
