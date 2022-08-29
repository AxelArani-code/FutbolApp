package com.example.appfutbol.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.appfutbol.R;
import com.example.appfutbol.Ui.Settings.Information;
import com.example.appfutbol.Ui.Settings.account;
import com.example.appfutbol.Ui.Settings.security;
import com.google.firebase.auth.FirebaseAuth;

public class Seting extends AppCompatActivity {
    Button Cuenta,Seguridad,Informacion,btnLout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seting);
        initializeUI();
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnLout:
                onLout();
                break;
            case R.id.btnBack:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                break;
            case R.id.btnCuenta:
                startActivity(new Intent(getApplicationContext(), account.class));
                break;
            case R.id.btnInformacion:
                startActivity(new Intent(getApplicationContext(), Information.class));
                break;
            case R.id.btnSeguridad:
                startActivity(new Intent(getApplicationContext(), security.class));
                break;

            //
            case R.id.btnBack_Account:
                startActivity(new Intent(getApplicationContext(),Seting.class));
                break;
            case R.id.btnBack_Security:
                startActivity(new Intent(getApplicationContext(),Seting.class));
                break;
            case R.id.btnBack_Information:
                startActivity(new Intent(getApplicationContext(),Seting.class));
                break;

        }
    }
    //Cerrar sesion
    private void onLout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        finish();
    }

    private void initializeUI() {
        Cuenta = findViewById(R.id.btnCuenta);
        Seguridad = findViewById(R.id.btnSeguridad);
        Informacion = findViewById(R.id.btnSeguridad);
    }
}