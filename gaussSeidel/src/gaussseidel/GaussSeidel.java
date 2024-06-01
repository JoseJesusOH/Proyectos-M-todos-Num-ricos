/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gaussseidel;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Clase que resuelva un sistema de ecuaciones lineales simultáneas por el
 * método de Gauss Sidel. Alumnno; José Jesús Orozco Hernández ID; 00000229141
 * Asignación 10b: Método de Gauss - Seidel Fecha; 14/10/2022 9:29PM
 *
 * @author José Jesús
 */
public class GaussSeidel {

    private static double sE[][];
    private static double sEA[];
    private static double terminos[];
    private static int nEcuaciones = 0;
    private static int numEcuaciones = 0;
    private static double error = 0;

    /**
     * Clase principal.
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("_________________________________________________________________");
        System.out.println("|    Programa que resulve un sistema de ecuaciones lineales      |");
        System.out.println("|            simultaneas  utilizando el metodo de                |");
        System.out.println("|            '          Gauss - Seidel           '               |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        leeEcuaciones();
        GaussSeidel();
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
                GaussSeidel.nEcuaciones = teclado.nextInt();
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

        boolean salida2 = true;
        do {
            try {
                System.out.println("Porfavor digite el error aproximado:");
                GaussSeidel.error = teclado.nextDouble();
                salida2 = false;
            } catch (Exception e) {
                teclado.nextLine();
                System.out.println("Error: Porfavor digite solo numeros.");
            }
        } while (salida2);
        numEcuaciones = nEcuaciones;
        GaussSeidel.sE = new double[GaussSeidel.nEcuaciones][GaussSeidel.nEcuaciones + 1];
        terminos=new double[nEcuaciones];
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Para continuar se le pedira ingresar los coeficientes y término");
        System.out.println("independientes de cada ecuacion del sistema. Sin embargo, el     ");
        System.out.println("sistema necesitara que sea convergente");
        System.out.println("-----------------------------------------------------------------");
        boolean analisis = true;
        do {
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
                        terminos[i]= sE[i][nEcuaciones];
                        break;
                    } catch (Exception e) {
                        System.out.println("Error: Porfavor digite solo numeros.");
                    }
                } while (true);
            }

            int m = 0;

            for (int i = 0; i < nEcuaciones; i++) {
                double valor = Math.abs(sE[i][i]);
                double suma = 0;
                for (int j = 0; j < nEcuaciones; j++) {
                    if (i != j) {
                        suma = Math.abs(sE[i][j]);
                    }
                }
                if (valor < suma) {
                    System.out.println("El sistema no tiene criterio de convergencia verifiquelo.");
                    System.out.println("-----------------------------------------------------------------");
                    System.out.println("A continuacion se muestra el sistema de ecuaciones ingresado");
                    for (i = 0; i < nEcuaciones; i++) {
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
                    m = -1;
                    break;
                }
            }
            if (m == 0) {
                analisis = false;
            } else {
                System.out.println("Digite el sitema de ecuaciones nuevamente.");
            }
        } while (analisis);

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
     * independientes se encuentran en un arreglo bidimensional con
     * gauss-seidel.
     */
    public static void GaussSeidel() {
        double[] pivote = new double[numEcuaciones];
        sEA = new double[numEcuaciones];
        double[] valorXiAnterior = new double[numEcuaciones];
        double[] porcentaje = new double[numEcuaciones];
        int auxiliarPorcentaje = numEcuaciones;
        double multiplicaciones = 0;
        int iteracion = 0;
        for (int i = 0; i < sE.length; i++) {
            pivote[i] = sE[i][i];
        }
        while (auxiliarPorcentaje > -1) {
            if (iteracion == 0) {
                for (int i = 0; i < numEcuaciones; i++) {
                    sEA[i] = 0;
                    valorXiAnterior[i] = 0;
                }
            }
            if (iteracion != 0) {
                for (int fila = 0; fila < numEcuaciones; fila++) {
                    for (int columna = 0; columna < numEcuaciones; columna++) {
                        if (fila == columna && columna == columna) {

                        } else {
                            multiplicaciones = multiplicaciones + sE[fila][columna] * valorXiAnterior[columna];
                        }
                    }
                    sEA[fila] = (terminos[fila] - (multiplicaciones)) / pivote[fila];
                    porcentaje[fila] = Math.abs((valorXiAnterior[fila] - sEA[fila]) / sEA[fila]) * 100;
                    valorXiAnterior[fila] = sEA[fila];
                    multiplicaciones = 0;
                }
            } else {
                for (int fila = 0; fila < numEcuaciones; fila++) {
                    for (int columna = 0; columna < numEcuaciones; columna++) {
                        if (fila == columna && columna == columna) {

                        } else {
                            multiplicaciones = multiplicaciones + sE[fila][columna] * valorXiAnterior[columna];
                        }
                    }
                    sEA[fila] = (terminos[fila] - (multiplicaciones)) / pivote[fila];
                    porcentaje[fila] = Math.abs((valorXiAnterior[fila] - sEA[fila]) / sEA[fila]) * 100;
                    valorXiAnterior[fila] = sEA[fila];
                    multiplicaciones = 0;
                }
            }
            iteracion++;
            for (int i = 0; i < numEcuaciones; i++) {
                if (porcentaje[i] < error) {
                    auxiliarPorcentaje--;
                }
            }
        }
    }

    /**
     * Funcion que despliega la solución del sistema de ecuaciones.
     */
    public static void despliegaSolucion() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("A continuacion se muestran los resultados obtenidos");
        for (int i = 0; i < numEcuaciones; i++) {
            System.out.println("X" + (i + 1) + "= "+String.format("%10.5f", sEA[i]));
        }
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Gracias por utilizar el programa.");
    }
}
