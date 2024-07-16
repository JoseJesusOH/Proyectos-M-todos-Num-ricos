/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interpolacionlagrange;

import java.util.Scanner;

/**
 * Programa que interpola un punto (x, y) datos de un polinomio de interpolación
 * de Lagrange de grado n, dado un conjunto de n+1 puntos. Alumno; Jose Jesus
 * Orozco Hernandez ID; 229141 Asignacion 15b: Interpolación de Lagrange Fecha;
 * 4/11/2022
 *
 * @author Jose Jesus Orozco Hernandez
 */
public class InterpolacionLagrange {

    private static double datos[][];
    private static double abcisa;
    private static double ordenada;
    private static int totalPuntos;

    /**
     * Función main() que invoca a las funciones leePuntos() e
     * interpolacionLagrange().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner teclado = new Scanner(System.in);
        System.out.println("_________________________________________________________________");
        System.out.println("|   Programa que interpole un punto (x, y) a un polinomio de    |");
        System.out.println("|    interpolación  de Lagrange de grado n, dado un conjunto de |");
        System.out.println("|                           n+1 puntos                          |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        boolean total = true;
        do {
            try {
                System.out.println("Escribe el numero de datos a introducir");
                totalPuntos = teclado.nextInt();
                if (totalPuntos > 11) {
                    System.out.println("No se permite introducir mas de 11 de datos");
                } else if (totalPuntos <= 0) {
                    System.out.println("El numero de datos debe de ser menor o igual a cero");
                } else {
                    total = false;
                }
            } catch (Exception e) {
                System.out.println("Porfavor introduzca datos validos");
            }

        } while (total);
        datos = new double[totalPuntos][2];
        leePuntos();
        System.out.println("");
        System.out.println("Resultados:");
        System.out.printf("Para la abscisa: %.6f %n", abcisa);
        System.out.printf("La ordenada es.: %.6f %n", ordenada);

    }

    /**
     * Funcion que lee las coordenadas de los puntos a los que va a ajustar el
     * polinomio (máximo 11) y los almacena en un arreglo bidimensional.
     */
    public static void leePuntos() {
        Scanner teclado = new Scanner(System.in);
        for (int i = 0; i < totalPuntos; i++) {
            System.out.println("Porfavor introduzca el par " + (i + 1) + " de datos como se muestra dato(x,y),");
            do {
                try {
                    System.out.println("Introduce el valor x:");
                    datos[i][0] = teclado.nextDouble();
                    break;
                } catch (Exception e) {
                    System.out.println("Porfavor introduzca datos validos no letras,o caracter extraño.");
                }
            } while (true);
            do {
                try {
                    System.out.println("Introduce el valor de f(x):");
                    datos[i][1] = teclado.nextDouble();
                    break;
                } catch (Exception e) {
                    System.out.println("Porfavor introduzca datos validos no letras,o caracter extraño.");
                }
            } while (true);
        }
        boolean salida = true;
        double abscisa = 0;
        do {
            try {
                System.out.println("Introduce la abscisa del punto que se desea interpolar.");
                double punto = teclado.nextDouble();
                if (punto < datos[0][0] || punto > datos[totalPuntos - 1][0]) {
                    System.out.println("La abcisa del punto que desea interpolar debe de estar en el rango permitido");
                    System.out.println("No debe de ser menor que " + datos[0][0] + " y mayor que " + datos[totalPuntos - 1][0]);
                } else {
                    InterpolacionLagrange.abcisa = punto;
                    abscisa=punto;
                    salida = false;
                }
            } catch (Exception e) {
                System.out.println("Porfavor introduzca datos validos no letras,o caracter extraño.");
            }

        } while (salida);
        InterpolacionLagrange.ordenada = interpolacionLagrange(abscisa);
    }

    /**
     * Función que interpola el punto de abscisa x al polinomio de grado grado y
     * cuyos puntos están en un arreglo bidimensional.La función regresa la
     * ordenada del punto a interpolar.
     *
     * @param abscisa abcisa o x
     * @return ordenada
     */
    public static double interpolacionLagrange(double abscisa) {

        double suma = 0.0;
        for (int i = 0; i < totalPuntos; i++) {
            double multi = 1;
            for (int j = 0; j < totalPuntos; j++) {
                if (i != j) {
                    multi *= ((abscisa - datos[j][0]) / (datos[i][0] - datos[j][0]));
                }
            }
            suma += ((datos[i][1]) * (multi));
        }
        return suma;
    }

}
