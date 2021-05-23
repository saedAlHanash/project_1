package com.example.myapplication.UI;

import android.os.Bundle;
import android.view.animation.Animation;
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
//    getWindow().setFlags(1024, 1024);
//    this.textView = (TextView)findViewById(2131362190);
//    this.imageView = (ImageView)findViewById(2131362189);
//    this.animation = AnimationUtils.loadAnimation((Context)this, 2130772010);
//    this.animation1 = AnimationUtils.loadAnimation((Context)this, 2130772011);
//    this.textView.setAnimation(this.animation);
//    this.imageView.setAnimation(this.animation1);
//    final MediaPlayer player = MediaPlayer.create((Context)this, 2131820544);
//    mediaPlayer.start();
//    (new Handler()).postDelayed(new Runnable() {
//          public void run() {
//            Intent intent = new Intent((Context)Main2Activity.this, saed.class);
//            Pair pair = new Pair(Main2Activity.this.imageView, "saed1");
//            ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation((Activity)Main2Activity.this, new Pair[] { pair });
//            Main2Activity.this.startActivity(intent, activityOptions.toBundle());
//            player.stop();
//          }
//        }3000L);
  }
}

