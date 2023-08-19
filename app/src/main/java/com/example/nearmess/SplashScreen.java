package com.example.nearmess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


/***
 * Author : Arpita Waghmare
 *last edited By : Vasudev Raut
 *Date : 14-08-2023
 * Note : Changed Intent
 */
public class SplashScreen extends AppCompatActivity {
    private static final int SPLASH_SCREEN_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        setStatusBarColor("#2a7453");
        addAnimation();




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Intent is used to switch from one activity to another.
                Intent i = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(i); // invoke the SecondActivity.
                finish(); // the current activity will get finished.
            }
        }, SPLASH_SCREEN_TIME_OUT);





    }


    private void setStatusBarColor(String hexColor) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // Convert hex color to integer color
            int color = Color.parseColor(hexColor);

            window.setStatusBarColor(color);
        }
    }



    public void addAnimation()
    {

        Animation scale = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.animation1);
        findViewById(R.id.outer_round).setAnimation(scale);



    }


}