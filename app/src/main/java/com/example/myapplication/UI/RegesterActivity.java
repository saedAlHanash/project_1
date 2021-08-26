package com.example.myapplication.UI;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.APIs.API;
import com.example.myapplication.APIs.ApiClint;
import com.example.myapplication.APIs.ApiResponse;
import com.example.myapplication.R;
import com.example.myapplication.User;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegesterActivity extends AppCompatActivity {
    TextInputEditText userName, email, pass, fullName;
    TextInputLayout userNameBox, emailBox, passBox, fullNameBox;
    Button button;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_regester);
        inflate();

        button.setOnClickListener(v -> {
            API api = ApiClint.getRetrofitInstance().create(API.class);
            Call<ApiResponse> call = api.insertUser(new User(
                    fullName.getText().toString(),
                    userName.getText().toString(),
                    pass.getText().toString(),
                    email.getText().toString()
            ));
            call.enqueue(new Callback<ApiResponse>() {
                @Override
                public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                    ApiResponse body = response.body();
                    Toast.makeText(RegesterActivity.this, ""+body.getStatus(), Toast.LENGTH_SHORT).show();
                    if(body.getResultCode()==0)
                    userName.setError("change this");
                    else if (body.getResultCode()==1) {
                        restFiled();
                        finish();
                    }
                }

                @Override
                public void onFailure(Call<ApiResponse> call, Throwable t) {
                    Toast.makeText(RegesterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });
        });
    }

    void restFiled() {
        userName.setText("");
        email.setText("");
        pass.setText("");
        fullName.setText("");
    }
    void inflate(){
        userName = findViewById(R.id.register_act_user_name);
        fullName = findViewById(R.id.register_act_fullName);
        email = findViewById(R.id.register_act_email);
        pass = findViewById(R.id.register_act_password);
        button = findViewById(R.id.register_act_add_account);


        userNameBox = findViewById(R.id.register_act_user_name_box);
        fullNameBox = findViewById(R.id.register_act_fullName_box);
        emailBox = findViewById(R.id.register_act_email_box);
        passBox = findViewById(R.id.register_act_password_box);
    }
}


/* Location:              D:\dfSDF\dex-tools-2.1-20171001-lanchon\dex-tools-2.1-20171001-lanchon\saed-dex2jar.jar!\com\example\myapplication\Main3Activity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */