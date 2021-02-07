package com.example.koza;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class LaunchScreen extends AppCompatActivity {
        private final int SPLASH_DISPLAY_LENGTH = 2000;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.launch_screen);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainIntent = new Intent(LaunchScreen.this, MainActivity.class);
                    LaunchScreen.this.startActivity(mainIntent);
                    LaunchScreen.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
        }

    }

