package com.hoangsv.lolguide;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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

import com.hoangsv.adapter.BuildAdapter;
import com.hoangsv.model.Build;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BuildFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BuildFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BuildFragment extends Fragment {
    final String DATABASE_NAME="lol.sqlite";
    SQLiteDatabase database;
    RecyclerView rvBuild;
    ArrayList<Build> dsBuild;
    BuildAdapter adapterBuild;
    RecyclerView.LayoutManager layoutManagerBuild;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public BuildFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BuildFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BuildFragment newInstance(String param1, String param2) {
        BuildFragment fragment = new BuildFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

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
                    build.setId1(anhDo);

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
                    build.setId2(anhDo);

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
                    build.setId3(anhDo);

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
                    build.setId4(anhDo);

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
                    build.setId5(anhDo);

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
                    build.setId6(anhDo);

                }
                dsBuild.add(build);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
