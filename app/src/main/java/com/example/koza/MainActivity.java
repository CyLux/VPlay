package com.example.koza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    Button btnLog;
    EditText login, password;
    TextView signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLog = findViewById(R.id.signin);
        login = findViewById(R.id.edUsername);
        password = findViewById(R.id.edPassword);
        signUp = findViewById(R.id.signup);

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , SignUp.class));
            }
        });
        btnLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(login.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    String message = "Заполните все поля ";
                    Toast.makeText(MainActivity.this,message, Toast.LENGTH_LONG).show();
                } else {
                    LoginRequest loginRequest = new LoginRequest();
                    loginRequest.setUsername(login.getText().toString());
                    loginRequest.setPassword(password.getText().toString());

                    loginUser(loginRequest);
                }
            }
        });
    }
    public void loginUser(LoginRequest loginRequest){
        Call<LoginResponse> loginResponseCall = ApiClient.getService().loginUser(loginRequest);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    LoginResponse loginResponse = response.body();
                    startActivity(new Intent(MainActivity.this, ActivityMain.class).putExtra("data", loginResponse));
                    finish();
                }else{
                    String message = "Произошла ошибка, попробуйте заново";
                    Toast.makeText(MainActivity.this,message, Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                String message =t.getLocalizedMessage();
                Toast.makeText(MainActivity.this,message, Toast.LENGTH_LONG).show();
            }
        });

    }
}