package com.example.javacalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Loggedin extends AppCompatActivity {
    WebView haruni;

    @SuppressLint({"MissingInflatedId", "SetJavaScriptEnabled"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin);
        haruni=findViewById(R.id.webWiew);
        haruni.setWebViewClient(new WebViewClient());
        haruni.loadUrl("http://web.harran.edu.tr/bilgisayar");
        WebSettings webSettings=haruni.getSettings();
        boolean ture=true;
        webSettings.setLoadWithOverviewMode(ture);
        webSettings.setUseWideViewPort(true);
        webSettings.setJavaScriptEnabled(true);
    }
}