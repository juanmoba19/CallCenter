package com.co.personal.callcenter.dispatcher;

import com.co.personal.callcenter.factory.PersonalCallCenter;
import com.co.personal.callcenter.llamada.Llamada;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Prueba Unitaria Junit
 */
public class DispatcherTest {

    Llamada llamada;

    @Before
    public void setUp() {
        llamada = new Llamada("juan", new Date(), 1);
    }

    @Test
    public void dispatchCall_not_null() {
        Dispatcher dispatcher = new Dispatcher(llamada);
        assertNotNull(dispatcher);
    }

    @Test
    public void dispatchCall_asignarOperador() {
        Dispatcher dispatcher = new Dispatcher(llamada);
        dispatcher.run();
        assertEquals("Operador", dispatcher.getEmpleado().getClass().getSimpleName());
    }

    @Test
    public void dispatchCall_asignarSupervisor() {
        Dispatcher dispatcher = new Dispatcher(llamada);
        PersonalCallCenter personalCallCenter = PersonalCallCenter.getInstance();
        personalCallCenter.setCantOperador(0);
        dispatcher.run();
        assertEquals("Supervisor", dispatcher.getEmpleado().getClass().getSimpleName());
    }

    @Test
    public void dispatchCall_asignarDirector() {
        Dispatcher dispatcher = new Dispatcher(llamada);
        PersonalCallCenter personalCallCenter = PersonalCallCenter.getInstance();
        personalCallCenter.setCantOperador(0);
        personalCallCenter.setCantSupervisor(0);
        dispatcher.run();
        assertEquals("Director", dispatcher.getEmpleado().getClass().getSimpleName());
    }

    @Test
    public void dispatchCall_empleados_no_disponibles() throws RuntimeException {
        Dispatcher dispatcher = new Dispatcher(llamada);
        PersonalCallCenter personalCallCenter = PersonalCallCenter.getInstance();
        personalCallCenter.setCantEmpleadosOcup(11);
        dispatcher.dispatchCall();
        assertNull(dispatcher.getEmpleado());
    }

    @Test
    public void ejecutarTestDiezLlamadas() {
        Llamada llamada = new Llamada("Juan", new Date(), 1);
        Llamada llamada1 = new Llamada("Diego", new Date(), 2);
        Llamada llamada2 = new Llamada("Molina", new Date(), 3);
        Llamada llamada3 = new Llamada("Bareno", new Date(), 4);
        Llamada llamada4 = new Llamada("Gabriela", new Date(), 5);
        Llamada llamada5 = new Llamada("Mateo", new Date(), 6);
        Llamada llamada6 = new Llamada("Denny", new Date(), 7);
        Llamada llamada7 = new Llamada("Santiago", new Date(), 8);
        Llamada llamada8 = new Llamada("Jhon", new Date(), 9);
        Llamada llamada9 = new Llamada("Luz", new Date(), 10);
        // Cuando se sobrepasan el numero de llamadas permitidas
        // Llamada llamada10 = new Llamada("Luz", new Date(), 11);

        Stream<Llamada> flujo = Stream.of(llamada, llamada1, llamada2,
                llamada3, llamada4, llamada5, llamada6, llamada7,
                llamada8, llamada9);

        ExecutorService servicio = Executors.newCachedThreadPool();

        flujo.map(t->new Dispatcher(t)).forEach(servicio::execute);
    }
}