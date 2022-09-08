package com.example.appfutbol.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.appfutbol.Model.Cancha;
import com.example.appfutbol.Model.Turnos;
import com.example.appfutbol.R;
import com.example.appfutbol.TurnoModel.TurJueves;
import com.example.appfutbol.TurnoModel.TurLunes;
import com.example.appfutbol.TurnoModel.TurMartes;
import com.example.appfutbol.TurnoModel.TurMiercoles;
import com.example.appfutbol.TurnoModel.TurSabado;
import com.example.appfutbol.TurnoModel.TurViernes;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class LoadTurno extends AppCompatActivity {
    ImageView btn_Publicar, imageView_1, imageView_2,imageView_3,imageView_4,imageView_5,imageView_6;
    LinearLayout layout;
    TextView Numero_Cancha, btn_Cancelar, btn_Sigueinte;
    TextView textNombre;
    EditText N_Turno,Coste_Cancha,Canctidad_Persona;
    TextView textView_Horario_Inicio, textView_Horario_Final, btn_Listo_1,btn_Listo_2,btn_Listo_3,btn_Listo_4,btn_Listo_5,btn_Listo_6;
    Button btn_Horario_Inicio, btn_Horario_Final;
    CheckBox Lunes, Martes, Miercoles, Jueves, Viernes, Sabado;



    private int dia, mes, ano, hora, minutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_turno);

        controllerUI();
        getInstanceController();
        onClick_Crear_Turno();
        getData();



        setOnTouchListener();



    }



    private void setOnTouchListener() {
//Primero
        btn_Listo_1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    getData();
                    layout.setVisibility(View.GONE);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    btn_Publicar.setImageResource(R.drawable.car_view_3);
                    imageView_1.setImageResource(R.drawable.car_view_1);
                    btn_Listo_2.setVisibility(View.VISIBLE);
                }

                return true;
            }
        });

//Segundo
        btn_Listo_2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    getDataMartes();
                    layout.setVisibility(View.GONE);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    imageView_1.setImageResource(R.drawable.car_view_3);
                    imageView_2.setImageResource(R.drawable.car_view_1);
                }

                return true;
            }
        });

        //Trcero
        btn_Listo_3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    getDataMiercoles();
                    layout.setVisibility(View.GONE);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    imageView_2.setImageResource(R.drawable.car_view_3);
                    imageView_3.setImageResource(R.drawable.car_view_1);
                }

                return true;
            }
        });
        //Cuarto
        btn_Listo_4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    getDataJueves();
                    layout.setVisibility(View.GONE);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    imageView_3.setImageResource(R.drawable.car_view_3);
                    imageView_4.setImageResource(R.drawable.car_view_1);
                }

                return true;
            }
        });
        //Quiento
        btn_Listo_5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    getDataViernes();
                    layout.setVisibility(View.GONE);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    imageView_4.setImageResource(R.drawable.car_view_3);
                    imageView_5.setImageResource(R.drawable.car_view_1);
                }

                return true;
            }
        });

        //Secto

        btn_Listo_6.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    getDataSabado();
                    layout.setVisibility(View.GONE);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    imageView_5.setImageResource(R.drawable.car_view_3);
                    imageView_6.setImageResource(R.drawable.car_view_1);
                    btn_Sigueinte.setVisibility(View.VISIBLE);
                }

                return true;
            }
        });


    }

    private void getData() {
        String nombreTurno;
        String horario_Inicio;
        String horario_Final;
        String totalPersonas;
        String costeTurno;
        nombreTurno = N_Turno.getText().toString().trim();
        horario_Inicio = textView_Horario_Inicio.getText().toString().trim();
        horario_Final =textView_Horario_Final.getText().toString().trim();
        totalPersonas = Canctidad_Persona.getText().toString().trim();
        costeTurno = Coste_Cancha.getText().toString().trim();
        ///
        if (nombreTurno.isEmpty()){
            N_Turno.setError("Ingrese el precio");
            N_Turno.requestFocus();
            return;
        }
        if (totalPersonas.isEmpty()){
            Canctidad_Persona.setError("Ingrese el precio");
            Canctidad_Persona.requestFocus();
            return;
        }
        if (costeTurno.isEmpty()){
            Coste_Cancha.setError("Ingrese el precio");
            Coste_Cancha.requestFocus();
            return;
        }
        ///
        if (nombreTurno.equals("")|| totalPersonas.equals("")||costeTurno.equals("")){
            getData();
        }else {
            TurLunes turLunes = new TurLunes();
            turLunes.setNameTurno(nombreTurno);
            turLunes.setCosteTurno(Integer.parseInt(costeTurno));
            turLunes.setCantidadPersona(Integer.parseInt(totalPersonas));
            turLunes.setHoraInicio(horario_Inicio);
            turLunes.setHoraFinal(horario_Final);
            //
            FirebaseDatabase.getInstance().getReference("turnos_Lunes")
                    .child("turno")
                  .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
              .setValue(turLunes);
            //
            clearBox();

        }

    }

    private void getDataMartes() {
        String nombreTurno;
        String horario_Inicio;
        String horario_Final;
        String totalPersonas;
        String costeTurno;
        nombreTurno = N_Turno.getText().toString().trim();
        horario_Inicio = textView_Horario_Inicio.getText().toString().trim();
        horario_Final =textView_Horario_Final.getText().toString().trim();
        totalPersonas = Canctidad_Persona.getText().toString().trim();
        costeTurno = Coste_Cancha.getText().toString().trim();
        ///
        if (nombreTurno.isEmpty()){
            N_Turno.setError("Ingrese el precio");
            N_Turno.requestFocus();
            return;
        }
        if (totalPersonas.isEmpty()){
            Canctidad_Persona.setError("Ingrese el precio");
            Canctidad_Persona.requestFocus();
            return;
        }
        if (costeTurno.isEmpty()){
            Coste_Cancha.setError("Ingrese el precio");
            Coste_Cancha.requestFocus();
            return;
        }
        ///
        if (nombreTurno.equals("")|| totalPersonas.equals("")||costeTurno.equals("")){
            getData();
        }else {
            TurMartes turMartes = new TurMartes();
            turMartes.setNameTurno(nombreTurno);
            turMartes.setCosteTurno(Integer.parseInt(costeTurno));
            turMartes.setCantidadPersona(Integer.parseInt(totalPersonas));
            turMartes.setHoraInicio(horario_Inicio);
            turMartes.setHoraFinal(horario_Final);
            //
            FirebaseDatabase.getInstance().getReference("turnos_Martes")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(turMartes);
            //
            clearBox();

        }

    }


    private void getDataMiercoles() {
        String nombreTurno;
        String horario_Inicio;
        String horario_Final;
        String totalPersonas;
        String costeTurno;
        nombreTurno = N_Turno.getText().toString().trim();
        horario_Inicio = textView_Horario_Inicio.getText().toString().trim();
        horario_Final =textView_Horario_Final.getText().toString().trim();
        totalPersonas = Canctidad_Persona.getText().toString().trim();
        costeTurno = Coste_Cancha.getText().toString().trim();
        ///
        if (nombreTurno.isEmpty()){
            N_Turno.setError("Ingrese el precio");
            N_Turno.requestFocus();
            return;
        }
        if (totalPersonas.isEmpty()){
            Canctidad_Persona.setError("Ingrese el precio");
            Canctidad_Persona.requestFocus();
            return;
        }
        if (costeTurno.isEmpty()){
            Coste_Cancha.setError("Ingrese el precio");
            Coste_Cancha.requestFocus();
            return;
        }
        ///
        if (nombreTurno.equals("")|| totalPersonas.equals("")||costeTurno.equals("")){
            getData();
        }else {
            TurMiercoles turMiercoles = new TurMiercoles();
            turMiercoles.setNameTurno(nombreTurno);
            turMiercoles.setCosteTurno(Integer.parseInt(costeTurno));
            turMiercoles.setCantidadPersona(Integer.parseInt(totalPersonas));
            turMiercoles.setHoraInicio(horario_Inicio);
            turMiercoles.setHoraFinal(horario_Final);
            //
            FirebaseDatabase.getInstance().getReference("turnos_Miercoles")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(turMiercoles);
            //
            clearBox();

        }

    }


    private void getDataJueves() {
        String nombreTurno;
        String horario_Inicio;
        String horario_Final;
        String totalPersonas;
        String costeTurno;
        nombreTurno = N_Turno.getText().toString().trim();
        horario_Inicio = textView_Horario_Inicio.getText().toString().trim();
        horario_Final =textView_Horario_Final.getText().toString().trim();
        totalPersonas = Canctidad_Persona.getText().toString().trim();
        costeTurno = Coste_Cancha.getText().toString().trim();
        ///
        if (nombreTurno.isEmpty()){
            N_Turno.setError("Ingrese el precio");
            N_Turno.requestFocus();
            return;
        }
        if (totalPersonas.isEmpty()){
            Canctidad_Persona.setError("Ingrese el precio");
            Canctidad_Persona.requestFocus();
            return;
        }
        if (costeTurno.isEmpty()){
            Coste_Cancha.setError("Ingrese el precio");
            Coste_Cancha.requestFocus();
            return;
        }
        ///
        if (nombreTurno.equals("")|| totalPersonas.equals("")||costeTurno.equals("")){
            getData();
        }else {
            TurJueves turJueves = new TurJueves();
            turJueves.setNameTurno(nombreTurno);
            turJueves.setCosteTurno(Integer.parseInt(costeTurno));
            turJueves.setCantidadPersona(Integer.parseInt(totalPersonas));
            turJueves.setHoraInicio(horario_Inicio);
            turJueves.setHoraFinal(horario_Final);
            //
            FirebaseDatabase.getInstance().getReference("turnos_Jueves")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(turJueves);
            //
            clearBox();

        }

    }
    private void getDataViernes() {
        String nombreTurno;
        String horario_Inicio;
        String horario_Final;
        String totalPersonas;
        String costeTurno;
        nombreTurno = N_Turno.getText().toString().trim();
        horario_Inicio = textView_Horario_Inicio.getText().toString().trim();
        horario_Final =textView_Horario_Final.getText().toString().trim();
        totalPersonas = Canctidad_Persona.getText().toString().trim();
        costeTurno = Coste_Cancha.getText().toString().trim();
        ///
        if (nombreTurno.isEmpty()){
            N_Turno.setError("Ingrese el precio");
            N_Turno.requestFocus();
            return;
        }
        if (totalPersonas.isEmpty()){
            Canctidad_Persona.setError("Ingrese el precio");
            Canctidad_Persona.requestFocus();
            return;
        }
        if (costeTurno.isEmpty()){
            Coste_Cancha.setError("Ingrese el precio");
            Coste_Cancha.requestFocus();
            return;
        }
        ///
        if (nombreTurno.equals("")|| totalPersonas.equals("")||costeTurno.equals("")){
            getData();
        }else {
            TurViernes turViernes = new TurViernes();
            turViernes.setNameTurno(nombreTurno);
            turViernes.setCosteTurno(Integer.parseInt(costeTurno));
            turViernes.setCantidadPersona(Integer.parseInt(totalPersonas));
            turViernes.setHoraInicio(horario_Inicio);
            turViernes.setHoraFinal(horario_Final);
            //
            FirebaseDatabase.getInstance().getReference("turnos_Viernes")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(turViernes);
            //
            clearBox();

        }

    }
    private void getDataSabado() {
        String nombreTurno;
        String horario_Inicio;
        String horario_Final;
        String totalPersonas;
        String costeTurno;
        nombreTurno = N_Turno.getText().toString().trim();
        horario_Inicio = textView_Horario_Inicio.getText().toString().trim();
        horario_Final =textView_Horario_Final.getText().toString().trim();
        totalPersonas = Canctidad_Persona.getText().toString().trim();
        costeTurno = Coste_Cancha.getText().toString().trim();
        ///
        if (nombreTurno.isEmpty()){
            N_Turno.setError("Ingrese el precio");
            N_Turno.requestFocus();
            return;
        }
        if (totalPersonas.isEmpty()){
            Canctidad_Persona.setError("Ingrese el precio");
            Canctidad_Persona.requestFocus();
            return;
        }
        if (costeTurno.isEmpty()){
            Coste_Cancha.setError("Ingrese el precio");
            Coste_Cancha.requestFocus();
            return;
        }
        ///
        if (nombreTurno.equals("")|| totalPersonas.equals("")||costeTurno.equals("")){
            getData();
        }else {
            TurSabado turSabado = new TurSabado();
            turSabado.setNameTurno(nombreTurno);
            turSabado.setCosteTurno(Integer.parseInt(costeTurno));
            turSabado.setCantidadPersona(Integer.parseInt(totalPersonas));
            turSabado.setHoraInicio(horario_Inicio);
            turSabado.setHoraFinal(horario_Final);
            //
            FirebaseDatabase.getInstance().getReference("turnos_Sabado")
                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                    .setValue(turSabado);
            //
            clearBox();

        }

    }

    private void clearBox() {
        Coste_Cancha.setText("");
        Canctidad_Persona.setText("");
        N_Turno.setText("");
        textView_Horario_Inicio.setText("");
        textView_Horario_Final.setText("");

    }


    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_Turno_Nuevo_1:
                layout.setVisibility(View.VISIBLE);

                break;

            case R.id.btn_Turno_Nuevo_2:
                layout.setVisibility(View.VISIBLE);
                N_Turno.setText("Turno N-2");
                btn_Listo_1.setVisibility(View.GONE);
                btn_Listo_2.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_Turno_Nuevo_3:
                layout.setVisibility(View.VISIBLE);
                N_Turno.setText("Turno N-3");

                btn_Listo_2.setVisibility(View.GONE);
                btn_Listo_3.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_Turno_Nuevo_4:
                layout.setVisibility(View.VISIBLE);
                N_Turno.setText("Turno N-4");
                btn_Listo_3.setVisibility(View.GONE);
                btn_Listo_4.setVisibility(View.VISIBLE);

                break;

            case R.id.btn_Turno_Nuevo_5:
                layout.setVisibility(View.VISIBLE);
                N_Turno.setText("Turno N-5");
                btn_Listo_4.setVisibility(View.GONE);
                btn_Listo_5.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_Turno_Nuevo_6:
                layout.setVisibility(View.VISIBLE);
                N_Turno.setText("Turno N-6");
                btn_Listo_5.setVisibility(View.GONE);
                btn_Listo_6.setVisibility(View.VISIBLE);
                break;




            case R.id.btn_Siguiente:
                Cancha cancha = new Cancha();
                Intent intent = new Intent(getApplicationContext(),InformationTurno.class);
                intent.putExtra("nombre", cancha.getNombre());
                intent.putExtra("descripsion", cancha.getDescripcion());
                intent.putExtra("ubicacion", cancha.getUbicacion());
                intent.putExtra("horarios_Apertura", cancha.getHorarios_Apertura());
                intent.putExtra("horarios_Cierre", cancha.getHorarios_Cierre());
                intent.putExtra("telefono", cancha.getNumero());
                intent.putExtra("presio", cancha.getPrecio());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
                break;


            case R.id.textViewCancelar_Turno:
                layout.setVisibility(View.GONE);
                break;

            case R.id.button_Hora_Apertura:
                horario_Apertura();
                break;
            case R.id.button_Hora_Cierre:
                horario_Cierre();
                break;



        }
    }

    private void horario_Apertura() {
        final Calendar c= Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textView_Horario_Inicio.setText(hourOfDay+":"+minute);
            }
        },hora, minutos,false);
        timePickerDialog.show();
    }

    private void horario_Cierre() {
        final Calendar c= Calendar.getInstance();
        hora = c.get(Calendar.HOUR_OF_DAY);
        minutos = c.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                textView_Horario_Final.setText(hourOfDay+":"+minute);
            }
        },hora, minutos,false);
        timePickerDialog.show();
    }



    private void getInstanceController() {
        textNombre.setText(getIntent().getStringExtra("nombre"));
        textNombre.setText(getIntent().getStringExtra("descripsion"));
        textNombre.setText(getIntent().getStringExtra("ubicacion"));
        textNombre.setText(getIntent().getStringExtra("horarios_Apertura"));
        textNombre.setText(getIntent().getStringExtra("horarios_Cierre"));
        textNombre.setText(getIntent().getStringExtra("telefono"));
        textNombre.setText(getIntent().getStringExtra("presio"));


    }


    private void controllerUI() {

        textNombre = findViewById(R.id.textView_TURNO2);

    }





    private void onClick_Crear_Turno() {
        //Buttunb
        btn_Publicar = findViewById(R.id.btn_Turno_Nuevo_1);
        imageView_1 = findViewById(R.id.btn_Turno_Nuevo_2);
        imageView_2= findViewById(R.id.btn_Turno_Nuevo_3);
        imageView_3 = findViewById(R.id.btn_Turno_Nuevo_4);
        imageView_4 = findViewById(R.id.btn_Turno_Nuevo_5);
        imageView_5 = findViewById(R.id.btn_Turno_Nuevo_6);





        layout = findViewById(R.id.view_carview);
        //Numero_Cancha = findViewById(R.id.textVi);
        btn_Cancelar = findViewById(R.id.textViewCancelar_Turno);

        //

        N_Turno =  findViewById(R.id.editTextTextNombre_Turno);
        Coste_Cancha =  findViewById(R.id.editTextText_Coste_Cancha);
        Canctidad_Persona =  findViewById(R.id.editTextText_Cantidad_Persona);
//Text
        textView_Horario_Inicio = findViewById(R.id.textView_Hora_Apertura);
        textView_Horario_Final =  findViewById(R.id.textView_Hora_Cierre);
        //Buton
        btn_Horario_Inicio = findViewById(R.id.button_Hora_Apertura);
                btn_Horario_Final = findViewById(R.id.button_Hora_Cierre);
                //chekc
        Lunes = findViewById(R.id.checkBox_Lunes);
        Martes= findViewById(R.id.checkBox_Martes);
        Miercoles=  findViewById(R.id.checkBox_Miercoles);
        Jueves=findViewById(R.id.checkBox_Jueves);
        Viernes= findViewById(R.id.checkBox_viernes);
        Sabado=findViewById(R.id.checkBox_Sabado);
//Button de Listo
        btn_Listo_1=findViewById(R.id.btn_Listo_1);
        btn_Listo_2=findViewById(R.id.btn_Listo_2);
        btn_Listo_3=findViewById(R.id.btn_Listo_3);
        btn_Listo_4=findViewById(R.id.btn_Listo_4);
        btn_Listo_5=findViewById(R.id.btn_Listo_5);
        btn_Listo_6=findViewById(R.id.btn_Listo_6);


        btn_Sigueinte=findViewById(R.id.btn_Siguiente);





    }
}