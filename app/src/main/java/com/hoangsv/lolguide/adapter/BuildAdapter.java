package com.hoangsv.lolguide.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.activity.WatchCosplayContentActivity;
import com.hoangsv.lolguide.model.Build;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by User on 3/2/2017.
 */

public class BuildAdapter extends RecyclerView.Adapter<BuildAdapter.ViewHolder> {
    private List<Build> dsBuild;
    private Activity context;

    public BuildAdapter(Activity context, List<Build> dsBuild) {
        this.context = context;
        this.dsBuild = dsBuild;
    }

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

        // Set ảnh dùng thư viện Picasso
        setImageForImageView(holder, build);

        // Set ảnh không dùng thư viện
//        setImageForImageView2(holder);
    }

    @Override
    public int getItemCount() {
        return dsBuild.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.txtGiaiDoan)     TextView txtGiaiDoan;
        @BindView(R.id.imgDo1)          ImageView imgDo1;
        @BindView(R.id.imgDo2)          ImageView imgDo2;
        @BindView(R.id.imgDo3)          ImageView imgDo3;
        @BindView(R.id.imgDo4)          ImageView imgDo4;
        @BindView(R.id.imgDo5)          ImageView imgDo5;
        @BindView(R.id.imgDo6)          ImageView imgDo6;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, WatchCosplayContentActivity.class);
            // Chuyển tới trang thông tin đồ vừa bấm vào
            movingToStuffInformationPage(v, intent);
        }
    }

    @OnClick(R.id.imgDo1)
    void watchInfoItem1() {
        Intent intent = new Intent(context, WatchCosplayContentActivity.class);
        Log.i("aaa", "aaa");
        context.startActivity(intent);
    }



    private void movingToStuffInformationPage(View v, Intent intent) {
        String id;
        switch (v.getId()) {
            case R.id.imgDo1:
                id=dsBuild.get(0).getId1();
                intent.putExtra("id", id);
                Log.i("aaa", id);
                break;
            case R.id.imgDo2:
                id=dsBuild.get(0).getId2();
                intent.putExtra("id", id);
                break;
            case R.id.imgDo3:
                id=dsBuild.get(0).getId3();
                intent.putExtra("id", id);
                break;
            case R.id.imgDo4:
                id=dsBuild.get(0).getId4();
                intent.putExtra("id", id);
                break;
            case R.id.imgDo5:
                id=dsBuild.get(0).getId5();
                intent.putExtra("id", id);
                break;
            case R.id.imgDo6:
                id=dsBuild.get(0).getId6();
                intent.putExtra("id", id);
                break;
        }

    }

    private void setImageForImageView(ViewHolder holder, Build build) {
        if (build.getImage1()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage1();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo1);
        } else {
            holder.imgDo1.setVisibility(View.GONE);
        }
        if (build.getImage2()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage2();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo2);
        } else {
            holder.imgDo2.setVisibility(View.GONE);
        }
        if (build.getImage3()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage3();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo3);
        } else {
            holder.imgDo3.setVisibility(View.GONE);
        }
        if (build.getImage4()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage4();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo4);
        } else {
            holder.imgDo4.setVisibility(View.GONE);
        }
        if (build.getImage5()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage5();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo5);
        } else {
            holder.imgDo5.setVisibility(View.GONE);
        }
        if (build.getImage6()!=null) {
            String outFileName = context.getApplicationInfo().dataDir + "/item/" + build.getImage6();
            Picasso.with(context).load("file://"+outFileName).into(holder.imgDo6);
        } else {
            holder.imgDo6.setVisibility(View.GONE);
        }
    }

//    private void setImageForImageView2(ViewHolder holder) {
//        AssetManager assetManager = holder.imgChampionFree.getContext().getAssets();
//        try {
//            InputStream open = assetManager.open("app/champion/"+championFree.getImage());
//            Bitmap bitmap = BitmapFactory.decodeStream(open);
//            BitmapDrawable bitmapDrawable= new BitmapDrawable(bitmap);
//            holder.imgChampionFree.setImageDrawable(bitmapDrawable);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
