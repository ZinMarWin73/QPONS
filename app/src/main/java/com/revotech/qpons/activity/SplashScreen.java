package com.revotech.qpons.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.revotech.qpons.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zinmarwin on 2/2/16.
 */
public class SplashScreen extends AppCompatActivity {

    @Bind(R.id.img_logo) ImageView imag_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.splash);


        ButterKnife.bind(this);


        //
        initialeLogo();
        LogoAnimation();
    }

    private void initialeLogo() {
        imag_logo.setImageResource(R.mipmap.logo_white);


        Thread thread = new Thread(){


            @Override
            public void run() {

                try {


                    int timer = 0;


                    while (timer < 3000) {
                        sleep(100);
                        timer += 100;
                    }


                startActivity(new Intent(SplashScreen.this, MainActivity.class));

                }catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    finish();
                }


            }
        };
        thread.start();

    }


    private void LogoAnimation() {
        final Animation animation = new AlphaAnimation(1, 0);
        animation.setDuration(700);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setRepeatMode(Animation.REVERSE);
        imag_logo.startAnimation(animation);
    }


}
