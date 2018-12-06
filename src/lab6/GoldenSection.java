package lab6;

import java.util.ArrayList;

/**
 * Created by ArtemBulkhak on 18.04.18.
 */
public class GoldenSection {

    public double a;
    public double b;
    public double alpha;
    public double beta;
    public final double epsilon;
    public int count;

    public GoldenSection(){

        a = -3;
        b = 0;
        alpha = a + (2/(3+Math.sqrt(5)))*(b-a);
        beta = a + (2/(1+Math.sqrt(5)))*(b-a);
        epsilon = 0.01;
        count = 0;
    }

    public void check(){
        while (Math.abs(b - a) > 2*epsilon) {

            if (f(alpha) < f(alpha + epsilon)) {
                b = beta;
                alpha = a + (2/(3+Math.sqrt(5)))*(b-a);
                beta = a + (2/(1+Math.sqrt(5)))*(b-a);
            } else if (f(alpha) > f(alpha + epsilon)) {
                a = alpha;
                alpha = a + (2/(3+Math.sqrt(5)))*(b-a);
                beta = a + (2/(1+Math.sqrt(5)))*(b-a);
            } else
                break;
            count++;
        }
        System.out.println("GoldenSection:");
        System.out.println("x_min = " + Math.abs(-1 - alpha));
        System.out.println("count = " + count);
    }

    public double f(double x){
        return x*x + 2*x +1;
    }
}
