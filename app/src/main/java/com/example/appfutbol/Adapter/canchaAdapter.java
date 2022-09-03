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

public class canchaAdapter extends RecyclerView.Adapter<canchaAdapter.ViewHolder> {
    Context context;
    ArrayList<Cancha> canchaArrayList;


    public canchaAdapter(Context context, ArrayList<Cancha> canchaArrayList ) {
        this.context = context;
        this.canchaArrayList = canchaArrayList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.container, parent,false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cancha cancha =  canchaArrayList.get(position);


        holder.name.setText(cancha.getNombre());
        holder.ubicacion.setText(cancha.getUbicacion());
       // holder.description.setText(cancha.getDescripcion());
        //holder.telefono.setText(cancha.getNumero());
        //older.precio.setText(cancha.getPrecio());
        //holder.horario.setText(cancha.getHorarios());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              Intent intent = new Intent(context,ViewDescripsion.class);
              intent.putExtra("nombre", cancha.getNombre());
                intent.putExtra("ubicacion", cancha.getUbicacion());
               // intent.putExtra("horario", cancha.getHorarios());
                intent.putExtra("presio", cancha.getPrecio());
                intent.putExtra("numero", cancha.getNumero());
                intent.putExtra("descripsion", cancha.getDescripcion());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return canchaArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, ubicacion, description, horario, telefono, precio;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tvfristName);
            ubicacion = itemView.findViewById(R.id.tvUbicacion);
            //description = itemView.findViewById(R.id.textvDescrption);
           // horario = itemView.findViewById(R.id.textvHorario);
           // precio = itemView.findViewById(R.id.textvPrecio);
        //    telefono = itemView.findViewById(R.id.textvTelefono);

        }


    }


}
