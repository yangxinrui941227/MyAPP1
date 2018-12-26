package yxr.com.myapp.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import yxr.com.myapp.R;
import yxr.com.myapp.util.AlterMessage;
import yxr.com.myapp.util.Logger;

public class ContactTestActivity extends AppCompatActivity {
    ArrayAdapter<String> adapter;
    List<String> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_test);
        ListView listView = (ListView) findViewById(R.id.contact_list);
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,contactList);
        listView.setAdapter(adapter);
        if (ContextCompat.checkSelfPermission(this,Manifest.permission.READ_CONTACTS) !=
                PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},2);

        }else{
            readContacts();
        }
    }
    private void readContacts(){
        Cursor cursor = null;
        try{
            cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if (cursor != null){
                while(cursor.moveToNext()){
                    String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    contactList.add(name+":"+number);
                }
                adapter.notifyDataSetChanged();
            }
        }catch (Exception e){
            Logger.log("readContacts",e.getMessage());
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){

            case 2:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    readContacts();
                }else {
                    AlterMessage.toast(ContactTestActivity.this,"your read_contacts permission failed");
                }
                break;
        }
    }
}
