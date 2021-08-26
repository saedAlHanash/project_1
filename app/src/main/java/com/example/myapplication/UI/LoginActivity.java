package com.example.myapplication.UI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.APIs.API;
import com.example.myapplication.APIs.ApiClint;
import com.example.myapplication.AppConfig;
import com.example.myapplication.R;
import com.example.myapplication.User;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    TextInputEditText userName, password;
    Button button, singIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppConfig.getSharedPreferencesInstance(getBaseContext());
        if(AppConfig.isUserLogin()){
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        inflate();
        listener();
    }

    void inflate(){
        userName = findViewById(R.id.sing_in_user_name);
        password = findViewById(R.id.sing_in_password);
        singIn = findViewById(R.id.sing_in_sin_in_btn);
        button = findViewById(R.id.sing_in_register_btn);
    }

    void listener(){

        button.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), RegesterActivity.class);
            startActivity(intent);
        });

        singIn.setOnClickListener(v -> {
            API api = ApiClint.getRetrofitInstance().create(API.class);
            Call<User> call = api.getUser(userName.getText().toString(), password.getText().toString());
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    User user = response.body();
                    if (user.id == -1) {
                        Toast.makeText(LoginActivity.this, "wrong password", Toast.LENGTH_SHORT).show();
                    }
                    else if(user.id==-2)
                    {
                        Toast.makeText(LoginActivity.this, "user name not hear!!", Toast.LENGTH_SHORT).show();
                    }
                    if (user.id >= 0) {
                        AppConfig config = new AppConfig(getBaseContext());
                        AppConfig.getSharedPreferencesInstance(getBaseContext());
                        config.login(user);
                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                        startActivity(intent);
                    }
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });

    }

}
