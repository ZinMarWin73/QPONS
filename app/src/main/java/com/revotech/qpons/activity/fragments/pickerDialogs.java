package com.revotech.qpons.activity.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import com.revotech.qpons.activity.controller.DateSetting;

import java.util.Calendar;

/**
 * Created by zinmarwin on 2/18/16.
 */
public class pickerDialogs extends DialogFragment {
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            DateSetting dateSetting=new DateSetting(getActivity());


            Calendar calendar= Calendar.getInstance();
            int year= calendar.get(calendar.YEAR);
            int month=calendar.get(calendar.MONTH);
            int day=calendar.get(calendar.DAY_OF_MONTH);

            DatePickerDialog dialog;

            dialog=new DatePickerDialog(getActivity(),dateSetting,year,month,day);
            return dialog;
        }

}
