package com.example.myapplication.UI;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.myapplication.AppConfig;
import com.example.myapplication.UI.fragments.FragmentSaed3;
import com.example.myapplication.R;
import com.example.myapplication.UI.fragments.groupFragment;
import com.example.myapplication.UI.fragments.personalFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    ImageButton button;
    TabLayout tabLayout;

    ViewPager viewPager;

   public static Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle paramBundle) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        context = getBaseContext();
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        AppConfig config = new AppConfig(getBaseContext());
        this.tabLayout = findViewById(R.id.tab_layout);
        this.viewPager = findViewById(R.id.view_pager_id);
        this.tabLayout.setTabGravity(0);
        this.button = findViewById(R.id.settings);

        com.example.myapplication.adapters.ViewPager viewPager = new com.example.myapplication.adapters.ViewPager(getSupportFragmentManager());

        viewPager.addFragment(new personalFragment(), "My Task");
        viewPager.addFragment(new groupFragment(), "Team Task");
        viewPager.addFragment(new FragmentSaed3(), "special Task");
        this.viewPager.setAdapter((PagerAdapter) viewPager);
        this.tabLayout.setupWithViewPager(this.viewPager);

        button.setOnClickListener(v -> {
           config.logout();
           finish();
        });
        String  saed = AppConfig.sp.getString("user_name","wrong");
        Toast.makeText(this, ""+saed, Toast.LENGTH_SHORT).show();

    }
}
