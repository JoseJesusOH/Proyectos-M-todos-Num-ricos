package newtonraphson;

import java.util.Scanner;

/**
 * Clase principal que realiza o ejecuta el metodo de newton Raphson para el
 * calculo de la raiz de una determinada función. Alumnno; José Jesús Orozco
 * Hernández ID; 00000229141. Asignación 05b Metodo de Newton - Raphson Fecha;
 * 17/09/2022 1:02 AM
 *
 * @author José Jesús
 */
public class NewtonRaphson {

    /**
     * Funcion principal la cual lee el valor inicial y
     * error maximo aproximado para posteriormente invocar a la funcion
     * newtonRaphson() para encontrar la raiz.
     *
     * @param args Argumentos
     */
    public static void main(String[] args) {
        //Variables a utilizar.
        boolean auxiliarInicial = true, auxiliarError = true;
        Scanner teclado = new Scanner(System.in);
        double valorInicial = 0.0, errorAproxMax = 0.0;
        //Titulo del programa
        System.out.println("_________________________________________________________________");
        System.out.println("|                Método de Newton - Raphson                     |");
        System.out.println("| Programa que determina una de las raices reales de la funcion |");
        System.out.println("|           f(x) = 9x^4 + 18x^3 + 38x^2 - 57x + 14              |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        //Solicitud al usuario de variables a utilizar.
        //Se solicita valor inicial
        do {
            try {
                System.out.println("Porfavor digite el valor inicial:");
                valorInicial = teclado.nextDouble();
                auxiliarInicial = false;
            } catch (Exception e) {
                System.out.println("Error: Porfavor digite solo numeros.");
            }
        } while (auxiliarInicial);
        System.out.println("");
        //Ciclo auxiliar para la captura del error aproximado
        do {
            try {
                System.out.println("Porfavor digite el error aproximado maximo:");
                errorAproxMax = teclado.nextDouble();
                auxiliarError = false;
            } catch (Exception e) {
                System.out.println("Error: Porfavor digite solo numeros.");
            }
        } while (auxiliarError);
        System.out.println("-----------------------------------------------------------------");
        //Llamada de funcion principal
        newtonRaphson(valorInicial, errorAproxMax);

    }

    /**
     * Funcion principal que realiza el calculo de la raiz a partir del metodo
     * de newton Raphson.
     *
     * @param valorInicial Valor inicial
     * @param errorMaxAprox Error aproximado
     */
    public static void newtonRaphson(double valorInicial, double errorMaxAprox) {
        double xR = 0.0, fXi = 0.0, fPrimaXi, errorAprox = 1.0;
        int i = -1;
        do {
            fXi = f(valorInicial);
            fPrimaXi = (36 * (Math.pow(valorInicial, 3))) + (54 * (Math.pow(valorInicial, 2))) + (76 * valorInicial) - 57;
            errorAprox = Math.abs(((valorInicial - xR) / valorInicial) * 100.0);
            xR = valorInicial;
            valorInicial = xR - (fXi / fPrimaXi);
            i++;
        } while (errorAprox > errorMaxAprox);
        System.out.println("");
        System.out.printf("La raiz es: %.8f %n", xR);
        System.out.printf("El valor de la funcion es: %.8f %n", fXi);
        System.out.printf("El error aproximado es: %.8f %n", errorAprox);
        System.out.println("Numero de iteracciones requeridas: " + i);

    }

    /**
     * Funcion que determina la ordena y deacuerdo a la abscisa x recibida .
     *
     * @param abscisa Valor de una abscisa x .
     * @return Ordena y.
     */
    public static double f(double abscisa) {
        double parte1 = 9 * (Math.pow(abscisa, 4));
        double parte2 = 18 * (Math.pow(abscisa, 3));
        double parte3 = 38 * (Math.pow(abscisa, 2));
        double parte4 = 57 * abscisa;
        double parte5 = 14;
        double suma = parte1 + parte2 + parte3 - parte4 + parte5;
        return suma;
    }
}
