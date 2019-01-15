package yxr.com.myapp.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

import yxr.com.myapp.R;

public class WebViewTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        Button btn1 = findViewById(R.id.btn_view);
        final WebView webView = findViewById(R.id.web_view1);
        final EditText editText = findViewById(R.id.url_text);
        editText.setText("https://www.baidu.com");


        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.setWebViewClient(new WebViewClient());
                webView.loadUrl(editText.getText().toString());
            }
        });
    }
}
