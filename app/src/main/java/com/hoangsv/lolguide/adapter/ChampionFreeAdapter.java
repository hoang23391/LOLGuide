package com.hoangsv.lolguide.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.model.ChampionFree;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChampionFreeAdapter extends RecyclerView.Adapter<ChampionFreeAdapter.ViewHolder> {
    private Activity context;
    private List<ChampionFree> danhSachChampionFree;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.imgChampionFree)
        ImageView imgChampionFree;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public ChampionFreeAdapter(Activity context, List<ChampionFree> danhSachChampionFree) {
        this.context = context;
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
        ChampionFree championFree = danhSachChampionFree.get(position);
        String imageName = championFree.getImage();
        String outFileName = context.getApplicationInfo().dataDir + "/champion/" + imageName;

        Picasso.with(context)
                .load("file://" + outFileName)
                .into(holder.imgChampionFree);

        /*
        AssetManager assetManager = holder.imgChampionFree.getContext().getAssets();
        try {
            InputStream open = assetManager.open("app/champion/"+championFree.getImage());
            Bitmap bitmap = BitmapFactory.decodeStream(open);
            BitmapDrawable bitmapDrawable= new BitmapDrawable(bitmap);
            holder.imgChampionFree.setImageDrawable(bitmapDrawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    @Override
    public int getItemCount() {
        return danhSachChampionFree.size();
    }
}


