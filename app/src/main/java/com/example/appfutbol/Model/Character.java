package com.example.appfutbol.Model;

public class Character {

    private String key;
    private String nombre_cancha;
    private String ubicacion;
    private String description;
    private int url;

    public Character() {
    }

    public Character(String key, String nombre_cancha, String ubicacion, String description, int url) {
        this.key = key;
        this.nombre_cancha = nombre_cancha;
        this.ubicacion = ubicacion;
        this.description = description;
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNombre_cancha() {
        return nombre_cancha;
    }

    public void setNombre_cancha(String nombre_cancha) {
        this.nombre_cancha = nombre_cancha;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUrl() {
        return url;
    }

    public void setUrl(int url) {
        this.url = url;
    }
}
