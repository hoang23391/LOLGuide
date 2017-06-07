package com.hoangsv.lolguide.fragment;


import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.hoangsv.lolguide.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.DOWNLOAD_SERVICE;


public class CosplayContentFragment extends Fragment {
    // ScreenSlidePageFragment
    /**
     * The argument key for the page number this fragment represents.
     */
    public static final String ARG_PAGE = "page";
    public static final String ARG_CONTENT = "noidung";

    /**
     * The fragment's page number, which is set to the argument value for {@link #ARG_PAGE}.
     */
    private int mPageNumber;
    private String mContent;

    @BindView(R.id.imgYeah)                 ImageView imgYeah;
    @BindView(R.id.txtSlide)                TextView txtSlide;
    @BindView(R.id.menu_cp)                 FloatingActionMenu menu_cp;
    @BindView(R.id.menu_item_share_cp)      FloatingActionButton menu_item_share_cp;
    @BindView(R.id.menu_item_download_cp)   FloatingActionButton menu_item_download_cp;

    /**
     * Factory method for this fragment class. Constructs a new fragment for the given page number.
     */
    public static CosplayContentFragment create(int pageNumber, String noiDung) {
        CosplayContentFragment fragment = new CosplayContentFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, pageNumber);
        args.putString(ARG_CONTENT, noiDung);
        fragment.setArguments(args);
        return fragment;
    }

    public CosplayContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPageNumber = getArguments().getInt(ARG_PAGE);
        mContent = getArguments().getString(ARG_CONTENT);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout containing a title and body text.
        View view = inflater.inflate(R.layout.fragment_cosplay_content, container, false);
        ButterKnife.bind(this,view);
        txtSlide.setText("#"+(mPageNumber+1));
        Picasso.with(getActivity().getApplicationContext()).load(mContent).into(imgYeah);
        return view;
    }

    @OnClick(R.id.menu_item_download_cp)
    void downloadCosplayImage() {
        DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(mContent));
        request.setNotificationVisibility(1);
        downloadManager.enqueue(request);
        Toast.makeText(getActivity(),"Success",Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.menu_item_share_cp)
    void shareCosplayImageUrl() {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBodyText = mContent;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Amazing LOL picture");
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBodyText);
        startActivity(Intent.createChooser(sharingIntent, "Sharing Option"));
    }

    /**
     * Returns the page number represented by this fragment object.
     */
    public int getPageNumber() {
        return mPageNumber;
    }
}