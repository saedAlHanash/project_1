package com.example.myapplication.UI;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;

public class Main2Activity extends AppCompatActivity {
    Animation animation;

    Animation animation1;

    ImageView imageView;

    TextView textView;

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main2);
        this.textView = (TextView) findViewById(R.id.splash_txt);
        this.imageView = (ImageView) findViewById(R.id.splash_img);
        this.animation = AnimationUtils.loadAnimation((Context) this, R.anim.saed1);
        this.animation1 = AnimationUtils.loadAnimation((Context) this, R.anim.saed2);
        this.textView.setAnimation(this.animation);
        this.imageView.setAnimation(this.animation1);
        (new Handler()).postDelayed((Runnable) () -> {
            Intent intent = new Intent((Context) Main2Activity.this, saed.class);
            Pair pair = new Pair(Main2Activity.this.imageView, "saed1");
            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity) Main2Activity.this, new Pair[]{pair});
            Main2Activity.this.startActivity(intent, activityOptions.toBundle());
        },3000);
    }
}

