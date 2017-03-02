package com.hoangsv.lolguide;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import com.hoangsv.adapter.ChampionAdapter;
import com.hoangsv.model.Champion;

import java.util.ArrayList;

public class HienThiTuongActivity extends AppCompatActivity {
    final String DATABASE_NAME="data_vi.sqlite";
    SQLiteDatabase database;

    GridView gvTuong;
    ArrayList<Champion> dsChampion;
    ChampionAdapter adapterChampion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_tuong);
        addControls();
        addEvents();
    }

    private void addEvents() {
        xuLyDocToanBoCSDL();
    }


    private void xuLyDocToanBoCSDL() {
        database = Database.initDatabase(this,DATABASE_NAME);
        dsChampion.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM champion",null);
        while (cursor.moveToNext())
        {

            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String key = cursor.getString(2);
            String title = cursor.getString(3);
            String position=cursor.getString(4);
            String video = cursor.getString(5);
            String prp = cursor.getString(6);
            String pip = cursor.getString(7);
            String created = cursor.getString(8);
            String spells = cursor.getString(9);
            String ferocity = cursor.getString(10);
            String resolve = cursor.getString(11);
            String cunning = cursor.getString(12);
            String rune = cursor.getString(13);
            String skill = cursor.getString(14);
            String item = cursor.getString(15);
            String weak_against = cursor.getString(16);
            String strong_against = cursor.getString(17);
            String lore = cursor.getString(18);
            String tags = cursor.getString(19);
            String info = cursor.getString(20);
            String spells2 = cursor.getString(21);
            String blurb = cursor.getString(22);
            String stats = cursor.getString(23);
            String passive = cursor.getString(24);
            String ver = cursor.getString(25);
            String image = cursor.getString(26);
            String skins = cursor.getString(27);

            Champion champion=new Champion();
            champion.setId(id);
            champion.setName(name);
            champion.setKey(key);
            champion.setTitle(title);
            champion.setPosition(position);
            champion.setVideo(video);
            champion.setPrp(prp);
            champion.setPip(pip);
            champion.setCreated(created);
            champion.setSpells(spells);
            champion.setFerocity(ferocity);
            champion.setResolve(resolve);
            champion.setCunning(cunning);
            champion.setRune(rune);
            champion.setSkill(skill);
            champion.setItem(item);
            champion.setWeak_against(weak_against);
            champion.setStrong_against(strong_against);
            champion.setLore(lore);
            champion.setTags(tags);
            champion.setInfo(info);
            champion.setSpells2(spells2);
            champion.setBlurb(blurb);
            champion.setStats(stats);
            champion.setPassive(passive);
            champion.setVer(ver);
            champion.setImage(image);
            champion.setSkins(skins);
            dsChampion.add(champion);

        }
        cursor.close();

        adapterChampion.notifyDataSetChanged();
    }

    private void addControls() {
        gvTuong= (GridView) findViewById(R.id.gvTuong);
        dsChampion=new ArrayList<>();
        adapterChampion=new ChampionAdapter(HienThiTuongActivity.this,R.layout.champion_item,dsChampion);
        gvTuong.setAdapter(adapterChampion);

    }
}
