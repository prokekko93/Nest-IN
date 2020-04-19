package com.example.connet;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText nameET;
    private EditText descriptionET;
    private EditText emailET;
    private EditText passwordET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nameET = findViewById(R.id.editName);
        descriptionET = findViewById(R.id.editDescription);
        emailET = findViewById(R.id.editMail);
        passwordET= findViewById(R.id.editPassword);

        Button registerButton = (Button) findViewById(R.id.buttonRegister);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup(view);
            }
        });

        Button annullButton = (Button) findViewById(R.id.buttonAnnull);
        annullButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                annull();
            }
        });


    }

    public void annull()
    {
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        startActivity(intent);
    }

    //nuova registrazione
    public void signup(View view){

        mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();

        String email = emailET.getText().toString();
        String password = passwordET.getText().toString();
        String name = emailET.getText().toString();
        String description = passwordET.getText().toString();

        if((!TextUtils.isEmpty(emailET.getText().toString()))&&(!TextUtils.isEmpty(passwordET.getText().toString())) &&
                (!TextUtils.isEmpty(nameET.getText().toString()))&&(!TextUtils.isEmpty(descriptionET.getText().toString()))) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "Registrazione avvenuta", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Utente già registrato", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }else if(TextUtils.isEmpty(nameET.getText().toString())){
            Toast.makeText(RegisterActivity.this,"Inserire il nome",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(descriptionET.getText().toString())){
            Toast.makeText(RegisterActivity.this,"Inserire la descrizione",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(emailET.getText().toString())){
            Toast.makeText(RegisterActivity.this,"Inserire la mail",Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(passwordET.getText().toString())){
            Toast.makeText(RegisterActivity.this,"Inserire la password",Toast.LENGTH_SHORT).show();
        }
    }
}