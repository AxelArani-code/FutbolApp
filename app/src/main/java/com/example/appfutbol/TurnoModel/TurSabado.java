package com.example.appfutbol.TurnoModel;

public class TurSabado {
    private String id;
    private String nameTurno;
    private int costeTurno;
    private int cantidadPersona;
    private String dia;
    private String horaInicio;
    private String horaFinal;

    public TurSabado() {
        this.id = id;
        this.nameTurno = nameTurno;
        this.costeTurno = costeTurno;
        this.cantidadPersona = cantidadPersona;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFinal = horaFinal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameTurno() {
        return nameTurno;
    }

    public void setNameTurno(String nameTurno) {
        this.nameTurno = nameTurno;
    }

    public int getCosteTurno() {
        return costeTurno;
    }

    public void setCosteTurno(int costeTurno) {
        this.costeTurno = costeTurno;
    }

    public int getCantidadPersona() {
        return cantidadPersona;
    }

    public void setCantidadPersona(int cantidadPersona) {
        this.cantidadPersona = cantidadPersona;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(String horaFinal) {
        this.horaFinal = horaFinal;
    }
}
