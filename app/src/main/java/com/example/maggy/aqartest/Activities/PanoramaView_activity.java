package com.example.maggy.aqartest.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.example.maggy.aqartest.R;
import com.gjiazhe.panoramaimageview.GyroscopeObserver;
import com.gjiazhe.panoramaimageview.PanoramaImageView;

public class PanoramaView_activity extends AppCompatActivity {


    private GyroscopeObserver gyroscopeObserver;

//    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panorama_view);

        gyroscopeObserver = new GyroscopeObserver();
        gyroscopeObserver.setMaxRotateRadian(Math.PI/9);

        PanoramaImageView panoramaImageView =  findViewById(R.id.panorama);
        panoramaImageView.setGyroscopeObserver(gyroscopeObserver);

    }

    @Override
    protected void onResume() {
        super.onResume();
        gyroscopeObserver.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gyroscopeObserver.unregister();
    }

    @Override
    public void onBackPressed() {
        Intent intent1 = new Intent(getApplicationContext(),Home_activity.class);
        startActivity(intent1);
        finish();
    }
}
