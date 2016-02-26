package com.revotech.qpons.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.revotech.qpons.R;
import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.utils.SharePreference;

import org.json.JSONObject;

public class LogIn_Activity extends AppCompatActivity {
    RelativeLayout real_layout;
    EditText ed_email ;
    EditText ed_username;
    EditText ed_phone_number;
    Button btn_Login;
    String email = "";
    String name = "";
    String id;
    String phnumber = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_log_in_);
      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);


        real_layout = (RelativeLayout) findViewById(R.id.realLayout_login);
        ed_email = (EditText) findViewById(R.id.ed_login_email);
        ed_phone_number = (EditText) findViewById(R.id.ed_login_phonenumber);
        ed_username = (EditText) findViewById(R.id.ed_login_name);
        btn_Login = (Button) findViewById(R.id.button_login);

        GetUserInfo();

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 phnumber = ed_phone_number.getText().toString();

                if(!phnumber.equals("")){
                    SharePreference.writeString(getBaseContext() , Constant.EMAILS, email);
                    SharePreference.writeString(getBaseContext(), Constant.NAME, name);
                    SharePreference.writeString(getBaseContext(), Constant.PH_NUMBER, phnumber);

                    Intent i = new Intent(getApplicationContext() , MainActivity.class);
                    startActivity(i);
                    finish();

                }else{
                    Toast.makeText(getBaseContext(), "Required Phone Nubmer", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
    private void GetUserInfo() {
        Intent i = getIntent();
        String strData = i.getStringExtra("userinfo");

        try{

            JSONObject jsonObject = new JSONObject(strData);
             email = jsonObject.get("email").toString();
            name = jsonObject.get("name").toString();

            ed_email.setText(email);
            ed_username.setText(name);

        }catch (Exception e){
            e.printStackTrace();
        }

            //email = SharePreference.readString(getBaseContext() , Constant.EMAILS , "");
            //name = SharePreference.readString(getBaseContext() , Constant.NAME , "");
            //phnumber = SharePreference.readString(getBaseContext() , Constant.PH_NUMBER , "");

    }
}
