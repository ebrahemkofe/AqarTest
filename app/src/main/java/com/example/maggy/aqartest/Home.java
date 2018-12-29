package com.example.maggy.aqartest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maggy.aqartest.Fargment.CompoundFragment;

import java.util.Locale;

public class Home extends AppCompatActivity {

    private TextView mTextMessage;
    private FrameLayout frameLayout;
    private long backPressedTime ;

    Button inside,Compound;




    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Toast.makeText(Home.this,"first",Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigation_dashboard:
                    Intent intent = new Intent(getApplicationContext(),PanoramaView.class);
                    startActivity(intent);
                    finish();
                    return true;
                case R.id.navigation_notifications:
                    String geoUri = "http://maps.google.com/maps?q=loc:" + 30.145305 + "," + 31.630784 + " ("+"Shourok"+")";
                    Intent map = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
                    startActivity(map);
                    return true;
                case R.id.navigation_notifications2:
                    Intent contact = new Intent(Intent.ACTION_DIAL);
                    contact.setData(Uri.parse("tel:01033001007"));
                    startActivity(contact);
                    return true;
                case R.id.logout:

                    if (backPressedTime + 2000 > System.currentTimeMillis()) {
                        SharedPreferences myPref = getSharedPreferences("remember", MODE_PRIVATE);
                        SharedPreferences.Editor e = myPref.edit();
                        e.putBoolean("intent",false);
                        e.commit();

                        Intent intent1 = new Intent(getApplicationContext(),Login.class);
                        startActivity(intent1);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "press again to Logout ", Toast.LENGTH_SHORT).show();
                    }

                    backPressedTime = System.currentTimeMillis();


                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        frameLayout=findViewById(R.id.frame);
        BottomNavigationView navigation = findViewById(R.id.navigationBar);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

       inside = findViewById(R.id.inside);
        Compound = findViewById(R.id.Compound);


        Fragment f =new BlankFragment();

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ft.replace(R.id.frame,f,"fra");

        ft.commit();

       inside.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment f =new BlankFragment();

               FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

               ft.replace(R.id.frame,f,"fra");

               ft.commit();

           }
       });
       Compound.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Fragment f =new CompoundFragment();

               FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

               ft.replace(R.id.frame,f,"Compound");

               ft.commit();
           }
       });


    }
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finish();
        } else {
            Toast.makeText(this, "press again to exit ", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
