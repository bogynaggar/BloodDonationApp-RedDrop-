package com.prasasd.nikhil.nss2k17;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends AppCompatActivity {
private ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv = (ImageView) findViewById(R.id.logoIcon);
        Animation fade = AnimationUtils.loadAnimation(this,R.anim.mytransition);
        iv.startAnimation(fade);

        final Intent i = new Intent(this,MainActivity.class);
        Thread timer = new Thread(){
          public void run() {
              try{
                  sleep(1000);
              }
              catch (InterruptedException e){
                  e.printStackTrace();
              }
              finally {
                  startActivity(i);
                  finish();
              }
          }
        };
        timer.start();

    }
}
