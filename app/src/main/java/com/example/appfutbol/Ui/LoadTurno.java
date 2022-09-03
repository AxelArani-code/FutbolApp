package com.example.appfutbol.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.appfutbol.R;

public class LoadTurno extends AppCompatActivity {
    ImageView btn_Publicar;
    LinearLayout View;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_turno);
        btn_Publicar = findViewById(R.id.imageView8);
        View = findViewById(R.id.view_carview);

        btn_Publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View.setVisibility(android.view.View.VISIBLE);
            }
        });

    }
}