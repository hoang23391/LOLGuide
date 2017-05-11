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
import com.hoangsv.lolguide.model.TrangPhuc;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by User on 2/22/2017.
 */

public class TrangPhucAdapter extends ArrayAdapter<TrangPhuc> {
    Activity context;
    int resource;
    List<TrangPhuc> objects;
    public TrangPhucAdapter(Activity context, int resource, List<TrangPhuc> objects) {
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
        TrangPhuc trangPhuc=this.objects.get(position);
        txtTrangPhuc.setText(trangPhuc.getName());
        Picasso.with(context).load(trangPhuc.getImage()).into(imgTrangPhuc);
        //new DownloadImageTask(imgTrangPhuc)                .execute(trangPhuc.getImage());



        return row;
    }

    /*private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }*/
}
