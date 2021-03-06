package es.udc.sistemasinteligentes.g2_14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainEj2a {

    public static void main(String[] args) throws Exception {
        int[][] matriz = {
            {4,9,2},
            {3,5,0},
            {0,1,0},
        };
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(3,matriz);
        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);

        EstrategiaBusqueda buscador = new EstrategiaBusquedaGrafo();

        Nodo[] solucion = buscador.soluciona(cuadrado);
        ArrayList<Nodo> listaNodos = new ArrayList<>(Arrays.asList(solucion));
        Collections.reverse(listaNodos);
        for(Nodo i : listaNodos)
            System.out.println(i.toString());
    }
}
