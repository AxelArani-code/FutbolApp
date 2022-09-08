package com.example.appfutbol.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.appfutbol.R;

public class InformationTurno extends AppCompatActivity {
TextView Nombre,Ubicacion,Coste,Horaio,Descripsion,Telefono,Turnos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_turno);
        controllerUI();
        getInstanceController();

    }



    private void getInstanceController() {
        Nombre.setText(getIntent().getStringExtra("nombre"));
        Descripsion.setText(getIntent().getStringExtra("descripsion"));
        Ubicacion.setText(getIntent().getStringExtra("ubicacion"));
        Horaio.setText(getIntent().getStringExtra("horarios_Apertura"));
        Horaio.setText(getIntent().getStringExtra("horarios_Cierre"));
        Telefono.setText(getIntent().getStringExtra("telefono"));
        Coste.setText(getIntent().getStringExtra("presio"));
        Log.i("a","hora" + getIntent().getStringExtra("horarios_Apertura"));
        Log.i("a","hora" + getIntent().getStringExtra("horarios_Cierre"));

    }

    private void controllerUI() {
        Nombre = findViewById(R.id.textViewNombre_Info);
        Ubicacion = findViewById(R.id.textViewUbicacionInfo);
        Coste = findViewById(R.id.textViewCosteInfo);
        Horaio = findViewById(R.id.textViewHorarioInfo);
        Descripsion = findViewById(R.id.textViewDescripInfo);
        Telefono = findViewById(R.id.textViewtelefonoInfo);
        Turnos = findViewById(R.id.textViewTurnoInfo);


    }
}