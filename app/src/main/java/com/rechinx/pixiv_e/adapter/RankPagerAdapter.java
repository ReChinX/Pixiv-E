package com.rechinx.pixiv_e.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.View;

import com.rechinx.pixiv_e.fragment.RankFragment;

/**
 * Created by Chin on 2016/7/24.
 */
public class RankPagerAdapter extends FragmentPagerAdapter {

    private static String[] sTitle = {"Daily", "Weekly", "Monthly"};

    public RankPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sTitle[position];
    }

    @Override
    public Fragment getItem(int position) {
        return new RankFragment(position);
    }

    @Override
    public int getCount() {
        return sTitle.length;
    }
}
