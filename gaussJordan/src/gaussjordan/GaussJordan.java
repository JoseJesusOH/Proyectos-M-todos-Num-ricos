package gaussjordan;

import java.util.Scanner;

/**
 * Clase que resuelva un sistema de ecuaciones lineales simultáneas por el
 * método de Gauss Jordan con pivoteo. Alumnno; José Jesús Orozco Hernández ID;
 * 00000229141 Asignación 8b: Método de Gauss - Jordan Fecha; 02/10/2022 12:59
 * AM
 *
 * @author José Jesús
 */
public class GaussJordan {
//Variables auxiliares

    private static double sE[][];
    private static double sEA[][];
    private static int nEcuaciones = 0;
    private static int indices[];
    private static double datos[];
    private static int auxiEcuaciones = 0;

    /**
     * Invoca a las funciones leeEcuaciones(), gaussJordan() y
     * despliegaSolucion(), con el fin de resolver un sistema de ecuaciones.
     *
     * @param args Argumentos
     */
    public static void main(String[] args) {
        System.out.println("_________________________________________________________________");
        System.out.println("|    Programa que resulve un sistema de ecuaciones lineales     |");
        System.out.println("|            simultaneas  utilizando el metodo de               |");
        System.out.println("|        ' Eliminacion de Gauss-Jordan con Pivoteo '            |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        leeEcuaciones();
        gaussJordan();
        despliegaSolucion();
    }

    /**
     * Funcion que lee el número de ecuaciones y después los valores de los
     * coeficientes y término independiente para cada ecuación y los almacena en
     * un arreglo bidimensional.
     */
    public static void leeEcuaciones() {
        Scanner teclado = new Scanner(System.in);
        boolean salida1 = true;
        do {
            try {
                System.out.println("Porfavor digite el numero de ecuaciones:");
                nEcuaciones = teclado.nextInt();
                if (nEcuaciones > 10) {
                    System.out.println("El numero de ecuaciones no debe de ser mayor a 10. ");
                } else if (nEcuaciones == 0) {
                    System.out.println("El numero de ecuaciones no debe de ser 0. ");
                } else if (nEcuaciones < 0) {
                    System.out.println("El numero de ecuaciones no debe ser negativo. ");
                } else {
                    salida1 = false;
                }
            } catch (Exception e) {
                teclado.nextLine();
                System.out.println("Error: Porfavor digite solo numeros.");
            }
        } while (salida1);
        sE = new double[nEcuaciones][nEcuaciones + 1];
        sEA = new double[nEcuaciones][nEcuaciones + 1];
        indices = new int[nEcuaciones];
        datos = new double[nEcuaciones];
        auxiEcuaciones = nEcuaciones;
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Para continuar se le pedira ingresar los coeficientes y término");
        System.out.println("independientes de cada ecuacion del sistema.");
        System.out.println("-----------------------------------------------------------------");
        for (int i = 0; i < nEcuaciones; i++) {
            System.out.println("Ecuacion " + (i + 1));
            for (int j = 0; j <= nEcuaciones - 1; j++) {
                do {
                    try {
                        System.out.println("Ingrese el coeficiente " + (j + 1) + ": ");
                        sE[i][j] = teclado.nextDouble();
                        break;
                    } catch (Exception e) {
                        System.out.println("Error: Porfavor digite solo numeros.");
                    }
                    teclado.nextLine();
                } while (true);
            }

            do {
                try {
                    System.out.println("Ingrese el termino independiente: ");
                    sE[i][nEcuaciones] = teclado.nextDouble();
                    break;
                } catch (Exception e) {
                    System.out.println("Error: Porfavor digite solo numeros.");
                }
                teclado.nextLine();

            } while (true);

        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestra el sistema de ecuaciones ingresado");
        for (int i = 0; i < nEcuaciones; i++) {
            if (sE[i][0] < 0) {
                System.out.printf("%12.4f   ", sE[i][0]);
            } else {
                System.out.printf("%12.4f   ", sE[i][0]);
            }
            for (int j = 1; j < nEcuaciones; j++) {
                if (sE[i][j] < 0) {
                    System.out.printf("-%13.4f   ", (sE[i][j] * -1));
                } else {
                    System.out.printf("+%13.4f   ", sE[i][j]);
                }
            }
            if (sE[i][nEcuaciones] < 0) {
                System.out.printf("= %13.4f", sE[i][nEcuaciones]);
            } else {
                System.out.printf("= %13.4f", sE[i][nEcuaciones]);
            }
            System.out.println("");
        }
    }

    /**
     * Función que resuelve el sistema de ecuaciones cuyos coeficientes y
     * términos independientes se encuentran en un arreglo bidimensional ) y
     * guarda la solución en un arreglo.
     */
    public static void gaussJordan() {
        for (int i = 0; i < nEcuaciones; i++) {
            pivotear(i);
            eliminar(i);
        }
    }

    /**
     * Función que reacomoda las ecuaciones a partir de la i-ésima para que el
     * elemento pivote sea el mayor de los elementos hacia abajo en su columna.
     *
     * @param posicion Posicion
     */
    public static void pivotear(int posicion) {
        int a = 0;
        for (int i = posicion; i < auxiEcuaciones; i++) {
            datos[a] = sE[i][posicion];
            indices[a] = i;
            a++;
        }

        int in, out;
        double temp = 0;
        int indice = 0;
        for (out = 0; out < datos.length; out++) {
            for (in = datos.length - 1; in > out; in--) {
                if (datos[in] > datos[in - 1]) {
                    temp = datos[in];
                    indice = indices[in];
                    datos[in] = datos[in - 1];
                    datos[in - 1] = temp;
                    indices[in] = indices[in - 1];
                    indices[in - 1] = indice;

                }
            }
        }

        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestra el pivoteo realizado");
        for (int i = 0; i < nEcuaciones; i++) {
            if (sE[i][0] < 0) {
                System.out.printf("%12.4f   ", sE[i][0]);
            } else {
                System.out.printf("%12.4f   ", sE[i][0]);
            }
            for (int j = 1; j < nEcuaciones; j++) {
                if (sE[i][j] < 0) {
                    System.out.printf("-%13.4f   ", (sE[i][j] * -1));
                } else {
                    System.out.printf("+%13.4f   ", sE[i][j]);
                }
            }
            if (sE[i][nEcuaciones] < 0) {
                System.out.printf("= %13.4f", sE[i][nEcuaciones]);
            } else {
                System.out.printf("= %13.4f", sE[i][nEcuaciones]);
            }
            System.out.println("");
        }

    }

    /**
     * Realizada con el fin de que cada renglon en base al pivote nuevo tenga un
     * nuevo valor realizando un calculo y eliminacion de datos anteriores
     *
     * @param posicion Posicion del pivote
     */
    public static void eliminar(int posicion) {
        double d = sE[posicion][posicion];
        for (int i = posicion; i < nEcuaciones + 1; i++) {
            sE[posicion][i] = sE[posicion][i] / d;
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestra la division del pivoteo");
        for (int i = 0; i < nEcuaciones; i++) {
            if (sE[i][0] < 0) {
                System.out.printf("%12.4f   ", sE[i][0]);
            } else {
                System.out.printf("%12.4f   ", sE[i][0]);
            }
            for (int j = 1; j < nEcuaciones; j++) {
                if (sE[i][j] < 0) {
                    System.out.printf("-%13.4f   ", (sE[i][j] * -1));
                } else {
                    System.out.printf("+%13.4f   ", sE[i][j]);
                }
            }
            if (sE[i][nEcuaciones] < 0) {
                System.out.printf("= %13.4f", sE[i][nEcuaciones]);
            } else {
                System.out.printf("= %13.4f", sE[i][nEcuaciones]);
            }
            System.out.println("");
        }

        int f = posicion + 1;
        if (f == nEcuaciones) {
            f = 0;
        }

        for (int fila = 0; fila < nEcuaciones - 1; fila++) {
            double k = sE[f][posicion];

            for (int c = posicion; c < nEcuaciones + 1; c++) {
                sE[f][c] = sE[f][c] - (k * sE[posicion][c]);
            }

            if (f == nEcuaciones - 1) {
                f = 0;
            } else {
                f++;
            }
        }

        auxiEcuaciones = auxiEcuaciones - 1;
        datos = new double[auxiEcuaciones];
        indices = new int[auxiEcuaciones];
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestra la eliminacion");
        for (int i = 0; i < nEcuaciones; i++) {
            if (sE[i][0] < 0) {
                System.out.printf("%12.4f   ", sE[i][0]);
            } else {
                System.out.printf("%12.4f   ", sE[i][0]);
            }
            for (int j = 1; j < nEcuaciones; j++) {
                if (sE[i][j] < 0) {
                    System.out.printf("-%13.4f   ", (sE[i][j] * -1));
                } else {
                    System.out.printf("+%13.4f   ", sE[i][j]);
                }
            }
            if (sE[i][nEcuaciones] < 0) {
                System.out.printf("= %13.4f", sE[i][nEcuaciones]);
            } else {
                System.out.printf("= %13.4f", sE[i][nEcuaciones]);
            }
            System.out.println("");
        }

    }

    /**
     * Funcion que despliega la solución del sistema de ecuaciones.
     */
    
    public static void despliegaSolucion() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestran los resultados obtenidos");

        for (int i = 0; i < nEcuaciones; i++) {
            System.out.println("x" + (i + 1) + "= " + sE[i][nEcuaciones]);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Gracias por utilizar el programa. Recuerde, si en los resultados aparece ");
        System.out.println("NaN el sistema no tiene una solucion o tiene soluciones infinitas");
    }
}
