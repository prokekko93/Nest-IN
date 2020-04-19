package com.example.connet;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;
//import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddPhotoActivity extends AppCompatActivity {

    private Uri outputUri;
    final static int ACTIVITY_START_CAMERA_APP= 1;
    private static final int PICK_IMAGE_REQUEST = 0;
    private ImageView imageView;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private FirebaseFirestore db;
    private String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_photo);
        imageView = (ImageView) findViewById(R.id.imageView);
        storageReference = FirebaseStorage.getInstance().getReference("schede");
        databaseReference = FirebaseDatabase.getInstance().getReference("schede");
        db = FirebaseFirestore.getInstance();

        if(Build.VERSION.SDK_INT>=23){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }

    }
    public void addPhoto(View view){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, ACTIVITY_START_CAMERA_APP);
    }

    public void takePhoto (View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
            super.onActivityResult(requestCode,resultCode,data);
            Log.i("DATA"," "+data);

        if(requestCode == ACTIVITY_START_CAMERA_APP && resultCode == RESULT_OK && data!=null){

                Bundle bundle = data.getExtras();
                Bitmap mImageBitmap =(Bitmap)bundle.get("data");
                imageView.setImageBitmap(mImageBitmap);
                outputUri = data.getData();

            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            mImageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(),mImageBitmap, "Title", null);
            outputUri = Uri.parse(path);

        }else if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK&&data!=null && data.getData()!=null){
            outputUri=data.getData();
            Picasso.with(this).load(outputUri).into(imageView);
        }
    }

    // caricamento della foto e della scheda
    public void upload(View view){
        final EditText titleET = (EditText) findViewById(R.id.titlePhoto);
        final EditText descriptionET = (EditText)findViewById(R.id.descriptionPhoto);
        final CheckBox checkBoxET = (CheckBox)findViewById(R.id.checkBox);
        data = new SimpleDateFormat("_MM_dd_yyyy_HH_mm_ss", Locale.getDefault()).format(Calendar.getInstance().getTime());
        final boolean flag;

        //flag per sapere la visualizzazione della foto
        if(checkBoxET.isChecked()){flag=true;}
        else{flag=false;}

        if((!TextUtils.isEmpty(titleET.getText().toString()))&&(!TextUtils.isEmpty(descriptionET.getText().toString()))&&(outputUri!=null)){

            final String nomeTmp = "Image_"+data+".jpg";
            final StorageReference imageReference = storageReference.child(nomeTmp);
            imageReference.putFile(outputUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(AddPhotoActivity.this,"Caricamento avvenuto",Toast.LENGTH_SHORT).show();

                            Task<Uri> urlTask = taskSnapshot.getStorage().getDownloadUrl();
                            while(!urlTask.isSuccessful());
                            Uri downloadUrl = urlTask.getResult();

                            FirebaseAuth mAuth =FirebaseAuth.getInstance();
                            FirebaseUser currentUser= mAuth.getCurrentUser();

                            Scheda scheda = new Scheda(currentUser.getUid(),titleET.getText().toString(),descriptionET.getText().toString(),downloadUrl.toString(), nomeTmp,flag);
                            db.collection("schede").add(scheda);
                            Intent intent = new Intent(AddPhotoActivity.this,MainActivity.class);
                            startActivity(intent);

                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(AddPhotoActivity.this,e.getMessage(),Toast.LENGTH_LONG).show();
                        }
                    });


        }



    }

}