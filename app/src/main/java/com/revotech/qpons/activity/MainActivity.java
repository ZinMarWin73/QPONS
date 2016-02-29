package com.revotech.qpons.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.service.textservice.SpellCheckerService;
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
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.revotech.qpons.R;

import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.controller.FaceBookSKD;
import com.revotech.qpons.activity.controller.LayoutController;
import com.revotech.qpons.activity.fragments.LogInFragment;
import com.revotech.qpons.activity.fragments.MyCuponsFragment;

import com.revotech.qpons.activity.fragments.MyCuponsListFragment;
import com.revotech.qpons.activity.fragments.PrivacyPolicyFragment;
import com.revotech.qpons.activity.fragments.TermNConditionFragment;
import com.revotech.qpons.activity.utils.SharePreference;


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
    CallbackManager callbackManager;
    private MenuItem mSearchAction;
    private boolean isSearchOpened = false;
    private EditText edtSeach;
    LayoutController controller ;
    SharedPreferences sharePreference;
    FragmentManager fragmentManager;



    String email = "" , name ="", phone_number ="";
    FaceBookSKD fb ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        sharePreference = SharePreference.getPreferences(this);
        FacebookSdk.sdkInitialize(this);
        callbackManager = CallbackManager.Factory.create();
         fb = new FaceBookSKD(MainActivity.this);

        controller = new LayoutController();

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

        if(Constant.LoginSession.equals("true")){
            //nvDrawer.getMenu().getItem(4).setVisible(true);
            nvDrawer.getMenu().getItem(4).setTitle("Sign out");
            if(!sharePreference.equals(null)){

                txt_email.setText(SharePreference.readString(this , Constant.EMAILS , ""));
                txt_username.setText(SharePreference.readString(this , Constant.NAME , ""));
            }

        }

       header.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               mdrawer.closeDrawers();
               tabLay.setVisibility(View.GONE);


               LoginControll();




           }
       });

       nvDrawer.getMenu().getItem(0).setChecked(true);

       selectDrawerItem(nvDrawer.getMenu().getItem(0));



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
       fragmentManager = getSupportFragmentManager();
       handler = new Handler();
       // Class fragmentClass = null;
        switch(menuItem.getItemId()) {
            case R.id.nav_first_fragment:


                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabLay.setTabMode(TabLayout.MODE_SCROLLABLE );

                        tabLay.setVisibility(View.VISIBLE);

                        fragmentManager.beginTransaction().replace(R.id.flContent, new MyCuponsFragment(Constant.DEALS)).commit();

                    }
                }, 200);




                break;
            case R.id.nav_first_fragment1:

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tabLay.setTabMode(TabLayout.MODE_FIXED);
                        tabLay.setVisibility(View.VISIBLE);
                        fragmentManager.beginTransaction().replace(R.id.flContent, new MyCuponsFragment(Constant.MYCOUPONS)).commit();

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

            case R.id.nav_changeLanguage:



                break;
            case R.id.nav_logout:
                   //  menuItem.setVisible(false);

                if(menuItem.getTitle().equals("Sign out")){

                    menuItem.setVisible(true);
                    LoginManager.getInstance().logOut();
                   // SpellCheckerService.Session session = SpellCheckerService.Session.getActiveSession();
                   // session.closeAndClearTokenInformation();
                    SharePreference.getPreferences(this).edit().clear();


                }else{

                    LoginControll();

                }


                break;
            default:
        }


       if(fragment != null){


           // Highlight the selected item, update the title, and close the drawer
           menuItem.setChecked(true);
           //setTitle(menuItem.getTitle());
           mdrawer.closeDrawers();
       }
        // Insert the fragment by replacing any existing fragment


    }



    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
       // onCreate(outState);
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
    public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if (mDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        int id = item.getItemId();

        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_search:
                 handleMenuSearch();
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
    @Override
    protected void onResume() {
        super.onResume();

        // Logs 'install' and 'app activate' App Events.
        AppEventsLogger.activateApp(this);
    }
    @Override
    protected void onPause() {
        super.onPause();

        // Logs 'app deactivate' App Event.
        AppEventsLogger.deactivateApp(this);
    }

    @Override
    public void onBackPressed() {
        if(isSearchOpened) {
            handleMenuSearch();
            return;
        }
        super.onBackPressed();
    }

    public void handleMenuSearch() {

        ActionBar action= getSupportActionBar();
        if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(getResources().getDrawable(R.mipmap.search));

            isSearchOpened = false;
        } else { //open the search entry

            action.setDisplayShowCustomEnabled(true); //enable it to display a
            // custom view in the action bar.
            action.setCustomView(R.layout.menu_searchbar);//add the custom view
            action.setDisplayShowTitleEnabled(false); //hide the title

            edtSeach = (EditText)action.getCustomView().findViewById(R.id.edSearch); //the text editor

            //this is a listener to do a search when the user clicks on search button
            edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        doSearch();
                        return true;
                    }
                    return false;
                }
            });


            edtSeach.requestFocus();

            //open the keyboard focused in the edtSearch
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(getResources().getDrawable(R.drawable.close));

            isSearchOpened = true;
        }
    }

    private void doSearch() {

    }
    private void LoginControll() {
        handler = new Handler();

        if(Constant.LoginSession.equals("true")){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    tabLay.setVisibility(View.GONE);
                    fragmentManager.beginTransaction().replace(R.id.flContent, new LogInFragment()).commit();

                }
            }, 200);

        }else{
            fb.FaceBookInit(callbackManager);

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
