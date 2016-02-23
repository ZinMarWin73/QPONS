package com.revotech.qpons.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.revotech.qpons.R;

import com.revotech.qpons.activity.fragments.LogInFragment;
import com.revotech.qpons.activity.fragments.MyCuponsFragment;

import com.revotech.qpons.activity.fragments.MyCuponsListFragment;
import com.revotech.qpons.activity.fragments.PrivacyPolicyFragment;
import com.revotech.qpons.activity.fragments.TermNConditionFragment;


import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout mdrawer;
    @Bind(R.id.nvView) NavigationView nvDrawer;
    public @Bind(R.id.tab_layout)
    TabLayout tabLay;

    ActionBarDrawerToggle mDrawerToggle;
    android.os.Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        setupDrawerContent(nvDrawer);

        SetUpNagation();



    }

   private void SetUpNagation() {
        View header = nvDrawer.inflateHeaderView(R.layout.nav_header);
        nvDrawer.getHeaderView(0);

        ImageView image_hearProfile = (ImageView) header.findViewById(R.id.navi_header_user_profile);
        TextView txt_username = (TextView) header.findViewById(R.id.nav_user_name);
        TextView txt_email = (TextView) header.findViewById(R.id.nav_header_email);


        mDrawerToggle = setupDrawerToggle();
        mdrawer.setDrawerListener(mDrawerToggle);

       header.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               mdrawer.closeDrawers();
               tabLay.setVisibility(View.GONE);

               FragmentManager fragmentManager = getSupportFragmentManager();
               fragmentManager.beginTransaction().replace(R.id.flContent, new LogInFragment()).commit();

               //Intent i = new Intent(getApplication() , LogIn_Activity.class);
               // startActivity(i);

           }
       });

       nvDrawer.getMenu().getItem(0).setChecked(true);

       selectDrawerItem(nvDrawer.getMenu().getItem(0));
       //FragmentManager fragmentManager = getSupportFragmentManager();
       //fragmentManager.beginTransaction().replace(R.id.flContent, new MyCuponsFragment()).commit();
//        MyCuponsFragment.newInstance(0);


    }




    // set up Navgtion View
    private void setupDrawerContent(NavigationView navigationView) {



        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(final MenuItem menuItem) {
                       mdrawer.closeDrawers();

                        selectDrawerItem(menuItem);

                        return true;
                    }


                });
    }


   private void selectDrawerItem(MenuItem menuItem) {
         Fragment fragment = null;
       final FragmentManager fragmentManager = getSupportFragmentManager();
       handler = new Handler();

       // Class fragmentClass = null;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabLay.setVisibility(View.VISIBLE);

                        fragmentManager.beginTransaction().replace(R.id.flContent, new MyCuponsFragment()).commit();

                    }
                }, 200);




                break;
            case R.id.nav_first_fragment1:

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabLay.setVisibility(View.GONE);
                        fragmentManager.beginTransaction().replace(R.id.flContent, new MyCuponsListFragment()).commit();

                    }
                }, 200);

                break;

            case R.id.nav_second_fragment:


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabLay.setVisibility(View.GONE);
                        fragmentManager.beginTransaction().replace(R.id.flContent, new TermNConditionFragment()).commit();

                    }
                }, 200);


                break;
            case R.id.nav_third_fragment:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabLay.setVisibility(View.GONE);
                        fragmentManager.beginTransaction().replace(R.id.flContent, new PrivacyPolicyFragment()).commit();

                    }
                }, 200);

                break;

            case R.id.nav_logout:
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabLay.setVisibility(View.GONE);
                        fragmentManager.beginTransaction().replace(R.id.flContent, new LogInFragment()).commit();

                    }
                }, 200);

                break;
            default:
        }

     try {
         //   fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

       if(fragment != null){


           // Highlight the selected item, update the title, and close the drawer
           menuItem.setChecked(true);
           //setTitle(menuItem.getTitle());
           mdrawer.closeDrawers();
       }
        // Insert the fragment by replacing any existing fragment


    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, mdrawer, toolbar, R.string.drawer_open,  R.string.drawer_close);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }


        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
