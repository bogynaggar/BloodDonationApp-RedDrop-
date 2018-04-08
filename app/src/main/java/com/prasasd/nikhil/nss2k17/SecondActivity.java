package com.prasasd.nikhil.nss2k17;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.HashMap;

public class SecondActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

   /* Button button;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;


*/

   private Button findDoner;
   private Button beDoner;

    private ArrayList<String> mUserId = new ArrayList<>();

    private DrawerLayout mDrawer;
    private ActionBarDrawerToggle mToggle;
    private TextView donersNum;
    private TextView requestNum;
    FirebaseAuth mAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    DatabaseReference mDatabase,nDatabase;

    String value;

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }


   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_second);
       mAuth = FirebaseAuth.getInstance();
       mDrawer = (DrawerLayout) findViewById(R.id.drawer);
       mToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.Open, R.string.Close);
       mDrawer.addDrawerListener(mToggle);
       mToggle.syncState();
       String name;
       String email;
       Uri photoUrl;


       getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       NavigationView navigationView = (NavigationView) findViewById(R.id.navigation_view);
       navigationView.setNavigationItemSelectedListener(this);
       NavigationView navigationView1 = (NavigationView) findViewById(R.id.navigation_view);
       View header = navigationView.getHeaderView(0);
       TextView nav_user = (TextView) header.findViewById(R.id.navUser);
       TextView nav_username = (TextView) header.findViewById(R.id.navUsername);
       ImageView img_user = (ImageView) header.findViewById(R.id.navImg);
       donersNum = (TextView) findViewById(R.id.numOfDoners);
       requestNum = (TextView) findViewById(R.id.numOfreq);
       mDatabase = FirebaseDatabase.getInstance().getReference().child("Doners");
       mDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               int dcount=0;
               for(DataSnapshot donerSnapshot : dataSnapshot.getChildren()){
                   dcount++;
               }
                donersNum.setText(String.valueOf(dcount).toString());
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });
       mDatabase = FirebaseDatabase.getInstance().getReference().child("Message");
       mDatabase.addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               int rcount=0;
               for(DataSnapshot donerSnapshot : dataSnapshot.getChildren()){
                   rcount++;
               }
               requestNum.setText(String.valueOf(rcount).toString());
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {

           }
       });


           FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
       if (user != null) {
           // Name, email address, and profile photo Url
           name = user.getDisplayName();
           email = user.getEmail();
           photoUrl = user.getPhotoUrl();

           nav_user.setText(email);
           nav_username.setText(name);
           img_user.setImageURI(null);
           img_user.setImageURI(photoUrl);

           // Check if user's email is verified
           boolean emailVerified = user.isEmailVerified();

           // The user's ID, unique to the Firebase project. Do NOT use this value to
           // authenticate with your backend server, if you have one. Use
           // FirebaseUser.getToken() instead.
           String uid = user.getUid();
           mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
           HashMap<String, String> dataMap = new HashMap<String, String>();
           dataMap.put("Name", name);
           dataMap.put("Email", email);
           mDatabase.child(uid).setValue(dataMap);

       }


       mAuthListener = new FirebaseAuth.AuthStateListener() {
           @Override
           public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
               if (firebaseAuth.getCurrentUser() == null) {
                   startActivity(new Intent(SecondActivity.this, MainActivity.class));
               }
           }
       };


       findDoner = (Button) findViewById(R.id.button3);
       beDoner = (Button) findViewById(R.id.button2);



           beDoner.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {

                   mAuth = FirebaseAuth.getInstance();
                   FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                       Intent activity = new Intent(SecondActivity.this, beADonerActivity.class);
                       SecondActivity.this.startActivity(activity);
                   }
                   else
                    {
                        Toast.makeText(SecondActivity.this,"Already A doner",Toast.LENGTH_SHORT).show();
                    }

               }
           });

           findDoner.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent activity = new Intent(SecondActivity.this, FindAdonerActivity.class);
                   SecondActivity.this.startActivity(activity);
               }
           });


   }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       if(mToggle.onOptionsItemSelected(item))
       {
           return true;
       }

       return super.onOptionsItemSelected(item);

    }






    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();



        if(id == R.id.log)
       {

           mAuth.signOut();

       }

       return false;
    }
}
