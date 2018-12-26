package yxr.com.myapp.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

import yxr.com.myapp.R;
import yxr.com.myapp.util.AlterMessage;
import yxr.com.myapp.util.Logger;

public class MedialPlayerActivity extends AppCompatActivity {
    private VideoView videoView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_player_activity);
        Button play = (Button) findViewById(R.id.btn_play);
        Button pause = (Button) findViewById(R.id.btn_pause);
        Button replay = (Button) findViewById(R.id.btn_replay);
        videoView = (VideoView) findViewById(R.id.video_view);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        } else {
            initVideoPlayer();
        }
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int duration = videoView.getDuration();
                Logger.log("duration",duration+"");
                AlterMessage.toast(MedialPlayerActivity.this,duration+"");
                if (!videoView.isPlaying()){
                    videoView.start();
                }
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    videoView.pause();
                }
            }
        });
        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    videoView.resume();
                }
            }
        });
    }

    private void initVideoPlayer() {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),"1.mp4");
            videoView.setVideoPath(file.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    initVideoPlayer();
                }else{
                    AlterMessage.toast(this,"faild permission");
                }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView != null){
            videoView.suspend();
        }
    }
}
