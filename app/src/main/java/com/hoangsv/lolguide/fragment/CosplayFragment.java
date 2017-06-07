package com.hoangsv.lolguide.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.activity.WatchCosplayContentActivity;
import com.hoangsv.lolguide.adapter.CosplayAdapter;
import com.hoangsv.lolguide.model.Cosplay;
import com.hoangsv.lolguide.utility.Constant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class CosplayFragment extends Fragment {
    RecyclerView rvCosplay;
    ArrayList<Cosplay> dsCosplay;
    CosplayAdapter adapterCosplay;

    RecyclerView.LayoutManager layoutManager;
    SharedPreferences sharedPreferencesCP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cosplay, container, false);

        // connect views.
        rvCosplay= (RecyclerView) v.findViewById(R.id.rvCosplay);
        // If the size of views will not change as the data changes.
        rvCosplay.setHasFixedSize(true);
        // Setting the LayoutManager.
        //layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(getActivity(),2);//or Recycler.this,5
        //layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);;

        rvCosplay.setLayoutManager(layoutManager);
        rvCosplay.addItemDecoration(new DividerItemDecoration(getActivity(), GridLayoutManager.VERTICAL));
        dsCosplay=new ArrayList<>();
        // Setting the adapter.
        adapterCosplay = new CosplayAdapter(getActivity(),dsCosplay);
        rvCosplay.setAdapter(adapterCosplay);
        sharedPreferencesCP=getActivity().getSharedPreferences("cosplay", Context.MODE_PRIVATE);
        addEvents();
        return v;
    }

    private void addEvents() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new DocXML().execute(Constant.URL_COSPLAY_2);
            }
        });

        rvCosplay.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                rvCosplay, new ClickListener() {
            @Override
            public void onClick(View view, final int position) {
                //Values are passing to activity & to fragment as well
                //Toast.makeText(getActivity(), "Single Click on position :"+position, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getActivity(),WatchCosplayContentActivity.class);
                intent.putExtra("noidung",dsCosplay.get(position).getNoiDung());

                SharedPreferences.Editor editor=sharedPreferencesCP.edit();
                editor.putString("ImageCPND",dsCosplay.get(position).getNoiDung());
                editor.commit();

                startActivity(intent);

                ImageView picture=(ImageView)view.findViewById(R.id.imgCosplay);
                picture.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),WatchCosplayContentActivity.class);
                        intent.putExtra("noidung",dsCosplay.get(position).getNoiDung());
                        startActivity(intent);
                    }
                });
            }

            @Override
            public void onLongClick(View view, int position) {
                Intent intent=new Intent(getActivity(),WatchCosplayContentActivity.class);
                intent.putExtra("noidung",dsCosplay.get(position).getNoiDung());
                startActivity(intent);
            }
        }));
    }

    class DocXML extends AsyncTask<String,Integer,String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            adapterCosplay.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Document document = Jsoup.connect(params[0]).get();

                for (int i=0;i<9;i++){
                    Element element= document.select("img[width=\"300\"]").get(i);
                    String a="";
                    a= element.attr("src");

                    Cosplay cosplay=new Cosplay();
                    cosplay.setAnhDaiDien(a);

                    Element element1= document.select("a[class=\"act\"]").get(i);
                    String b, c ="";
                    b=element1.attr("href");
                    cosplay.setLink(b);

                    Document mdocument = Jsoup.connect(b).get();
                    String noidung="";
                    //<img class=
                    for (int j=0;j<mdocument.select("p").size();j++) {
                        Element melement = mdocument.select("p").get(j);
                        noidung = noidung + melement.select("img[class]").attr("src");
                    }
                    cosplay.setNoiDung(mdocument.toString());
                    c=element1.ownText();
                    cosplay.setTieuDe(c);
                    dsCosplay.add(cosplay);

                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }

            //Doc RSS
            /*try {
                Document document = Jsoup.connect(params[0]).get();
                for (Element element : document.select("item")){

                    String a = element.select("title").first().text();
                    String b = element.select("link").first().text();
                    String c = element.select("pubDate").first().text();
                    String d = element.select("content|encoded").first().text();

                    Cosplay cosplay=new Cosplay();
                    cosplay.setTieuDe(a);
                    String noidung = "";
                    for (int i=0;i<Jsoup.parse(d).select("img").size();i++){
                        noidung = noidung + Jsoup.parse(d).select("img").get(i).attr("src");
                    }
                    cosplay.setNoiDung(noidung);
                    String src = Jsoup.parse(d).select("img").first().attr("src");
                    cosplay.setAnhDaiDien(src);


                    dsCosplay.add(cosplay);


                    //Log.d("haha",d);
                    Log.d("ha1",noidung);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }*/
            //Ket thuc Doc RSS
            return null;
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
}

