package lab4;

import java.util.ArrayList;

import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Created by ArtemBulkhak on 09.04.18.
 */
public class Trapezes {

    public ArrayList<Double> x0;
    public ArrayList<Double> x1;
    public ArrayList<Double> y0;
    public ArrayList<Double> y1;
    public final int a;
    public final int b;
    public int n;
    public final double length;
    public double h;
    public final double F = E-1;


    public double Ihh;
    public double Ih;

    public Trapezes(){

        x0 = new ArrayList<>();
        x1 = new ArrayList<>();
        y0 = new ArrayList<>();
        y1 = new ArrayList<>();
        a = 0;
        b = 1;
        n = 2;
        length = b-a;
        h = length/n;
    }

    public void algorithm(){

        calcArgAndFunc(n);
        calcI();

        while ((Math.abs(Ihh - Ih) / 3) > 0.00001){
            raisePartition();
        }

    }

    public void calcI(){

        /* Sum for h */
        double sumYh = 0;
        for (int i = 1; i < n; i++){
            sumYh += y0.get(i);
        }

        Ih = h*((y0.get(0) + y0.get(n))/2 + sumYh);
        System.out.println("Trapezes for h partitions: " + (Ih));


        /* Sum for h/2 */
        double sumYhh = 0;
        for (int i = 1; i < n*2; i++){
            sumYhh += y1.get(i);
        }

        Ihh = (h/2)*((y1.get(0) + y1.get(n*2))/2 + sumYhh);
        System.out.println("Trapezes for h/2 partitions: " + (Ihh));


        double I = Ihh + ((Ihh - Ih) / 3);
        System.out.println("Trapezes with Richardson: " + (I));
        System.out.println("n = " + n);

    }

    public void raisePartition(){
        this.n = n*2;
        this.h = length/n;
        calcArgAndFunc(n);
        calcI();
    }

    public void calcArgAndFunc(int n){

        x0.clear();
        x1.clear();
        y0.clear();
        y1.clear();

        for (int i = 0; i <= n; i++){
            x0.add(a + (h * i));
        }

        for (int i = 0; i <= n*2; i++){
            x1.add(a + (h/2) * i);
        }

        for (double x: x0){
            y0.add(pow(Math.E, x));
        }

        for (double x: x1){
            y1.add(pow(Math.E, x));
        }

        for (int i= 0; i <= n; i++) {
            System.out.println(x0.get(i) + " " + y0.get(i));
        }
        System.out.println();
        for (int i= 0; i <= n*2; i++) {
            System.out.println(x1.get(i) + " " + y1.get(i));
        }
        System.out.println();
    }
}
