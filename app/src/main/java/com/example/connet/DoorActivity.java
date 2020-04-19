package com.example.connet;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;

import com.example.connet.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DoorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_door);

        Button buttonEnter = (Button)findViewById(R.id.buttonEnter);
        Button buttonExit = (Button) findViewById(R.id.buttonExit);
        Button buttonLogout = (Button) findViewById(R.id.buttonAnnull);

        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoorActivity.this, RegisterClientActivity.class);
                intent.putExtra("flag",true);
                startActivity(intent);
            }
        });

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DoorActivity.this, RegisterClientActivity.class);
                intent.putExtra("flag",false);
                startActivity(intent);
            }
        });

   //     buttonLogout.setOnClickListener({});

    }

}
