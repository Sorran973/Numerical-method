package lab6;

import java.util.ArrayList;

/**
 * Created by ArtemBulkhak on 18.04.18.
 */
public class Bisection {

    public double a;
    public double b;
    public double alpha;
    public final double epsilon;
    public int count;

    public Bisection(){

        a = -3;
        b = 0;
        alpha = (b + a)/2;
        epsilon = 0.01;
        count = 0;
    }

    public void check(){
        while (Math.abs(b - a) > 2*epsilon) {

            if (f(alpha) < f(alpha + epsilon)) {
                b = alpha;
                alpha = (b + a)/2;
            } else if (f(alpha) > f(alpha + epsilon)) {
                a = alpha;
                alpha = (b + a)/2;
            } else
                break;
            count++;
        }
        System.out.println("Bisection:");
        System.out.println("x_min = " + Math.abs(-1 - alpha));
        System.out.println("count = " + count + "\n");
    }

    public double f(double x){
        return x*x + 2*x +1;
    }
}
