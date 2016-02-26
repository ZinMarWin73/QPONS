package com.revotech.qpons.activity.controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.fitness.data.Session;
import com.revotech.qpons.activity.LogIn_Activity;
import com.revotech.qpons.activity.constant.Constant;
import com.revotech.qpons.activity.utils.SharePreference;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by zinmarwin on 2/25/16.
 */
public class FaceBookSKD {

   // LoginButton loginButton ;
    CallbackManager callbackManager;
    Activity activity;
    String email,name,id;


    public FaceBookSKD(Activity activity) {
            this.activity = activity ;

    }

    public  void FaceBookInit(CallbackManager callbackManager){


        LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile", "user_friends", "email"));

        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
                Toast.makeText(activity, "Connected with Facebook Acc Info", Toast.LENGTH_SHORT).show();
                Log.i("Login onSuccess", loginResult.toString());

                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {

                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.i("Login Result", response.toString());

                                Constant.LoginSession = "true";
                                try {

                                    email = object.getString("email");
                                    name = object.getString("name");
                                    id = object.getString("id");

                                    Intent i = new Intent(activity , LogIn_Activity.class);
                                    i.putExtra("userinfo" , object.toString());
                                    activity.startActivity(i);

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
                Log.i("Login OnCancle", "Cancel");

            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Log.i("Login onError", "onError");

            }
        });
    }


}
