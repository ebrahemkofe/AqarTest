package com.example.maggy.aqartest;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Random;

public class Login extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference() ;
     boolean r = false ;

     CheckBox checkBox ;

    private   EditText getemail , password ;

    String prefPass , prefEmail ;

    dataOfFireBase data2 = new dataOfFireBase() ;

    private long backPressedTime ;
    boolean intent ; 


    String emailOfuser ;
    String passOfUser ;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

       getemail = findViewById(R.id.getEmail);
       password = findViewById(R.id.getPassword);
       checkBox= findViewById(R.id.rememberMe);
       
       
       

       SharedPreferences pref = getSharedPreferences("remember", 0);
       prefEmail =     pref.getString("email" , "");
       prefPass =  pref.getString("password" , "");
       intent = pref.getBoolean("intent",false );
       
       if (intent==true && prefEmail.equals("yossefEmad0000@gmail.com")){


           Intent intent = new Intent(getApplicationContext() , CustomerList.class );
           startActivity(intent);
           finish();
           Toast.makeText(this, prefEmail, Toast.LENGTH_SHORT).show();
       }else if(intent==true){

           Intent intent = new Intent(getApplicationContext() , Home.class );
           startActivity(intent);
           finish();
           Toast.makeText(this, prefEmail, Toast.LENGTH_SHORT).show();
       }




    }

    public void signUP(View view) {
        Intent i = new Intent (this , Rejester.class);
        startActivity(i);
        finish();
    }

    public void login(View view) {



        emailOfuser  = getemail.getText().toString();
         passOfUser = password.getText().toString();


        final ArrayList<dataOfFireBase> list = new ArrayList<>();


        ref.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                list.add(dataSnapshot1.getValue(dataOfFireBase.class));

                }

              for (int x = 0 ; x <list.size() ; x++){

                    if(  list.get(x).getEmail().equals(emailOfuser) && list.get(x).getPassworrd().equals(passOfUser) ){

                         r =  true ;

                        if (  checkBox.isChecked()){

                            SharedPreferences myPref = getSharedPreferences("remember", MODE_PRIVATE);
                            SharedPreferences.Editor e = myPref.edit();
                            e.putString("email", getemail.getText().toString());
                            e.putString("password" , password.getText().toString());
                            e.putBoolean("intent",true);

                            e.commit();

                        }

                       Intent intent = new Intent(getApplicationContext() , Home.class );
                        startActivity(intent);
                        Toast.makeText(Login.this, emailOfuser, Toast.LENGTH_SHORT).show();

                        finish();

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
                if (r == false){
                    Toast.makeText(Login.this, " Check email or password ", Toast.LENGTH_SHORT).show();

                }
            }
        }, 5000);


      Admin();

    }






    public void Admin (){
        if(emailOfuser.equals("yossefEmad0000@gmail.com")&&passOfUser.equals("123456789") )
        {

            if (  checkBox.isChecked()){

                SharedPreferences myPref = getSharedPreferences("remember", MODE_PRIVATE);
                SharedPreferences.Editor e = myPref.edit();
                e.putString("email", getemail.getText().toString());
                e.putString("password" , password.getText().toString());
                e.putBoolean("intent",true);

                e.commit();

            }

             r=true ;
            
            Intent intent = new Intent(getApplicationContext() , CustomerList.class );
            startActivity(intent);
            finish();

        }


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
