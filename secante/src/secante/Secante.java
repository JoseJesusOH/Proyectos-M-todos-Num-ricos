package secante;

import java.util.Scanner;

/**
 * Clase principal que ejecuta el metodo de la secante para el
 * calculo de la raiz de una determinada función. Alumnno; José Jesús Orozco
 * Hernández ID; 00000229141. Asignación 06b Metodo de la secante;
 * 17/09/2022 1:23 AM
 *
 * 
 * @author José Jesús
 */ 
public class Secante {

    /**
     * Funcion principal la cual lee los valores del intervalo de busqueda y
     * error maximo aproximado para posteriormente invocar a la funcion
     * secante() para encontrar la raiz.
     *
     * @param args Argumentos
     */
    public static void main(String[] args) {

        //Variables a utilizar.
        boolean auxiliarInicial = true, auxiliarFinal = true, auxiliarError = true;
        Scanner teclado = new Scanner(System.in);
        double valorInicial = 0.0, valorFinal = 0.0, errorAproxMax = 0.0;

        //Titulo del programa
        System.out.println("_________________________________________________________________");
        System.out.println("|                     Metodo de la secante                      |");
        System.out.println("| Programa que determina una de las raices reales de la funcion |");
        System.out.println("|                      f(x) = x^3 -2sin x                       |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        //Solicitud al usuario de variables a utilizar.
            //Se solicita valor inicial
            do {
                try {
                    System.out.println("Porfavor digite el valor x1:");
                    valorInicial = teclado.nextDouble();
                    auxiliarInicial = false;
                } catch (Exception e) {
                    System.out.println("Error: Porfavor digite solo numeros.");
                }
            } while (auxiliarInicial);
            System.out.println("");
            //Se solicita valor final
            do {
                try {
                    System.out.println("Porfavor digite el valor  x2:");
                    valorFinal = teclado.nextDouble();
                    auxiliarFinal = false;
                } catch (Exception e) {
                    System.out.println("Error: Porfavor digite solo numeros.");
                }
            } while (auxiliarFinal);
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
        secante(valorInicial, valorFinal, errorAproxMax);
    }

    /**
     * Funcion principal que realiza el calculo de la raiz a partir del metodo
     * de la secante.
     *
     * @param valor1 valor inicial del intervalo de busqueda.
     * @param valor2 valor final del intervalo de busqueda.
     * @param errorMaxAprox error maximo aproximado
     */
    public static void secante(double valor1, double valor2, double errorMaxAprox) {
        double xR = 0.0, fX1, fXr = 0.0, fX2, errorAprox = 0.0, xRnueva, xRanterior;
        int i = -1;
        do {
            xRanterior = xR;
            fX1 = f(valor1);
            fX2 = f(valor2);

            xR = (valor2 - ((valor1 - valor2) / (fX1 - fX2)) * fX2);
            fXr = f(xR);
            valor1 = valor2;
            valor2 = xR;
            xRnueva = xR;
            errorAprox = Math.abs(((xRnueva - xRanterior) / xRnueva) * 100.0);

            i++;
        } while (errorAprox > errorMaxAprox);
        System.out.println("");
        System.out.printf("La raiz es: %.8f %n", xR);
        System.out.printf("El valor de la funcion es: %.8f %n", fXr);
        System.out.printf("El error aproximado es: %.8f %n", errorAprox);
        System.out.println("Numero de iteracciones requeridas: " + i);
    }

    /**
     * Funcion que determina la ordena y deacuerdo a la abscisa x recibida.
     *
     * @param abscisa Valor de una abscisa x .
     * @return Ordena y.
     */
    public static double f(double abscisa) {
        double parte1 = Math.pow(abscisa, 3);
        double parte2 = 2 * Math.sin(abscisa);
        double suma = parte1 - parte2;
        return suma;
    }

}
