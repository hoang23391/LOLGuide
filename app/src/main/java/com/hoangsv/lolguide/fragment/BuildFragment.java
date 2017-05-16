package com.hoangsv.lolguide.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.adapter.BuildAdapter;
import com.hoangsv.lolguide.lt.Database;
import com.hoangsv.lolguide.model.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuildFragment extends Fragment {
    final String DATABASE_NAME="lol.sqlite";
    SQLiteDatabase database;
    RecyclerView rvBuild;
    ArrayList<Build> dsBuild;
    BuildAdapter adapterBuild;
    RecyclerView.LayoutManager layoutManagerBuild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_build, container, false);

        // connect views.
        rvBuild= (RecyclerView) v.findViewById(R.id.rvBuild);
        // If the size of views will not change as the data changes.
        rvBuild.setHasFixedSize(true);
        // Setting the LayoutManager.
        //layoutManager = new LinearLayoutManager(this);
        layoutManagerBuild = new LinearLayoutManager(getActivity());//or Recycler.this,5
        //layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);;

        rvBuild.setLayoutManager(layoutManagerBuild);
        rvBuild.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        dsBuild=new ArrayList<>();
        // Setting the adapter.
        adapterBuild = new BuildAdapter(getActivity(),dsBuild);
        rvBuild.setAdapter(adapterBuild);
        database = Database.initDatabase(getActivity(),DATABASE_NAME);

        // Inflate the layout for this fragment
        addEvents();
        return v;
    }

    private void addEvents() {
        Bundle bundle=getArguments();
        String recommended = bundle.getString("recommended1");
        try {
            JSONArray jsonArray = new JSONArray(recommended);
            JSONObject jsonObject=jsonArray.getJSONObject(6);
            JSONArray jsonArraySon = jsonObject.getJSONArray("blocks");
            for (int i=0;i<jsonArraySon.length();i++){
                Build build = new Build();
                JSONObject jsonObjectSon = jsonArraySon.getJSONObject(i);
                String type = jsonObjectSon.getString("type");
                build.setType(type);

                JSONArray jsonArraySon1 = jsonObjectSon.getJSONArray("items");


                /*for (int j=0;i<jsonArraySon1.length();j++){
                    JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(j);
                    String idItem = jsonObjectSon1.getString("id");
                    String countItem = jsonObjectSon1.getString("count");
                    Log.d("hehe2",type+idItem+countItem);

                    Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
                    cursor.moveToFirst();
                    String imageDo = cursor.getString(8);
                    JSONObject jsonObjectNew = new JSONObject(imageDo);
                    String anhDo = jsonObjectNew.getString("full");
                    build.setId1(anhDo);
                    dsBuild.add(build);
                }*/


                if (0<jsonArraySon1.length()){
                    JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(0);
                    String idItem = jsonObjectSon1.getString("id");
                    String countItem = jsonObjectSon1.getString("count");
                    Log.d("hehe2",type+idItem+countItem);

                    Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
                    cursor.moveToFirst();
                    String imageDo = cursor.getString(8);
                    JSONObject jsonObjectNew = new JSONObject(imageDo);
                    String anhDo = jsonObjectNew.getString("full");
                    build.setImage1(anhDo);

                }
                if (1<jsonArraySon1.length()){
                    JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(1);
                    String idItem = jsonObjectSon1.getString("id");
                    String countItem = jsonObjectSon1.getString("count");
                    Log.d("hehe2",type+idItem+countItem);

                    Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
                    cursor.moveToFirst();
                    String imageDo = cursor.getString(8);
                    JSONObject jsonObjectNew = new JSONObject(imageDo);
                    String anhDo = jsonObjectNew.getString("full");
                    build.setImage2(anhDo);

                }
                if (2<jsonArraySon1.length()){
                    JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(2);
                    String idItem = jsonObjectSon1.getString("id");
                    String countItem = jsonObjectSon1.getString("count");
                    Log.d("hehe2",type+idItem+countItem);

                    Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
                    cursor.moveToFirst();
                    String imageDo = cursor.getString(8);
                    JSONObject jsonObjectNew = new JSONObject(imageDo);
                    String anhDo = jsonObjectNew.getString("full");
                    build.setImage3(anhDo);

                }
                if (3<jsonArraySon1.length()){
                    JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(3);
                    String idItem = jsonObjectSon1.getString("id");
                    String countItem = jsonObjectSon1.getString("count");
                    Log.d("hehe2",type+idItem+countItem);

                    Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
                    cursor.moveToFirst();
                    String imageDo = cursor.getString(8);
                    JSONObject jsonObjectNew = new JSONObject(imageDo);
                    String anhDo = jsonObjectNew.getString("full");
                    build.setImage4(anhDo);

                }
                if (4<jsonArraySon1.length()){
                    JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(4);
                    String idItem = jsonObjectSon1.getString("id");
                    String countItem = jsonObjectSon1.getString("count");
                    Log.d("hehe2",type+idItem+countItem);

                    Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
                    cursor.moveToFirst();
                    String imageDo = cursor.getString(8);
                    JSONObject jsonObjectNew = new JSONObject(imageDo);
                    String anhDo = jsonObjectNew.getString("full");
                    build.setImage5(anhDo);

                }
                if (5<jsonArraySon1.length()){
                    JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(5);
                    String idItem = jsonObjectSon1.getString("id");
                    String countItem = jsonObjectSon1.getString("count");
                    Log.d("hehe2",type+idItem+countItem);

                    Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
                    cursor.moveToFirst();
                    String imageDo = cursor.getString(8);
                    JSONObject jsonObjectNew = new JSONObject(imageDo);
                    String anhDo = jsonObjectNew.getString("full");
                    build.setImage6(anhDo);

                }
                dsBuild.add(build);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
