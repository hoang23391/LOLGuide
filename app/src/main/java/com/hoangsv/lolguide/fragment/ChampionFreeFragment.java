package com.hoangsv.lolguide.fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.activity.ChampionDetailActivity;
import com.hoangsv.lolguide.adapter.ChampionFreeAdapter;
import com.hoangsv.lolguide.lt.Database;
import com.hoangsv.lolguide.lt.HttpHandler;
import com.hoangsv.lolguide.model.ChampionFree;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class ChampionFreeFragment extends Fragment {

    final String DATABASE_NAME="lol.sqlite";
    SQLiteDatabase database;
    private static String API_KEY = "RGAPI-2a7f3ae1-d188-4a69-bcc4-132f4de02f44";

    private static String url = "https://na.api.pvp.net/api/lol/na/v1.2/champion?freeToPlay=true&api_key="+API_KEY;
    private static String url2 = "https://lienminh.garena.vn/";
    RecyclerView rvChampionFree;
    ArrayList<ChampionFree> dsChampionFree;
    ChampionFreeAdapter adapterChampionFree;

    RecyclerView rvChampionFreeVN;
    ArrayList<ChampionFree> dsChampionFreeVN;
    ChampionFreeAdapter adapterChampionFreeVN;

    RecyclerView.LayoutManager layoutManager,layoutManager1;
    SharedPreferences sharedPreferences, sharedPreferencesVN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_champion_free, container, false);
        sharedPreferencesVN=getActivity().getSharedPreferences("champfreevn", Context.MODE_PRIVATE);
        sharedPreferences=getActivity().getSharedPreferences("config", Context.MODE_PRIVATE);
        // connect views.
        rvChampionFree= (RecyclerView) v.findViewById(R.id.rvChampionFree);
        // If the size of views will not change as the data changes.
        rvChampionFree.setHasFixedSize(true);
        // Setting the LayoutManager.
        //layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(getActivity(),5);//or Recycler.this,5
        //layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);;

        rvChampionFree.setLayoutManager(layoutManager);
        rvChampionFree.addItemDecoration(new DividerItemDecoration(getActivity(), GridLayoutManager.VERTICAL));
        dsChampionFree=new ArrayList<>();
        // Setting the adapter.
        adapterChampionFree = new ChampionFreeAdapter(getActivity(),dsChampionFree);
        rvChampionFree.setAdapter(adapterChampionFree);


        rvChampionFreeVN= (RecyclerView) v.findViewById(R.id.rvChampionFreeVN);
        // If the size of views will not change as the data changes.
        rvChampionFreeVN.setHasFixedSize(true);
        // Setting the LayoutManager.
        //layoutManager = new LinearLayoutManager(this);
        layoutManager1 = new GridLayoutManager(getActivity(),5);//or Recycler.this,5
        //layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);;

        rvChampionFreeVN.setLayoutManager(layoutManager1);
        rvChampionFreeVN.addItemDecoration(new DividerItemDecoration(getActivity(), GridLayoutManager.VERTICAL));
        dsChampionFreeVN=new ArrayList<>();
        // Setting the adapter.
        adapterChampionFreeVN = new ChampionFreeAdapter(getActivity(),dsChampionFreeVN);
        rvChampionFreeVN.setAdapter(adapterChampionFreeVN);

        database = Database.initDatabase(getActivity(),DATABASE_NAME);
        addEvents();
        return v;
    }

    private void addEvents() {
        String freeChampionSP=sharedPreferences.getString("FreeChampion","");
        if (freeChampionSP.trim().length()==0){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putString("FreeChampion","{\"champions\":[{\"id\":64,\"active\":true,\"botEnabled\":true,\"freeToPlay\":true,\"botMmEnabled\":true,\"rankedPlayEnabled\":true},{\"id\":40,\"active\":true,\"botEnabled\":false,\"freeToPlay\":true,\"botMmEnabled\":false,\"rankedPlayEnabled\":true},{\"id\":54,\"active\":true,\"botEnabled\":true,\"freeToPlay\":true,\"botMmEnabled\":true,\"rankedPlayEnabled\":true},{\"id\":21,\"active\":true,\"botEnabled\":true,\"freeToPlay\":true,\"botMmEnabled\":true,\"rankedPlayEnabled\":true},{\"id\":267,\"active\":true,\"botEnabled\":false,\"freeToPlay\":true,\"botMmEnabled\":true,\"rankedPlayEnabled\":true},{\"id\":421,\"active\":true,\"botEnabled\":false,\"freeToPlay\":true,\"botMmEnabled\":false,\"rankedPlayEnabled\":true},{\"id\":102,\"active\":true,\"botEnabled\":true,\"freeToPlay\":true,\"botMmEnabled\":true,\"rankedPlayEnabled\":true},{\"id\":134,\"active\":true,\"botEnabled\":false,\"freeToPlay\":true,\"botMmEnabled\":false,\"rankedPlayEnabled\":true},{\"id\":29,\"active\":true,\"botEnabled\":false,\"freeToPlay\":true,\"botMmEnabled\":false,\"rankedPlayEnabled\":true},{\"id\":112,\"active\":true,\"botEnabled\":false,\"freeToPlay\":true,\"botMmEnabled\":false,\"rankedPlayEnabled\":true}]}");
            editor.commit();
        }


        ConnectivityManager connectivityManager= (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager.getActiveNetworkInfo()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new DocVaLuuJSONVaoSP().execute(url);
                    new JSOUPHTML().execute(url2);
                }
            });
        }
        else {
            docJSONTuSP();
            docVN();
        }

        rvChampionFree.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                rvChampionFree, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                //Toast.makeText(getActivity(), "Single Click on position :"+position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),ChampionDetailActivity.class);
                intent.putExtra("id",dsChampionFree.get(position).getId());
                startActivity(intent);

                ImageView picture=(ImageView)view.findViewById(R.id.imgChampionFree);
                picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),ChampionDetailActivity.class);
                        intent.putExtra("id",dsChampionFree.get(position).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent intent=new Intent(getActivity(),ChampionDetailActivity.class);
                intent.putExtra("id",dsChampionFree.get(position).getId());
                startActivity(intent);
            }
        }));

        rvChampionFreeVN.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                rvChampionFreeVN, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                //Toast.makeText(getActivity(), "Single Click on position :"+position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),ChampionDetailActivity.class);
                intent.putExtra("id",dsChampionFreeVN.get(position).getId());
                startActivity(intent);

                ImageView picture=(ImageView)view.findViewById(R.id.imgChampionFree);
                picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),ChampionDetailActivity.class);
                        intent.putExtra("id",dsChampionFreeVN.get(position).getId());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent intent=new Intent(getActivity(),ChampionDetailActivity.class);
                intent.putExtra("id",dsChampionFreeVN.get(position).getId());
                startActivity(intent);
            }
        }));
    }

    private void docJSONTuSP() {
        try {
            JSONObject root=new JSONObject(sharedPreferences.getString("FreeChampion",""));
            JSONArray jsonArray=root.getJSONArray("champions");
            for (int i=0;i<jsonArray.length();i++){
                JSONObject son=jsonArray.getJSONObject(i);
                ChampionFree championFree=new ChampionFree();
                championFree.setId(son.getString("id"));
                dsChampionFree.add(championFree);
            }

            for (int i=0;i<dsChampionFree.size();i++) {
                Cursor cursor=database.rawQuery("SELECT * FROM ChampionHoangSV WHERE id=?",new String[]{dsChampionFree.get(i).getId()});
                while (cursor.moveToNext()){
                    String id = cursor.getString(0);
                    String name = cursor.getString(2);
                    String image = cursor.getString(4);
                    String skins = cursor.getString(5);
                    String lore = cursor.getString(6);
                    try {
                        JSONObject object=new JSONObject(image);
                        String image1=object.getString("full");
                        dsChampionFree.get(i).setImage(image1);
                        //Log.d("ha1",image);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //dsChampionFree.get(i).setId(key);
                    //dsChampionFree.get(i).setImage(image);
                }
                cursor.close();
            }
            adapterChampionFree.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void docVN() {
        for (int i=0;!sharedPreferencesVN.getString("FreeChampionVN"+i,"").equals("");i++){
            ChampionFree championFreeVN=new ChampionFree();
            championFreeVN.setId(sharedPreferencesVN.getString("FreeChampionVN"+i,""));
            dsChampionFreeVN.add(championFreeVN);
        }
        for (int i=0;i<dsChampionFreeVN.size();i++) {
            Cursor cursor = database.rawQuery("SELECT * FROM ChampionHoangSV WHERE id=?", new String[]{dsChampionFreeVN.get(i).getId()});
            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String name = cursor.getString(2);
                String image = cursor.getString(4);
                String skins = cursor.getString(5);
                String lore = cursor.getString(6);
                try {
                    JSONObject object = new JSONObject(image);
                    String image1 = object.getString("full");
                    dsChampionFreeVN.get(i).setImage(image1);
                    Log.d("ha2",i+"");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //dsChampionFree.get(i).setId(key);
                //dsChampionFree.get(i).setImage(image);
            }
            cursor.close();
        }
        adapterChampionFreeVN.notifyDataSetChanged();
    }

    class DocVaLuuJSONVaoSP extends AsyncTask<String,Integer,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            String sharedChampionFree=sharedPreferences.getString("FreeChampion","");
            if (s!=null && !s.equals(sharedChampionFree)){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("FreeChampion", s);
                editor.commit();
            }
            docJSONTuSP();
            //Toast.makeText(RecyclerActivity.this,sharedPreferences.getString("FreeChampion",""),Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {

            HttpHandler httpHandler=new HttpHandler();
            String url1=httpHandler.makeServiceCall(params[0]);
            return url1;
        }
    }

    /**
     * RecyclerView: Implementing single item click and long press (Part-II)
     *
     * - creating an Interface for single tap and long press
     * - Parameters are its respective view and its position
     * */

    public static interface ClickListener{
        public void onClick(View view,int position);
        public void onLongClick(View view,int position);
    }

    /**
     * RecyclerView: Implementing single item click and long press (Part-II)
     *
     * - creating an innerclass implementing RevyvlerView.OnItemTouchListener
     * - Pass clickListener interface as parameter
     * */

    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{

        private ClickListener clicklistener;
        private GestureDetector gestureDetector;

        public RecyclerTouchListener(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

            this.clicklistener=clicklistener;
            gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                    if(child!=null && clicklistener!=null){
                        clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            View child=rv.findChildViewUnder(e.getX(),e.getY());
            if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
                clicklistener.onClick(child,rv.getChildAdapterPosition(child));
            }

            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    class JSOUPHTML extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            docVN();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                Document document = Jsoup.connect(params[0]).get();
                //for (Element element:document.select("a[title=\"Detail\"]")){
                for (int i=0;i<document.select("a[title=\"Detail\"]").size();i++){
                    Element element = document.select("a[title=\"Detail\"]").get(i);
                    String a ="";
                    a= element.attr("href");
                    String b = a.replaceAll("\\D+","");

                    //Log.d("ha12",b);

                    SharedPreferences.Editor editor = sharedPreferencesVN.edit();
                    editor.putString("FreeChampionVN"+i, b);
                    editor.apply();

                }
                //Log.d("ha1",dsChampionFreeVN.size()+"");

            }
            catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}

