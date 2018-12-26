package yxr.com.myapp.ui;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import yxr.com.myapp.R;
import yxr.com.myapp.util.Logger;

public class UIActitvty extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Logger.log("UIActivity","onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_layout);
        ActionBar bar = getSupportActionBar();
        if (bar != null){
            bar.hide();
        }
        Button btn1 = (Button) findViewById(R.id.btn1);
        if (savedInstanceState != null){
            String a = savedInstanceState.getString("a");
            EditText et = (EditText) findViewById(R.id.et1);
            et.getText().append(a);
        }
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TextView tv1 = (TextView) findViewById(R.id.tv1);
                tv1.setText("yxr");
                Toast.makeText(UIActitvty.this,"world has been changed",Toast.LENGTH_SHORT).show();
            }
        });
        Button btn2 = (Button) findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView iv = (ImageView) findViewById(R.id.img1);
                Resources resources = iv.getResources();
                AlertDialog.Builder dialog = new AlertDialog.Builder(UIActitvty.this);
                dialog.setTitle("this is dialog");
                dialog.setMessage("dialogMessage");
                dialog.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UIActitvty.this,"ok",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(UIActitvty.this,"no",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show();
                iv.setImageResource(R.drawable.b);
                Intent i = new Intent(UIActitvty.this,DialogActivity.class);
                startActivity(i);
            }
        });

        Button viewById = (Button) findViewById(R.id.title_edit);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent("shazi");
                i.putExtra("who","你是个大筛子");
                i.addCategory("dashazi");
                startActivityForResult(i,1);
//                startActivity(i);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        EditText et = (EditText) findViewById(R.id.et1);
        if (et.getText() != null ){

            outState.putString("a",et.getText().toString());

        }else
            outState.putString("a","大猪蹄子");
    }

    @Override
    protected void onPause() {
        Logger.log("UIActivity","onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Logger.log("UIActivity","onResume");
        super.onResume();
    }

    @Override
    protected void onStop() {
        Logger.log("UIActivity","onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        Logger.log("UIActivity","onRestart");
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        Logger.log("UIActivity","onDestroy");
        super.onDestroy();
    }

    @Override
    protected void onStart() {
        Logger.log("UIActivity","onStart");
        super.onStart();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mian,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.add_item:
                Toast.makeText(UIActitvty.this,"add_item click",Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(UIActitvty.this,"remove_item click",Toast.LENGTH_SHORT).show();
                break;
            case R.id.close_item:
                finish();
            case R.id.to_activity2:
                Intent i = new Intent("shazi");
                i.putExtra("who","你是个大筛子");
                i.addCategory("dashazi");
                startActivityForResult(i,1);
//                startActivity(i);
                break;
                default:
                    break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case 1:
                if (resultCode == RESULT_OK){
                    String key = data.getStringExtra("key");
                    Toast.makeText(UIActitvty.this,key,Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
