package com.example.appfutbol.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appfutbol.Model.Cancha;
import com.example.appfutbol.R;
import com.example.appfutbol.Ui.ViewDescripsion;

import java.util.ArrayList;

public class myCanchasAdapter extends RecyclerView.Adapter<myCanchasAdapter.ViewHolder>{


    Context context;
    ArrayList<Cancha> canchaArrayList;

    public myCanchasAdapter(Context context, ArrayList<Cancha> canchaArrayList) {
        this.context = context;
        this.canchaArrayList = canchaArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.car_my_canchas, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cancha cancha =  canchaArrayList.get(position);

        holder.name.setText(cancha.getNombre());
        holder.ubicacion.setText(cancha.getUbicacion());
        holder.telefono.setText(cancha.getNumero());

    }

    @Override
    public int getItemCount() {
        return canchaArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, ubicacion, telefono;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView_UserName);
            ubicacion = itemView.findViewById(R.id.textView_Ubicacion);
              telefono = itemView.findViewById(R.id.textView_Telefono);

        }
    }
}
