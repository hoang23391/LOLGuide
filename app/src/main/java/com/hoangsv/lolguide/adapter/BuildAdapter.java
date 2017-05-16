package com.hoangsv.lolguide.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.activity.XemNoiDungCosplayActivity;
import com.hoangsv.lolguide.model.Build;
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
        if (build.getImage1()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage1();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo1);
        }
        if (build.getImage2()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage2();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo2);
        }
        if (build.getImage3()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage3();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo3);
        }
        if (build.getImage4()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage4();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo4);
        }
        if (build.getImage5()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage5();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo5);
        }
        if (build.getImage6()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage6();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo6);
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
            Intent intent = new Intent(context, XemNoiDungCosplayActivity.class);
            switch (v.getId()) {
                case R.id.imgDo1:
                    intent.putExtra("id", dsBuild.get(0).getId1());
                    context.startActivity(intent);
                    break;
                case R.id.imgDo2:
                    intent.putExtra("id", dsBuild.get(0).getId2());
                    context.startActivity(intent);
                    break;
                case R.id.imgDo3:
                    intent.putExtra("id", dsBuild.get(0).getId3());
                    context.startActivity(intent);
                    break;
                case R.id.imgDo4:
                    intent.putExtra("id", dsBuild.get(0).getId4());
                    context.startActivity(intent);
                    break;
                case R.id.imgDo5:
                    intent.putExtra("id", dsBuild.get(0).getId5());
                    context.startActivity(intent);
                    break;
                case R.id.imgDo6:
                    intent.putExtra("id", dsBuild.get(0).getId6());
                    context.startActivity(intent);
                    break;

            }

        }
    }

    public BuildAdapter(Activity context, List<Build> dsBuild) {
        this.context = context;
        this.dsBuild = dsBuild;
    }
}
