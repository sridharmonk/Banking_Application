package com.bank.basic_bank_system;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import static com.bank.basic_bank_system.R.*;

public class splash_screen extends Activity {

    TextView designed, name, app_name;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.splash_screen);

        logo = findViewById(id.logo);
        designed = findViewById(id.designed);
        name = findViewById(id.name);
        app_name = findViewById(id.app_name);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startEnterAnimation();

            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();

            }
        }, 5500);
    }

    private void startEnterAnimation() {
        app_name.startAnimation(AnimationUtils.loadAnimation(splash_screen.this, anim.bottom));
        logo.startAnimation(AnimationUtils.loadAnimation(splash_screen.this, anim.p_in));

        logo.setVisibility(View.VISIBLE);
        designed.setVisibility(View.VISIBLE);
        name.setVisibility(View.VISIBLE);
        app_name.setVisibility(View.VISIBLE);
    }
}

