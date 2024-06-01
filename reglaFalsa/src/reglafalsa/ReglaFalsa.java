
package reglafalsa;

import java.util.Scanner;

/**
 *
 * @author Giovanni Garrido
 * ID 00000228724
 * Metodos Numericos
 * Profesor: Juan Francisco Vazquez Beltran
 * Asignacion 4b: Metodo de Regla falsa
 * 20/10/2021
 */
public class ReglaFalsa {

    /*
     * funcion principal
     * lee los valores inicial y final del ontervalo de busqueda y error maximo aproximado
     * invoca funcion reglaFalsa() para encontrar la raiz.
     */
    public static void main(String[] args) {
        
        double xI,xF,xR,errorMaxAprox;
        Scanner tc = new Scanner(System.in);
    
        System.out.println("Asignacion 4b: Metodo de Regla Falsa\n");
        System.out.println("Programa que determina las raices reales de la funcion:");
        System.out.println("f(x)=(1-0.6x)/x\n");
        
        System.out.print("Ingrese el valor inicial.........: ");
        xI=tc.nextDouble();
        
        System.out.print("Ingrese el valor final...........: ");
        xF=tc.nextDouble();
        
        System.out.print("Ingrese el error maximo permitido: ");
        errorMaxAprox=tc.nextDouble();
        
        reglaFalsa(xI, xF, errorMaxAprox);


  
    }
    /**
     * Funcion que calcula la raiz de la funcion y despliega el valor de la raiz,
     * el valor de la funcion de esa raiz y el numero de interacciones para encontrar la raiz
     * @param xI valor inicial del intervalo de busqueda.
     * @param xF valor final del intervalo de busqueda.
     * @param errorMaxAprox error maximo aproximado
     */
    public static void reglaFalsa(double xI, double xF, double errorMaxAprox){
        double xR=0.0, fXi,fXr=0.0,fXf, signo,errorAprox=1.0,xRnueva,xRanterior;
        int i=-1;
        
        while(errorAprox>errorMaxAprox){
        xRanterior=xR;
        fXi=f(xI);
        fXf=f(xF);
        
        xR=(xI-((xF-xI)/(fXf-fXi))*fXi);
        fXr=f(xR);

        signo=fXi*fXr;
        xRnueva=xR;
        errorAprox=Math.abs(((xRnueva-xRanterior)/xRnueva)*100.0);

        if(signo>0)
            xI=xR;

        
        if(signo<0)
            xF=xR;

        
        i++;
        }
        
        System.out.println("");
        System.out.printf("La raiz es...............: %.6f %n", xR);
        System.out.printf("El valor de la funcion es: %.6f %n", fXr);
        System.out.println("Numero de Interacciones..: " + i);
    }
    
    /**
     * Funcion para obtener el valor de la funcion
     * @param x recibe el valor de la abscisa de la funcion.
     * @return regresa el valor de la ordenada de la funcion.
     */
    public static double f(double x){
        double y;
        y=((1-(0.6*x))/x);
        return y;
    
        
    }

}
