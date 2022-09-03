package com.example.appfutbol.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.appfutbol.Adapter.canchaAdapter;
import com.example.appfutbol.Model.Cancha;
import com.example.appfutbol.R;
import com.example.appfutbol.Ui.ActivityImagen;
import com.example.appfutbol.Ui.CreationMatch;
import com.example.appfutbol.Ui.ViewDescripsion;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }
     ImageView btn_CrearCancha;
    canchaAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    RecyclerView recyclerView;
    private List<Cancha> canchaList = new ArrayList<>();



    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        btn_CrearCancha = view.findViewById(R.id.btn_CrearCancha);
        btn_CrearCancha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CreationMatch.class));

            }
        });

        recyclerView = view.findViewById(R.id.characterRecyclerView);
        recycler();

        return view;
    }

    private void recycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        canchaList = new ArrayList<>();
        adapter = new canchaAdapter(getContext(), (ArrayList<Cancha>) canchaList);
        recyclerView.setAdapter(adapter);
        mostrarDatos();
    }

    private void moveToDescripsion(Cancha item) {
        Intent intent = new Intent(getContext(), View.class);
        intent.putExtra("cancha", item);
        startActivity(intent);
    }

    private void mostrarDatos() {
        FirebaseDatabase.getInstance().getReference("cancha").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Cancha cancha = dataSnapshot.getValue(Cancha.class);
                    canchaList.add(cancha);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}