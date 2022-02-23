package es.udc.sistemasinteligentes;


import java.util.Arrays;
import java.util.function.Predicate;

public abstract class ProblemaBusqueda {
    private Estado estadoInicial;
    public Estado getEstadoInicial() {
        return estadoInicial;
    }

    public ProblemaBusqueda(Estado estadoInicial) {
        this.estadoInicial = estadoInicial;
    }

    /**
     * Indica si el estado "es" constituye una solución al problema
     * @param es Estado a verificar
     * @return True si el estado es meta o false en caso contrario
     */
    public abstract boolean esMeta(Estado es);

    /**
     * Devuelve una lista con las acciones aplicables a un estado dado
     * @param es Estado al que aplicar las acciones
     * @return Lista de acciones aplicables
     */
    public abstract Accion[] acciones(Estado es);

    /**
     * Devuelve el resultado de aplicar una acción sobre un estado, ambos pasados como parámetros
     * @param es Estado sobre el que aplicar la acción
     * @param acc Acción a aplicar
     * @return Estado resultante
     */
    public Estado result(Estado es, Accion acc){
        return acc.aplicaA(es);
    }
}
