package es.udc.sistemasinteligentes.g2_14;

import java.util.ArrayList;

public interface EstrategiaBusqueda {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @return Estado meta obtenido
     */
    Nodo[] soluciona(ProblemaBusqueda p) throws Exception;

}
