package com.co.personal.callcenter.factory;

import com.co.personal.callcenter.empleado.Director;
import com.co.personal.callcenter.empleado.Empleado;
import com.co.personal.callcenter.empleado.Operador;
import com.co.personal.callcenter.empleado.Supervisor;

public class FactoryEmpleado {


    public static Empleado getEmpleado(String tipo) {

        if (tipo.equals("Operador")) {

            return new Operador();
        } else if(tipo.equals("Supervisor")){

            return new Supervisor();
        } else {

            return new Director();
        }

    }
}
