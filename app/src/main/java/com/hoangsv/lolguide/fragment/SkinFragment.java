package com.hoangsv.lolguide.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.activity.SkinInformationActivity;
import com.hoangsv.lolguide.adapter.SkinAdapter;
import com.hoangsv.lolguide.model.Skin;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SkinFragment extends Fragment {

    ArrayList<Skin> dsSkin;
    SkinAdapter adapterTrangPhuc;

    @BindView(R.id.lvTrangPhucF)        ListView lvTrangPhuc;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_trang_phuc, container, false);
        ButterKnife.bind(this,view);
        dsSkin =new ArrayList<>();
        adapterTrangPhuc=new SkinAdapter(getActivity(),R.layout.trang_phuc_recycler_item, dsSkin);
        lvTrangPhuc.setAdapter(adapterTrangPhuc);
        addEvents();
        return view;
    }

    private void addEvents() {
        xuLyLayThongTin();
        lvTrangPhuc.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getActivity(),SkinInformationActivity.class);
                intent.putExtra("ImgLink", dsSkin.get(position).getImage());
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
                for(int i=0;i<root.length();i++)
                {
                    JSONObject son=root.getJSONObject(i);
                    Skin skin =new Skin();
                    skin.setId(son.getString("id"));
                    skin.setNum(son.getString("num"));
                    skin.setName(i+1+". "+son.getString("name"));
                    skin.setImage("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+ key1 +"_"+ son.getString("num") +".jpg");
                    dsSkin.add(skin);
                }
            }
            catch (Exception ex)
            {
                Log.e("LOI_"+getContext().getClass().getSimpleName(),ex.toString());
            }
        adapterTrangPhuc.notifyDataSetChanged();
    }
}
