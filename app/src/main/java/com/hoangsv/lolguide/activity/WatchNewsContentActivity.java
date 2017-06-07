package com.hoangsv.lolguide.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hoangsv.lolguide.R;

public class WatchNewsContentActivity extends AppCompatActivity {
    WebView wvNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_noi_dung_news);
        addControls();
        addEvents();
    }

    private void addEvents() {
        Intent intent=getIntent();
        String link = intent.getStringExtra("link");
        wvNews.loadUrl(link);
        // Enable Javascript
        WebSettings webSettings = wvNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Force links and redirects to open in the WebView instead of in a browser
        wvNews.setWebViewClient(new WebViewClient());
    }

    @Override
    public void onBackPressed() {
        if (wvNews.canGoBack()){
            wvNews.goBack();
        }
        else {
            finish();
        }
    }

    private void addControls() {
        wvNews= (WebView) findViewById(R.id.wvNews);
    }
}
