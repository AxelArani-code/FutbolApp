package com.example.appfutbol.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.appfutbol.Fragment.BuscarFragment;
import com.example.appfutbol.Fragment.HomeFragment;
import com.example.appfutbol.Fragment.PerfilFragment;
import com.example.appfutbol.R;


import com.example.appfutbol.databinding.ActivityMainBinding;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    //ActivityMainBinding binding;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        remplaceFragment(new HomeFragment());
        //
        actionNavigation();
    }

    private void actionNavigation() {
        binding.bottomNavigationView.setOnItemReselectedListener(item -> {
            switch (item.getItemId()){

                case R.id.Home:
                    remplaceFragment(new HomeFragment());
                    break;
                case  R.id.Buscar:
                    remplaceFragment(new BuscarFragment());
                    break;
                case R.id.Perfil:
                    remplaceFragment(new PerfilFragment());
                    break;
            }

            return ;
        });
    }

    private void remplaceFragment  (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_Layout, fragment);
        fragmentTransaction.commit();

    }
    public void onClick(View view){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = database.getReference("message");
        databaseReference.setValue("HOLA");
    }
}