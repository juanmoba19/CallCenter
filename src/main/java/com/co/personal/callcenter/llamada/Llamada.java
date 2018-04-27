package com.co.personal.callcenter.llamada;

import com.co.personal.callcenter.empleado.Empleado;

import java.util.Date;

public class Llamada {

    private String usuario;
    private Empleado empleado;
    private Date Fecha;
    private int numLlamada;

    public Llamada(String usuario, Date fecha, int numLlamada) {
        this.usuario = usuario;
        this.Fecha = fecha;
        this.numLlamada = numLlamada;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date fecha) {
        Fecha = fecha;
    }

    public int getNumLlamada() {
        return numLlamada;
    }

    public void setNumLlamada(int numLlamada) {
        this.numLlamada = numLlamada;
    }
}
