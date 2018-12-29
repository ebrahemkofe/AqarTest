package com.example.maggy.aqartest.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maggy.aqartest.R;
import com.example.maggy.aqartest.Models.dataOfFireBase_Model;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login_activity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference();
    boolean r = false;

    CheckBox checkBox;

    private EditText getemail, password;

    String prefPass, prefEmail;

    dataOfFireBase_Model data2 = new dataOfFireBase_Model();

    private long backPressedTime;
    boolean intent;


    String emailOfuser;
    String passOfUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getemail = findViewById(R.id.getEmail);
        password = findViewById(R.id.getPassword);
        checkBox = findViewById(R.id.rememberMe);


        SharedPreferences pref = getSharedPreferences("remember", 0);
        prefEmail = pref.getString("email", "");
        prefPass = pref.getString("password", "");
        intent = pref.getBoolean("intent", false);

        if (intent == true && prefEmail.equals("yossefEmad0000@gmail.com")) {


            Intent intent = new Intent(getApplicationContext(), CustomerList_activity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, prefEmail, Toast.LENGTH_SHORT).show();
        } else if (intent == true) {

            Intent intent = new Intent(getApplicationContext(), Home_activity.class);
            startActivity(intent);
            finish();
            Toast.makeText(this, prefEmail, Toast.LENGTH_SHORT).show();
        }


    }

    public void signUP(View view) {
        Intent i = new Intent(this, Rejester_activity.class);
        startActivity(i);
        finish();
    }

    public void login(View view) {


        emailOfuser = getemail.getText().toString();
        passOfUser = password.getText().toString();


        final ArrayList<dataOfFireBase_Model> list = new ArrayList<>();


        ref.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    list.add(dataSnapshot1.getValue(dataOfFireBase_Model.class));

                }

                for (int x = 0; x < list.size(); x++) {

                    if (list.get(x).getEmail().equals(emailOfuser) && list.get(x).getPassworrd().equals(passOfUser)) {

                        r = true;

                        if (checkBox.isChecked()) {

                            SharedPreferences myPref = getSharedPreferences("remember", MODE_PRIVATE);
                            SharedPreferences.Editor e = myPref.edit();
                            e.putString("email", getemail.getText().toString());
                            e.putString("password", password.getText().toString());
                            e.putBoolean("intent", true);

                            e.commit();

                        }

                        Intent intent = new Intent(Login_activity.this, Home_activity.class);
                        startActivity(intent);
                        Toast.makeText(Login_activity.this, emailOfuser, Toast.LENGTH_SHORT).show();

                        break;
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (r == false) {
                    Toast.makeText(Login_activity.this, " Check email or password ", Toast.LENGTH_SHORT).show();

                }
            }
        }, 5000);


        Admin();

    }


    public void Admin() {
        if (emailOfuser.equals("yossefEmad0000@gmail.com") && passOfUser.equals("123456789")) {

            if (checkBox.isChecked()) {

                SharedPreferences myPref = getSharedPreferences("remember", MODE_PRIVATE);
                SharedPreferences.Editor e = myPref.edit();
                e.putString("email", getemail.getText().toString());
                e.putString("password", password.getText().toString());
                e.putBoolean("intent", true);

                e.apply();

            }

            r = true;

            Intent intent = new Intent(this, CustomerList_activity.class);
            startActivity(intent);

        }


    }

    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            finishAffinity();
        } else {
            Toast.makeText(this, "press again to exit ", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }
}
