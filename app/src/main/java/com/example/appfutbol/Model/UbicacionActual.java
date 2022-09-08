package com.example.appfutbol.Model;

public class UbicacionActual {
    private double Longitud;
    private double Latitud;

    public UbicacionActual(double longitud, double latitud) {
        Longitud = longitud;
        Latitud = latitud;
    }

    public double getLongitud() {
        return Longitud;
    }

    public void setLongitud(double longitud) {
        Longitud = longitud;
    }

    public double getLatitud() {
        return Latitud;
    }

    public void setLatitud(double latitud) {
        Latitud = latitud;
    }
}
