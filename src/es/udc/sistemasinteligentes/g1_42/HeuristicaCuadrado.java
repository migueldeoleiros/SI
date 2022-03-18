package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.Heuristica;
import es.udc.sistemasinteligentes.g1_42.ProblemaCuadradoMagico.*;

public class HeuristicaCuadrado extends Heuristica {

    @Override
    public float evalua(Estado e) {
        EstadoCuadrado esC = (EstadoCuadrado) e;

        int result = 0;
        int n = esC.getN();
        int[][] cuadrado = esC.getCuadrado();
        int maxN = (n*((n*n)+1))/2;

        int sumd1 = 0,sumd2=0;
        boolean sized1 = true, sized2 = true;
        for (int i = 0; i < n; i++) {
            sumd1 += cuadrado[i][i];
            if (cuadrado[i][i] == 0) sized1 = false;
            sumd2 += cuadrado[i][n-1-i];
            if (cuadrado[i][n-1-i] == 0) sized2 = false;
        }

        result += score(sumd1, maxN, sized1);
        result += score(sumd2, maxN, sized2);

        for (int i = 0; i < n; i++) {
            int rowSum = 0, colSum = 0;
            boolean sizeRow = true, sizedCol = true;
            for (int j = 0; j < n; j++) {
                rowSum += cuadrado[i][j];
                if (cuadrado[i][j] == 0) sizeRow = false;
                colSum += cuadrado[j][i];
                if (cuadrado[j][i] == 0) sizedCol = false;
            }
            result += score(rowSum, maxN, sizeRow);
            result += score(colSum, maxN, sizedCol);

        }

        return result;
    }

    private int score(int sum, int maxN, boolean complete){
        if(sum == maxN && complete) return 0;
        else if(sum < maxN && !complete) return 1;
        else return 1000;
    }

}
