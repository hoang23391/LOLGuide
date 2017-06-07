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
import com.hoangsv.lolguide.utility.Constant;

public class CheckingUserAccountOthersFragment extends Fragment {
    WebView wvSamSoiOthers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_sam_soi_others, container, false);
        wvSamSoiOthers= (WebView) v.findViewById(R.id.wvSamSoiOthers);
        wvSamSoiOthers.loadUrl(Constant.URL_CHECKING_USER_INFORMATION_OTHER);
        // Enable Javascript
        WebSettings webSettings = wvSamSoiOthers.getSettings();
        webSettings.setJavaScriptEnabled(true);
        // Force links and redirects to open in the WebView instead of in a browser
        wvSamSoiOthers.setWebViewClient(new WebViewClient());

        wvSamSoiOthers.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {


                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(wvSamSoiOthers.canGoBack())
                            {
                                {
                                    {{
                                        {{

                                        }}
                                    }}
                                }
                                wvSamSoiOthers.goBack();
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
