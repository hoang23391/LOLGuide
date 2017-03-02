package com.hoangsv.adapter;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoangsv.lolguide.R;
import com.hoangsv.model.ChampionFree;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ChampionFreeAdapter extends RecyclerView.Adapter<ChampionFreeAdapter.ViewHolder> {
    private List<ChampionFree> danhSachChampionFree;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgChampionFree;

        public ViewHolder(View view) {
            super(view);
            imgChampionFree= (ImageView) view.findViewById(R.id.imgChampionFree);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public ChampionFreeAdapter(List<ChampionFree> danhSachChampionFree) {
        this.danhSachChampionFree = danhSachChampionFree;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.championfree_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChampionFree championFree=danhSachChampionFree.get(position);

        AssetManager assetManager = holder.imgChampionFree.getContext().getAssets();
        try {
            InputStream open = assetManager.open("app/champion/"+championFree.getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            BitmapDrawable bitmapDrawable= new BitmapDrawable(bitmap);
            holder.imgChampionFree.setImageDrawable(bitmapDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return danhSachChampionFree.size();
    }
}


