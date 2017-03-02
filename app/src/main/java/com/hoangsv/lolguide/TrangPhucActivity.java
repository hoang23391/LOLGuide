package com.hoangsv.lolguide;

import android.app.ProgressDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;

import com.hoangsv.adapter.TrangPhucAdapter;
import com.hoangsv.model.TrangPhuc;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class TrangPhucActivity extends AppCompatActivity {
    ProgressDialog dialog;
    ImageView imgTrangPhuc;
    ListView lvTrangPhuc;
    ArrayList<TrangPhuc> dsTrangPhuc;
    TrangPhucAdapter adapterTrangPhuc;

    final String DATABASE_NAME="vn_vn.sqlite";
    SQLiteDatabase database;

    final String employee = "{    \"contacts\": [        {                \"id\": \"c200\",                \"name\": \"Ravi Tamada\",                \"email\": \"ravi@gmail.com\",                \"address\": \"xx-xx-xxxx,x - street, x - country\",                \"gender\" : \"male\",                \"phone\": {                    \"mobile\": \"+91 0000000000\",                    \"home\": \"00 000000\",                    \"office\": \"00 000000\"                }        },        {                \"id\": \"c201\",                \"name\": \"Johnny Depp\",                \"email\": \"johnny_depp@gmail.com\",                \"address\": \"xx-xx-xxxx,x - street, x - country\",                \"gender\" : \"male\",                \"phone\": {                    \"mobile\": \"+91 0000000000\",                    \"home\": \"00 000000\",                    \"office\": \"00 000000\"                }        }            ]}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_phuc);
        addControls();
        addEvents();
    }

    private void addEvents() {
        xuLyLayThongTin();
    }

    private void xuLyLayThongTin() {
        database = Database.initDatabase(TrangPhucActivity.this,DATABASE_NAME);
        dsTrangPhuc.clear();
        Cursor cursor = database.rawQuery("SELECT * FROM champion WHERE id=?",new String[]{"Ahri"});
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String skins = cursor.getString(27);

            String skins1="{    \"trangphuc\": "+skins+"}";

            try{
                JSONObject root=new JSONObject(skins1);
                JSONArray jsonArray=root.getJSONArray("trangphuc");
                for(int i=0;i<jsonArray.length();i++)
                {
                    JSONObject son=jsonArray.getJSONObject(i);
                    TrangPhuc trangPhuc=new TrangPhuc();
                    trangPhuc.setId(son.getString("id"));
                    trangPhuc.setNum(son.getString("num"));
                    trangPhuc.setName(son.getString("name"));
                    trangPhuc.setImage("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+ id +"_"+ son.getString("num") +".jpg");
                    dsTrangPhuc.add(trangPhuc);
                }
            }
            catch (Exception ex)
            {
                Log.e("LOI",ex.toString());
            }

            //trangPhuc.setImage("http://ddragon.leagueoflegends.com/cdn/img/champion/splash/"+ id +"_"+num+".jpg");

        }
        cursor.close();

        adapterTrangPhuc.notifyDataSetChanged();
    }

    private void addControls() {
        lvTrangPhuc= (ListView) findViewById(R.id.lvTrangPhuc);
        dsTrangPhuc=new ArrayList<>();
        adapterTrangPhuc=new TrangPhucAdapter(TrangPhucActivity.this,R.layout.trang_phuc_recycler_item,dsTrangPhuc);
        lvTrangPhuc.setAdapter(adapterTrangPhuc);
        imgTrangPhuc= (ImageView) findViewById(R.id.imgTrangPhuc);
        dialog=new ProgressDialog(TrangPhucActivity.this);
        dialog.setTitle("Thông báo");
        dialog.setMessage("Đang tải về, vui lòng chờ...");
        dialog.setCanceledOnTouchOutside(false);
    }

}
