package com.hoangsv.lolguide.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoangsv.lolguide.R;
import com.hoangsv.lolguide.adapter.ViewPagerAdapter;

public class NewsMainFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    NewsFragment newsFragment;
    CosplayFragment cosplayFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_news_main, container, false);
        viewPager = (ViewPager) v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        return v;
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        newsFragment = new NewsFragment();
        //twoFragment = new TwoFragment();
        cosplayFragment = new CosplayFragment();
        adapter.addFragment(newsFragment, "News");
        //adapter.addFragment(twoFragment, "Hot");
        adapter.addFragment(cosplayFragment, "Cosplay");
        viewPager.setAdapter(adapter);
    }
}
