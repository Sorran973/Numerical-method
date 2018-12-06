package lab4;

import java.util.ArrayList;

import static java.lang.Math.E;
import static java.lang.Math.pow;

/**
 * Created by ArtemBulkhak on 09.04.18.
 */
public class CentrRectangls {

    public  ArrayList<Double> x0;
    public  ArrayList<Double> x1;
    public  ArrayList<Double> y0;
    public  ArrayList<Double> y1;
    public final int a;
    public final int b;
    public int n;
    public final double length;
    public double h;
    public final double F = E-1;


    public double Ihh;
    public double Ih;

    public CentrRectangls(){

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

        while ((Math.abs(Ihh - Ih) / 3) > 0.001){
            raisePartition();
        }

    }

    public void calcI(){

        /* Sum for h/2 */
        double sumYhh = 0;
        for (double y: y1){
            sumYhh += y;
        }

        /* Sum for h */
        double sumYh = 0;
        for (double y: y0){
            sumYh += y;
        }

        Ih = h*sumYh;
        Ihh = (h/2)*sumYhh;


        System.out.println("Central rectangles for h partitions: " + (Ih));
        System.out.println("Central rectangles for h/2 partitions: " + (Ihh));


        double I = Ihh + ((Ihh - Ih) / 3);
        System.out.println("Central rectangles with Richardson: " + (I));
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

        for (int i = 0; i < n; i++){
            x0.add(a + (h * (i + 0.5)));
        }

        for (int i = 0; i < n*2; i++){
            x1.add(a + ((h/2) * (i + 0.5)));
        }

        for (double x: x0){
            y0.add(pow(Math.E, x));
        }

        for (double x: x1)
            y1.add(pow(Math.E, x));


        for (int i= 0; i < n; i++) {
            System.out.println(x0.get(i) + " " + y0.get(i));
        }
        System.out.println();
        for (int i= 0; i < n*2; i++) {
            System.out.println(x1.get(i) + " " + y1.get(i));
        }
        System.out.println();
    }
}
