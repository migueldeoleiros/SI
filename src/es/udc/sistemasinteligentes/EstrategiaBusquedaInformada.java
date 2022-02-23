package es.udc.sistemasinteligentes;

public interface EstrategiaBusquedaInformada {
    /**
     * Soluciona el problema de búsqueda, obteniendo un estado meta o arrojando una Excepcion si no encuentra una
     * @param p Problema a solucionar
     * @param h Heurística que asigna a un estado un valor de utilidad
     * @return Estado meta obtenido
     */
    public abstract Estado soluciona(ProblemaBusqueda p, Heuristica h) throws Exception;
}
