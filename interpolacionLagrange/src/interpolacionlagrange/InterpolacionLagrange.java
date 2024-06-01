
package interpolacionlagrange;

import java.util.Scanner;

/**
 *
 * @author Giovanni Garrido
 * ID 00000228724
 * Métodos Numéricos
 * Profesor: Juan Francisco Vazquez Beltran
 * Asignacion 15b: Interpolación de Lagrange
 * 05/11/2021
 */

/*
programa que interpole un punto (x, y) datos un polinomio de interpolación de 
Lagrange de grado n, dado un conjunto de n+1 puntos.
*/
public class InterpolacionLagrange {
    
    static final int MAXN=  11;
    static double[][] datos=new double[MAXN][2];

    static int grado;
    static double abscisa;


    /*
    invoca datos las funciones leePuntos() e interpolacionLagrange().
    */
    public static void main(String[] args) {
        
        Scanner tc = new Scanner(System.in);
        

        System.out.println(ANSI_YELLOW+"Asignacion 15b: Interpolación de Lagrange\n");
        System.out.println(ANSI_YELLOW+"Programa que interpole un punto (x, y) a un polinomio de interpolación ");
        System.out.println(ANSI_YELLOW+"de Lagrange de grado n, dado un conjunto de n+1 puntos."+ANSI_RESET);  
        
        leePuntos();
        
        System.out.println("");
        System.out.println(ANSI_GREEN+"RESULTADOS: \n"+ANSI_RESET);
        System.out.printf("Para la abscisa: %.6f %n", abscisa);
        System.out.printf("La ordenada es.: %.6f %n%n",interpolacionLagrange());

    }
    
    /*
    Una función llamada leePuntos() que lee las coordenadas de los puntos
    datos los que va datos ajustar el polinomio (máximo 11) y los almacena en un 
    arreglo bidimensional
    */
    public static void leePuntos(){

        
        Scanner sc=new Scanner(System.in);
        
        System.out.print("\nIngrese el grado n del polinomio (maximo 10): ");
        grado=sc.nextInt();
        while (grado>11||grado<1){
            System.out.println("\nEl grado n tiene que ser entre 1 y 10 ");
            System.out.print("Ingrese otro grado n del polinomio (maximo 10): ");
            grado=sc.nextInt();
        }
            

        System.out.println("\nIngrese las coordenadas: ");
        for (int i = 0; i <= grado; i++) {// Ingresa las coordenadas
            for (int j = 0; j < 2; j++) {
                if(j==0)
                    System.out.print("\nIngrese el valor de X" + i+ " ...: ");
                else
                    System.out.print("Ingrese el valor de f(X)" + i + " : ");
                
                datos[i][j]=sc.nextDouble();

            }
        }
        
        System.out.print("\nIngrese la abscisa del punto que se desea interpolar: ");
        abscisa=sc.nextDouble();
        
        System.out.println("\n\nAbscisa que se desea interpolar: " + abscisa);
        System.out.println("\nCoordenadas Ingresados: ");
        System.out.println("-------------------------------");
        System.out.println("|        X     |      f(x)    |");
        System.out.println("-------------------------------");

        for (int x=0; x < grado; x++) {
            System.out.print("|");
                for (int y=0; y < 2; y++) {
                    System.out.printf ("%11.6f ",datos[x][y]);
                    if (y!=datos[x].length) System.out.print(" |  ");
                    }
            System.out.println("");
         }
        System.out.println("-------------------------------");
        

    }//end leePuntos()
    
    /*
    interpola el punto de abscisa x al polinomio de grado grado y cuyos puntos 
    están en un arreglo bidimensional. La función regresa la ordenada del punto
    datos interpolar.
    */
    
    public static double interpolacionLagrange(){
        double interpolacion=0;
        double dividendo;
        double divisor;
        
        for(int i=0;i<=grado;i++){
            divisor=1;
            dividendo=1;
            
            for(int j=0;j<=grado;j++){
                if (i==j)
                    continue;
                dividendo=dividendo*(abscisa-datos[j][0]);
                divisor=divisor*(datos[i][0]-datos[j][0]);

        }

            interpolacion=interpolacion+((dividendo/divisor)*datos[i][1]);

        }

        return interpolacion;
    }//end interpolacionLagrange()
    
    /*
    para dar color datos la salida de texto
    */
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

}
