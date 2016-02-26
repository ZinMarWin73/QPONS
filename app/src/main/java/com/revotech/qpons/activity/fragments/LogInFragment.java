package com.revotech.qpons.activity.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.login.widget.LoginButton;
import com.revotech.qpons.R;
import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.utils.SharePreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;


public class LogInFragment extends Fragment implements View.OnClickListener {

    LoginButton loginButton ;
    CallbackManager callbackManager;

    RelativeLayout real_layout;
    EditText ed_email ;
    EditText ed_username;
    EditText ed_phone_number;
    Button btn_Login;
    String email ;
    String name;
    String id;
    String phnumber;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.content_log_in_, container, false);



        real_layout = (RelativeLayout) view.findViewById(R.id.realLayout_login);
        ed_email = (EditText) view.findViewById(R.id.ed_login_email);
        ed_phone_number = (EditText) view.findViewById(R.id.ed_login_phonenumber);
        ed_username = (EditText) view.findViewById(R.id.ed_login_name);
        btn_Login = (Button) view.findViewById(R.id.button_login);

        btn_Login.setOnClickListener(this);


        email = SharePreference.readString(getContext() , Constant.EMAILS , "");
        name  = SharePreference.readString(getContext() , Constant.NAME , "");
        phnumber =SharePreference.readString(getContext(), Constant.PH_NUMBER, "");

        if(!email.equals(null)){

            ed_email.setText(email);
        }
        if(!name.equals(null)){

            ed_username.setText(name);

        }
        if(!phnumber.equals(null)){
            ed_phone_number.setText(phnumber);
        }

        btn_Login.setText("Edit");

        /*loginButton.setReadPermissions(Arrays.asList("public_profile"));
        // If using in a fragment
        loginButton.setFragment(this);
        // Other app specific specialization
        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(getContext() , "OnSuccess" , Toast.LENGTH_SHORT).show();
                Log.i("Login onSuccess" , loginResult.toString());

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback(){

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("Login Result" , response.toString());

      ;                          try {
                                     email =  object.getString("email");
                                     name = object.getString("name");
                                     id = object.getString("id");

                                    loginButton.setVisibility(View.GONE);
                                    real_layout.setVisibility(View.VISIBLE);

                                    ed_username.setText(name);
                                    ed_email.setText(email);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                );

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender");

                request.setParameters(parameters);
                request.executeAsync();
            }

            @Override
            public void onCancel() {
                // App code
                Log.i("Login OnCancle" , "Cancel");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i("Login onError" , "onError");

            }
        });*/
        return view;
    }

    /*@Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }*/


    @Override
    public void onClick(View v) {
        String phnumber = ed_phone_number.getText().toString();




    }
}
