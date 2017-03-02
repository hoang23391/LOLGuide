package com.hoangsv.lolguide;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.hoangsv.adapter.TrangPhucAdapter;
import com.hoangsv.model.TrangPhuc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TrangPhucFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TrangPhucFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrangPhucFragment extends Fragment {

    ImageView imgTrangPhuc;
    ListView lvTrangPhuc;
    ArrayList<TrangPhuc> dsTrangPhuc;
    TrangPhucAdapter adapterTrangPhuc;

    final String DATABASE_NAME="lol.sqlite";
    SQLiteDatabase database;

    final String employee = "{    \"contacts\": [        {                \"id\": \"c200\",                \"name\": \"Ravi Tamada\",                \"email\": \"ravi@gmail.com\",                \"address\": \"xx-xx-xxxx,x - street, x - country\",                \"gender\" : \"male\",                \"phone\": {                    \"mobile\": \"+91 0000000000\",                    \"home\": \"00 000000\",                    \"office\": \"00 000000\"                }        },        {                \"id\": \"c201\",                \"name\": \"Johnny Depp\",                \"email\": \"johnny_depp@gmail.com\",                \"address\": \"xx-xx-xxxx,x - street, x - country\",                \"gender\" : \"male\",                \"phone\": {                    \"mobile\": \"+91 0000000000\",                    \"home\": \"00 000000\",                    \"office\": \"00 000000\"                }        }            ]}";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public TrangPhucFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TrangPhucFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TrangPhucFragment newInstance(String param1, String param2) {
        TrangPhucFragment fragment = new TrangPhucFragment();
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
