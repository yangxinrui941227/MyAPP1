package yxr.com.myapp.service;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;

import yxr.com.myapp.R;
import yxr.com.myapp.ui.MainActivity;
import yxr.com.myapp.ui.TestServiceActivity;
import yxr.com.myapp.util.AlterMessage;
import yxr.com.myapp.util.Logger;

public class TestService extends Service {
    private DownloadBinder mBinder = new DownloadBinder();
    public class DownloadBinder extends Binder{
        public  void startDownload(){
            Logger.log("*************************","startDownload");
        }
        public  int getProgress(){
            Logger.log("*************************","getProgress");
            return 0;
        }


    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this,0,intent,0);
        Notification build = new NotificationCompat.Builder(this).setContentTitle("this is coentent title").setContentText("text").setWhen(System.currentTimeMillis()).setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .build();
        startForeground(1,build);

        Logger.log("*************************","service onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.log("*************************","onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Logger.log("*************************","onDestroy");
        super.onDestroy();

    }
}
