package com.hoangsv.lolguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class XemNoiDungNewsActivity extends AppCompatActivity {
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
    }

    private void addControls() {
        wvNews= (WebView) findViewById(R.id.wvNews);
    }
}
