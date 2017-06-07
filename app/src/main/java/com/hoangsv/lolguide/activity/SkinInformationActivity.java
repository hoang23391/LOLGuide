package com.hoangsv.lolguide.activity;

import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.hoangsv.lolguide.R;
import com.squareup.picasso.Picasso;

public class SkinInformationActivity extends AppCompatActivity {
    ImageView imgAnhTrangPhuc;
    FloatingActionMenu menu_cttp;
    FloatingActionButton menu_item_share_cttp, menu_item_download_cttp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_trang_phuc);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        addControls();
        addEvents();
    }

    private void addEvents() {
        Intent intent=getIntent();
        String imglink = intent.getStringExtra("ImgLink");

        Picasso.with(this).load(imglink).into(imgAnhTrangPhuc);
    }

    private void addControls() {
        imgAnhTrangPhuc = (ImageView) findViewById(R.id.imgAnhTrangPhuc);
        menu_cttp = (FloatingActionMenu) findViewById(R.id.menu_cttp);
        menu_item_download_cttp = (FloatingActionButton) findViewById(R.id.menu_item_download_cttp);
        menu_item_share_cttp = (FloatingActionButton) findViewById(R.id.menu_item_share_cttp);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.menu_item_download_cttp) {
            Intent intent=getIntent();
            String imglink = intent.getStringExtra("ImgLink");
            DownloadManager downloadManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(imglink));
            request.setNotificationVisibility(1);
            downloadManager.enqueue(request);
        } else if (id == R.id.menu_item_share_cttp) {
            Intent intent=getIntent();
            String imglink = intent.getStringExtra("ImgLink");
            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            String shareBodyText = imglink;
            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Amazing LOL picture");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
            startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
        }
    }
}
