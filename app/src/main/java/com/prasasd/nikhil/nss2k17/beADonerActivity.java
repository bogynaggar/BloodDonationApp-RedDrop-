package com.prasasd.nikhil.nss2k17;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.prasasd.nikhil.nss2k17.Doners;

public class beADonerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button register;
    EditText phoneNumber;
    Spinner bloodGroup;
    String item;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_adoner);



        phoneNumber = (EditText) findViewById(R.id.phoneNumber);
        bloodGroup = (Spinner) findViewById(R.id.bloodGroups);
        Toast.makeText(getApplicationContext(),"blood"+bloodGroup.getSelectedItemId(),Toast.LENGTH_SHORT).show();

        /*bloodGroup.setOnItemSelectedListener(this);

        List<String> list = new ArrayList<String>();
        list.add("A+");
        list.add("O+");
        list.add("B+");
        list.add("AB+");
        list.add("A-");
        list.add("B-");
        list.add("O-");
        list.add("AB-");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(dataAdapter);
        */



        register = (Button) findViewById(R.id.button4);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(bloodGroup.getSelectedItem().toString().toUpperCase() == "O+") {
                    FirebaseMessaging.getInstance().subscribeToTopic("Opositive");
                }
                else if(bloodGroup.getSelectedItem().toString().toUpperCase() == "A+") {
                    FirebaseMessaging.getInstance().subscribeToTopic("Apositive");
                }
                else if(bloodGroup.getSelectedItem().toString().toUpperCase() == "B+") {
                    FirebaseMessaging.getInstance().subscribeToTopic("Bpositive");
                }
                else if(bloodGroup.getSelectedItem().toString().toUpperCase() == "AB+") {
                    FirebaseMessaging.getInstance().subscribeToTopic("ABpositive");
                }
                else if(bloodGroup.getSelectedItem().toString().toUpperCase() == "A-") {
                    FirebaseMessaging.getInstance().subscribeToTopic("Anegative");
                }
                else if(bloodGroup.getSelectedItem().toString().toUpperCase() == "B-") {
                    FirebaseMessaging.getInstance().subscribeToTopic("Bnegative");
                }
                else if(bloodGroup.getSelectedItem().toString().toUpperCase() == "O-") {
                    FirebaseMessaging.getInstance().subscribeToTopic("Onegative");
                }
                else if(bloodGroup.getSelectedItem().toString().toUpperCase() == "AB-") {
                    FirebaseMessaging.getInstance().subscribeToTopic("ABnegative");
                }
                Toast.makeText(getApplicationContext(),"blood"+bloodGroup.getSelectedItemId(),Toast.LENGTH_SHORT).show();

                //FirebaseMessaging.getInstance().subscribeToTopic(String.valueOf(bloodGroup.getSelectedItemId()));

                String uid;
                String name;
                String email;

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                if (user != null) {
                    // Name, email address, and profile photo Url
                    name = user.getDisplayName();
                    email = user.getEmail();
                    uid = user.getUid();
                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Doners");
                    Doners doner = new Doners(uid,name,bloodGroup.getSelectedItem().toString(),phoneNumber.getText().toString());
                    mDatabase.child(uid).setValue(doner);

                    Toast.makeText(getApplicationContext(),"YOU ARE NOW A SUPER-HERO",Toast.LENGTH_SHORT).show();




                    Intent activity = new Intent(beADonerActivity.this,SecondActivity.class);
                    activity.putExtra("msg", "doner");
                    beADonerActivity.this.startActivity(activity);


                }


    }});
    }






    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        item = parent.getItemAtPosition(position).toString();
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }



}
