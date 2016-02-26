package com.revotech.qpons.activity.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.revotech.qpons.activity.constant.Constant;

/**
 * Created by zinmarwin on 2/25/16.
 */
public class SharePreference {

    SharedPreferences prefs ;
    SharedPreferences.Editor editor;
    public static final String PREFS = "qpon_prefs";
    public static final int MODE  = Context.MODE_PRIVATE;



    public static SharedPreferences getPreferences(Context context) {
         return context.getSharedPreferences(PREFS , MODE);


    }
    public static SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public static void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();

    }


    public static int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public static void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
        Log.e("Save " , value) ;

    }

    public static String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

}
