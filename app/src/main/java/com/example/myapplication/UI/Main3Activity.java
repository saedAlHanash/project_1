package com.example.myapplication.UI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.API;
import com.example.myapplication.R;
import com.example.myapplication.TestGson;
import com.example.myapplication.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Main3Activity extends AppCompatActivity {
    TextInputEditText userName, email, pass, fullName;
    Button button;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main3);
        userName = findViewById(R.id.register_act_user_name);
        fullName = findViewById(R.id.register_act_fullName);
        email = findViewById(R.id.register_act_email);
        pass = findViewById(R.id.register_act_password);
        button = findViewById(R.id.register_act_add_account);

        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("http://192.168.1.105:8080/").
                addConverterFactory(GsonConverterFactory.create(gson)).
                build();
        API api = retrofit.create(API.class);
        button.setOnClickListener(v -> {
            Call<User> call = api.saed();
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Toast.makeText(Main3Activity.this, ""+response.body().getPassword(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {
                    Toast.makeText(Main3Activity.this, ""+t.getMessage()+" ss", Toast.LENGTH_LONG).show();
                }
            });
//            Call<User> call = api.store();
//            call.enqueue(new Callback<User>() {
//                @Override
//                public void onResponse(Call<User> call, Response<User> response) {
//                    Toast.makeText(Main3Activity.this, ""+response.message(), Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(Call<User> call, Throwable t) {
//                    Toast.makeText(Main3Activity.this, ""+t.getMessage(), Toast.LENGTH_LONG).show();
//                }
//            });
//            restFiled();
        });
    }

    void restFiled() {
        userName.setText("");
        email.setText("");
        pass.setText("");
        fullName.setText("");
    }
}


/* Location:              D:\dfSDF\dex-tools-2.1-20171001-lanchon\dex-tools-2.1-20171001-lanchon\saed-dex2jar.jar!\com\example\myapplication\Main3Activity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */