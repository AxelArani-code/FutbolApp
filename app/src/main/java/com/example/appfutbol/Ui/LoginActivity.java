package com.example.appfutbol.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfutbol.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private EditText txtLoginEmail, txtLoginPassword;
    private Button btnLogin;
    private TextView lblCrearCuenta,TxtReset, textError;
    private ProgressBar progressBa;
    private ProgressDialog progressDialog;
    private   FirebaseAuth mAuth;
    private String error_DataBase = "Error en la base de datos, espere...";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        initializeUI();
        TxtReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText resetMail = new EditText(v.getContext());
                final AlertDialog.Builder passwordResertDialog = new AlertDialog.Builder(v.getContext());
                passwordResertDialog.setTitle("Reset Password");
                passwordResertDialog.setMessage("Enter your mail to recived reser Link");
                passwordResertDialog.setView(resetMail);

                passwordResertDialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail = resetMail.getText().toString();
                        mAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(LoginActivity.this,"Reset Link sent to tour email",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this,"Error reset link is not sant  "+e.getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                passwordResertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordResertDialog.create().show();
            }
        });
    }

    private void initializeUI() {
        txtLoginEmail = findViewById(R.id.LoginEmail);
        txtLoginPassword = findViewById(R.id.LoginPassword);
        btnLogin = findViewById(R.id.btnlogin);
        lblCrearCuenta = findViewById(R.id.Registrarse);
        TxtReset = findViewById(R.id.forgotPassword);
        textError = findViewById(R.id.textViewError);
        //progressBa = findViewById(R.id.progressBar);

    }

    public void onClick (View view){
        switch (view.getId()){
            case R.id.btnlogin:
                loginUserAccount();
                break;

            case R.id.Registrarse:
                startActivity(new Intent(this,RegisterActivity.class));
                break;

        }
    }

    private void loginUserAccount() {

        String email, password;
        email = txtLoginEmail.getText().toString().trim();
        password =txtLoginPassword.getText().toString().trim();

        if (email.isEmpty()){
            txtLoginEmail.setError("Email es erroneo");
            txtLoginEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtLoginEmail.setError("Porfavor Ingrese Email");
            txtLoginEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            txtLoginPassword.setError("Contrseña incorecta");
            txtLoginPassword.requestFocus();
            return;
        }
        if (password.length()<5){
            txtLoginPassword.setError("Ingrese mas caracteres");
            txtLoginPassword.requestFocus();
            return;
        }
        //progressBa.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            //Toast.makeText(LoginActivity.this, "Iniciando, espere...", Toast.LENGTH_LONG).show();
                            //progressBa.setVisibility(View.GONE);
                            ProgressBarStart();

                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Registro falla, verifique "+ task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            //progressBa.setVisibility(View.GONE);
                            ProgressBarStartError();

                        }
                    }
                });
    }



    private void ProgressBarStart() {
        startActivity(new Intent(LoginActivity.this,ViewLoad.class));
    }
    private void  ProgressBarStartError(){
        startActivity(new Intent(LoginActivity.this,ViewError.class));
        //textError.setText(error_DataBase);
        //Para poder, por ejemplo enviar el datos de errores tendria que armar una clase con un array o hacer un metodo con json, en donde traigo los erroes, pero y asi enviarlos a la otra vista
    }

}