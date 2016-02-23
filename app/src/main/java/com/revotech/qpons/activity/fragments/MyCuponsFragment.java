package com.revotech.qpons.activity.fragments;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.revotech.qpons.R;
import com.revotech.qpons.activity.MainActivity;
import com.revotech.qpons.activity.adapter.Adapter;
import com.revotech.qpons.activity.adapter.ViewPagerAdapter;

/**
 * Created by zinmarwin on 2/2/16.
 */
public class MyCuponsFragment extends Fragment {

 //   ViewPager viewPager ;
  //  TabLayout tabLayout;

    public static TabLayout tabLayout;
    public static ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View x =  inflater.inflate(R.layout.tab_home,null);
       // tabLayout = (TabLayout) x.findViewById(R.id.tabs);
        viewPager = (ViewPager) x.findViewById(R.id.pager);

        //setupViewPager(viewPager);

        /** Instantiating FragmentPagerAdapter */
        ViewPagerAdapter pagerAdapter = new ViewPagerAdapter(getChildFragmentManager());

        /** Setting the pagerAdapter to the pager object */
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        ((MainActivity)getActivity()).tabLay.setupWithViewPager(viewPager);
        return x;
    }




}
