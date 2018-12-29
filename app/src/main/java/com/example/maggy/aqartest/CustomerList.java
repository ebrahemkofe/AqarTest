package com.example.maggy.aqartest;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CustomerList extends AppCompatActivity {

    ListView listView ;
    Button logOut ;

    ArrayList<dataOfFireBase>dataList = new ArrayList<>();

    DatabaseReference reff = FirebaseDatabase.getInstance().getReference();
    private long backPressedTime;
     Adapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);

        logOut = findViewById(R.id.admin_logout);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences myPref = getSharedPreferences("remember", MODE_PRIVATE);
                SharedPreferences.Editor e = myPref.edit();
                e.putBoolean("intent",false);
                e.commit();

                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
            }
        });





        listView = findViewById(R.id.list_item);
          adapter  = new Adapter(this,R.layout.item_of_list,dataList);


        reff.child("users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    dataList.add(dataSnapshot1.getValue(dataOfFireBase.class));

                }

                adapter.notifyDataSetChanged();


            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        listView.setAdapter(adapter);

    }






    public class Adapter extends ArrayAdapter {

        ArrayList<dataOfFireBase> mlist;

        public Adapter(@NonNull Context context, int resource, @NonNull ArrayList objects) {
            super(context, resource, objects);

            mlist = objects;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInflater.inflate(R.layout.item_of_list, parent, false);


            TextView username = convertView.findViewById(R.id.userNameForList);
            TextView useremail = convertView.findViewById(R.id.useremailForList);
            TextView userphone = convertView.findViewById(R.id.userphoneForList);
            TextView userjop = convertView.findViewById(R.id.userjopForList);

            username.setText(mlist.get(position).getUsername());
            useremail.setText(mlist.get(position).getEmail());
            userphone.setText(mlist.get(position).getPhonenumber());
            userjop.setText(mlist.get(position).getJoptittle());






            return convertView;
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
