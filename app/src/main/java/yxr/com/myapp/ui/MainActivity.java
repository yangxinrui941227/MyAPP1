package yxr.com.myapp.ui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;

import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.MapView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import yxr.com.myapp.R;
import yxr.com.myapp.sql.MySql;
import yxr.com.myapp.util.AlterMessage;
import yxr.com.myapp.util.Logger;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private IntentFilter intentFilter;
    private NetChangeReceiver netChangeReceiver;
    private MySql mySql;
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        } else {
            initMediaPlayer();
        }
        final Button btn1 = (Button) findViewById(R.id.main_btn1);
        btn1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                btn1.setText("onclicked");
                return false;
            }
        });
        btn1.setOnClickListener(this);
        Button btn2 = (Button) findViewById(R.id.main_btn2);
        btn2.setOnClickListener(this);
        Button btn3 = (Button) findViewById(R.id.main_btn3);
        btn3.setOnClickListener(this);
        Button btn4 = (Button) findViewById(R.id.main_btn4);
        btn4.setOnClickListener(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        netChangeReceiver = new NetChangeReceiver();
        registerReceiver(netChangeReceiver, intentFilter);


        Button btn5 = (Button) findViewById(R.id.main_btn5);
        btn5.setOnClickListener(this);
        Button btn6 = (Button) findViewById(R.id.main_btn6);
        btn6.setOnClickListener(this);
        Button btn7 = (Button) findViewById(R.id.main_btn7);
        btn7.setOnClickListener(this);
        Button btn8 = (Button) findViewById(R.id.main_btn8);
        btn8.setOnClickListener(this);
        Button btn9 = (Button) findViewById(R.id.main_btn9);
        btn9.setOnClickListener(this);
        Button btn10 = (Button) findViewById(R.id.main_btn10);
        btn10.setOnClickListener(this);
        Button btn11 = (Button) findViewById(R.id.main_btn11);
        btn11.setOnClickListener(this);

        Button btn12 = (Button) findViewById(R.id.main_btn12);
        btn12.setOnClickListener(this);


        Button btn13_1 = (Button) findViewById(R.id.main_btn13_1);
        btn13_1.setOnClickListener(this);
        Button btn13_2 = (Button) findViewById(R.id.main_btn13_2);
        btn13_2.setOnClickListener(this);
        Button btn13_3 = (Button) findViewById(R.id.main_btn13_3);
        btn13_3.setOnClickListener(this);
        Button btn14 = (Button) findViewById(R.id.main_btn14);
        btn14.setOnClickListener(this);
        Button btn15 = findViewById(R.id.main_btn15);
        btn15.setOnClickListener(this);
        Button btn16 = findViewById(R.id.main_btn16);
        btn16.setOnClickListener(this);
        Button btn17 = findViewById(R.id.main_btn17);
        btn17.setOnClickListener(this);
        Button btn18 = findViewById(R.id.main_btn18);
        btn18.setOnClickListener(this);
        Button btn19 = findViewById(R.id.main_btn19);
        btn19.setOnClickListener(this);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void initMediaPlayer() {
        File file = null;
        try {
            file = new File(Environment.getExternalStorageDirectory(), "if.mp3");
            Logger.log("initMediaPlayer", file.getPath());
            mediaPlayer.setDataSource(file.getPath());
            mediaPlayer.prepare();
        } catch (Exception e) {
            AlterMessage.toast(this, "没有找到" + file.getAbsolutePath());

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netChangeReceiver);
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    call();
                } else {
                    AlterMessage.toast(MainActivity.this, "you permission failed");
                }
                break;
            case 2:
                if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    initMediaPlayer();
                } else {
                    AlterMessage.toast(MainActivity.this, "you permission failed");
                    finish();
                }
                break;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View v) {
        Intent i = null;
        switch (v.getId()) {
            case R.id.main_btn1:
                i = new Intent(MainActivity.this, UIActitvty.class);
                startActivity(i);
                break;
            case R.id.main_btn2:
                i = new Intent(MainActivity.this, RecycleListActivity.class);
                startActivity(i);
                break;
            case R.id.main_btn3:
                i = new Intent(MainActivity.this, FragActivity.class);
                startActivity(i);
                break;
            case R.id.main_btn4:
                i = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(i);
                break;
            case R.id.main_btn5:
                i = new Intent("yxr.com.myapp.receiver.MY_BROADCAST");
                i.setComponent(new ComponentName("yxr.com.myapp.receiver", "yxr.com.myapp.receiver.MyBroadcastReceiver"));
                sendBroadcast(i);
                break;
            case R.id.main_btn6:
                Button viewById = (Button) v.findViewById(R.id.main_btn6);
                CharSequence text = viewById.getText();
                if (text.toString().equals("^0^")) {
                    viewById.setText("^-^");
                } else {
                    viewById.setText("^0^");
                }

                FileOutputStream data = null;
                BufferedWriter writer = null;
                try {
                    data = openFileOutput("data", Context.MODE_PRIVATE);
                    writer = new BufferedWriter(new OutputStreamWriter(data));
                    writer.write("^-^");
                    FileInputStream input = openFileInput("data");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String s = reader.readLine();
                    Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (writer != null) {
                        try {
                            writer.flush();
                            writer.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    if (data != null) {
                        try {
                            data.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                break;
            case R.id.main_btn7:
                i = new Intent(MainActivity.this, RememberPasswordActivity.class);
                startActivity(i);
                break;
            case R.id.main_btn8:
                Intent ii = new Intent(MainActivity.this, SqlActivity.class);
                startActivity(ii);
                break;
            case R.id.main_btn9:
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) !=
                        PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 1);
                } else {
                    call();
                }
                break;
            case R.id.main_btn10:
                Intent iiii = new Intent(MainActivity.this, ContactTestActivity.class);
                startActivity(iiii);
                break;
            case R.id.main_btn11:
                NotificationManager systemService = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                NotificationChannel notificationChannel = new NotificationChannel("channel1", "channelName", NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setLightColor(Color.RED);
                notificationChannel.setVibrationPattern(new long[]{0, 1000, 0, 1000});
                NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel1");
                systemService.createNotificationChannel(notificationChannel);
                Intent i1 = new Intent(this, SecondActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i1, 0);
                Notification build = builder.
                        setStyle(new NotificationCompat.BigTextStyle().bigText("asdfffffffffffffffffffffffffffffffffffkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk")).
                        setContentTitle("内容").
                        setSmallIcon(R.mipmap.ic_launcher).
                        setVisibility(111).
                        setWhen(System.currentTimeMillis()).
                        setContentIntent(pendingIntent).
                        setLights(Color.RED, 1000, 1000).
                        setAutoCancel(true).
                        build();
                systemService.notify(1, build);
                break;
            case R.id.main_btn12:
                startActivity(new Intent(this, CameraActivity.class));
                break;
            case R.id.main_btn13_1:
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start();
                }
                break;
            case R.id.main_btn13_2:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                }
                break;
            case R.id.main_btn13_3:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.reset();
                    initMediaPlayer();
                }
                break;
            case R.id.main_btn14:
                startActivity(new Intent(MainActivity.this, MedialPlayerActivity.class));
                break;
            case R.id.main_btn15:
                startActivity(new Intent(MainActivity.this, WebViewTestActivity.class));
                break;
            case R.id.main_btn16:
                startActivity(new Intent(MainActivity.this, HttpUrlConectionTestActivity.class));
                break;
            case R.id.main_btn17:
                startActivity(new Intent(MainActivity.this, XMLPullSaxActivity.class));
                break;
            case R.id.main_btn18:
                startActivity(new Intent(MainActivity.this, TestServiceActivity.class));
                break;
            case R.id.main_btn19:
                startActivity(new Intent(MainActivity.this,MapActivity.class));
                break;
        }

    }



    private void call() {
        try {
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:10010"));
            startActivity(i);
        } catch (Exception e) {
            Logger.log("^^^^^^^^^^", e.getMessage());
        }
    }

    class NetChangeReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager systemService = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = systemService.getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                AlterMessage.toast(MainActivity.this, "net work change");
            } else {
                AlterMessage.toast(MainActivity.this, "network die");
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

}
