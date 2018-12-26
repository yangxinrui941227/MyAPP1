package yxr.com.myapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import yxr.com.myapp.util.AlterMessage;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        AlterMessage.toast(context,"received in MyBroadcastReceiver");
    }
}
