/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package inviertematriz;

import java.util.Scanner;

/**
 * Clase que imprime la matriz inversa de un sitema de ecuaciones utilizando
 * Gaus-Jordan con pivoteo . Alumnno; José Jesús Orozco Hernández ID;
 * 00000229141 Asignación 9b: Método Inversa Fecha; 07/10/2022 12:03 AM
 *
 * @author José Jesús
 */
public class InvierteMatriz {

    private static int nEcuaciones;
    private static double matrizA[][];
    private static double matrizB[][];
    private static int index[];
    private static double c[];
    private static int axuliar = 0;

    /**
     * Invoca a las funciones a las funciones de leeMatriz(),ampliarMatriz(),
     * invertirMatriz() y despliegaMatriz().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("_______________________________________________________________");
        System.out.println("|    Programa que muestra la matriz inversa de  un sistema de   |");
        System.out.println("|     ecuaciones lineales  simultaneas  utilizando              |");
        System.out.println("|                Gauss-Jordan con Pivoteo                       |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        leeMatriz();
        ampliarMatriz();
        invertirMatriz();
        despliegaMatriz();
    }

    /**
     * Lee los valores de la matriz.
     */
    public static void leeMatriz() {
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
        matrizA = new double[nEcuaciones][nEcuaciones];
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Para continuar se le pedira ingresar los coeficientes ");
        System.out.println("-----------------------------------------------------------------");

        for (int i = 0; i < nEcuaciones; i++) {
            for (int j = 0; j < nEcuaciones; j++) {
                do {
                    try {
                        System.out.println("Ingrese el valor  [" + (i + 1) + "," + (j + 1) + "]: ");
                        matrizA[i][j] = teclado.nextDouble();
                        break;
                    } catch (Exception e) {
                        System.out.println("Error: Porfavor digite solo numeros.");
                    }
                    teclado.nextLine();
                } while (true);
            }
        }
    }

    /**
     * Amplia la matriz
     */
    public static void ampliarMatriz() {
        matrizB = new double[nEcuaciones][nEcuaciones];
    }

    /**
     * Realiza la inversión de valores.
     */
    public static void invertirMatriz() {
        int n = matrizA.length;
        double x[][] = new double[n][n];
        double b[][] = new double[n][n];
        index = new int[n];
        for (int i = 0; i < n; ++i) {
            b[i][i] = 1;
        }

        axuliar = index.length;
        c = new double[axuliar];

        for (int i = 0; i < axuliar; ++i) {
            index[i] = i;
        }

        for (int i = 0; i < axuliar; ++i) {
            double c1 = 0;
            for (int j = 0; j < axuliar; ++j) {
                double c0 = Math.abs(matrizA[i][j]);
                if (c0 > c1) {
                    c1 = c0;
                }
            }
            c[i] = c1;
        }

        pivotea();

        for (int i = 0; i < n - 1; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int f = 0; f < n; ++f) {
                    b[index[j]][f]
                            -= matrizA[index[j]][i] * b[index[i]][f];
                }
            }
        }

        for (int i = 0; i < n; ++i) {
            x[n - 1][i] = b[index[n - 1]][i] / matrizA[index[n - 1]][n - 1];
            for (int j = n - 2; j >= 0; --j) {
                x[j][i] = b[index[j]][i];
                for (int d = j + 1; d < n; ++d) {
                    x[j][i] -= matrizA[index[j]][d] * x[d][i];
                }

                x[j][i] /= matrizA[index[j]][j];
            }
        }
        matrizB = x;

    }

    /**
     * Pivotea resultados
     */
    public static void pivotea() {

        int k = 0;
        for (int j = 0; j < axuliar - 1; ++j) {
            double pi1 = 0;
            for (int i = j; i < axuliar; ++i) {

                double pi0 = Math.abs(matrizA[index[i]][j]);
                pi0 /= c[index[i]];
                if (pi0 > pi1) {
                    pi1 = pi0;
                    k = i;
                }
            }

            int itmp = index[j];
            index[j] = index[k];
            index[k] = itmp;
            for (int i = j + 1; i < axuliar; ++i) {
                double pj = matrizA[index[i]][j] / matrizA[index[j]][j];

                matrizA[index[i]][j] = pj;

                for (int l = j + 1; l < axuliar; ++l) {
                    matrizA[index[i]][l] -= pj * matrizA[index[j]][l];
                }
            }
        }
    }

    /**
     * Despliega resultados
     */
    public static void despliegaMatriz() {
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("A continuacion se muestra la matriz inversa del sistema de ecuaciones ingresado");

        for (int i = 0; i < nEcuaciones; ++i) {
            for (int j = 0; j < nEcuaciones; ++j) {
                System.out.printf("%12.4f   ", matrizB[i][j]);
            }
            System.out.println();
        }

    }

}
