package com.example.maggy.aqartest;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class SplashScreen extends Activity {
    MediaPlayer ourSong;
    private int lastAngle = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ImageView image=(ImageView)findViewById(R.id.Logo);
        Thread timer = new Thread()
        {
            public void run() {
                try{
                    sleep(3500);
                } catch(InterruptedException e){
                    e.printStackTrace();
                } finally{
                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                }
            }
        };
        timer.start();
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        finish();
    }
}
