package com.hoangsv.lolguide.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.activity.ChiTietTrangPhucActivity;
import com.hoangsv.lolguide.adapter.TrangPhucAdapter;
import com.hoangsv.lolguide.model.TrangPhuc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrangPhucFragment extends Fragment {

    ImageView imgTrangPhuc;
    ListView lvTrangPhuc;
    ArrayList<TrangPhuc> dsTrangPhuc;
    TrangPhucAdapter adapterTrangPhuc;

    final String DATABASE_NAME="lol.sqlite";
    SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_trang_phuc, container, false);
        lvTrangPhuc= (ListView) v.findViewById(R.id.lvTrangPhucF);
        dsTrangPhuc=new ArrayList<>();
        adapterTrangPhuc=new TrangPhucAdapter(getActivity(),R.layout.trang_phuc_recycler_item,dsTrangPhuc);
        lvTrangPhuc.setAdapter(adapterTrangPhuc);
        imgTrangPhuc= (ImageView) v.findViewById(R.id.imgTrangPhuc);
        addEvents();
        return v;
    }

    private void addEvents() {
        xuLyLayThongTin();
        lvTrangPhuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),ChiTietTrangPhucActivity.class);
                intent.putExtra("ImgLink",dsTrangPhuc.get(position).getImage());
                startActivity(intent);
            }
        });
    }

    private void xuLyLayThongTin() {
        Bundle bundle=getArguments();
        String skins1=bundle.getString("skins1");
        String key1=bundle.getString("key1");



            try{

                JSONArray root=new JSONArray(skins1);
                //JSONObject root=new JSONObject(skins1);
                //JSONArray jsonArray=root.getJSONArray("trangphuc");
                for(int i=0;i<root.length();i++)
                {
                    JSONObject son=root.getJSONObject(i);
                    TrangPhuc trangPhuc=new TrangPhuc();
                    trangPhuc.setId(son.getString("id"));
                    trangPhuc.setNum(son.getString("num"));
                    trangPhuc.setName(i+1+". "+son.getString("name"));
                    trangPhuc.setImage("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+ key1 +"_"+ son.getString("num") +".jpg");
                    dsTrangPhuc.add(trangPhuc);
                }
            }
            catch (Exception ex)
            {
                Log.e("LOI",ex.toString());
            }

            //trangPhuc.setImage("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+ id +"_"+num+".jpg");



        adapterTrangPhuc.notifyDataSetChanged();
    }
}
