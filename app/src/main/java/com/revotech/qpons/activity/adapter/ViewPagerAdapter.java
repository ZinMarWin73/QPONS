package com.revotech.qpons.activity.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.revotech.qpons.activity.constant.Constant;
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
    int PAGE_COUNT = 0;
    String [] str_title  = new String[]{} ;
    String str_context;
    /** Constructor of the class */

    public ViewPagerAdapter(FragmentManager fm, String[] str_title, String str_context) {
        super(fm);
        this.str_title = str_title ;
        PAGE_COUNT = str_title.length;
        this.str_context = str_context ;
    }


    /** This method will be invoked when a page is requested to create */

    @Override
    public Fragment getItem(int arg0) {

        Fragment frag = null;

        if(str_context.equals(Constant.DEALS)) {

            frag = new FeatureTabFragment();

            switch (arg0) {

                case 0:
                    FeatureTabFragment fragment = new FeatureTabFragment();
                    return fragment;

                case 1:
                    FeatureTabFragment fragment1 = new FeatureTabFragment();
                    return fragment1;
                case 2:
                    FeatureTabFragment fragment2 = new FeatureTabFragment();
                    return fragment2;

                case 3:
                    FeatureTabFragment fragment3 = new FeatureTabFragment();
                    return fragment3;
                case 4:
                    FeatureTabFragment fragment4 = new FeatureTabFragment();
                    return fragment4;
                case 5:
                    FeatureTabFragment fragment5 = new FeatureTabFragment();
                    return fragment5;
                default:
                    // FeatureTabFragment frag = new FeatureTabFragment();
                    //return frag1;
            }

        }else{
             frag = new FeatureTabFragment();


        }


        return frag;

    }

    /** Returns the number of pages */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {


       /* String tilte = "";

        switch (position)
        {

            case 0:
                tilte = "Travel" ;
                return tilte;
            case 1:

                tilte = "Food" ;
                return tilte;
            case 2:

                tilte = "Health" ;
                return tilte;
            case 3:

                tilte = "Lifestyle";
                return tilte;
            case 4:
                tilte = "Beauty";
                return tilte;
            case 5:
                tilte = "Rooms";
                return tilte;
        }*/
        return str_title[position];
    }

}