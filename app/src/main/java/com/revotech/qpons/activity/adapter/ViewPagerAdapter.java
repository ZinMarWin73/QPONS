package com.revotech.qpons.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.revotech.qpons.activity.fragments.FeatureTabFragment;
import com.revotech.qpons.activity.fragments.MyCuponsFragment;
import com.revotech.qpons.activity.fragments.PrivacyPolicyFragment;
import com.revotech.qpons.activity.fragments.TermNConditionFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zinmarwin on 2/10/16.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;

    /** Constructor of the class */
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    /** This method will be invoked when a page is requested to create */

    @Override
    public Fragment getItem(int arg0) {

        FeatureTabFragment frag1 = new FeatureTabFragment();

        switch (arg0)
        {

            case 0:
               FeatureTabFragment fragment = new FeatureTabFragment();
                return fragment;

            case 1:
                FeatureTabFragment fragment1 = new FeatureTabFragment();
                return fragment1;
            case 2:
                FeatureTabFragment fragment2 = new FeatureTabFragment();
                return fragment2;
             default:
                // FeatureTabFragment frag = new FeatureTabFragment();
                 //return frag1;
        }
        return frag1;
    }

    /** Returns the number of pages */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {


        String tilte = "";

        switch (position)
        {

            case 0:
                tilte = "Feature" ;
                return tilte;
            case 1:

                tilte = "Goods" ;
                return tilte;
            case 2:

                tilte = "Promotion" ;
                return tilte;

        }
        return tilte;
    }

}