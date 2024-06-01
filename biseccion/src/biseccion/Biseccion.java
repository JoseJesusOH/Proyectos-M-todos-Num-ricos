package biseccion;

import java.util.Scanner;

/**
 * Clase principal que realiza o ejecuta el metodo de bisección para el calculo
 * de la raiz de una determinada función. Alumnno; José Jesús Orozco Hernández
 * ID; 00000229141. Asignación 03b Metodo de Biseccion Fecha; 10/09/2022 12:02
 * AM
 *
 * @author José Jesús
 */
public class Biseccion {

    /**
     * Funcion principal la cual lee los valores del intervalo de busqueda y
     * error maximo aproximado para posteriormente invocar a la funcion
     * biseccion() para encontrar la raiz.
     *
     * @param args Argumentos
     */
    public static void main(String[] args) {
        //Variables a utilizar.
        boolean auxiliarInicial = true, auxiliarFinal = true, auxiliarIntervalo = true, auxiliarError = true, auxiliarErrorCiclo = true;
        Scanner teclado = new Scanner(System.in);
        double valorInicial = 0.0, valorFinal = 0.0, errorAproxMax = 0.0;

        //Titulo del programa
        System.out.println("_________________________________________________________________");
        System.out.println("|                      Metodo de biseccion                      |");
        System.out.println("| Programa que determina una de las raices reales de la funcion |");
        System.out.println("|           f(x) = 9x^4 + 18x^3 + 38x^2 - 57x + 14              |");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("");
        //Solicitud al usuario de variables a utilizar.
        do {
            //Se solicita valor inicial
            do {
                try {
                    System.out.println("Porfavor digite el valor inicial del intervalo de busqueda:");
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
                    System.out.println("Porfavor digite el valor final del intervalo de busqueda:");
                    valorFinal = teclado.nextDouble();
                    auxiliarFinal = false;
                } catch (Exception e) {
                    System.out.println("Error: Porfavor digite solo numeros.");
                }
            } while (auxiliarFinal);
            System.out.println("");
            //Condicion que nos ayuda a determinar si los valores del intervalo cumplan con los requisitos
            double auxliarInicialDos = f(valorInicial);
            double auxliarFinalDos = f(valorFinal);
            if (valorFinal == valorInicial) {
                System.out.println("Valor inicial y final no deben de ser iguales porfavor verique su entrada.");
                System.out.println("");

            } else if (auxliarInicialDos * auxliarFinalDos > 0) {
                System.out.println("""
                                   Los valores ingresados no son validos, estos deben de cumplir una condicion 
                                   donde el resultado de la ordena y de ambos valores multiplicado debe de ser negativo.""");
                auxiliarInicial = true;
                auxiliarFinal = true;
                System.out.println("");

            } else {
                auxiliarIntervalo = false;
            }
        } while (auxiliarIntervalo);
        //Ciclo auxiliar para la captura del error aproximado
        do {
            do {
                try {
                    System.out.println("Porfavor digite el error aproximado maximo:");
                    errorAproxMax = teclado.nextDouble();
                    auxiliarError = false;
                } catch (Exception e) {
                    System.out.println("Error: Porfavor digite solo numeros.");
                }
            } while (auxiliarError);
            if (errorAproxMax <= 0) {
                System.out.println("""
                                   El error aproximado maximo debe de ser menor o igual a cero.
                                   Esto para evitar fallos en el sistema.""");
                auxiliarError = true;

            } else if (errorAproxMax >= 100) {
                System.out.println("""
                                   El error aproximado maximo debe de ser menor a 100.
                                   Esto para evitar fallos en el sistema.""");
                auxiliarError = true;
            } else {
                auxiliarErrorCiclo = false;
            }
        } while (auxiliarErrorCiclo);
        System.out.println("-----------------------------------------------------------------");
        //Llamada de funcion principal
        biseccion(valorInicial, valorFinal, errorAproxMax);

    }

    /**
     * Funcion que calcula y desplega los valores de la raíz, el valor de la
     * función para esa raíz y el número de iteraciones requerida para encontrar
     * la raíz, todo esto atraves del metodo de biseccion .
     *
     * @param valorInicial Valor inicial del intervalo de busqueda.
     * @param ValorFinal Valor final del intervalo de busqueda.
     * @param errorMaxAprox Error maximo aproximado
     */
    public static void biseccion(double valorInicial, double ValorFinal, double errorMaxAprox) {
        //Se le da el nombre de variables para asociarla mejor al metodo escrito
        double xR = 0.0, fXi, fXr = 0.0, signo, errorAprox = 1.0, xRnueva, xRanterior;
        int i = -1;
        //Ciclo del codigo
        while (errorAprox > errorMaxAprox) {
            xRanterior = xR;
            xR = ((valorInicial + ValorFinal) / 2);
            fXi = f(valorInicial);
            fXr = f(xR);
            signo = fXi * fXr;
            xRnueva = xR;
            errorAprox = Math.abs(((xRnueva - xRanterior) / xRnueva) * 100.0);
            if (signo > 0) {
                valorInicial = xR;
            }
            if (signo < 0) {
                ValorFinal = xR;
            }
            i++;
        }
        System.out.println("");
        System.out.println("Numero de iteracciones requeridas: " + i);
        System.out.printf("La raiz es: %.8f %n", xR);
        System.out.printf("El valor de la funcion es: %.8f %n", fXr);

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
