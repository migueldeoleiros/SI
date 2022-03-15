package es.udc.sistemasinteligentes.g1_42;

import es.udc.sistemasinteligentes.Accion;
import es.udc.sistemasinteligentes.Estado;
import es.udc.sistemasinteligentes.ProblemaBusqueda;

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

    public static class AccionAspiradora extends Accion{
        public enum Tipo {IZQ, DER, ASP};

        int x;
        int y;
        int num;

        public AccionAspiradora(int x, int y, int num) {
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
            return true;
        }

        @Override
        public Estado aplicaA(Estado es) {
            EstadoCuadrado esAs = (EstadoCuadrado)es;
            EstadoCuadrado.PosicionRobot nuevaPosicionRobot=esAs.posicionRobot;
            EstadoCuadrado.PosicionBasura nuevaPosicionBasura=esAs.posicionBasura;

            if (tipo==Tipo.IZQ)
                nuevaPosicionRobot = EstadoCuadrado.PosicionRobot.IZQ;
            else if (tipo==Tipo.DER)
                nuevaPosicionRobot = EstadoCuadrado.PosicionRobot.DER;
            else if (tipo==Tipo.ASP) {
                if (esAs.posicionRobot== EstadoCuadrado.PosicionRobot.IZQ) { //Aspiramos izquierda
                    if ((esAs.posicionBasura== EstadoCuadrado.PosicionBasura.DER) ||
                            (esAs.posicionBasura== EstadoCuadrado.PosicionBasura.AMBAS)) {
                        nuevaPosicionBasura = EstadoCuadrado.PosicionBasura.DER;
                    }
                    else
                        nuevaPosicionBasura = EstadoCuadrado.PosicionBasura.NINGUNA;
                }
                else{ //Aspiramos derecha
                    if ((esAs.posicionBasura== EstadoCuadrado.PosicionBasura.IZQ) ||
                            (esAs.posicionBasura== EstadoCuadrado.PosicionBasura.AMBAS)) {
                        nuevaPosicionBasura = EstadoCuadrado.PosicionBasura.IZQ;
                    }
                    else
                        nuevaPosicionBasura = EstadoCuadrado.PosicionBasura.NINGUNA;
                }
            }
            return new EstadoCuadrado(nuevaPosicionRobot, nuevaPosicionBasura);
        }
    }

    //Como toda las acciones se pueden aplicar en cualquier estado y son pocas,
    //podemos mantenerlas en un array para cuando nos las pidan con el método acciones.
    private Accion[] listaAcciones;

    public ProblemaCuadradoMagico(EstadoCuadrado estadoInicial) {
        super(estadoInicial);
        //Inicializamos la lista de acciones
        listaAcciones = new Accion[]{new AccionAspiradora(AccionAspiradora.Tipo.IZQ),
                new AccionAspiradora(AccionAspiradora.Tipo.DER),
                new AccionAspiradora(AccionAspiradora.Tipo.ASP)};
    }

    public Accion[] acciones(Estado es){
        //No es necesario generar las acciones dinámicamente a partir del estado porque todas las acciones se pueden
        //aplicar a todos los estados
        return listaAcciones;
    }


    @Override
    public boolean esMeta(Estado es) {
        return ((EstadoCuadrado)es).posicionBasura == EstadoCuadrado.PosicionBasura.NINGUNA;
    }
}
