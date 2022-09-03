package com.example.appfutbol.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appfutbol.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ActivityImagen extends AppCompatActivity {
    private ImageView imageView;
    private Button btnSelet, btnLoad;
    private Uri uri;
    private StorageReference storageReference;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen);

/*
        imageView = findViewById(R.id.firebaseImagen);
        btnLoad = findViewById(R.id.uploadImagenbtn);
        btnSelet = findViewById(R.id.selectImagenbtn);


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,100);
            }
        });

        btnSelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.CANADA);
                Date now  = new Date();
                String fileName = format.format(now);


                storageReference  = FirebaseStorage.getInstance().getReference("image/"+fileName);
                storageReference.putFile(uri)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                imageView.setImageURI(null);
                                Toast.makeText(getApplicationContext(),"SUBIDO",Toast.LENGTH_LONG).show();
                                if (dialog.isShowing())
                                    dialog.dismiss();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                if (dialog.isShowing())
                                    dialog.dismiss();
                                Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==100 && data != null && data.getData() != null){
            uri = data.getData();
            imageView.setImageURI(uri);

        }*/
    }
}