package com.rechinx.pixiv_e.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.rechinx.pixiv_e.fragment.FavoriteFragment;

/**
 * Created by Chin on 2016/7/28.
 */
public class FavoritePagerAdapter extends FragmentPagerAdapter {

    public static String[] sTITLE = {"Publicity", "Privacy"};

    public FavoritePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return sTITLE[position];
    }

    @Override
    public Fragment getItem(int position) {
        return new FavoriteFragment(position);
    }

    @Override
    public int getCount() {
        return sTITLE.length;
    }
}
