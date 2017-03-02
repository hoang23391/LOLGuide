package com.hoangsv.lolguide;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hoangsv.adapter.ViewPagerAdapter;

public class ItemOneFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    NewsFragment newsFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_item_one, container, false);
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
        threeFragment = new ThreeFragment();
        adapter.addFragment(newsFragment, "News");
        //adapter.addFragment(twoFragment, "Hot");
        adapter.addFragment(threeFragment, "Cosplay");
        viewPager.setAdapter(adapter);
    }
}
