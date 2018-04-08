package com.prasasd.nikhil.nss2k17;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class FindAdonerActivity extends AppCompatActivity {

    String msg ;
    private EditText phone;
    private Button confirm;
    private ListView doners;
    List<Doners> mDoner = new ArrayList<>();
    Spinner bloodGroup;
    private DatabaseReference mDatabase,nDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_adoner);
        phone = (EditText) findViewById(R.id.phoneSender);
        confirm = (Button) findViewById(R.id.confirm);
        doners = (ListView) findViewById(R.id.doners);
        bloodGroup = (Spinner) findViewById(R.id.bloodGroups);




        final Date currentTime = Calendar.getInstance().getTime();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Message");
        nDatabase = FirebaseDatabase.getInstance().getReference().child("Doners");


        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                nDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        mDoner.clear();

                        for(DataSnapshot donerSnapshot : dataSnapshot.getChildren()){
                            Doners doner = donerSnapshot.getValue(Doners.class);

                            if(doner.getDonerBloodGroup().equals(bloodGroup.getSelectedItem()))
                            { mDoner.add(doner);}
                               // Toast.makeText(getApplicationContext(),"TEST PASS : LHS="+doner.getDonerBloodGroup().toString()+" || RHS="+bloodGroup.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();}
                            else
                                {}
                                    //Toast.makeText(getApplicationContext(),"TEST FAIL : LHS="+doner.getDonerBloodGroup().toString()+" || RHS="+bloodGroup.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();}

                        DonerList adapter = new DonerList(FindAdonerActivity.this, mDoner);
                        doners.setAdapter(adapter);
                    }}

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });




        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(bloodGroup.getSelectedItem().equals("O+")) {
                    msg="Opositive";
                }
                else if(bloodGroup.getSelectedItem().equals("A+")) {
                    msg="Apositive";
                }
                else if(bloodGroup.getSelectedItem().equals("B+")) {
                    msg="Bpositive";
                }
                else if(bloodGroup.getSelectedItem().equals("AB+")) {
                    msg="ABpositive";
                }
                else if(bloodGroup.getSelectedItem().equals("A-")) {
                    msg="Anegative";
                }
                else if(bloodGroup.getSelectedItem().equals("B-")) {
                    msg="Bnegative";
                }
                else if(bloodGroup.getSelectedItem().equals("O-")) {
                    msg="Onegative";
                }
                else if(bloodGroup.getSelectedItem().equals("AB-")) {
                    msg="ABnegative";
                }
                String mid = mDatabase.push().getKey();
                Message message = new Message(mid,msg,phone.getText().toString());
                mDatabase.child(mid).setValue(message);

                Toast.makeText(getApplicationContext(),"Waiting for doner response",Toast.LENGTH_SHORT).show();
                Intent activity = new Intent(FindAdonerActivity.this,requestDashboard.class);
                activity.putExtra("msg", "doner");
                FindAdonerActivity.this.startActivity(activity);
            }
        });


    }
}
