package com.revotech.qpons.activity.controller;

import android.app.Activity;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.revotech.qpons.R;
import com.revotech.qpons.activity.constant.Constant;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Created by zinmarwin on 2/15/16.
 */
public class LayoutController {

    public LinearLayout addTextView(LinearLayout layout, Context context, String[] hight_light){
      //  LinearLayout view = new LinearLayout(context);

        for(int i = 0 ; i < hight_light.length ; i++)
        {
            TextView txtView = new TextView(context);
            txtView.setTextColor(context.getResources().getColor(R.color.colorPrimaryText));
            txtView.setTextSize(14);
            txtView.setCompoundDrawablePadding(10);
            txtView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.bullet , 0 , 0 , 0);
            txtView.setText(hight_light[i]);


            layout.addView(txtView);
        }

        return layout;

    }

    public String CalculateTotal(int qty , String  str_price){
        String numberformat = "";
       try {
            Long price = (Long) NumberFormat.getNumberInstance(Locale.US).parse(str_price);

           long number = price * qty ;

           String str = NumberFormat.getNumberInstance(Locale.US).format(number);
           //numberformat = Integer.parseInt(str);
            numberformat = str;

       } catch (ParseException e) {
            e.printStackTrace();
        }



        return numberformat;
    }

    public int ItemCount(Button button , int number , String status){

       // num = number ;



            if(status.equals(Constant.INCREASE)){

                number +=1;

            }else {

                if(number != 1)
                number -= 1 ;

            }



        button.setText(number + "");

        return number;
    }

    public void handleMenuSearch(MenuItem menuItem  , Activity context) {
         MenuItem mSearchAction = menuItem;
         boolean isSearchOpened = false;
         EditText edtSeach = null;

        android.app.ActionBar action = context.getActionBar(); //get the actionbar

        if(isSearchOpened){ //test if the search is open

            action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
            action.setDisplayShowTitleEnabled(true); //show the title in the action bar

            //hides the keyboard
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

            //add the search icon in the action bar
            mSearchAction.setIcon(context.getResources().getDrawable(R.mipmap.search));

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
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);


            //add the close icon
            mSearchAction.setIcon(context.getResources().getDrawable(R.drawable.close));

            isSearchOpened = true;
        }
    }

    private void doSearch() {

    }

}
