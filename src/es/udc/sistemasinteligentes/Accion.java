package es.udc.sistemasinteligentes;

public abstract class Accion {
    @Override
    public abstract String toString();

    /**
     * Determina si un estado cumple las precondiciones de la acción
     * @param es Estado que se consulta
     * @return True si "es" cumple las precondiciones y false en caso contrario
     */
    public abstract boolean esAplicable(Estado es);

    /**
     * Devuelve el estado resultante de aplicar la acción al estado "es"
     * @param es Estado sobre el que se aplica la acción
     * @return Estado resultante
     */
    public abstract Estado aplicaA(Estado es);

    /**
     * Devuelve el coste de aplicar la acción, que, por defecto, será uno.
     * @return Coste de aplicar la acción
     */
    public float getCoste(){ return 1; }
}
