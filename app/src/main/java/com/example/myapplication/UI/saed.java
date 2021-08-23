package com.example.myapplication.UI;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;

public class saed extends AppCompatActivity {

    Button button,singIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saed);
        singIn =findViewById(R.id.sing_in_sin_in_btn);
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getBaseContext(), Main3Activity.class);
            startActivity(intent);
        });
        singIn.setOnClickListener(v ->{
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
            Toast.makeText(this, "saed", Toast.LENGTH_SHORT).show();
        });
      //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


}
