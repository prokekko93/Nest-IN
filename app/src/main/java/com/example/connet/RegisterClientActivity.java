package com.example.connet;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;
import com.google.firebase.ml.vision.text.RecognizedLanguage;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterClientActivity extends AppCompatActivity {

    final static int ACTIVITY_START_CAMERA_APP= 1;
    EditText textCodFisc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        boolean flag = getIntent().getExtras().getBoolean("flag");
        String data = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()).format(Calendar.getInstance().getTime());

        textCodFisc = findViewById(R.id.editCodFisc);
        TextView textOrario = findViewById(R.id.textOrario);
        textOrario.setText(data);


        Button buttonCamera = (Button)findViewById(R.id.buttonCamera);
        Button buttonSave = (Button) findViewById(R.id.buttonSave);



        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterClientActivity.this, DoorActivity.class);
                startActivity(intent);
            }
        });
    }

    public void addPhoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ACTIVITY_START_CAMERA_APP);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        Log.i("DATA"," "+data);

        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK && data!=null){
            Bundle bundle = data.getExtras();
            Bitmap mImageBitmap =(Bitmap)bundle.get("data");
            FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(mImageBitmap);
            FirebaseVisionTextRecognizer detector = FirebaseVision.getInstance().getOnDeviceTextRecognizer();
            detector.processImage(image).addOnSuccessListener(new OnSuccessListener<FirebaseVisionText>() {
                        @Override
                        public void onSuccess(FirebaseVisionText firebaseVisionText) {
                            processResultText(firebaseVisionText);
                        }
                    })
                    .addOnFailureListener(
                            new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast toast=Toast. makeText(getApplicationContext(),"ERROR ACQUISITION",Toast. LENGTH_SHORT);
                                    toast. setMargin(50,50);
                                    toast. show();
                                    //addPhoto();
                                }
                            });

        }else{
            Toast toast=Toast. makeText(getApplicationContext(),"Male",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
        }
    }

    protected void processResultText(@NotNull FirebaseVisionText result)
    {
        if(result.getTextBlocks().size() == 0)
        {
            Toast toast=Toast. makeText(getApplicationContext(),"AQUISIZIONE NON RIUSCITA",Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
        }
        else {
            String resultText = result.getText();
            String patternString = "[a-zA-Z]{6}[0-9]{2}[a-zA-Z][0-9]{2}[a-zA-Z][0-9]{3}[a-zA-Z]";
            String codiceFiscale;
            Pattern pattern = Pattern.compile(patternString);

            Matcher matcher = pattern.matcher(resultText);
            if(matcher.find())
            {
                codiceFiscale = matcher.group(0);
            }
            else
            {
                codiceFiscale = "Codice Fiscale Non trovata";
            }

            textCodFisc.setText(codiceFiscale, EditText.BufferType.EDITABLE);
            Toast toast=Toast. makeText(getApplicationContext(),codiceFiscale,Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
            /*
            Log.d("Testo Totale",resultText);
            Toast toast=Toast. makeText(getApplicationContext(),resultText,Toast. LENGTH_SHORT);
            toast. setMargin(50,50);
            toast. show();
            for (FirebaseVisionText.TextBlock block: result.getTextBlocks()) {

                String blockText = block.getText();
                Log.d("BLOCKTEST",blockText);
                Float blockConfidence = block.getConfidence();
                List<RecognizedLanguage> blockLanguages = block.getRecognizedLanguages();

                Point[] blockCornerPoints = block.getCornerPoints();
                Rect blockFrame = block.getBoundingBox();
                for (FirebaseVisionText.Line line: block.getLines()) {
                    String lineText = line.getText();
                    Log.d("LINE",lineText);
                    Float lineConfidence = line.getConfidence();
                    List<RecognizedLanguage> lineLanguages = line.getRecognizedLanguages();
                    Point[] lineCornerPoints = line.getCornerPoints();
                    Rect lineFrame = line.getBoundingBox();
                    for (FirebaseVisionText.Element element: line.getElements()) {
                        String elementText = element.getText();
                        Log.d("ELEMENT",elementText);
                        Float elementConfidence = element.getConfidence();
                        List<RecognizedLanguage> elementLanguages = element.getRecognizedLanguages();
                        Point[] elementCornerPoints = element.getCornerPoints();
                        Rect elementFrame = element.getBoundingBox();
                    }
                }
            }*/
        }
    }
}

/*
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
 */
