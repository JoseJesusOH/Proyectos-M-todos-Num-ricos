package eliminaciongauss;

import java.util.Scanner;

/**
 * Clase que resuelva un sistema de ecuaciones lineales simultáneas por el
 * método de Eliminación de Gauss con pivoteo. Alumnno; José Jesús Orozco
 * Hernández ID; 00000229141 Asignación 7b: Método de Eliminación de Gauss
 * Fecha; 24/09/2022 12:44 AM
 *
 * @author José Jesús
 */
public class EliminacionGauss {

    //Sistema de Ecuaciones o matriz
    private static double sE[][] = new double[12][12];
    //Sistema de Ecuaciones o matriz auxiliar
    private static double sEA[][] = new double[12][12];
    //Numero de Ecuaciones maxima
    private static int nEcuaciones = 0;

    /**
     * Invoca a las funciones leeEcuaciones(), eliminacionGauss() y
     * despliegaSolucion(), con el fin de resolver un sistema de ecuaciones.
     *
     * @param args Argumentos
     */
    public static void main(String[] args) {

        System.out.println("_________________________________________________________________");
        System.out.println("|    Programa que resulve un sistema de ecuaciones lineales     |");
        System.out.println("|            simultaneas  utilizando el metodo de                |");
        System.out.println("|            ' Eliminacion de Gauss con Pivoteo '               |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        leeEcuaciones();
        eliminacionGauss();
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
                } while (true);
            }
            do {
                try {
                    System.out.println("Ingrese el termino independiente: ");
                    sE[i][nEcuaciones] = teclado.nextDouble();
                    break;
                } catch (Exception e) {
                }
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
     * Resuelve el sistema de ecuaciones cuyos coeficientes y términos
     * independientes. Esta función llama a las funciones eliminacionAdelante()
     * y sustitucionAtras() para hacer su trabajo.
     */
    public static void eliminacionGauss() {
        for (int i = 0; i < nEcuaciones - 1; i++) {
            pivotea(i);
            eliminacionAdelante(i);
        }
        sustitucionAtras();
    }

    /**
     * Funcion que implementa la etapa de Eliminación hacia Adelante..
     *
     * @param posicion Posicion del arreglo
     */
    public static void eliminacionAdelante(int posicion) {

        for (int i = posicion + 1; i < nEcuaciones; i++) {
            double division = sE[i][posicion] / sE[posicion][posicion];
            for (int j = posicion; j <= nEcuaciones; j++) {
                sE[i][j] = sE[i][j] - sE[posicion][j] * division;
            }

        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestran los resultados de la eliminacion;");
        System.out.println("");
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
     * Función que reacomoda las ecuaciones a partir de la i-ésima para que el
     * elemento pivote sea el mayor de los elementos hacia abajo en su columna.
     *
     * @param posicion Posicion
     */
    public static void pivotea(int posicion) {
    
        for (int i = 0; i < nEcuaciones; i++) {
            for (int j = 0; j <= nEcuaciones; j++) {
                sEA[i][j] = sE[i][j];
            }
        }

        int m = 0;
        double mayor = 0.0;
        for (int i = posicion; i < nEcuaciones; i++) {
            if (Math.abs(sE[i][posicion]) > mayor) {
                mayor = Math.abs(sE[i][posicion]);
                m = i;
            }
        }

        for (int j = 0; j <= nEcuaciones; j++) {

            sE[m][j] = sEA[posicion][j];
            sE[posicion][j] = sEA[m][j];

        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestran los resultados del pivoteo;");
        System.out.println("");
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
     * Funcion que implementa la etapa de Substitución hacia Atrás.
     */
    public static void sustitucionAtras() {

        double suma;

        for (int k = nEcuaciones - 1; k > -1; k--) {

            suma = 0.0;
            for (int j = 0; j < nEcuaciones; j++) {
                suma += sE[k][j] * sE[j][nEcuaciones + 1];
            }

            sE[k][nEcuaciones + 1] = (sE[k][nEcuaciones] - suma) / sE[k][k];
        }
    }

    /**
     * Funcion qque despliega la solución del sistema de ecuaciones.
     */
    public static void despliegaSolucion() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestran los resultados obtenidos");
        for (int i = 0; i < nEcuaciones; i++) {
            System.out.println("x" + (i + 1) + "= " + sE[i][nEcuaciones + 1]);
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Gracias por utilizar el programa.");
    }

}
