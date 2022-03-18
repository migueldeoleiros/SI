package es.udc.sistemasinteligentes.g2_14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class ProblemaCuadradoMagico extends ProblemaBusqueda {
    public static class EstadoCuadrado extends Estado {
        private final int n;
        private final int[][] cuadrado;

        /**
         * Constructor del estadoCuadrado
         * @param n número de filas y columnas
         * @param cuadrado matriz con los valores de cada casilla
         */
        public EstadoCuadrado(int n, int[][] cuadrado) {
            this.n = n;
            this.cuadrado = cuadrado;
        }

        /**
         * @return número de filas y columnas
         */
        public int getN() {
            return n;
        }

        /**
         * @return matriz con los valores de cada casilla
         */
        public int[][] getCuadrado() {
            return cuadrado;
        }

        @Override
        public String toString() {
            StringBuilder str = new StringBuilder();
            str.append("{");
            for(int i=0;i<n;i++) {
                str.append("{");
                for (int j = 0; j < n; j++) {
                    str.append(cuadrado[i][j]).append(",");
                }
                str.append("}");
            }
            str.append("}");
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

        /**
         * @param x coordenada x
         * @param y coordenada y
         * @param num numero a introducir
         */
        public AccionCuadrado(int x, int y, int num) {
            this.x = x;
            this.y = y;
            this.num = num;
        }

        @Override
        public String toString() {
            return "(" + "(" + x + "," + y + ")" + num + ")";
        }

        @Override
        public boolean esAplicable(Estado es) {
            EstadoCuadrado esC = (EstadoCuadrado) aplicaA(es);

            if (((EstadoCuadrado)es).cuadrado[x][y] != 0) return false;

            int num = esC.getN();
            int maxN = (num*((num*num)+1))/2;

            int sumd1 = 0,sumd2=0;
            int rowSum = 0, colSum = 0;
            for (int i = 0; i < esC.n; i++) {
                if (maxN < (sumd1 += esC.cuadrado[i][i])) return false;
                if (maxN < (sumd2 += esC.cuadrado[i][esC.n-1-i])) return false;
                if (maxN < (rowSum += esC.cuadrado[x][i])) return false;
                if (maxN < (colSum += esC.cuadrado[i][y])) return false;
            }
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado esC = ((EstadoCuadrado) es);
            int[][] matriz = new int[esC.n][esC.n];

            for(int i = 0 ; i < esC.n ; i++)
                System.arraycopy(esC.cuadrado[i], 0, matriz[i], 0, esC.n);

            matriz[x][y] = num;

            return new EstadoCuadrado(esC.n, matriz);
        }
    }

    /**
     * Constructor del problemaCuadradoMagico
     * @param estadoInicial estado en el que empezar la búsqueda
     */
    public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial) {
        super(estadoInicial);
    }

    public Accion[] acciones(Estado es){
        EstadoCuadrado esC = (EstadoCuadrado) es;
        ArrayList<Accion> listaAcciones = new ArrayList<>();
        ArrayList<Integer> listA = new ArrayList<>();
        ArrayList<Integer> listB = new ArrayList<>();

        // mete todos los valores de la matriz en una lista
        for (int i = 0; i < esC.n; i++) {
            for (int j = 0; j < esC.n; j++) {
                listA.add(esC.cuadrado[i][j]);
            }
        }
        // hace una lista con los valores que no están en la matriz
        for(int i=1;i<= (esC.n * esC.n);i++){
            if(!listA.contains(i))
                listB.add(i);
        }

        // crea las posibles acciones para cada casilla vacía
        for (int i = 0; i < esC.n; i++) {
            for (int j = 0; j < esC.n; j++) {
                if(esC.cuadrado[i][j] == 0){
                    for(Integer item : listB){
                        Accion a = new AccionCuadrado(i,j,item);
                        if (a.esAplicable(esC))
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
        //compara que las diagonales sumen lo mismo
        if(sumd1!=sumd2)
            return false;

        for (int i = 0; i < esC.n; i++) {
            int rowSum = 0, colSum = 0;
            for (int j = 0; j < esC.n; j++) {
                rowSum += esC.cuadrado[i][j];
                colSum += esC.cuadrado[j][i];
            }
            //compara que las filas y columnas sumen lo mismo
            if (rowSum != colSum || colSum != sumd1)
                return false;
        }
        return true;
    }
}
