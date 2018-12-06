package lab3;

import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 * Created by ArtemBulkhak on 14.03.18.
 */
public class lab3 {

    public static final ArrayList<Double> x0 = new ArrayList<>();
    public static final ArrayList<Double> y0 = new ArrayList<>();
    public static final ArrayList<Double> ln_y = new ArrayList<>();
    public static int a = 0;
    public static int b = 1;
    public static int n = 10;
    public static double length = b-a;
    public static double h = length/n;


    public static void main(String[] args) {


        double h0 = 0;
        for (int i = 0; i <= n; i++, h0+=h){
            x0.add(h0);
        }

        for (int i = 0; i <= n; i++){
            y0.add(pow(Math.E, x0.get(i)));
        }

        for (int i = 0; i <= n; i++){
            ln_y.add(Math.log(y0.get(i)));
        }


        for (int i= 0; i <= n; i++) {
            System.out.println(x0.get(i) + " " + y0.get(i) + " " + ln_y.get(i));
        }


        double A = 0;
        double B = 0;
        double C = n;
        double D = 0;
        double F = 0;

        for (int i = 0; i < n; i++){
            A+= pow(x0.get(i), 2);
            B+= x0.get(i);
            D+= x0.get(i)*ln_y.get(i);
            F+= ln_y.get(i);
        }


        double a = (D*C - B*F)/(A*C - B*B);
        double b = F/C - (B/C)*a;
        System.out.println(a);
        System.out.println(b);

        double b_1 = a;
        double a_1 = Math.pow(Math.E, b);
        System.out.println(a_1);
        System.out.println(b_1);


    }
}
