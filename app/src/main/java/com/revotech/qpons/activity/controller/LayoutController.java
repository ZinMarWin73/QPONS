package com.revotech.qpons.activity.controller;

import android.content.Context;
import android.graphics.Color;
import android.widget.Button;
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
}
