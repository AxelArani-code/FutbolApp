package com.example.appfutbol.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appfutbol.Model.User;
import com.example.appfutbol.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class RegisterActivity extends AppCompatActivity {
    TextView tieneCuenta;
    ImageView img;
    Button btnRegistrar,btnSubirFoto;
    EditText txtInputUsername, txtInputEmail, txtInputPassword, txtInputTelefono,txtInputLocation;
    ProgressBar mProgressBar;
    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    StorageReference mStorage;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        mStorage = FirebaseStorage.getInstance().getReference();
        initializeUI();
    }
    public void onClick (View view){
        switch (view.getId()){
            case R.id.btnRegister:
               //testDateBase();
                registerNewUser();
                break;
            case R.id.alreadyHaveAccount:
                //startActivity(new Intent(this,LoginActivity.class));
                break;
        }
    }

    private void registerNewUser() {
        String email, password,fullname,Telefono,Location;
        email = txtInputEmail.getText().toString().trim();
        password =txtInputPassword.getText().toString().trim();
        fullname = txtInputUsername.getText().toString().trim();
        Telefono = txtInputTelefono.getText().toString().trim();
        Location = txtInputLocation.getText().toString().trim();

        if (fullname.isEmpty()){
            txtInputUsername.setError("Full name is required");
            txtInputUsername.requestFocus();
            return;
        }
        if (Location.isEmpty()){
            txtInputLocation.setError("Ingrese la localización");
            txtInputLocation.requestFocus();
        }
        if (Telefono.isEmpty()){
            txtInputTelefono.setError("Ingrese Telefono");
            txtInputTelefono.requestFocus();
            return;
        }
        if (email.isEmpty()){
            txtInputEmail.setError("Ingrese Email");
            txtInputEmail.requestFocus();
            return;
        }
        if (! Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtInputEmail.setError("Porfavor ingrese bien el Gmail");
            txtInputEmail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            txtInputPassword.setError("Contrseña es incorrecta");
            txtInputPassword.requestFocus();
            return;
        }
        if (password.length()<5){
            txtInputPassword.setError("Igrese mas caracteres");
            txtInputPassword.requestFocus();
            return;
        }
        //mProgressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    User user = new User( fullname,Telefono,email,Location);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(RegisterActivity.this, "El usuario se ha registrado correctamente", Toast.LENGTH_LONG).show();
                                        //mProgressBar.setVisibility(View.GONE);

                                        //Redirecto to login Latout
                                        //startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                                        ProgressBarStart();

                                    }else {
                                        Toast.makeText(RegisterActivity.this,"Error de registro intentalo", Toast.LENGTH_LONG).show();
                                        //mProgressBar.setVisibility(View.GONE);
                                    }
                                }
                            });
                }else {
                    Toast.makeText(RegisterActivity.this,"Error de registro, Database no funciona espere.", Toast.LENGTH_LONG).show();
                    //mProgressBar.setVisibility(View.GONE);
                }
            }
        });

    }


    private void ProgressBarStart() {
        progressDialog = new ProgressDialog(RegisterActivity.this);

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
    private void initializeUI() {
        txtInputUsername = findViewById(R.id.inputUsername);
        txtInputEmail = findViewById(R.id.inputEmail);
        txtInputPassword = findViewById(R.id.inputPassword);
        txtInputTelefono = findViewById(R.id.inputTelefono);
        txtInputLocation = findViewById(R.id.inputLocation);

        btnRegistrar = findViewById(R.id.btnRegister);
        tieneCuenta =findViewById(R.id.alreadyHaveAccount);


        // mProgressBar = findViewById(R.id.progressBar);

    }
}