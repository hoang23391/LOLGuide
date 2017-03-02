package com.hoangsv.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.model.Cosplay;
import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * Created by User on 2/27/2017.
 */

public class CosplayAdapter extends RecyclerView.Adapter<CosplayAdapter.ViewHolder> {
    private List<Cosplay> danhSachCosplay;
    private Activity context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgCosplay;
        TextView txtCosplay;
        public ViewHolder(View itemView) {
            super(itemView);
            imgCosplay= (ImageView) itemView.findViewById(R.id.imgCosplay);
            txtCosplay= (TextView) itemView.findViewById(R.id.txtCosplay);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public CosplayAdapter(Activity context, List<Cosplay> danhSachCosplay) {
        this.context = context;
        this.danhSachCosplay = danhSachCosplay;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_three_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Cosplay cosplay=danhSachCosplay.get(position);
        holder.txtCosplay.setText(cosplay.getTieuDe());
        Picasso.with(context).load(cosplay.getAnhDaiDien()).into(holder.imgCosplay);

    }

    @Override
    public int getItemCount() {
        return danhSachCosplay.size();
    }

}
