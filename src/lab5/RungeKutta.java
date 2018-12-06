package lab5;

import java.util.ArrayList;

import static java.lang.Math.*;

/**
 * Created by ArtemBulkhak on 15.04.18.
 */
public class RungeKutta {

    public ArrayList<Double> x0;
    public  ArrayList<Double> x1;
    public  ArrayList<Double> y0;
    public  ArrayList<Double> y1;
    public final int a;
    public final int b;
    public int n;
    public final double length;
    public double h;


    public RungeKutta(){

        x0 = new ArrayList<>();
        x1 = new ArrayList<>();
        y0 = new ArrayList<>();
        y1 = new ArrayList<>();
        a = 0;
        b = 1;
        n = 10;
        length = b-a;
        h = length/n;
    }

    public void calcArgs(){

        for (int i = 0; i <= n; i++)
            x0.add(a + h * i);

        for (int i = 0; i < n; i++)
            x1.add(a + (h * (i + 0.5)));
    }

    public void calcFuncValues(){

        y0.add(exp(x0.get(0)));

        double k1, k2, k3, k4;
        for (int i = 0; i < n-1; i++) {

            k1 = exp(x0.get(i));
            k2 = exp(x1.get(i));
            k3 = exp(x1.get(i));
            k4 = exp(x0.get(i+1));

            y0.add(y0.get(i) + h/6 * (k1 + 2*k2 + 2*k3 + k4));
        }

        y0.add(exp(x0.get(n)));
    }

    public void printRes(){
        for(int i = 0; i <= n; i++)
            System.out.println(x0.get(i)  + " " + y0.get(i) + " " + abs(exp(x0.get(i)) - y0.get(i)));
    }

}
