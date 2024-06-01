/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interpolacionlagrange;

import java.util.Scanner;

/**
 *
 * @author Judi
 */
public class m {

    static int num;      //Este es el numero de nodos que voy a introducir  
    static double[][] datos;		//tabla de datos

    public static double Poli(double x)//esta funcion calcula la 
    {				   //interpolacion segun el polinomio
        double suma = 0.0;
        for (int i = 0; i < num; i++) {
            suma += ((datos[i][1]) * (lagra(i, x)));
        }
        return (suma);
    }

    private static double lagra(int i, double x) {
        double multi = 1;
        for (int j = 0; j < num; j++) {
            if (i != j) {
                multi *= ((x - datos[j][0]) / (datos[i][0] - datos[j][0]));
            }
        }
        return (multi);
    }

    public static void leetabla() {
        Scanner teclado = new Scanner(System.in);
        for (int i = 0; i < num; i++) //en este ciclo se introducen
        {                                         //los datos
            System.out.println("Introduce el dato(x,y) numero:" + (i + 1));
            datos[i][0] = teclado.nextDouble();           //se introducen las "x"
            datos[i][1] = teclado.nextDouble();           //se introducen las "y" o f(x)
        }
    }

    public static void main(String args[]) {
        Scanner teclado = new Scanner(System.in);

//numero de muestras
        double intra, resultado;		//numero que se desea intrapolar
        boolean pregunta = true;
        String resp;
        char comp = 'y';
        System.out.println("Escribe el numero de datos a introducir");
        num = teclado.nextInt();
        datos = new double[num][2];		//tabla de datos
        leetabla();				//pone las "x" y las "y" iniciales
        while (pregunta) {
            System.out.println("Introduce el numero a intrapolar");
            intra = teclado.nextDouble();
            if (intra < datos[0][0] || intra > datos[num - 1][0]) {
                System.out.println("CUIDADO CON EL COLERA, ESTAS EXTRAPOLANDO");
            }
            resultado = Poli(intra);
            System.out.println("El valor encontrado es: " + resultado);
            System.out.println("Deseas intrapolar otro numero?:(y/n)");
            resp = teclado.nextLine();
            if (resp.charAt(0) == comp) {
                pregunta = true;
            } else {
                pregunta = false;
            }
        }
    }
}
