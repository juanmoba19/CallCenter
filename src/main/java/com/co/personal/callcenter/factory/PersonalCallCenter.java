package com.co.personal.callcenter.factory;
import com.co.personal.callcenter.empleado.Empleado;
import com.co.personal.callcenter.util.Constants;

import java.util.ArrayList;

public class PersonalCallCenter {

    private int cantDirector = Constants.CANT_DIRECTOR;
    private int cantOperador = Constants.CANT_OPERADOR;
    private int cantSupervisor = Constants.CANT_SUPERVISOR;
    private int cantEmpleadosOcup = 0;
    private static PersonalCallCenter INSTANCE = null;

    private PersonalCallCenter() {

    }

    private synchronized  static void createInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PersonalCallCenter();
        }
    }

    public static PersonalCallCenter getInstance() {
        if (INSTANCE == null) createInstance();
        return INSTANCE;
    }

    public int getCantDirector() {
        return cantDirector;
    }

    public void setCantDirector(int cantDirector) {
        this.cantDirector = cantDirector;
    }

    public int getCantOperador() {
        return cantOperador;
    }

    public void setCantOperador(int cantOperador) {
        this.cantOperador = cantOperador;
    }

    public int getCantSupervisor() {
        return cantSupervisor;
    }

    public void setCantSupervisor(int cantSupervisor) {
        this.cantSupervisor = cantSupervisor;
    }

    public int getCantEmpleadosOcup() {
        return cantEmpleadosOcup;
    }

    public void setCantEmpleadosOcup(int cantEmpleadosOcup) {
        this.cantEmpleadosOcup = cantEmpleadosOcup;
    }

    public void disminuirCapOperador() {
        this.cantOperador--;
    }

    public void disminuirCapSupervisor() {
        this.cantSupervisor--;
    }

    public void disminuirCapDirector() {
        this.cantDirector--;
    }

    public void sumarCantEmpleadosOcupados() {
        this.cantEmpleadosOcup++;
    }
}
