package com.hoangsv.adapter;

import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.model.Champion;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by User on 2/20/2017.
 */

public class ChampionAdapter extends ArrayAdapter<Champion> {
    Activity context;
    int resource;
    List<Champion> objects;

    public ChampionAdapter(Activity context, int resource, List<Champion> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView txtChampionName= (TextView) row.findViewById(R.id.txtChampionName);
        ImageView imgChampion= (ImageView) row.findViewById(R.id.imgChampion);

        Champion champion=this.objects.get(position);
        txtChampionName.setText(champion.getName());

        AssetManager assetManager = context.getAssets();

        try {
            InputStream open = assetManager.open("app/champion/"+champion.getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            BitmapDrawable bitmapDrawable= new BitmapDrawable(bitmap);
            imgChampion.setBackgroundDrawable(bitmapDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return row;
    }
}
