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
//        Queue<Nodo> frontera = new LinkedList<>(); //anchura
        Stack<Nodo> frontera = new Stack<>(); //profundidad
        Nodo nodoActual = new Nodo(null, p.getEstadoInicial(), null);
        frontera.add(nodoActual);
        ArrayList<Estado> explorados = new ArrayList<>();

        int i = 1;
        int nCreados = 1;
        System.out.println((i++) + " - Empezando búsqueda en " + nodoActual.estado);

        while(true) {
            if (frontera.isEmpty())
                throw new Exception("No se ha podido encontrar una solución");
//            nodoActual = frontera.remove(); //anchura
            nodoActual = frontera.pop(); //profundidad
            System.out.println((i++) + " ! Estado actual cambiado a " + nodoActual.estado);
            if (p.esMeta(nodoActual.estado)) break;
            else {
                System.out.println((i++) + " - " + nodoActual.estado + " no es meta");
                explorados.add(nodoActual.estado);
                Accion[] accionesDisponibles = p.acciones(nodoActual.estado);
                for (Accion acc : accionesDisponibles) {
                    Estado sc = p.result(nodoActual.estado, acc);
                    Nodo nodo = new Nodo(nodoActual, sc, acc); nCreados++;
                    System.out.println((i++) + " - RESULT(" + nodoActual.estado + ","+ acc + ")=" +sc);
                    if (!frontera.contains(nodo) && !explorados.contains(sc)) {
                        frontera.add(nodo);
                        System.out.println((i++) + " - " + sc + " NO explorado");
                        System.out.println((i++) + " - Nodo anadido a frontera" + nodo);
                    }
                    else
                        System.out.println((i++) + " - " + sc + " ya explorado");
                }
            }
        }
        System.out.println((i++) + " - FIN - " + nodoActual);
        System.out.println("Nodos expandidos: " + explorados.size());
        System.out.println("Nodos creados: " + nCreados);
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
