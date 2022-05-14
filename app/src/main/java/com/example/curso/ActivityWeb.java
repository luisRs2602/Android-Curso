package com.example.curso;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ActivityWeb extends AppCompatActivity {
    WebView wb1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        wb1=(WebView)findViewById(R.id.wbv1);
        String url="www.google.com";
        wb1.setWebViewClient(new WebViewClient());
        wb1.loadUrl("http://"+url);
    }
}