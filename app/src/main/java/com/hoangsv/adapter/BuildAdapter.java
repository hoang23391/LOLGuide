package com.hoangsv.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.model.Build;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by User on 3/2/2017.
 */

public class BuildAdapter extends RecyclerView.Adapter<BuildAdapter.ViewHolder> {
    private List<Build> dsBuild;
    private Activity context;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_build_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Build build=dsBuild.get(position);
        holder.txtGiaiDoan.setText(build.getType());
        if (build.getId1()!=null) {
            Picasso.with(context).load("file:///android_asset/app/item/"+build.getId1()).into(holder.imgDo1);
        }
        if (build.getId2()!=null) {
            Picasso.with(context).load("file:///android_asset/app/item/"+build.getId2()).into(holder.imgDo2);
        }
        if (build.getId3()!=null) {
            Picasso.with(context).load("file:///android_asset/app/item/"+build.getId3()).into(holder.imgDo3);
        }
        if (build.getId4()!=null) {
            Picasso.with(context).load("file:///android_asset/app/item/"+build.getId4()).into(holder.imgDo4);
        }
        if (build.getId5()!=null) {
            Picasso.with(context).load("file:///android_asset/app/item/"+build.getId5()).into(holder.imgDo5);
        }
        if (build.getId6()!=null) {
            Picasso.with(context).load("file:///android_asset/app/item/"+build.getId6()).into(holder.imgDo6);
        }


        /*AssetManager assetManager = holder.imgChampionFree.getContext().getAssets();
        try {
            InputStream open = assetManager.open("app/champion/"+championFree.getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            BitmapDrawable bitmapDrawable= new BitmapDrawable(bitmap);
            holder.imgChampionFree.setImageDrawable(bitmapDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public int getItemCount() {
        return dsBuild.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtGiaiDoan) TextView txtGiaiDoan;
        @BindView(R.id.imgDo1) ImageView imgDo1;
        @BindView(R.id.imgDo2) ImageView imgDo2;
        @BindView(R.id.imgDo3) ImageView imgDo3;
        @BindView(R.id.imgDo4) ImageView imgDo4;
        @BindView(R.id.imgDo5) ImageView imgDo5;
        @BindView(R.id.imgDo6) ImageView imgDo6;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public BuildAdapter(Activity context, List<Build> dsBuild) {
        this.context = context;
        this.dsBuild = dsBuild;
    }
}
