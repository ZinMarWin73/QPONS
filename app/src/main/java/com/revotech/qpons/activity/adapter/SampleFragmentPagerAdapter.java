package com.revotech.qpons.activity.adapter;

import android.content.Context;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.*;

import com.revotech.qpons.activity.fragments.MyCuponsFragment;

/**
 * Created by zinmarwin on 2/9/16.
 */
public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3" };
    private Context context;

    public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return null;
       // return MyCuponsFragment.newInstance(position + 1);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}
