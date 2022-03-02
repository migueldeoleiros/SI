package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Queue;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda{
    public EstrategiaBusquedaGrafo(){
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<>();
        Queue <Estado> frontera = new PriorityQueue<>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo nodoActual = new Nodo(null,estadoActual,null);
        int i=1;
        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for(Accion acc: accionesDisponibles){
                Estado sc = p.result(estadoActual,acc);
                frontera.add(sc);
            }
            for(Estado e: frontera){
                Accion[] accionesDisp = p.acciones(e);
                for(Accion acc: accionesDisp){
                    Estado sc = p.result(estadoActual, acc);
                    System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                    nodoActual = new Nodo(nodoActual, estadoActual, acc);
                    if (!explorados.contains(sc)) {
                        estadoActual = sc;
                        frontera.remove(sc);
                        System.out.println((i++) + " - " + sc + " NO explorado");
                        explorados.add(estadoActual);
                        modificado = true;
                        System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                        break;
                    }
                    else
                        System.out.println((i++) + " - " + sc + " ya explorado");
                }
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return reconstruye_sol(nodoActual);
    }

    public Nodo[] reconstruye_sol(Nodo nodo) {
        ArrayList<Nodo> solucion = new ArrayList<Nodo>();
        Nodo actual = nodo;
        while(actual != null){
            solucion.add(actual);
            actual = actual.padre;
        }
        return solucion.toArray(new Nodo[0]);
    }
}
