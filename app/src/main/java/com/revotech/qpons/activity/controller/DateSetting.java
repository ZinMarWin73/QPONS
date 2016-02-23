package com.revotech.qpons.activity.controller;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.DatePicker;

import com.revotech.qpons.R;
import com.revotech.qpons.activity.SendAsGiftActivty;

import java.util.Calendar;

/**
 * Created by zinmarwin on 2/18/16.
 */
public class DateSetting implements DatePickerDialog.OnDateSetListener {

    Calendar calendar;
    Context context;
    public DateSetting(Context context) {
        this.context = context;

    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH) ;
        int year_cal= calendar.get(Calendar.YEAR);


        int pick_month  =  monthOfYear+1;


        if( (day  ==  dayOfMonth) && (month == monthOfYear) && (year_cal == year) ){
            Log.i("Today" , day + " Date picker day" + dayOfMonth);
            SendAsGiftActivty.txt_showDate.setText("Today");

        }else {
            SendAsGiftActivty.txt_showDate.setText(dayOfMonth + "/" + pick_month + "/" + year);
        }

    }
}
