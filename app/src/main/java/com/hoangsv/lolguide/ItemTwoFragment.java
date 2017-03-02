package com.hoangsv.lolguide;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hoangsv.adapter.ChampionAdapter;
import com.hoangsv.model.Champion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemTwoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemTwoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemTwoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    final String DATABASE_NAME="lol.sqlite";
    SQLiteDatabase database;

    GridView gvTuong1;
    ArrayList<Champion> dsChampion;
    ChampionAdapter adapterChampion;

    public ItemTwoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ItemTwoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ItemTwoFragment newInstance(String param1, String param2) {
        ItemTwoFragment fragment = new ItemTwoFragment();
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
        View v = inflater.inflate(R.layout.fragment_item_two, container, false);
        gvTuong1= (GridView) v.findViewById(R.id.gvTuong1);
        dsChampion=new ArrayList<>();
        adapterChampion=new ChampionAdapter(getActivity(),R.layout.champion_item,dsChampion);
        gvTuong1.setAdapter(adapterChampion);

        addEvents();
        return v;
    }

    private void addEvents() {
        xuLyDocToanBoCSDL();

        gvTuong1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Fragment fragment = new Fragment();
                Bundle bundle = new Bundle();
                bundle.putInt("PUTYOURHANDUP", value);
                fragment.setArguments(bundle);*/
                Intent intent=new Intent(getActivity(),ChampionDetailActivity.class);
                intent.putExtra("id",dsChampion.get(position).getId());
                intent.putExtra("key",dsChampion.get(position).getKey());
                intent.putExtra("name",dsChampion.get(position).getName());
                intent.putExtra("title",dsChampion.get(position).getTitle());
                intent.putExtra("image",dsChampion.get(position).getImage());
                intent.putExtra("skins",dsChampion.get(position).getSkins());
                intent.putExtra("lore",dsChampion.get(position).getLore());
                intent.putExtra("blurb",dsChampion.get(position).getBlurb());
                intent.putExtra("allytips",dsChampion.get(position).getAllytips());
                intent.putExtra("enemytips",dsChampion.get(position).getEnemytips());
                intent.putExtra("tags",dsChampion.get(position).getTags());
                intent.putExtra("partype",dsChampion.get(position).getPartype());
                intent.putExtra("info",dsChampion.get(position).getInfo());
                intent.putExtra("stats",dsChampion.get(position).getStats());
                intent.putExtra("spells",dsChampion.get(position).getSpells());
                intent.putExtra("passive",dsChampion.get(position).getPassive());
                intent.putExtra("recommended",dsChampion.get(position).getRecommended());

                startActivity(intent);
            }
        });
    }

    private void xuLyDocToanBoCSDL() {
        database = Database.initDatabase(getActivity(),DATABASE_NAME);
        dsChampion.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM ChampionHoangSV",null);
        while (cursor.moveToNext())
        {
            String id = cursor.getString(0);
            String key = cursor.getString(1);
            String name = cursor.getString(2);
            String title = cursor.getString(3);
            String image=cursor.getString(4);
            String skins = cursor.getString(5);
            String lore = cursor.getString(6);
            String blurb = cursor.getString(7);
            String allytips = cursor.getString(8);
            String enemytips = cursor.getString(9);
            String tags = cursor.getString(10);
            String partype = cursor.getString(11);
            String info = cursor.getString(12);
            String stats = cursor.getString(13);
            String spells = cursor.getString(14);
            String passive = cursor.getString(15);
            String recommended = cursor.getString(16);

            Champion champion=new Champion();
            champion.setId(id);
            champion.setKey(key);
            champion.setName(name);
            champion.setTitle(title);
            try {
                JSONObject object=new JSONObject(image);
                String image1=object.getString("full");
                champion.setImage(image1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            champion.setSkins(skins);
            champion.setLore(lore);
            champion.setBlurb(blurb);
            champion.setAllytips(allytips);
            champion.setEnemytips(enemytips);
            champion.setTags(tags);
            champion.setPartype(partype);
            champion.setInfo(info);
            champion.setStats(stats);
            champion.setSpells(spells);
            champion.setPassive(passive);
            champion.setRecommended(recommended);

            dsChampion.add(champion);
            Collections.sort(dsChampion, new Comparator<Champion>(){
                public int compare(Champion obj1, Champion obj2)
                {
                    // TODO Auto-generated method stub
                    return obj1.getName().compareToIgnoreCase(obj2.getName());
                }
            });
        }
        cursor.close();
        adapterChampion.notifyDataSetChanged();
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
