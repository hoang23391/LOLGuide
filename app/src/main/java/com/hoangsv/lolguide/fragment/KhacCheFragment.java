package com.hoangsv.lolguide.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hoangsv.lolguide.R;

import org.json.JSONArray;
import org.json.JSONException;

public class KhacCheFragment extends Fragment {
    TextView txtDongDoi, txtDoiThu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_khac_che, container, false);
        txtDongDoi= (TextView) v.findViewById(R.id.txtDongDoi);
        txtDoiThu= (TextView) v.findViewById(R.id.txtDoiThu);


        Bundle bundle=getArguments();
        String allytips = bundle.getString("allytips1");
        String enemytips = bundle.getString("enemytips1");

        try {
            JSONArray jsonArray=new JSONArray(allytips);
            String ally = "";
            for (int i=0; i<jsonArray.length();i++){
                ally = ally+(i+1)+". "+jsonArray.get(i).toString()+"<br/>";
            }
            txtDongDoi.setText(Html.fromHtml(ally));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArray=new JSONArray(enemytips);
            String enemy = "";
            for (int i=0; i<jsonArray.length();i++){
                enemy = enemy+(i+1)+". "+jsonArray.get(i).toString()+"<br/>";
            }
            txtDoiThu.setText(Html.fromHtml(enemy));
        } catch (JSONException e) {
            e.printStackTrace();
        }



        return v;
    }
}
