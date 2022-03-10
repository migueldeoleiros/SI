package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;

import java.util.Objects;

public class Nodo{
    Nodo padre;
    Estado estado;
    Accion accion;

    @Override
    public String toString() {
        return "(" + estado +
                ", " + accion +
                ')';
    }
    public Nodo(Nodo padre, Estado estado, Accion accion) {
        this.padre = padre;
        this.estado = estado;
        this.accion = accion;
    }    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Nodo)) return false;
        Nodo nodo = (Nodo) o;
        return Objects.equals(estado, nodo.estado);
    }
    @Override
    public int hashCode() {
        return Objects.hash(padre, estado, accion);
    }
}
