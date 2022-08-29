package com.example.appfutbol.Model;

public class User {
    private String fullname, Telefono,email,Location;
    public User() {

    }

    public User(String fullname, String telefono, String email, String location) {
        this.fullname = fullname;
        Telefono = telefono;
        this.email = email;
        Location = location;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
