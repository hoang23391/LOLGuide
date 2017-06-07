package com.hoangsv.lolguide.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoangsv.lolguide.R;

public class LoreFragment extends Fragment {

    SQLiteDatabase database;

    TextView txtTruyenThuyet,txtTruyenThuyetND;
    //WebView webView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_truyen_thuyet, container, false);


        //webView= (WebView) v.findViewById(R.id.webview);
        txtTruyenThuyet= (TextView) v.findViewById(R.id.txtTruyenThuyet);
        txtTruyenThuyetND= (TextView) v.findViewById(R.id.txtTruyenThuyetND);

        addEvents();
        return v;
    }

    private void addEvents() {
        xuLyLayThongTin();
    }

    private void xuLyLayThongTin() {
        Bundle bundle=getArguments();
        String lore1=bundle.getString("lore1");

        txtTruyenThuyetND.setText(Html.fromHtml(lore1));

        /*bundle
        String lore2="<html><body>" + "<p style=\"color:#FF0000\" align=\"justify\">" +lore1+ "</p> " + "</body></html>";
        webView.loadData(lore1,"text/html;charset=utf-8", "utf-8");*/

        /*Xu ly bang cach rut ra tu database
        database = Database.initDatabase(getActivity(),DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM champion WHERE id=?",new String[]{id1});
        cursor.moveToFirst();
        String lore = cursor.getString(18);
        String lore1="<html><body>" + "<p style=\"color:#FF0000\" align=\"justify\">" +lore+ "</p> " + "</body></html>";
        webView.loadData(lore1,"text/html;charset=utf-8", "utf-8");*/


        /*Bundle bundle=getArguments();
        String id1=bundle.getString("id1");
        String lore1=bundle.getString("lore1");
        txtTruyenThuyet.setText(Html.fromHtml(lore1));*/
    }
}
