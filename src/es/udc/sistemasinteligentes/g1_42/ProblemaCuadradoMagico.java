package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public static class EstadoCuadrado extends Estado {
        int n;
        int[][] cuadrado = new int[n][n];

        public EstadoCuadrado(int[][] cuadrado) {
            this.cuadrado = cuadrado;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            for(int i=0;i<n;i++) {
                for (int j = 0; j < n; j++) {
                    str.append(cuadrado[i][j]).append(" ");
                }
                str.append("\n");
            }
            return str.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof EstadoCuadrado that)) return false;
            return n == that.n && Arrays.deepEquals(cuadrado, that.cuadrado);
        }

        @Override
        public int hashCode() {
            int result = Objects.hash(n);
            result = 31 * result + Arrays.deepHashCode(cuadrado);
            return result;
        }
    }

    public static class AccionCuadrado extends Accion{
        int x;
        int y;
        int num;

        public AccionCuadrado(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("(").append("(").append(x).append(",").append(y).append(")").append(num).append(")");

            return str.toString();
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadrado esC = (EstadoCuadrado)es;
            return esC.cuadrado[x][y] == 0;
        }

        @Override
        public Estado aplicaA(Estado es) {
            int[][] matriz = ((EstadoCuadrado) es).cuadrado;

            matriz[x][y] = num;

            return new EstadoCuadrado(matriz);
        }
    }

    public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial) {
        super(estadoInicial);
    }

    public Accion[] acciones(Estado es){
        EstadoCuadrado esC = (EstadoCuadrado) es;
        ArrayList<Accion> listaAcciones = new ArrayList<>();
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();

        for (int i = 0; i < esC.n; i++) {
            for (int j = 0; j < esC.n; j++) {
                listA.add(esC.cuadrado[i][j]);
            }
        }
        for(int i=1;i<=(esC.n^2);i++){
            for(Integer j : listA){
                if(!listA.contains(i))
                    listB.add(i);
            }
        }

        for (int i = 0; i < esC.n; i++) {
            for (int j = 0; j < esC.n; j++) {
                if(esC.cuadrado[i][j] == 0){
                    for(Integer item : listB){
                        Accion a = new AccionCuadrado(i,j,item);
                        listaAcciones.add(a);
                    }
                }
            }
        }

        return listaAcciones.toArray(new Accion[0]);
    }

    @Override
    public boolean esMeta(Estado es) {
        EstadoCuadrado esC = (EstadoCuadrado) es;

        int sumd1 = 0,sumd2=0;
        for (int i = 0; i < esC.n; i++) {
            sumd1 += esC.cuadrado[i][i];
            sumd2 += esC.cuadrado[i][esC.n-1-i];
        }
        if(sumd1!=sumd2) //compara que las diagonales sean iguales
            return false;

        for (int i = 0; i < esC.n; i++) {
            int rowSum = 0, colSum = 0;
            for (int j = 0; j < esC.n; j++) {
                rowSum += esC.cuadrado[i][j];
                colSum += esC.cuadrado[j][i];
            }
            if (rowSum != colSum || colSum != sumd1)
                return false;
        }
        return true;
    }
}
