package es.udc.sistemasinteligentes.g2_14;

import java.util.ArrayList;

public class Estrategia4 implements EstrategiaBusqueda {

    public Estrategia4() {
    }

    @Override
    public Nodo[] soluciona(ProblemaBusqueda p) throws Exception{
        ArrayList<Estado> explorados = new ArrayList<Estado>();
        Estado estadoActual = p.getEstadoInicial();
        explorados.add(estadoActual);
        Nodo nodoActual = new Nodo(null, estadoActual, null, null);

        int i = 1;

        System.out.println((i++) + " - Empezando búsqueda en " + estadoActual);

        while (!p.esMeta(estadoActual)){
            System.out.println((i++) + " - " + estadoActual + " no es meta");
            Accion[] accionesDisponibles = p.acciones(estadoActual);
            boolean modificado = false;
            for (Accion acc: accionesDisponibles) {
                Estado sc = p.result(estadoActual, acc);
                System.out.println((i++) + " - RESULT(" + estadoActual + ","+ acc + ")=" + sc);
                if (!explorados.contains(sc)) {
                    estadoActual = sc;
                    System.out.println((i++) + " - " + sc + " NO explorado");
                    explorados.add(estadoActual);
                    modificado = true;
                    System.out.println((i++) + " - Estado actual cambiado a " + estadoActual);
                    nodoActual = new Nodo(nodoActual, estadoActual, acc, null);
                    break;
                }
                else
                    System.out.println((i++) + " - " + sc + " ya explorado");
            }
            if (!modificado) throw new Exception("No se ha podido encontrar una solución");
        }
        System.out.println((i++) + " - FIN - " + estadoActual);
        return reconstruye_sol(nodoActual);
    }

    /**
     * Calcula el camino para llegar a la solucion dado el nodo final
     * @param nodo nodo final
     * @return array con los nodos seguidos para llegar a la solucion
     */
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
