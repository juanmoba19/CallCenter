package com.co.personal.callcenter.dispatcher;


import com.co.personal.callcenter.empleado.Director;
import com.co.personal.callcenter.empleado.Empleado;
import com.co.personal.callcenter.empleado.Operador;
import com.co.personal.callcenter.empleado.Supervisor;
import com.co.personal.callcenter.factory.FactoryEmpleado;
import com.co.personal.callcenter.factory.PersonalCallCenter;
import com.co.personal.callcenter.llamada.Llamada;
import com.co.personal.callcenter.util.Utils;

/**
 * Juan.Molina
 * Clase para manejar la asignacion de las llamadas
 */
public class Dispatcher implements  Runnable{

    private  Llamada llamada;
    private Empleado empleado;
    PersonalCallCenter personalCallCenter = PersonalCallCenter.getInstance();

    public Dispatcher(Llamada llamada) {
        this.llamada = llamada;
    }

    /**
     * Metodo que selecciona el empleado disponible para contestar llamada
     * @return
     * @throws RuntimeException
     */
    public Empleado dispatchCall() throws RuntimeException {

        if(personalCallCenter.getCantEmpleadosOcup() < 10) {
            if(personalCallCenter.getCantOperador() > 0) {
                Operador operador = (Operador) FactoryEmpleado.getEmpleado("Operador");
                operador.setFirstName("Operador" + llamada.getNumLlamada());
                operador.setLlamada(llamada);
                personalCallCenter.disminuirCapOperador();
                personalCallCenter.sumarCantEmpleadosOcupados();
                return operador;
            } else if(personalCallCenter.getCantSupervisor() > 0) {
                Supervisor supervisor = (Supervisor) FactoryEmpleado.getEmpleado("Supervisor");
                supervisor.setFirstName("Supervisor" + llamada.getNumLlamada());
                supervisor.setLlamada(llamada);
                personalCallCenter.sumarCantEmpleadosOcupados();
                personalCallCenter.disminuirCapSupervisor();
                return supervisor;
            } else if (personalCallCenter.getCantDirector() > 0) {
                Director director = (Director) FactoryEmpleado.getEmpleado("Director");
                director.setFirstName("Director" + llamada.getNumLlamada());
                director.setLlamada(llamada);
                personalCallCenter.sumarCantEmpleadosOcupados();
                personalCallCenter.disminuirCapDirector();
                return director;
            }
        } else {
            new RuntimeException("No Hay mas Empleados disponibles");
        }
        return null;
    }

    /**
     * Hilo que ejecuta la accion de contestar llamada
     */
    @Override
    public void run() {

        try {
            empleado = dispatchCall();

            System.out.println("El empleado " + empleado.getFirstName() + " Con Cargo "
                    + empleado.getClass().getSimpleName() + " atiende llamada en el tiempo: "
                    + (System.currentTimeMillis() - empleado.getInitialTime()) / 1000
                    + "seg" + " al usuario: " + llamada.getUsuario());

            Utils util = new Utils();
            int segLlamada = util.generarIntensidadLlamada();
            this.tiempoLlamada(segLlamada);

            System.out.println("El empleado " + empleado.getFirstName()+ " HA TERMINADO LA LLAMADA " +
                    "EN EL TIEMPO: "
                    + (System.currentTimeMillis() - empleado.getInitialTime()) / 1000
                    + "seg");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void tiempoLlamada(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    public Llamada getLlamada() {
        return llamada;
    }

    public void setLlamada(Llamada llamada) {
        this.llamada = llamada;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
