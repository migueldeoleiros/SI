package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;

public class Nodo implements Comparable<Nodo>{
    Nodo padre;
    Estado estado;
    Accion accion;
    float coste;
    float f;

    public Nodo(Nodo padre, Estado estado, Accion accion, Heuristica heuristica) {
        this.padre = padre;
        this.estado = estado;
        this.accion = accion;
        if(padre != null){
            this.coste = padre.coste + accion.getCoste();
            if(heuristica != null)
                this.f = this.coste + heuristica.evalua(estado);
        }
    }

    @Override
    public String toString() {
        return "(" + estado +
                ", " + accion +
                ", " + coste +
                ", " + f +
                ')';
    }

    @Override
    public int compareTo(Nodo nodo) {
        return nodo.f < this.f ? 1 : -1;
    }
}
