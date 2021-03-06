package com.hoangsv.lolguide.adapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.utility.RoundedTransformation;
import com.hoangsv.lolguide.model.Champion;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 2/20/2017.
 */

public class ChampionAdapter extends ArrayAdapter<Champion> {
    Activity context;
    int resource;
    List<Champion> objects;
    @BindView(R.id.imgChampion)
    ImageView imgChampion;
    @BindView(R.id.txtChampionName)
    TextView txtChampionName;

    public ChampionAdapter(Activity context, int resource, List<Champion> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(this.resource, null);
        ButterKnife.bind(this, row);
//        TextView txtChampionName = (TextView) row.findViewById(R.id.txtChampionName);
//        ImageView imgChampion = (ImageView) row.findViewById(R.id.imgChampion);

        Champion champion = this.objects.get(position);
        txtChampionName.setText(champion.getName());
        String imageName = champion.getImage();

        String outFileName = getContext().getApplicationInfo().dataDir + "/champion/" + imageName;
        Picasso.with(context)
                .load("file://" + outFileName)
                .transform(new RoundedTransformation(160, 4))
                .fit()
                .centerCrop()
                .into(imgChampion);
        Log.d("aChampionAdapter", "load anh vao picasso");


//        Picasso.with(context)
//                .load("file:///android_asset/app/champion/"+champion.getImage())
//                .transform(new RoundedTransformation(160, 4))
//                .fit()
//                .centerCrop()
//                .into(imgChampion);

//        Picasso.with(yourContext)
//                .load("...") // Your image source.
//                .transform(new RoundedTransformation(50, 4))
//                .fit()  // Fix centerCrop issue: http://stackoverflow.com/a/20824141/1936697
//                .centerCrop()
//                .into(yourImageView);

//        AssetManager assetManager = context.getAssets();
//
//        try {
//            InputStream open = assetManager.open("app/champion/"+champion.getImage());
//            Bitmap bitmap = BitmapFactory.decodeStream(open);
//            BitmapDrawable bitmapDrawable= new BitmapDrawable(bitmap);
//            imgChampion.setBackgroundDrawable(bitmapDrawable);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return row;
    }
}
