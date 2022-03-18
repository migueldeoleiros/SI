package es.udc.sistemasinteligentes.g2_14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainEj2b {

    public static void main(String[] args) throws Exception {
        int n = 4;
//        int[][] matriz = {
//            {2,0,0},
//            {0,0,0},
//            {0,0,0},
//        };
        int[][] matriz = {
            {2,8,15,9},
            {14,12,5,0},
            {0,0,0,0},
            {0,0,0,0},
        };
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(n,matriz);
        ProblemaBusqueda cuadrado = new ProblemaCuadradoMagico(estadoInicial);
        Heuristica heuristica = new HeuristicaCuadrado();

        EstrategiaBusquedaInformada buscador = new EstrategiaBusquedaA();

        Nodo[] solucion = buscador.soluciona(cuadrado, heuristica);
        ArrayList<Nodo> listaNodos = new ArrayList<>(Arrays.asList(solucion));
        Collections.reverse(listaNodos);
        for(Nodo i : listaNodos)
            System.out.println(i.toString());
    }
}
