package com.example.appfutbol.Model;

import java.io.Serializable;

public class Cancha implements Serializable {
    private  String Id;
    private String nombre;
    private String ubicacion;
    private String precio;
    private String numero;
    private String horarios;
    private String descripcion;
    public Cancha() {

    }
    public Cancha(String id, String nombre, String ubicacion, String precio, String numero, String horarios, String descripcion) {
        Id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.precio = precio;
        this.numero = numero;
        this.horarios = horarios;
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

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
