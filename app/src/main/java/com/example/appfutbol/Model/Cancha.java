package com.example.appfutbol.Model;

import java.io.Serializable;

public class Cancha implements Serializable {
    private String Id;
    private String nombre;
    private String ubicacion;
    private String precio;
    private String numero;
    private String horarios_Apertura;
    private String horarios_Cierre;
    private String descripcion;
    public Cancha() {

    }

    public Cancha(String id, String nombre, String ubicacion, String precio, String numero, String horarios_Apertura, String horarios_Cierre, String descripcion) {
        Id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.numero = numero;
        this.horarios_Apertura = horarios_Apertura;
        this.horarios_Cierre = horarios_Cierre;
        this.descripcion = descripcion;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getHorarios_Apertura() {
        return horarios_Apertura;
    }

    public void setHorarios_Apertura(String horarios_Apertura) {
        this.horarios_Apertura = horarios_Apertura;
    }

    public String getHorarios_Cierre() {
        return horarios_Cierre;
    }

    public void setHorarios_Cierre(String horarios_Cierre) {
        this.horarios_Cierre = horarios_Cierre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
