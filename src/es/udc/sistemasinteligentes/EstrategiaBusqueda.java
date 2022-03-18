package es.udc.sistemasinteligentes;

import es.udc.sistemasinteligentes.g1_42.Nodo;

public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @return Estado meta obtenido
     */
    Nodo[] soluciona(ProblemaBusqueda p) throws Exception;
}
