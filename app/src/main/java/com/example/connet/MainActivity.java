package com.example.connet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText emailET;
    private EditText passwordET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailET = findViewById(R.id.editText);
        passwordET= findViewById(R.id.editText2);
        Button loginButton = (Button)findViewById(R.id.button);
        Button registerButton = (Button) findViewById(R.id.button2);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(view);
            }
        });
    }

    // Login
    public void signup(View v){
        //  mAuth = FirebaseAuth.getInstance();

        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        if(email.equalsIgnoreCase("admin") && password.equalsIgnoreCase("admin")) {
        /*
        if((!TextUtils.isEmpty(email))&&(!TextUtils.isEmpty(password))){
            mAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){*/
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
                              /*  finish();
                            }
                            else{
                                Toast.makeText(MainActivity.this,"Email o password errate",Toast.LENGTH_SHORT).show();

                            }
                        }
                    });

        }else if((TextUtils.isEmpty(email))&&(!TextUtils.isEmpty(password))){
            Toast.makeText(MainActivity.this,"Inserisci la mail",Toast.LENGTH_SHORT).show();
        }else if((TextUtils.isEmpty(email))&&(TextUtils.isEmpty(password))){
            Toast.makeText(MainActivity.this,"Inserisci la mail e la password",Toast.LENGTH_SHORT).show();
        }else if((!TextUtils.isEmpty(email))&&(TextUtils.isEmpty(password))){
            Toast.makeText(MainActivity.this,"Inserisci la password",Toast.LENGTH_SHORT).show();
        }

*/
        }else{
        Toast.makeText(MainActivity.this,"Accesso Negato",Toast.LENGTH_SHORT).show();
    }
    }
}