package com.example.appfutbol.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appfutbol.R;
import com.example.appfutbol.Ui.Seting;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;


public class PerfilFragment extends Fragment {

    public PerfilFragment() {
        // Required empty public constructor
    }
    ImageView btnSeting;
    Button btnSubirfoto;
    StorageReference mStorage;
    private static final  int GALLERY_INTENT =1 ;
    TextView txtInputUsername, txtInputEmail, txtInputPassword, txtInputTelefono,txtUbicacion,txtUbicacion_1;
    DatabaseReference mDatabase;


    public static PerfilFragment newInstance(String param1, String param2) {
        PerfilFragment fragment = new PerfilFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);
        txtInputEmail = view.findViewById(R.id.userEmail);
        txtInputUsername = view.findViewById(R.id.userName);
        txtInputTelefono = view.findViewById(R.id.userTelefono);
        txtUbicacion = view.findViewById(R.id.txtUbicacion);
        txtUbicacion_1 = view.findViewById(R.id.txtUbicacion_1);

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");
        mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //Obtenemos los datos de FirebaseData
                    String email = snapshot.child("email").getValue().toString();
                    String telefono = snapshot.child("telefono").getValue().toString();
                    String userName = snapshot.child("fullname").getValue().toString();
                    String ubicacion = snapshot.child("location").getValue().toString();
                    //Mostramos los datos
                    txtInputEmail.setText(email);
                    txtInputTelefono.setText(telefono);
                    txtInputUsername.setText(userName);
                    txtUbicacion.setText(ubicacion);
                    txtUbicacion_1.setText(ubicacion);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });





        btnSeting = view.findViewById(R.id.btnSeting);
        btnSeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Seting.class);
                startActivity(intent);
            }
        });
        return view;
    }
}