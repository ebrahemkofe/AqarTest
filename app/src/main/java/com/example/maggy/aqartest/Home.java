package com.example.maggy.aqartest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private TextView mTextMessage;
    private FrameLayout frameLayout;
    private long backPressedTime ;

    Button inside;




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
                    Toast.makeText(Home.this,"third",Toast.LENGTH_LONG).show();
                    return true;
                case R.id.navigation_notifications2:
                    Toast.makeText(Home.this,"four",Toast.LENGTH_LONG).show();
                    return true;
                case R.id.logout:
                    SharedPreferences myPref = getSharedPreferences("remember", MODE_PRIVATE);
                    SharedPreferences.Editor e = myPref.edit();
                    e.putBoolean("intent",false);
                    e.commit();

                    Intent intent1 = new Intent(getApplicationContext(),Login.class);
                    startActivity(intent1);
                    finish();
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
