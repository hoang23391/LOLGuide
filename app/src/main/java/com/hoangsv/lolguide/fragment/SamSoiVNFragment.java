package com.hoangsv.lolguide.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.hoangsv.lolguide.R;

public class SamSoiVNFragment extends Fragment {
    WebView wvSamSoiVN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_sam_soi_vn, container, false);
        wvSamSoiVN= (WebView) v.findViewById(R.id.wvSamSoiVN);
        wvSamSoiVN.loadUrl("http://lienminhsamsoi.vn/vi/summoner/hoangcoi113/profile");
        // Enable Javascript
        WebSettings webSettings = wvSamSoiVN.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Force links and redirects to open in the WebView instead of in a browser
        wvSamSoiVN.setWebViewClient(new WebViewClient());

        wvSamSoiVN.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {


                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(wvSamSoiVN.canGoBack())
                            {
                                wvSamSoiVN.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });
        return v;
    }
}
