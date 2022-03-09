package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.EstrategiaBusqueda;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.*;

public class EstrategiaBusquedaGrafo implements EstrategiaBusqueda{

    public EstrategiaBusquedaGrafo(){
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        Queue<Nodo> frontera = new LinkedList<>();
        Estado estadoActual = p.getEstadoInicial();
        Nodo nodoActual = new Nodo(null, estadoActual, null);
        frontera.add(nodoActual);
        ArrayList<Estado> explorados = new ArrayList<>();

        int i = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while(true) {
            if (frontera.isEmpty())
                throw new Exception("No se ha podido encontrar una solución");

            nodoActual = frontera.remove();
            System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);

            if (p.esMeta(estadoActual)) {
                return reconstruye_sol(nodoActual);
            } else {
                System.out.println((i++) + " - " + estadoActual + " no es meta");
                explorados.add(nodoActual.estado);

                Accion[] accionesDisponibles = p.acciones(nodoActual.estado);
                for (Accion acc : accionesDisponibles) {
                    Estado sc = p.result(estadoActual, acc);
                    nodoActual = new Nodo(nodoActual, sc, acc);
                    System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" +sc);
                    if (!frontera.contains(nodoActual) && !explorados.contains(sc)) {
                        frontera.add(nodoActual);
                        System.out.println((i++) + " - " + sc + " NO explorado");
                        estadoActual = sc;
                        System.out.println((i++) + " - Nodo anadido a frontera" + estadoActual);
                    }else
                        System.out.println((i++) + " - " + sc + " ya explorado");

                }
            }
        }
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
