package com.hoangsv.lolguide.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.activity.WatchNewsContentActivity;
import com.hoangsv.lolguide.adapter.NewsAdapter;
import com.hoangsv.lolguide.model.News;
import com.hoangsv.lolguide.utility.Constant;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

public class NewsFragment extends Fragment {

    RecyclerView rvNews;
    ArrayList<News> dsNews;
    NewsAdapter adapterNews;
    RecyclerView.LayoutManager layoutManagerNews;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news, container, false);

        rvNews= (RecyclerView) v.findViewById(R.id.rvNews);
        // If the size of views will not change as the data changes.
        rvNews.setHasFixedSize(true);
        // Setting the LayoutManager.
        //layoutManager = new LinearLayoutManager(this);
        layoutManagerNews = new LinearLayoutManager(getActivity());//or Recycler.this,5
        //layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);;

        rvNews.setLayoutManager(layoutManagerNews);
        rvNews.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        dsNews=new ArrayList<>();
        // Setting the adapter.
        adapterNews = new NewsAdapter(getActivity(),dsNews);
        rvNews.setAdapter(adapterNews);

        // Inflate the layout for this fragment
        addEvents();
        return v;
    }

    private void addEvents() {


            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    new DocXML().execute(Constant.URL_NEWS_1);
                }
            });


        rvNews.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), rvNews, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent=new Intent(getActivity(),WatchNewsContentActivity.class);
                intent.putExtra("link",dsNews.get(position).getLink());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

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
            adapterNews.notifyDataSetChanged();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {

            try {
                Document document = Jsoup.connect(params[0]).get();

                for (int i=0;i<10;i++){
                    Element element= document.select("img[width=\"320\"][height=\"180\"").get((i+6));
                    String a="";
                    a= "https://lienminh.garena.vn"+element.attr("src");

                    News news=new News();
                    news.setAnhDaiDien(a);

                    Element element1= document.select("a[title=\"\"]").get(i);
                    String b, c ="";
                    b="https://lienminh.garena.vn"+element1.attr("href");
                    news.setLink(b);
                    Log.d("link",b);
                    String tieudemoi = document.select("a[href=\""+element1.attr("href")+"\"]").get(1).ownText();
                    news.setTieuDe(tieudemoi);

                    /*Document mdocument = Jsoup.connect(b).get();
                    String noidung="";
                    if (mdocument.select("div[class=\"content-editor\"]").size()>0){
                        Element element2=mdocument.select("div[class=\"content-editor\"]").get(0);
                        news.setNoiDung(element2.ownText());
                    }
                    */

                    dsNews.add(news);


                }

            }
            catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }


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
