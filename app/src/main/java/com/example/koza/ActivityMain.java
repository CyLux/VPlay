package com.example.koza;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ActivityMain extends AppCompatActivity {
    LoginResponse loginResponse;
    protected void onCreate(Bundle savedInstanceState) {
        TextView username;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

        username=findViewById(R.id.textView5);
        Intent i =getIntent();
        if(i.getExtras() != null){
            loginResponse=(LoginResponse) i.getSerializableExtra("data");
            username.setText(loginResponse.getUsername());
            Log.e("TAG",loginResponse.getEmail());
        }
    }

}