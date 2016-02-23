package com.revotech.qpons.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.revotech.qpons.R;
import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.fragments.pickerDialogs;

import org.w3c.dom.Text;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SendAsGiftActivty extends AppCompatActivity implements View.OnClickListener {

    // public static  @Bind(R.id.txt_sendgit_date_show)
    public static TextView txt_showDate;
    @Bind(R.id.ed_reciver_email)
    EditText ed_recive_mail;
    @Bind(R.id.ed_reciver_name)
    EditText ed_reciver_name;

    @Bind(R.id.button_continue)
    Button btn_continuse;

    @Bind(R.id.imgButton_close)
    ImageButton imag_close;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_as_gift_activty);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        txt_showDate = (TextView) findViewById(R.id.txt_sendgit_date_show);
        txt_showDate.setOnClickListener(this);

        btn_continuse.setOnClickListener(this);
        imag_close.setOnClickListener(this);
    }

    public void setDate(View view) {
        pickerDialogs pickerDialogs = new pickerDialogs();
        pickerDialogs.show(getSupportFragmentManager(), "date_picker");

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.imgButton_close:
                finish();

            case R.id.button_continue:

                SetResultActivity();


                break;
            case R.id.txt_sendgit_date_show:
                setDate(v);

                break;
        }


    }

    private void SetResultActivity() {
        String str_name  = ed_reciver_name.getText().toString() ;
        String str_mail = ed_recive_mail.getText().toString();
        String date = txt_showDate.getText().toString() ;

        if(!str_name.equals("") && !str_mail.equals("")) {

            if (!str_name.equals("")) {

                if (!str_mail.equals("")) {

                    Intent i = getIntent();
                    Bundle b = new Bundle();

                    b.putString("name", str_name);
                    b.putString("date", date);
                    i.putExtras(b);

                    setResult(Activity.RESULT_OK, i);
                    finish();

                } else {
                    Toast.makeText(this, "Required Recipient's Email", Toast.LENGTH_SHORT).show();
                }

            } else {
                Toast.makeText(this, "Required  Recipient's Name ", Toast.LENGTH_SHORT).show();
            }

        }else {
            Toast.makeText(this, "Required Recipient's Name and Email ", Toast.LENGTH_SHORT).show();

        }


    }


}
