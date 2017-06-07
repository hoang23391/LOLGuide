package com.hoangsv.lolguide.fragment;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.activity.ChampionDetailActivity;
import com.hoangsv.lolguide.adapter.ChampionAdapter;
import com.hoangsv.lolguide.utility.Constant;
import com.hoangsv.lolguide.utility.Database;
import com.hoangsv.lolguide.model.Champion;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ChampionListFragment extends Fragment {
    SQLiteDatabase database;

    GridView gvTuong1;
    ArrayList<Champion> dsChampion;
    ChampionAdapter adapterChampion;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_champion, menu);
        MenuItem searchItem = menu.findItem(R.id.menu_item_search);

        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //updateItems();
//                QueryPerferences.setQuery(getActivity(), query);
                xuLyDocToanBoCSDL(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                xuLyDocToanBoCSDL(newText);
                return false;
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_champion, container, false);
        gvTuong1= (GridView) v.findViewById(R.id.gvTuong1);
        dsChampion=new ArrayList<>();
        adapterChampion=new ChampionAdapter(getActivity(),R.layout.champion_item,dsChampion);
        gvTuong1.setAdapter(adapterChampion);

        addEvents();
        return v;
    }

    private void addEvents() {
        xuLyDocToanBoCSDL(null);

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
                getActivity().overridePendingTransition(0, 0);
            }
        });
    }

    private void xuLyDocToanBoCSDL(String chuoiKiTu) {
        database = Database.initDatabase(getActivity(), Constant.DATABASE_NAME);
        dsChampion.clear();
        Cursor cursor;
        if (chuoiKiTu==null) {
            cursor = database.rawQuery("SELECT * FROM ChampionHoangSV", null);
        }
        else {
            cursor = database.rawQuery("SELECT * FROM ChampionHoangSV WHERE key LIKE?", new String[]{"%"+chuoiKiTu+"%"});
            //LIKE, =
            //%
        }

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
}
