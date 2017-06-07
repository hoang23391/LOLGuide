package com.hoangsv.lolguide.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.adapter.ViewPagerAdapter;
import com.hoangsv.lolguide.fragment.BuildFragment;
import com.hoangsv.lolguide.fragment.CounterFragment;
import com.hoangsv.lolguide.fragment.OverviewFragment;
import com.hoangsv.lolguide.fragment.SkinFragment;
import com.hoangsv.lolguide.fragment.LoreFragment;
import com.hoangsv.lolguide.utility.Constant;
import com.hoangsv.lolguide.utility.Database;

import org.json.JSONException;
import org.json.JSONObject;

public class ChampionDetailActivity extends AppCompatActivity {

    SQLiteDatabase database;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    OverviewFragment overviewFragment;
    BuildFragment buildFragment;
    CounterFragment counterFragment;
    LoreFragment loreFragment;
    SkinFragment skinFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_champion_detail);
        addControls();
        addEvents();
    }

    private void addEvents() {


    }

    /*
    private void xuLyThemTabs() {
        TabLayout.Tab tabLayout1=tabLayout.newTab().setText("Tổng quan");
        TabLayout.Tab tabLayout2=tabLayout.newTab().setText("Lên đồ");
        TabLayout.Tab tabLayout3=tabLayout.newTab().setText("Khắc chế");
        TabLayout.Tab tabLayout4=tabLayout.newTab().setText("Trang phục");
        TabLayout.Tab tabLayout5=tabLayout.newTab().setText("Truyền thuyết");
        tabLayout.addTab(tabLayout1);
        tabLayout.addTab(tabLayout2);
        tabLayout.addTab(tabLayout3);
        tabLayout.addTab(tabLayout4);
        tabLayout.addTab(tabLayout5);
    }
    */


    private void addControls() {
        viewPager= (ViewPager) findViewById(R.id.viewpagerChampionDetail);
        tabLayout= (TabLayout) findViewById(R.id.tabsChampionDetail);
        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);

        Intent intent=getIntent();
        Bundle bundle = new Bundle();
        if (intent.getStringExtra("id")==null || intent.getStringExtra("lore")==null || intent.getStringExtra("name")==null || intent.getStringExtra("skins")==null){
            database = Database.initDatabase(this, Constant.DATABASE_NAME);
            Cursor cursor = database.rawQuery("SELECT * FROM ChampionHoangSV WHERE id=?",new String[]{intent.getStringExtra("id")});
            cursor.moveToFirst();

            String id = cursor.getString(0);
            String key = cursor.getString(1);
            String name = cursor.getString(2);
            String title = cursor.getString(3);
            String image=cursor.getString(4);
            try {
                JSONObject object=new JSONObject(image);
                String image1=object.getString("full");
                bundle.putString("image1", image1);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

            bundle.putString("id1", id);
            bundle.putString("key1", key);
            bundle.putString("name1", name);
            bundle.putString("title1", title);
            //bundle.putString("image1", image);
            bundle.putString("skins1", skins);
            bundle.putString("lore1", lore);
            bundle.putString("blurb1", blurb);
            bundle.putString("allytips1", allytips);
            bundle.putString("enemytips1", enemytips);
            bundle.putString("tags1", tags);
            bundle.putString("partype1", partype);
            bundle.putString("info1", info);
            bundle.putString("stats1", stats);
            bundle.putString("spells1", spells);
            bundle.putString("passive1", passive);
            bundle.putString("recommended1", recommended);
        } else {
            String id = intent.getStringExtra("id");
            String key = intent.getStringExtra("key");
            String name = intent.getStringExtra("name");
            String title = intent.getStringExtra("title");
            String image = intent.getStringExtra("image");
            String skins = intent.getStringExtra("skins");
            String lore = intent.getStringExtra("lore");
            String blurb = intent.getStringExtra("blurb");
            String allytips = intent.getStringExtra("allytips");
            String enemytips = intent.getStringExtra("enemytips");
            String tags = intent.getStringExtra("tags");
            String partype = intent.getStringExtra("partype");
            String info = intent.getStringExtra("info");
            String stats = intent.getStringExtra("stats");
            String spells = intent.getStringExtra("spells");
            String passive = intent.getStringExtra("passive");
            String recommended = intent.getStringExtra("recommended");

            bundle.putString("id1", id);
            bundle.putString("key1", key);
            bundle.putString("name1", name);
            bundle.putString("title1", title);
            bundle.putString("image1", image);
            bundle.putString("skins1", skins);
            bundle.putString("lore1", lore);
            bundle.putString("blurb1", blurb);
            bundle.putString("allytips1", allytips);
            bundle.putString("enemytips1", enemytips);
            bundle.putString("tags1", tags);
            bundle.putString("partype1", partype);
            bundle.putString("info1", info);
            bundle.putString("stats1", stats);
            bundle.putString("spells1", spells);
            bundle.putString("passive1", passive);
            bundle.putString("recommended1", recommended);
        }

        loreFragment.setArguments(bundle);
        skinFragment.setArguments(bundle);
        overviewFragment.setArguments(bundle);
        counterFragment.setArguments(bundle);
        buildFragment.setArguments(bundle);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        overviewFragment = new OverviewFragment();
        buildFragment = new BuildFragment();
        counterFragment = new CounterFragment();
        skinFragment = new SkinFragment();
        loreFragment = new LoreFragment();
        adapter.addFragment(overviewFragment, getString(R.string.titleOverviewFragment));
        adapter.addFragment(buildFragment, getString(R.string.titleBuild));
        adapter.addFragment(counterFragment, getString(R.string.titleCounterFragment));
        adapter.addFragment(skinFragment, getString(R.string.titleSkinFragment));
        adapter.addFragment(loreFragment, getString(R.string.titleLoreFragment));
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0, 0);
    }
}
