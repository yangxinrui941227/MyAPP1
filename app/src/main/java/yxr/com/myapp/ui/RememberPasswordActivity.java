package yxr.com.myapp.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import yxr.com.myapp.R;
import yxr.com.myapp.util.AlterMessage;

public class RememberPasswordActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.remember_password);
        final EditText account = (EditText) findViewById(R.id.account);
        final EditText password = (EditText) findViewById(R.id.password);
        final CheckBox is_remember = (CheckBox) findViewById(R.id.is_remember);
        Button login = (Button) findViewById(R.id.login_btn);
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(RememberPasswordActivity.this);
        boolean is_remember1 = preferences.getBoolean("is_remember", false);
        if (is_remember1){
            String account1 = preferences.getString("account", "");
            String password1 = preferences.getString("password", "");
            is_remember.setChecked(is_remember1);
            account.setText(account1);
            password.setText(password1);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acc = account.getText().toString();
                String pass = password.getText().toString();
                if (account.getText()==null || acc == ""){
                    AlterMessage.toast(RememberPasswordActivity.this,"account must be not null");
                    account.setSelection(0);
                    return;
                }
                if (password.getText() == null || pass == ""){
                    AlterMessage.toast(RememberPasswordActivity.this,"password must be not null");
                    password.setSelection(0);
                    return;
                }
                SharedPreferences.Editor edit = preferences.edit();
                if (acc.equals("admin") && pass.equals("123456")){
                    if (is_remember.isChecked()){
                        edit.putBoolean("is_remember",true);
                        edit.putString("account", acc);
                        edit.putString("password", pass);
                    }else{
                        edit.clear();
                    }
                    AlterMessage.toast(RememberPasswordActivity.this,"login success");
                    finish();
                }else{
                    AlterMessage.toast(RememberPasswordActivity.this,"login failed");
                    account.setText("");
                    password.setText("");
                    is_remember.setChecked(false);
                }

                edit.apply();
            }
        });
    }
}
