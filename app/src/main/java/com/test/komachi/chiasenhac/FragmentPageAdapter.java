package com.test.komachi.chiasenhac;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FragmentPageAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 4;
    private Context context;


    FragmentPageAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new TopFragment();
            case 1:
                return new FragmentTab1();
            case 2:
                return new Fragment3();
            case 3:
                return new Fragment4();
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
    }
}
