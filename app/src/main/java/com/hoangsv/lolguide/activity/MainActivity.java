package com.hoangsv.lolguide.activity;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.fragment.ChampionFragment;
import com.hoangsv.lolguide.fragment.ChampionFreeFragment;
import com.hoangsv.lolguide.fragment.LienHeFragment;
import com.hoangsv.lolguide.fragment.NewsMainFragment;
import com.hoangsv.lolguide.fragment.SamSoiOthersFragment;
import com.hoangsv.lolguide.fragment.SamSoiVNFragment;
import com.hoangsv.lolguide.lt.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedPreferencesVersion;
    final String DATABASE_NAME = "lol.sqlite";
    SQLiteDatabase database;

    ProgressDialog mProgressDialog;

    private static String API_KEY = "RGAPI-2a7f3ae1-d188-4a69-bcc4-132f4de02f44";

    private static String urlversion = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/versions?api_key=" + API_KEY;
    private static String url = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/champion?champData=all&dataById=true&locale=vn_VN&api_key=" + API_KEY;
    private static String urlitem = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/item?locale=vn_VN&itemListData=all&api_key=" + API_KEY;
    private static String urlmastery = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/mastery?masteryListData=all&locale=vn_VN&api_key=" + API_KEY;
    private static String urlsummonerspell = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/summoner-spell?locale=vn_VN&api_key=" + API_KEY;
    private static String urlrune = "https://global.api.riotgames.com/api/lol/static-data/NA/v1.2/rune?locale=vn_VN&api_key=" + API_KEY;


    private Toolbar mToolbar;
    private DrawerLayout mDrawerLayout;
    NavigationView mNavigationView;
    FrameLayout mContentFrame;

    private static final String PREFERENCES_FILE = "mymaterialapp_settings";
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private int mCurrentSelectedPosition;
    TextView txtHienThiVersion;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getActiveNetworkInfo() != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new DocVersion().execute(urlversion);
                    }
                });
            } else {

            }
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(broadcastReceiver, filter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (broadcastReceiver != null)
            unregisterReceiver(broadcastReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setUpToolbar();

        mDrawerLayout = (DrawerLayout) findViewById(R.id.nav_drawer);

        mUserLearnedDrawer = Boolean.valueOf(readSharedSetting(this, PREF_USER_LEARNED_DRAWER, "false"));

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        setUpNavDrawer();
        startFragment(new NewsMainFragment());

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        View header = mNavigationView.getHeaderView(0);
        txtHienThiVersion = (TextView) header.findViewById(R.id.txtHienThiVersion);
        mContentFrame = (FrameLayout) findViewById(R.id.nav_contentframe);

        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.navigation_item_1:
                        //Snackbar.make(mContentFrame, "News", Snackbar.LENGTH_SHORT).show();
                        mCurrentSelectedPosition = 0;
                        startFragment(new NewsMainFragment());
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.navigation_item_2:
                        //Intent intent = new Intent(MainActivity.this,TrangPhucActivity.class);
                        //startActivity(intent);

                        //Snackbar.make(mContentFrame, "Champion", Snackbar.LENGTH_SHORT).show();
                        mCurrentSelectedPosition = 2;
                        startFragment(new ChampionFragment());
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.navigation_item_3:
                        mCurrentSelectedPosition = 3;
                        startFragment(new SamSoiVNFragment());
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.navigation_item_4:
                        mCurrentSelectedPosition = 4;
                        //mCurrentSelectedPosition = menuItem.getItemId();
                        startFragment(new SamSoiOthersFragment());
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.navigation_item_5:
                        mCurrentSelectedPosition = 1;
                        startFragment(new ChampionFreeFragment());
                        mDrawerLayout.closeDrawers();
                        return true;
                    case R.id.tttacgia:
                        mCurrentSelectedPosition = 5;
                        startFragment(new LienHeFragment());
                        mDrawerLayout.closeDrawers();
                        return true;

                    default:
                        return true;
                }
            }
        });

        addControls();
        addEvents();
    }

    private void addEvents() {
        String versionSP = sharedPreferencesVersion.getString("version", "");
        if (versionSP.trim().length() == 0) {
            SharedPreferences.Editor editor = sharedPreferencesVersion.edit();
            editor.putString("version", "7.4.3");
            editor.commit();
        }
        txtHienThiVersion.setText(versionSP);


    }

    private void addControls() {

        sharedPreferencesVersion = getSharedPreferences("version", Context.MODE_PRIVATE);
        database = Database.initDatabase(MainActivity.this, DATABASE_NAME);

        // instantiate it within the onCreate method
        mProgressDialog = new ProgressDialog(MainActivity.this);
        mProgressDialog.setTitle("Updating data");
        mProgressDialog.setMessage("You can hide this window.");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mProgressDialog.setCancelable(true);
        mProgressDialog.setIndeterminate(true);
//        mProgressDialog.setProgress(0);
//        mProgressDialog.setMax(100);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION, 0);
        Menu menu = mNavigationView.getMenu();
        menu.getItem(mCurrentSelectedPosition).setChecked(true);
    }


    private void setUpToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }

    private void setUpNavDrawer() {
        if (mToolbar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mToolbar.setNavigationIcon(R.drawable.ic_drawer);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mDrawerLayout.openDrawer(GravityCompat.START);
                }
            });
        }

        if (!mUserLearnedDrawer) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            mUserLearnedDrawer = true;
            saveSharedSetting(this, PREF_USER_LEARNED_DRAWER, "true");
        }

    }

    public static void saveSharedSetting(Context ctx, String settingName, String settingValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(settingName, settingValue);
        editor.apply();
    }

    public static String readSharedSetting(Context ctx, String settingName, String defaultValue) {
        SharedPreferences sharedPref = ctx.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
        return sharedPref.getString(settingName, defaultValue);
    }

    public void startFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.nav_contentframe, fragment, fragmentTag);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            //ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    class DocVersion extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONArray root = new JSONArray(s);
                final String versionFromURL = root.getString(0);
                String versionSP = sharedPreferencesVersion.getString("version", "");

                SharedPreferences.Editor editor = sharedPreferencesVersion.edit();
                editor.putString("versionFromURL", versionFromURL);
                editor.apply();

                //kiem tra version, neu khac thi cap nhat
                if (!versionSP.equals(versionFromURL)) {
                    mProgressDialog.show();
                    //textView.setText(sharedPreferencesVersion.getString("version","")+"\n"+versionFromURL);

                    // có thể sử dụng cách gắn cờ để chạy 1-2-3-4-5 hoặc lệnh này chèn vào onPoseExecute của lệnh khác
                    new DocJSONChampion().execute(url);
//                    new DocJSONItem().execute(urlitem);
//                    new DocJSONMastery().execute(urlmastery);
//                    new DocJSONSummonerSpell().execute(urlsummonerspell);
//                    new DocJSONRune().execute(urlrune);


//                    editor.putString("version", versionFromURL);
//                    editor.apply();
//                    txtHienThiVersion.setText(sharedPreferencesVersion.getString("version", ""));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
//            Su dung HttpUrlConnect
//            HttpHandler httpHandler=new HttpHandler();
//            String url1=httpHandler.makeServiceCall(params[0]);
//            return url1;

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder reqBuilder = new Request.Builder();
                reqBuilder.url(params[0]);
                Request request = reqBuilder.build();
                Response response = okHttpClient.newCall(request).execute();
                String kqTraVe = response.body().string();
                return kqTraVe;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class DocJSONChampion extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject root = new JSONObject(s);

                String type, format, version, data, keys;
                type = root.getString("type");
                format = root.getString("format");
                version = root.getString("version");
                data = root.getString("data");
                keys = root.getString("keys");

                ContentValues contentValues = new ContentValues();
                contentValues.put("type", type);
                contentValues.put("format", format);
                contentValues.put("version", version);
                contentValues.put("data", "");
                contentValues.put("keys", keys);

                int count = database.update("DownloadChampionHoangSV", contentValues, "type=?", new String[]{type});
                if (count <= 0) {
                    database.insert("DownloadChampionHoangSV", null, contentValues);
                } else {

                    database.update("DownloadChampionHoangSV", contentValues, "type=?", new String[]{type});
                }


                JSONObject son1 = root.getJSONObject("data");

                for (int i = 0; i < son1.names().length(); i++) {
                    JSONObject champ = son1.getJSONObject(son1.names().get(i) + "");
                    ContentValues values = new ContentValues();
                    xuLyDocVaGanCSDLChoTungTuong(champ, values);
                }
                /*Cursor cursor2 = database.rawQuery("SELECT * FROM ChampionHoangSV WHERE id=?",new String[]{79 + ""});
                cursor2.moveToFirst();
                son1.names().length();
                textView.setText(cursor2.getString(16)+"");
                cursor2.close();*/

                xuLyDocLinkVaTenAnhTuCSDLRoiTaiVe1();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            new DocJSONItem().execute(urlitem);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
//            Su dung HttpUrlConnect
//            HttpHandler httpHandler=new HttpHandler();
//            String url1=httpHandler.makeServiceCall(params[0]);
//            return url1;

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder reqBuilder = new Request.Builder();
                reqBuilder.url(params[0]);
                Request request = reqBuilder.build();
                Response response = okHttpClient.newCall(request).execute();
                String kqTraVe = response.body().string();
                return kqTraVe;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    class DocJSONItem extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();

            //Toast.makeText(RecyclerActivity.this,sharedPreferences.getString("JSONCHAMPION",""),Toast.LENGTH_LONG).show();

            try {
                JSONObject root = new JSONObject(s);
                String type, version, basic, data, groups, tree;
                type = root.getString("type");
                version = root.getString("version");
                basic = root.getString("basic");
                data = root.getString("data");
                groups = root.getString("groups");
                tree = root.getString("tree");

                ContentValues contentValues = new ContentValues();
                contentValues.put("type", type);
                contentValues.put("version", version);
                contentValues.put("basic", basic);
                contentValues.put("data", "");
                contentValues.put("groups", groups);
                contentValues.put("tree", tree);

                int count = database.update("DownloadItemHoangSV", contentValues, "type=?", new String[]{type});
                if (count <= 0) {
                    database.insert("DownloadItemHoangSV", null, contentValues);
                } else {

                    database.update("DownloadItemHoangSV", contentValues, "type=?", new String[]{type});
                }


                JSONObject son1 = root.getJSONObject("data");

                for (int i = 0; i < son1.names().length(); i++) {
                    JSONObject item = son1.getJSONObject(son1.names().get(i) + "");
                    ContentValues values = new ContentValues();

                    xuLyDocVaGanCSDLChoTungItem(item, values);
                }
                /*Cursor cursor2 = database.rawQuery("SELECT * FROM ChampionHoangSV WHERE id=?",new String[]{79 + ""});
                cursor2.moveToFirst();
                son1.names().length();
                textView.setText(cursor2.getString(16)+"");
                cursor2.close();*/


                xuLyDocLinkVaTenAnhTuCSDLRoiTaiVe2();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            new DocJSONMastery().execute(urlmastery);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
//            Su dung HttpUrlConnect
//            HttpHandler httpHandler=new HttpHandler();
//            String url1=httpHandler.makeServiceCall(params[0]);
//            return url1;

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder reqBuilder = new Request.Builder();
                reqBuilder.url(params[0]);
                Request request = reqBuilder.build();
                Response response = okHttpClient.newCall(request).execute();
                String kqTraVe = response.body().string();
                return kqTraVe;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class DocJSONMastery extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();

            //Toast.makeText(RecyclerActivity.this,sharedPreferences.getString("JSONCHAMPION",""),Toast.LENGTH_LONG).show();

            try {
                JSONObject root = new JSONObject(s);
                String type, version, data;
                type = root.getString("type");
                version = root.getString("version");
                data = root.getString("data");

                ContentValues contentValues = new ContentValues();
                contentValues.put("type", type);
                contentValues.put("version", version);
                contentValues.put("data", data);

                int count = database.update("DownloadMasteryHoangSV", contentValues, "type=?", new String[]{type});
                if (count <= 0) {
                    database.insert("DownloadMasteryHoangSV", null, contentValues);
                } else {

                    database.update("DownloadMasteryHoangSV", contentValues, "type=?", new String[]{type});
                }

                JSONObject son1 = root.getJSONObject("data");

                for (int i = 0; i < son1.names().length(); i++) {
                    JSONObject mastery = son1.getJSONObject(son1.names().get(i) + "");
                    ContentValues values = new ContentValues();
                    xuLyDocVaGanCSDLChoTungMastery(mastery, values);
                }
                /*Cursor cursor2 = database.rawQuery("SELECT * FROM ChampionHoangSV WHERE id=?",new String[]{79 + ""});
                cursor2.moveToFirst();
                son1.names().length();
                textView.setText(cursor2.getString(16)+"");
                cursor2.close();*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new DocJSONSummonerSpell().execute(urlsummonerspell);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
//            Su dung HttpUrlConnect
//            HttpHandler httpHandler=new HttpHandler();
//            String url1=httpHandler.makeServiceCall(params[0]);
//            return url1;

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder reqBuilder = new Request.Builder();
                reqBuilder.url(params[0]);
                Request request = reqBuilder.build();
                Response response = okHttpClient.newCall(request).execute();
                String kqTraVe = response.body().string();
                return kqTraVe;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class DocJSONSummonerSpell extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();

            //Toast.makeText(RecyclerActivity.this,sharedPreferences.getString("JSONCHAMPION",""),Toast.LENGTH_LONG).show();

            try {
                JSONObject root = new JSONObject(s);
                String type, version, data;
                type = root.getString("type");
                version = root.getString("version");
                data = root.getString("data");

                ContentValues contentValues = new ContentValues();
                contentValues.put("type", type);
                contentValues.put("version", version);
                contentValues.put("data", data);

                int count = database.update("DownloadSummonerSpellHoangSV", contentValues, "type=?", new String[]{type});
                if (count <= 0) {
                    database.insert("DownloadSummonerSpellHoangSV", null, contentValues);
                } else {

                    database.update("DownloadSummonerSpellHoangSV", contentValues, "type=?", new String[]{type});
                }

                JSONObject son1 = root.getJSONObject("data");

                for (int i = 0; i < son1.names().length(); i++) {
                    JSONObject summonerspell = son1.getJSONObject(son1.names().get(i) + "");
                    ContentValues values = new ContentValues();
                    xuLyDocVaGanCSDLChoTungSummonerSpell(summonerspell, values);
                }
                /*Cursor cursor2 = database.rawQuery("SELECT * FROM ChampionHoangSV WHERE id=?",new String[]{79 + ""});
                cursor2.moveToFirst();
                son1.names().length();
                textView.setText(cursor2.getString(16)+"");
                cursor2.close();*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
            new DocJSONRune().execute(urlrune);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
//            Su dung HttpUrlConnect
//            HttpHandler httpHandler=new HttpHandler();
//            String url1=httpHandler.makeServiceCall(params[0]);
//            return url1;

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder reqBuilder = new Request.Builder();
                reqBuilder.url(params[0]);
                Request request = reqBuilder.build();
                Response response = okHttpClient.newCall(request).execute();
                String kqTraVe = response.body().string();
                return kqTraVe;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    class DocJSONRune extends AsyncTask<String, Integer, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();

            //Toast.makeText(RecyclerActivity.this,sharedPreferences.getString("JSONCHAMPION",""),Toast.LENGTH_LONG).show();

            try {
                JSONObject root = new JSONObject(s);
                String type, version, data;
                type = root.getString("type");
                version = root.getString("version");
                data = root.getString("data");

                ContentValues contentValues = new ContentValues();
                contentValues.put("type", type);
                contentValues.put("version", version);
                contentValues.put("data", data);


                int count = database.update("DownloadRuneHoangSV", contentValues, "type=?", new String[]{type});
                if (count <= 0) {
                    database.insert("DownloadRuneHoangSV", null, contentValues);
                } else {

                    database.update("DownloadRuneHoangSV", contentValues, "type=?", new String[]{type});
                }

                JSONObject son1 = root.getJSONObject("data");

                for (int i = 0; i < son1.names().length(); i++) {
                    JSONObject rune = son1.getJSONObject(son1.names().get(i) + "");
                    ContentValues values = new ContentValues();
                    xuLyDocVaGanCSDLChoTungRune(rune, values);
                }
                /*Cursor cursor2 = database.rawQuery("SELECT * FROM ChampionHoangSV WHERE id=?",new String[]{79 + ""});
                cursor2.moveToFirst();
                son1.names().length();
                textView.setText(cursor2.getString(16)+"");
                cursor2.close();*/

            } catch (JSONException e) {
                e.printStackTrace();
            }
            SharedPreferences.Editor editor = sharedPreferencesVersion.edit();
            editor.putString("version", sharedPreferencesVersion.getString("versionFromURL", ""));
            editor.apply();
            txtHienThiVersion.setText(sharedPreferencesVersion.getString("version", ""));
            mProgressDialog.dismiss();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(String... params) {
//            Su dung HttpUrlConnect
//            HttpHandler httpHandler=new HttpHandler();
//            String url1=httpHandler.makeServiceCall(params[0]);
//            return url1;

            try {
                OkHttpClient okHttpClient = new OkHttpClient();
                Request.Builder reqBuilder = new Request.Builder();
                reqBuilder.url(params[0]);
                Request request = reqBuilder.build();
                Response response = okHttpClient.newCall(request).execute();
                String kqTraVe = response.body().string();
                return kqTraVe;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    private void xuLyDocVaGanCSDLChoTungTuong(JSONObject champXL, ContentValues vlXL) throws JSONException {
        vlXL.put("id", champXL.getString("id"));
        vlXL.put("key", champXL.getString("key"));
        vlXL.put("name", champXL.getString("name"));
        vlXL.put("title", champXL.getString("title"));
        vlXL.put("image", champXL.getString("image"));
        vlXL.put("skins", champXL.getString("skins"));
        vlXL.put("lore", champXL.getString("lore"));
        vlXL.put("blurb", champXL.getString("blurb"));
        vlXL.put("allytips", champXL.getString("allytips"));
        vlXL.put("enemytips", champXL.getString("enemytips"));
        vlXL.put("tags", champXL.getString("tags"));
        vlXL.put("partype", champXL.getString("partype"));
        vlXL.put("info", champXL.getString("info"));
        vlXL.put("stats", champXL.getString("stats"));
        vlXL.put("spells", champXL.getString("spells"));
        vlXL.put("passive", champXL.getString("passive"));
        vlXL.put("recommended", champXL.getString("recommended"));

        int count = database.update("ChampionHoangSV", vlXL, "id=?", new String[]{champXL.getString("id")});
        if (count <= 0) {
            database.insert("ChampionHoangSV", null, vlXL);
        } else {

            database.update("ChampionHoangSV", vlXL, "id=?", new String[]{champXL.getString("id")});
        }


    }


    private void xuLyDocLinkVaTenAnhTuCSDLRoiTaiVe2() {
        Cursor cursor = database.rawQuery("SELECT image FROM ItemHoangSV", null);
        while (cursor.moveToNext()) {
            String image = cursor.getString(0);
            try {
                String versionFromURL = sharedPreferencesVersion.getString("versionFromURL", "");
                JSONObject jsonObjectItem = new JSONObject(image);
                String itemName = jsonObjectItem.getString("full");
                final String category1 = "item";
                String itemUrl = "http://ddragon.leagueoflegends.com/cdn/" + versionFromURL + "/img/" + category1 + "/" + itemName;
                ArrayList<String> itemNameList = new ArrayList<>();
                itemNameList.add(itemName);
                final ArrayList<String> itemUrlList = new ArrayList<>();
                itemUrlList.add(itemUrl);


                for (int i = 0; i < itemUrlList.size(); i++) {
                    new DownloadImage(category1).execute(itemUrlList.get(i));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        cursor.close();
    }


    private void xuLyDocLinkVaTenAnhTuCSDLRoiTaiVe1() {
        Cursor cursor = database.rawQuery("SELECT image, passive, spells FROM ChampionHoangSV", null);
        while (cursor.moveToNext()) {
            String image = cursor.getString(0);
            String passive = cursor.getString(1);
            String spells = cursor.getString(2);
            try {
                String versionFromURL = sharedPreferencesVersion.getString("versionFromURL", "");
                JSONObject jsonObjectImage = new JSONObject(image);
                String imageName = jsonObjectImage.getString("full");
                final String category1 = "champion";
                String imageUrl = "http://ddragon.leagueoflegends.com/cdn/" + versionFromURL + "/img/" + category1 + "/" + imageName;
                ArrayList<String> imageNameList = new ArrayList<>();
                imageNameList.add(imageName);
                final ArrayList<String> imageUrlList = new ArrayList<>();
                imageUrlList.add(imageUrl);


                for (int i = 0; i < imageUrlList.size(); i++) {
                    new DownloadImage(category1).execute(imageUrlList.get(i));
                }


                JSONObject jsonObjectPassive = new JSONObject(passive);
                JSONObject jsonObjectPassiveSon = jsonObjectPassive.getJSONObject("image");
                String passiveName = jsonObjectPassiveSon.getString("full");
                final String category2 = "passive";
                String passiveUrl = "http://ddragon.leagueoflegends.com/cdn/" + versionFromURL + "/img/" + category2 + "/" + passiveName;
                ArrayList<String> passiveNameList = new ArrayList<>();
                passiveNameList.add(passiveName);
                final ArrayList<String> passiveUrlList = new ArrayList<>();
                passiveUrlList.add(passiveUrl);


                for (int i = 0; i < passiveUrlList.size(); i++) {
                    new DownloadImage(category2).execute(passiveUrlList.get(i));
                }


                JSONArray jsonArraySpells = new JSONArray(spells);
                ArrayList<String> spellNameList = new ArrayList<>();
                final ArrayList<String> spellUrlList = new ArrayList<>();
                final String category3 = "spell";
                for (int i = 0; i < jsonArraySpells.length(); i++) {
                    JSONObject jsonObject = jsonArraySpells.getJSONObject(i).getJSONObject("image");
                    String spellName = jsonObject.getString("full");
                    String spellUrl = "http://ddragon.leagueoflegends.com/cdn/" + versionFromURL + "/img/" + category3 + "/" + spellName;
                    spellNameList.add(spellName);
                    spellUrlList.add(spellUrl);
                }


                for (int i = 0; i < spellUrlList.size(); i++) {
                    new DownloadImage(category3).execute(spellUrlList.get(i));
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        cursor.close();
    }

    class DownloadImage extends AsyncTask<String, Integer, String> {

        String folderName;

        //  constructor
        public DownloadImage(String folderName) {
            this.folderName = folderName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);

            final DownloadTask downloadTask = new DownloadTask(getApplicationContext(), folderName);

            downloadTask.execute(s);

//            mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                @Override
//                public void onCancel(DialogInterface dialog) {
//                    downloadTask.cancel(true);
//                }
//            });
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(final String... params) {
            URL url = null;
            String fileName = null;
            try {
                url = new URL(params[0]);
                fileName = URLUtil.guessFileName(params[0], null, null);
                URLConnection urlConnection = url.openConnection();
                urlConnection.connect();
                int file_url_size = urlConnection.getContentLength();


//                final URL uri = new URL(urlChampionImage);
//                URLConnection ucon;
//
//                ucon = uri.openConnection();
//                ucon.connect();
//                String contentLengthStr = ucon.getHeaderField("content-length");
//                int contentLengthInt = Integer.parseInt(contentLengthStr);

                File rootPath = new File(getApplicationContext().getApplicationInfo().dataDir + "/" + folderName + "/");
                if (!rootPath.exists()) {
                    rootPath.mkdirs();
                }
                File file = new File(rootPath, fileName);
                int file_local_size = Integer.parseInt(String.valueOf(file.length()));
                if (!file.exists()) {
                    Log.d("aMainActivity", "xu ly tai anh neu file khong ton tai");
                    return params[0];
//                    final DownloadTask downloadTask = new DownloadTask(getApplicationContext());
//                            downloadTask.execute(params[0]);
//
//                    mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                        @Override
//                        public void onCancel(DialogInterface dialog) {
//                            downloadTask.cancel(true);
//                        }
//                    });


                } else {
                    if (file_url_size != file_local_size) {
//                        final DownloadTask downloadTask = new DownloadTask(getApplicationContext());
//                                downloadTask.execute(params[0]);
//                        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
//                            @Override
//                            public void onCancel(DialogInterface dialog) {
//                                downloadTask.cancel(true);
//                            }
//                        });
                        Log.d("aMainActivity", "xu ly tai anh neu file ton tai va khac kich thuoc");
                        return params[0];
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;
        private String folderName;


        public DownloadTask(Context context, String folderName) {
            this.context = context;
            this.folderName = folderName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
//            mProgressDialog.show();
        }

        @Override
        protected void onPostExecute(String s) {
            mWakeLock.release();
//            mProgressDialog.dismiss();

//            if (s != null)
//                Toast.makeText(context, "Download error: " + s, Toast.LENGTH_LONG).show();
//            else
//                Toast.makeText(context, "File downloaded", Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            // if we get here, length is known, now set indeterminate to false
//            mProgressDialog.setIndeterminate(false);
//            mProgressDialog.setMax(100);
//            mProgressDialog.setProgress(values[0]);
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(sUrl[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                String urlString = url.toString();
                String fileExtenstion = MimeTypeMap.getFileExtensionFromUrl(urlString);
                String name = URLUtil.guessFileName(urlString, null, fileExtenstion);

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(getApplicationContext().getApplicationInfo().dataDir + "/" + folderName + "/" + name);

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }
    }


    private void xuLyDocVaGanCSDLChoTungItem(JSONObject itemXL, ContentValues vlXLI) throws JSONException {

        if (itemXL.isNull("id")) {
            vlXLI.put("id", "");
        } else {
            vlXLI.put("id", itemXL.getString("id"));
        }
        if (itemXL.isNull("name")) {
            vlXLI.put("name", "");
        } else {
            vlXLI.put("name", itemXL.getString("name"));
        }
        if (itemXL.isNull("description")) {
            vlXLI.put("description", "");
        } else {
            vlXLI.put("description", itemXL.getString("description"));
        }
        if (itemXL.isNull("sanitizedDescription")) {
            vlXLI.put("sanitizedDescription", "");
        } else {
            vlXLI.put("sanitizedDescription", itemXL.getString("sanitizedDescription"));
        }
        if (itemXL.isNull("plaintext")) {
            vlXLI.put("plaintext", "");
        } else {
            vlXLI.put("plaintext", itemXL.getString("plaintext"));
        }
        if (itemXL.isNull("into")) {
            vlXLI.put("otni", "");
        } else {
            vlXLI.put("otni", itemXL.getString("into"));
        }
        if (itemXL.isNull("tags")) {
            vlXLI.put("tags", "");
        } else {
            vlXLI.put("tags", itemXL.getString("tags"));
        }
        if (itemXL.isNull("maps")) {
            vlXLI.put("maps", "");
        } else {
            vlXLI.put("maps", itemXL.getString("maps"));
        }
        if (itemXL.isNull("image")) {
            vlXLI.put("image", "");
        } else {
            vlXLI.put("image", itemXL.getString("image"));
        }
        if (itemXL.isNull("stats")) {
            vlXLI.put("stats", "");
        } else {
            vlXLI.put("stats", itemXL.getString("stats"));
        }
        if (itemXL.isNull("gold")) {
            vlXLI.put("gold", "");
        } else {
            vlXLI.put("gold", itemXL.getString("gold"));
        }
        if (itemXL.isNull("consumed")) {
            vlXLI.put("consumed", "");
        } else {
            vlXLI.put("consumed", itemXL.getString("consumed"));
        }
        if (itemXL.isNull("consumeOnFull")) {
            vlXLI.put("consumeOnFull", "");
        } else {
            vlXLI.put("consumeOnFull", itemXL.getString("consumeOnFull"));
        }
        if (itemXL.isNull("requiredChampion")) {
            vlXLI.put("requiredChampion", "");
        } else {
            vlXLI.put("requiredChampion", itemXL.getString("requiredChampion"));
        }
        if (itemXL.isNull("colloq")) {
            vlXLI.put("colloq", "");
        } else {
            vlXLI.put("colloq", itemXL.getString("colloq"));
        }
        if (itemXL.isNull("depth")) {
            vlXLI.put("depth", "");
        } else {
            vlXLI.put("depth", itemXL.getString("depth"));
        }
        if (itemXL.isNull("from")) {
            vlXLI.put("morf", "");
        } else {
            vlXLI.put("morf", itemXL.getString("from"));
        }
        if (itemXL.isNull("effect")) {
            vlXLI.put("effect", "");
        } else {
            vlXLI.put("effect", itemXL.getString("effect"));
        }
        if (itemXL.isNull("stacks")) {
            vlXLI.put("stacks", "");
        } else {
            vlXLI.put("stacks", itemXL.getString("stacks"));
        }

        int count = database.update("ItemHoangSV", vlXLI, "id=?", new String[]{itemXL.getString("id")});
        if (count <= 0) {
            database.insert("ItemHoangSV", null, vlXLI);
        } else {

            database.update("ItemHoangSV", vlXLI, "id=?", new String[]{itemXL.getString("id")});
        }
    }


    private void xuLyDocVaGanCSDLChoTungMastery(JSONObject masteryXL, ContentValues vlXL) throws JSONException {
        vlXL.put("id", masteryXL.getString("id"));
        vlXL.put("name", masteryXL.getString("name"));
        vlXL.put("description", masteryXL.getString("description"));


        int count = database.update("MasteryHoangSV", vlXL, "id=?", new String[]{masteryXL.getString("id")});
        if (count <= 0) {
            database.insert("MasteryHoangSV", null, vlXL);
        } else {

            database.update("MasteryHoangSV", vlXL, "id=?", new String[]{masteryXL.getString("id")});
        }
    }


    private void xuLyDocVaGanCSDLChoTungSummonerSpell(JSONObject summonerSpellXL, ContentValues vlXLS) throws JSONException {
        vlXLS.put("name", summonerSpellXL.getString("name"));
        vlXLS.put("description", summonerSpellXL.getString("description"));
        vlXLS.put("summonerLevel", summonerSpellXL.getString("summonerLevel"));
        vlXLS.put("id", summonerSpellXL.getString("id"));
        vlXLS.put("key", summonerSpellXL.getString("key"));


        int count = database.update("SummonerSpellHoangSV", vlXLS, "id=?", new String[]{summonerSpellXL.getString("id")});
        if (count <= 0) {
            database.insert("SummonerSpellHoangSV", null, vlXLS);
        } else {

            database.update("SummonerSpellHoangSV", vlXLS, "id=?", new String[]{summonerSpellXL.getString("id")});
        }
    }


    private void xuLyDocVaGanCSDLChoTungRune(JSONObject runeXL, ContentValues vlXLR) throws JSONException {
        vlXLR.put("id", runeXL.getString("id"));
        vlXLR.put("name", runeXL.getString("name"));
        vlXLR.put("description", runeXL.getString("description"));
        if (runeXL.isNull("rune")) {
            vlXLR.put("rune", "");
        } else {
            vlXLR.put("rune", runeXL.getString("rune"));
        }

        int count = database.update("RuneHoangSV", vlXLR, "id=?", new String[]{runeXL.getString("id")});
        if (count <= 0) {
            database.insert("RuneHoangSV", null, vlXLR);
        } else {

            database.update("RuneHoangSV", vlXLR, "id=?", new String[]{runeXL.getString("id")});
        }
    }

}
