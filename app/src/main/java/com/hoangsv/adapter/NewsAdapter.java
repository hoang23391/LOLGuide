package com.hoangsv.adapter;


import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoangsv.lolguide.R;
import com.hoangsv.model.News;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 3/2/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter <NewsAdapter.ViewHolder> {
    private Activity context;
    private List<News> dsNews;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgNews;
        TextView txtNews, txtNewsND;
        public ViewHolder(View itemView) {
            super(itemView);
            imgNews= (ImageView) itemView.findViewById(R.id.imgNews);
            txtNews= (TextView) itemView.findViewById(R.id.txtNews);
            txtNewsND= (TextView) itemView.findViewById(R.id.txtNewsND);
        }

        @Override
        public void onClick(View v) {

        }
    }

    public NewsAdapter(Activity context, List<News> dsNews) {
        this.context = context;
        this.dsNews = dsNews;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_news_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = dsNews.get(position);
        holder.txtNews.setText(news.getTieuDe());
        Picasso.with(context).load(news.getAnhDaiDien()).into(holder.imgNews);

    }

    @Override
    public int getItemCount() {
        return dsNews.size();
    }


}
