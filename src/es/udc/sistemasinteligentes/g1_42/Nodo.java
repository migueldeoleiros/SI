package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

public class Nodo{
    Nodo padre;
    Estado estado;
    Accion accion;

    public Nodo(Nodo padre, Estado estado, Accion accion) {
        this.padre = padre;
        this.estado = estado;
        this.accion = accion;
    }
}
