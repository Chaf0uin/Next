package com.kerboocorp.next.adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.kerboocorp.next.fragments.StuffCalendarFragment;
import com.kerboocorp.next.fragments.StuffListFragment;
import com.kerboocorp.next.fragments.StuffMapFragment;

/**
 * Created by cgo on 20/03/2015.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    CharSequence Titles[] = {"List", "Calendar", "Map"};
    int NumbOfTabs = 3;

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                StuffListFragment stuffListFragment = new StuffListFragment();
                return stuffListFragment;
            case 1:
                StuffCalendarFragment stuffCalendarFragment = new StuffCalendarFragment();
                return stuffCalendarFragment;
            case 2:
                StuffMapFragment stuffMapFragment = new StuffMapFragment();
                return stuffMapFragment;
        }

        return null;


    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Titles[position];
    }

    @Override
    public int getCount() {
        return NumbOfTabs;
    }
}