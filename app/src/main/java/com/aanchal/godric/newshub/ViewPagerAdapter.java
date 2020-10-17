package com.aanchal.godric.newshub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    String[] arr;
    @Override
    public Fragment getItem(int i) {
        final FragmentNews fragment = new FragmentNews();
        String[] sources = {"the-times-of-india","cnn","the-new-york-times","the-hindu","mtv-news","espn","time"};
        Bundle bundle = new Bundle();
        bundle.putString("source",sources[i]);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        arr = new String[]{"The Times Of India", "CNN News", "The New York Times", "The Hindu", "MTV News", "ESPN","Time"};
        return arr[position];
    }

}

