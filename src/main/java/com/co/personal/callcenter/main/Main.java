package com.co.personal.callcenter.main;

import com.co.personal.callcenter.dispatcher.Dispatcher;
import com.co.personal.callcenter.llamada.Llamada;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Main {

    public static void main(String arg[]) {
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
