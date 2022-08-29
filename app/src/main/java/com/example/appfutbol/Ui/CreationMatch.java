package com.example.appfutbol.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.appfutbol.Adapter.canchaAdapter;
import com.example.appfutbol.Model.Cancha;
import com.example.appfutbol.R;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CreationMatch extends AppCompatActivity {
    ImageView btn_Back, viewImage;
    ListView mostarDatos;
    EditText nameEditText, ubicacionEditText,descripcionEditText, horarioEditText, telefonoEditText,precioEditText;
    Button btn_Guardar, btn_GuardarImage;
    FirebaseAuth mAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    ProgressDialog progressDialog;
    //
    private List<Cancha> canchaList = new ArrayList<>();
    ArrayAdapter<Cancha> arrayAdapter ;
    //
    canchaAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    //
    private Uri imageUri;
    private String myUri;
    private StorageTask uploadTask;
    private StorageReference storageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_match);
        initializeUI();
    }


    public  void onClick(View view){

        switch (view.getId()){
            case R.id.btn_GuardarMat:
                matchSave();
                break;
            case R.id.btn_GuardaImage:
                updateImage();
                break;


        }
    }

    private void updateImage() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Set your profeile ");
        progressDialog.setMessage("Porfavor, configure bien el movil");
        progressDialog.show();

        if (imageUri!= null){
            final  StorageReference fileRef = storageReference
                    .child(mAuth.getCurrentUser().getUid()+".jpg");

            uploadTask = fileRef.putFile(imageUri);

            uploadTask.continueWithTask(new Continuation() {
                @Override
                public Object then(@NonNull @NotNull Task task) throws Exception {

                    if (!task.isSuccessful()){
                        throw  task.getException();
                    }
                    return fileRef.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull @NotNull Task task) {
                    if (task.isSuccessful()){
                        Uri downloadUri= (Uri) task.getResult();
                        myUri = downloadUri.toString();

                        HashMap<String, Object> userMap = new HashMap<>();
                        userMap.put("image", myUri);

                        databaseReference.child(mAuth.getCurrentUser().getUid()).updateChildren(userMap);
                        progressDialog.dismiss();
                    }
                }
            });
        }else {
            progressDialog.dismiss();
            Toast.makeText(this,"Imagen nor Selected", Toast.LENGTH_SHORT).show();
        }
    }

/*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK && data!= null){
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            imageUri = result.getUri();

            viewImage.setImageURI(imageUri);
        }else {
            Toast.makeText(this,"Error", Toast.LENGTH_SHORT).show();
        }
    }*/


    private void matchSave() {
        String nombre, ubicacion, descripcion, horario,telefono, precio;
        nombre =nameEditText.getText().toString().trim();
        ubicacion = ubicacionEditText.getText().toString().trim();
        descripcion = descripcionEditText.getText().toString().trim();
        horario = horarioEditText.getText().toString().trim();
        telefono =telefonoEditText.getText().toString().trim();
        precio = precioEditText.getText().toString().trim();

        if (precio.isEmpty()){
            precioEditText.setError("Ingrese el precio");
            precioEditText.requestFocus();
            return;
        }

        if (horario.isEmpty()){
            horarioEditText.setError("Ingrese el horario");
            horarioEditText.requestFocus();
            return;
        }
        if (telefono.isEmpty()){
            telefonoEditText.setError("Ingrese el numero de telefono");
            telefonoEditText.requestFocus();
            return;
        }

        if (nombre.isEmpty()){
            nameEditText.setError("Ingrese el nombre");
            nameEditText.requestFocus();
            return;
        }
        if (ubicacion.isEmpty()){
            ubicacionEditText.setError("Ingrese Ubicación");
            ubicacionEditText.requestFocus();
            return;
        }
        if (descripcion.isEmpty()){
            descripcionEditText.setError("Ingrese alguna descripción");
            descripcionEditText.requestFocus();
            return;
        }

        if (nombre.equals("")|| ubicacion.equals("")||descripcion.equals("")||horario.equals("")||telefono.equals("")||precio.equals("")){
            matchSave();
        }else{
            Cancha cancha = new Cancha();
            cancha.setNombre(nombre);
            cancha.setDescripcion(descripcion);
            cancha.setUbicacion(ubicacion);
            cancha.setHorarios(horario);
            cancha.setNumero(telefono);
            cancha.setPrecio(precio);
            //databaseReference.child("canchas").child(cancha.getId()).setValue(cancha);
            FirebaseDatabase.getInstance().getReference("cancha")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(cancha);
            Toast.makeText(this,"Agregado correctamente",Toast.LENGTH_LONG).show();
            ProgressBarStart();
            clearBox();
        }
        Toast.makeText(this,"Error, intente más tarde",Toast.LENGTH_LONG).show();
    }


    private void ProgressBarStart() {
        progressDialog = new ProgressDialog(CreationMatch.this);

        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);

        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent
        );

        Thread timer = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(2500);
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    progressDialog.dismiss();
                    super.run();
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        };
        timer.start();
    }
    private void clearBox() {
        nameEditText.setText("");
        ubicacionEditText.setText("");
        descripcionEditText.setText("");
        horarioEditText.setText("");
        telefonoEditText.setText("");
        precioEditText.setText("");
    }
    private void initializeUI() {
        btn_Back = findViewById(R.id.btn_Back);
        btn_Guardar = findViewById(R.id.btn_GuardarMat);
        btn_GuardarImage = findViewById(R.id.btn_GuardaImage);

        viewImage = findViewById(R.id.imageViewUrl);

        nameEditText = findViewById(R.id.nameEditText);
        ubicacionEditText = findViewById(R.id.animeEditText);
        descripcionEditText = findViewById(R.id.descriptionEditText);
        horarioEditText = findViewById(R.id.horarioEditText);
        precioEditText = findViewById(R.id.precioEditText);
        telefonoEditText = findViewById(R.id.telefonoEditText);

        //recyclerView = findViewById(R.id.characterRecyclerView);

        //mostarDatos = findViewById(R.id.lv_datos);
    }

}