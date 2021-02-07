package com.example.koza;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity {
    Button signup1;
    EditText edUsername, edEmail, edPassword,edCPassword;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        signup1=findViewById(R.id.signup);
        edUsername=findViewById(R.id.edUsername);
        edEmail=findViewById(R.id.edEmail);
        edPassword=findViewById(R.id.edPassword);
        edCPassword=findViewById(R.id.edCPassword);

        signup1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(edEmail.getText().toString()) || TextUtils.isEmpty(edUsername.getText().toString()) || TextUtils.isEmpty(edPassword.getText().toString()) || TextUtils.isEmpty(edCPassword.getText().toString())){
                    String message = "Заполните все поля ";
                    Toast.makeText(SignUp.this,message, Toast.LENGTH_LONG).show();
                }else {
                RegisterRequest registerRequest= new RegisterRequest();
                registerRequest.setEmail(edEmail.getText().toString());
                registerRequest.setPassword(edPassword.getText().toString());
                registerRequest.setUsername(edUsername.getText().toString());

                registerUser(registerRequest);
                }
            }
        });
}
public void registerUser(RegisterRequest registerRequest){
    Call<RegisterResponse> registerResponseCall = ApiClient.getService().registerUser(registerRequest);
    registerResponseCall.enqueue(new Callback<RegisterResponse>() {
        @Override
        public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
            if(response.isSuccessful()){
                String message = "Учетная запись успешно зарегистрирована";
                Toast.makeText(SignUp.this,message, Toast.LENGTH_LONG).show();
                startActivity(new Intent(SignUp.this, MainActivity.class));
                finish();
            }else {
                String message = "Произошла ошибка, попробуйте заново";
                Toast.makeText(SignUp.this,message, Toast.LENGTH_LONG).show();
        }
        }

        @Override
        public void onFailure(Call<RegisterResponse> call, Throwable t) {
            String message =t.getLocalizedMessage();
            Toast.makeText(SignUp.this,message, Toast.LENGTH_LONG).show();
        }
    });
}
}
