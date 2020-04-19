package com.example.connet;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;
/*
public class DeletePhotoActivity extends AppCompatActivity {

    StorageReference storageRef;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    private ImageAdapter imageAdapter;
    private List<Scheda> schede;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        Log.i("user",user+"");
        Toast.makeText(DeletePhotoActivity.this, "Per cancellare la foto, tenerla premuta", Toast.LENGTH_LONG).show();

        schede = new ArrayList<>();
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        db = FirebaseFirestore.getInstance();
        db.collection("schede")
                .whereEqualTo("user",user.getUid().toString())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot document : task.getResult()){
                                Log.i("DELETE FOTO", document.getId()+" "+document.getData());
                                Scheda tmp = document.toObject(Scheda.class);
                                schede.add(tmp);


                            }

                            imageAdapter = new ImageAdapter(DeletePhotoActivity.this,schede,1);
                            recyclerView.setAdapter(imageAdapter);




                        }else{
                            Toast.makeText(DeletePhotoActivity.this,"Fallito",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        String imageUrl = getIntent().getStringExtra("TitleDelete");
        db.collection("schede")
                .whereEqualTo("imageUrl",imageUrl)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()){
                            for(QueryDocumentSnapshot documentSnapshot : task.getResult()){
                                String s = documentSnapshot.getId();
                                String nomeFoto = documentSnapshot.getString("nameFile");
                                Log.i("DELETE ACTIVITY", s+"  "+ nomeFoto);
                                deleteFoto(s, nomeFoto);
                            }
                        }else{
                            Log.e("DELECTE ACTIVITY", "Error getting document ", task.getException());
                            //prova pero devi caricare una nuova foto ed elinare quellaok
                        }

                    }
                });

    }
    //serve il nome della foto tu lo salvi?

    private void deleteFoto(String id, final String nomeFoto){
        //scrivi qua
        // ma qua non ho il databas vai

        db.collection("schede").document(id).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
// dice che è eliminato ma ci sta AGGIIORNA LA PAGINAAAAnon ci sta piu gay, non si puo mettere un refresh?
                    @Override
                    public void onSuccess(Void aVoid) {


                        Log.d("deletephoto", "DocumentSnapshot successfully deleted!");
                        //qui puoi mandare l'intent// che intent? che mi deve riaprire l'activitu?
                        deleteFotoFromStorage(nomeFoto);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("deletephoto", "Error deleting document", e);
                    }
                });
    }

    private void deleteFotoFromStorage(String name){
        StorageReference deleteRef = storageRef.child("schede/"+name);
        deleteRef.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Log.i("DELETE", "Storage foto eliminata");

                finish();
                startActivity(getIntent());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e("DELETE", "errore "+e.toString());
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent a = new Intent(DeletePhotoActivity.this, MainActivityLogin.class);
        startActivity(a);
    }
}*/


