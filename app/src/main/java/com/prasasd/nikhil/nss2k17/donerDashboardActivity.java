package com.prasasd.nikhil.nss2k17;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import static android.net.Uri.parse;

public class donerDashboardActivity extends AppCompatActivity {

    private TextView t1;
    private TextView t2;
    private Button callToDonate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doner_dashboard);

        t1=(TextView) findViewById(R.id.bgroup);
        t2=(TextView) findViewById(R.id.contact);
        callToDonate = (Button) findViewById(R.id.callToDonate);



        String t = getIntent().getStringExtra("EXTRA_TITLE");
        String b = getIntent().getStringExtra("EXTRA_BODY");

        t1.setText(t.toString());
        final String number = b.substring(b.lastIndexOf(":") + 1).replaceAll("\"","").toString();
        t2.setText(b.substring(b.lastIndexOf(":") + 1).replaceAll("\"","").toString());

        callToDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = parse("tel:"+number);
                Intent dial = new Intent(Intent.ACTION_DIAL);
                dial.setData(uri);
                startActivity(dial);
            }
        });

    }
}
