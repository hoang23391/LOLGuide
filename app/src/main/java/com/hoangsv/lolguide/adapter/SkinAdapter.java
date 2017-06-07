package com.hoangsv.lolguide.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.model.Skin;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 2/22/2017.
 */

public class SkinAdapter extends ArrayAdapter<Skin> {
    Activity context;
    int resource;
    List<Skin> objects;
    public SkinAdapter(Activity context, int resource, List<Skin> objects) {
        super(context, resource, objects);
        this.objects=objects;
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater=this.context.getLayoutInflater();
        View row=inflater.inflate(this.resource,null);
        TextView txtTrangPhuc= (TextView) row.findViewById(R.id.txtTrangPhuc);
        ImageView imgTrangPhuc= (ImageView) row.findViewById(R.id.imgTrangPhuc);
        //http://ddragon.leagueoflegends.com/cdn/img/champion/splash/Heimerdinger_0.jpg
        Skin skin =this.objects.get(position);
        txtTrangPhuc.setText(skin.getName());
        Picasso.with(context).load(skin.getImage()).into(imgTrangPhuc);
        return row;
    }
}
