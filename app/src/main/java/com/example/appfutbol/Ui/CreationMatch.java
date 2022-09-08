package com.example.appfutbol.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class CreationMatch extends AppCompatActivity {
    ImageView btn_Back, viewImage;
    ListView mostarDatos, btn_Guardar;
    TextView horarioEntradaEditText,horarioCierre_daEditText;
    EditText nameEditText, ubicacionEditText,descripcionEditText,  telefonoEditText,precioEditText;
    Button  btn_GuardarImage,btn_Hora_Apertura,btn_Hora_Cierre;
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

    private int dia, mes, ano, hora, minutos;
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

            case R.id.button_btn:
                horario_Apertura();

                break;

            case R.id.button_Cierre:
                horario_Cierre();
                break;



        }
    }

    private void horario_Apertura() {
        final Calendar c= Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horarioEntradaEditText.setText(hourOfDay+":"+minute);
            }
        },hora, minutos,false);
        timePickerDialog.show();
    }

    private void horario_Cierre() {
        final Calendar c= Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                horarioCierre_daEditText.setText(hourOfDay+":"+minute);
            }
        },hora, minutos,false);
        timePickerDialog.show();
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
        String nombre;
        String ubicacion;
        String descripcion;
        String horarios_Apertura;
        String horarios_Cierre;
        String telefono;
        String precio;
        nombre =nameEditText.getText().toString().trim();
        ubicacion = ubicacionEditText.getText().toString().trim();
        descripcion = descripcionEditText.getText().toString().trim();
        horarios_Apertura = horarioEntradaEditText.getText().toString().trim();
        horarios_Cierre = horarioCierre_daEditText.getText().toString().trim();
        telefono =telefonoEditText.getText().toString().trim();
        precio = precioEditText.getText().toString().trim();

        if (precio.isEmpty()){
            precioEditText.setError("Ingrese el precio");
            precioEditText.requestFocus();
            return;
        }

       /* if (btnlol.setOnClickListener();){

            //horarioEntradaEditText.setError("Ingrese el horario");
           // horarioEntradaEditText.requestFocus();
           // return;
        }
        if (horarios_Cierre.isEmpty()){
            horarioCierre_daEditText.setError("Ingrese el horario");
            horarioCierre_daEditText.requestFocus();
            return;
        }*/
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

        if (nombre.equals("")|| ubicacion.equals("")||descripcion.equals("")||telefono.equals("")||precio.equals("")){
            matchSave();
        }else{
            Cancha cancha = new Cancha();
            cancha.setNombre(nombre);
            cancha.setDescripcion(descripcion);
            cancha.setUbicacion(ubicacion);
            cancha.setHorarios_Apertura(horarios_Apertura);
            cancha.setHorarios_Cierre(horarios_Cierre);
            cancha.setNumero(Integer.parseInt(telefono));
            cancha.setPrecio(Integer.parseInt(precio));
            //databaseReference.child("canchas").child(cancha.getId()).setValue(cancha);
            FirebaseDatabase.getInstance().getReference("cancha")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(cancha);
            ProgressBarStart();
           // Toast.makeText(this,"Agregado correctamente",Toast.LENGTH_LONG).show();
            Log.i("Enviado_datos_De_Cancha", "Los datos se enviaros a Database");
            //ProgressBarStart();

         //   Intent intent = new Intent(getApplicationContext(),LoadTurno.class);
            // intent.putExtra("nombre", cancha.getNombre());
            //  intent.putExtra("descripsion", cancha.getDescripcion());
            //  intent.putExtra("ubicacion", cancha.getUbicacion());
            //   intent.putExtra("horarios_Apertura", cancha.getHorarios_Apertura());
            //  intent.putExtra("horarios_Cierre", cancha.getHorarios_Cierre());
            //    intent.putExtra("telefono", cancha.getNumero());
            // intent.putExtra("presio", cancha.getPrecio());

            //  intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //   getApplicationContext().startActivity(intent);


            clearBox();
        }
       // Toast.makeText(this,"Error, intente más tarde",Toast.LENGTH_LONG).show();




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

                    startActivity(new Intent(getApplicationContext(), LoadTurno.class));
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
        horarioEntradaEditText.setText("");
        horarioCierre_daEditText.setText("");
        telefonoEditText.setText("");
        precioEditText.setText("");
    }
    private void initializeUI() {
        btn_Back = findViewById(R.id.btn_Back);
        //btn_Guardar = findViewById(R.id.btn_GuardarMat);
        // btn_GuardarImage = findViewById(R.id.btn_GuardaImage);

        //viewImage = findViewById(R.id.imageViewUrl);
        btn_Hora_Apertura = findViewById(R.id.button_btn);
        btn_Hora_Cierre= findViewById(R.id.button_Cierre);
        nameEditText = findViewById(R.id.nameEditText);
        ubicacionEditText = findViewById(R.id.animeEditText);
        descripcionEditText = findViewById(R.id.descriptionEditText);
        horarioEntradaEditText = findViewById(R.id.horarioEntradaEditText);
        horarioEntradaEditText = findViewById(R.id.horarioEntradaEditText);
        horarioCierre_daEditText = findViewById(R.id.horarioCierre_daEditText);
        precioEditText = findViewById(R.id.precioEditText);
        telefonoEditText = findViewById(R.id.telefonoEditText);


        //recyclerView = findViewById(R.id.characterRecyclerView);

        //mostarDatos = findViewById(R.id.lv_datos);
    }

}