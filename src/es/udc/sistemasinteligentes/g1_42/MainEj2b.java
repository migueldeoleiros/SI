package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainEj2b {

    public static void main(String[] args) throws Exception {
        int[][] matriz = {
            {2,9,4},
            {7,5,3},
            {0,0,0},
        };
//        int[][] matriz = {
//            {2,8,15,9},
//            {0,0,5,3},
//            {0,0,0,0},
//            {0,0,0,0},
//        };
        ProblemaCuadradoMagico.EstadoCuadrado estadoInicial = new ProblemaCuadradoMagico.EstadoCuadrado(4,matriz);
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
