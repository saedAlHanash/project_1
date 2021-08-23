package com.example.myapplication.UI;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.myapplication.UI.fragments.FragmentSaed3;
import com.example.myapplication.R;
import com.example.myapplication.UI.fragments.groupFragment;
import com.example.myapplication.UI.fragments.personalFragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;

    ViewPager viewPager;

   public static Context context;

    @RequiresApi(api = Build.VERSION_CODES.N)
    protected void onCreate(Bundle paramBundle) {
        context = getBaseContext();
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);
        this.tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        this.viewPager = (ViewPager) findViewById(R.id.view_pager_id);
        this.tabLayout.setTabGravity(0);
        com.example.myapplication.adapters.ViewPager viewPager = new com.example.myapplication.adapters.ViewPager(getSupportFragmentManager());
        viewPager.addFragment(new personalFragment(), "My Task");
        viewPager.addFragment(new groupFragment(), "Team Task");
        viewPager.addFragment(new FragmentSaed3(), "special Task");
        this.viewPager.setAdapter((PagerAdapter) viewPager);
        this.tabLayout.setupWithViewPager(this.viewPager);
    }
}
