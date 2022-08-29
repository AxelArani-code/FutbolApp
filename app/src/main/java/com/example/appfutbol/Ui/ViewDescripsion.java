package com.example.appfutbol.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.appfutbol.Fragment.HomeFragment;
import com.example.appfutbol.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ViewDescripsion extends AppCompatActivity {
    TextView textNombre,textUbicacion,textPresio,textHorario,textTelefono,textDescripsion;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_descripsion);

        controllerUI();
        getInstanceController();
    }
    public void onClick (View view){
        Intent intent = new Intent(this,HomeFragment.class);
        startActivity(intent);
    }

    private void getInstanceController() {
        textUbicacion.setText(getIntent().getStringExtra("ubicacion"));
        textNombre.setText(getIntent().getStringExtra("nombre"));
        textHorario.setText("Horario : " + getIntent().getStringExtra("horario"));
        textTelefono.setText("Numero : " + getIntent().getStringExtra("numero"));
        textPresio.setText("Presio : " + getIntent().getStringExtra("presio"));
        textDescripsion.setText(getIntent().getStringExtra("descripsion"));
    }

    private void controllerUI() {
        textUbicacion = findViewById(R.id.textView_Ubicacion);
        textNombre = findViewById(R.id.textView_Nombre);
        textDescripsion = findViewById(R.id.textView_Descripsion_1);
        textPresio = findViewById(R.id.textView16);
        textTelefono = findViewById(R.id.textView15);
        textHorario = findViewById(R.id.textView14);
    }
}