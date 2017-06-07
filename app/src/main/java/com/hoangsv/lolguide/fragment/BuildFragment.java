package com.hoangsv.lolguide.fragment;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.adapter.BuildAdapter;
import com.hoangsv.lolguide.model.Build;
import com.hoangsv.lolguide.utility.Constant;
import com.hoangsv.lolguide.utility.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class BuildFragment extends Fragment {

    SQLiteDatabase database;
    ArrayList<Build> dsBuild;
    BuildAdapter adapterBuild;
    RecyclerView.LayoutManager layoutManagerBuild;

    @BindView(R.id.rvBuild)
    RecyclerView rvBuild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_build, container, false);
        ButterKnife.bind(this, view);
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
        database = Database.initDatabase(getActivity(), Constant.DATABASE_NAME);

        // Inflate the layout for this fragment
        addEvents();
        return view;
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
                getSixImageName(build, jsonArraySon1);
                dsBuild.add(build);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        rvBuild.addOnItemTouchListener(new RecyclerTouchListener(getActivity(),
                rvBuild, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void getSixImageName(Build build, JSONArray jsonArraySon1) throws JSONException {
        if (0<jsonArraySon1.length()){
            JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(0);
            String idItem = jsonObjectSon1.getString("id");
            String countItem = jsonObjectSon1.getString("count");

            Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
            cursor.moveToFirst();
            String imageDo = cursor.getString(8);
            JSONObject jsonObjectNew = new JSONObject(imageDo);
            String anhDo = jsonObjectNew.getString("full");
            build.setImage1(anhDo);
            cursor.close();
        }
        if (1<jsonArraySon1.length()){
            JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(1);
            String idItem = jsonObjectSon1.getString("id");
            String countItem = jsonObjectSon1.getString("count");

            Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
            cursor.moveToFirst();
            String imageDo = cursor.getString(8);
            JSONObject jsonObjectNew = new JSONObject(imageDo);
            String anhDo = jsonObjectNew.getString("full");
            build.setImage2(anhDo);
            cursor.close();
        }
        if (2<jsonArraySon1.length()){
            JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(2);
            String idItem = jsonObjectSon1.getString("id");
            String countItem = jsonObjectSon1.getString("count");

            Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
            cursor.moveToFirst();
            String imageDo = cursor.getString(8);
            JSONObject jsonObjectNew = new JSONObject(imageDo);
            String anhDo = jsonObjectNew.getString("full");
            build.setImage3(anhDo);
            cursor.close();
        }
        if (3<jsonArraySon1.length()){
            JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(3);
            String idItem = jsonObjectSon1.getString("id");
            String countItem = jsonObjectSon1.getString("count");

            Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
            cursor.moveToFirst();
            String imageDo = cursor.getString(8);
            JSONObject jsonObjectNew = new JSONObject(imageDo);
            String anhDo = jsonObjectNew.getString("full");
            build.setImage4(anhDo);
            cursor.close();
        }
        if (4<jsonArraySon1.length()){
            JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(4);
            String idItem = jsonObjectSon1.getString("id");
            String countItem = jsonObjectSon1.getString("count");

            Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
            cursor.moveToFirst();
            String imageDo = cursor.getString(8);
            JSONObject jsonObjectNew = new JSONObject(imageDo);
            String anhDo = jsonObjectNew.getString("full");
            build.setImage5(anhDo);
            cursor.close();
        }
        if (5<jsonArraySon1.length()){
            JSONObject jsonObjectSon1 = jsonArraySon1.getJSONObject(5);
            String idItem = jsonObjectSon1.getString("id");
            String countItem = jsonObjectSon1.getString("count");

            Cursor cursor = database.rawQuery("SELECT * FROM ItemHoangSV WHERE id=?",new String[]{idItem});
            cursor.moveToFirst();
            String imageDo = cursor.getString(8);
            JSONObject jsonObjectNew = new JSONObject(imageDo);
            String anhDo = jsonObjectNew.getString("full");
            build.setImage6(anhDo);
            cursor.close();
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
