package com.example.maggy.aqartest.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maggy.aqartest.R;
import com.example.maggy.aqartest.Models.dataOfFireBase_Model;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class Rejester_activity extends AppCompatActivity {


    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference() ;
    boolean oneTimeClick = true  ;



   private   EditText username , email , password , phonenumber , jopTittle;

   private  String   username1 , email1 , password1, phonenumber1 , jopTittle1;

   dataOfFireBase_Model data1 = new dataOfFireBase_Model() ;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rejester);


        username = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        phonenumber = findViewById(R.id.phone);
        jopTittle = findViewById(R.id.jopTittel);

    }

    public void reject (View view) {

        username1 = username.getText().toString();
        email1 = email.getText().toString();
        jopTittle1 = jopTittle.getText().toString();
        password1 = password.getText().toString();
        phonenumber1 = phonenumber.getText().toString();


        if (username1.isEmpty()) {
            username.setError("Enter username ");
        }
        else if (email1.isEmpty()) {
            email.setError(" Enter your email ");
        }
        else if (password1.isEmpty()) {
            password.setError("Enter the password");
        }
        else if (phonenumber1.isEmpty()) {
            phonenumber.setError("Enter your phone number");
        }

        else {

            if (oneTimeClick) {


                data1.setUsername(username1);
                data1.setEmail(email1);
                data1.setJoptittle(jopTittle1);
                data1.setPassworrd(password1);
                data1.setPhonenumber(phonenumber1);


                ref.child("users").child(String.valueOf(new Random().nextInt())).setValue(data1);

                username.setText("");
                email.setText("");
                phonenumber.setText("");
                jopTittle.setText("");
                password.setText("");

                Toast.makeText(this, "Succssful", Toast.LENGTH_SHORT).show();

                oneTimeClick = false;
                Intent intent = new Intent(this, Login_activity.class);
                startActivity(intent);
                finish();

            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
