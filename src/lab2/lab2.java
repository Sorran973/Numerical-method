package lab2;

import lab1.lab1;
import java.util.ArrayList;

import static java.lang.Math.abs;
import static java.lang.Math.pow;
import static lab2.lab2.*;


/**
 * Created by ArtemBulkhak on 13.02.18.
 */

class Spline{

    private ArrayList<Double> a;
    private ArrayList<Double> b;
    private ArrayList<Double> c;
    private ArrayList<Double> d;
    private ArrayList<Double> value1;
    private ArrayList<Double> value2;

    Spline(){
        a = new ArrayList();
        b = new ArrayList();
        c = new ArrayList();
        d = new ArrayList();
        value1 = new ArrayList();
        value2 = new ArrayList();
    }

    public void valueSpline1(double x){

        int i ;
        for (i = 0; i < n; i++)
            if (x >= x0.get(i) && x <= x0.get(i+1))
                break;




        value1.add(this.a.get(i)
                + this.b.get(i) * (x - x0.get(i))
                + this.c.get(i) * (pow(x - x0.get(i), 2))
                + this.d.get(i) * (pow(x - x0.get(i), 3)));



    }

    public void valueSpline2(double x){

        int i ;
        for (i = 0; i < n-1; i++)
            if (x >= x0.get(i) && x <= x0.get(i+1))
                break;




        value2.add(this.a.get(i) +
                this.b.get(i) * (x - x0.get(i)) +
                this.c.get(i) * (pow(x - x0.get(i), 2)) +
                this.d.get(i) * (pow(x - x0.get(i), 3)));



    }

    public ArrayList<Double> getValue1(){
        return this.value1;
    }

    public ArrayList<Double> getValue2(){
        return this.value2;
    }

    public ArrayList getD() {
        return d;
    }
    public Double getD(int i) {
        return d.get(i);
    }

    public ArrayList getA() {
        return a;
    }

    public ArrayList getC() {
        return c;
    }
    public Double getC(int i) {
        return c.get(i);
    }

    public ArrayList getB() {
        return b;
    }
}


public class lab2 {

    public static final ArrayList<Double> x0 = new ArrayList<>();
    public static final ArrayList<Double> x1 = new ArrayList<>();
    public static final ArrayList<Double> y0 = new ArrayList<>();
    public static final ArrayList<Double> y1 = new ArrayList<>();
    public static int a = 0;
    public static int b = 1;
    public static int n = 10;
    public static double length = b-a;
    public static double h = length/(n-1);

    public static void main(String[] args) {

        Spline spline = new Spline();

        double h0 = 0;
        for (int i = 0; i < n; i++, h0+=h){
            x0.add(h0);
        }

        for (int i = 0; i < n-1; i++){
            x1.add((x0.get(i)+x0.get(i+1))/2);
        }

        for (int i = 0; i < n; i++){
            y0.add(pow(Math.E, x0.get(i)));
        }

        for (int i = 0; i < n-1; i++){
            y1.add(pow(Math.E, x1.get(i)));
        }


//----------------- fill d for PROGONKA
        ArrayList<Double> d = new ArrayList();
        for (int i = 1; i < n-1; i++)
            d.add((3/ pow(h, 2))*(y0.get(i+1) - 2 * y0.get(i) + y0.get(i-1)));


//----------------- Tridiagonal matrix algorithm
        ArrayList x = lab1.main(n-2, d);


//----------------- fill spline.C
        spline.getC().add(0.0);
        for (int i = 1; i < n-1; i++)
            spline.getC().add(x.get(i-1));

        spline.getC().add(0.0);


//----------------- fill spline.A; spline.B; spline.D
        for (int i = 0; i < n-1; i++) {

            spline.getA().add(y0.get(i));

            spline.getD().add((spline.getC(i+1) - spline.getC(i)) / (3 * h));

            spline.getB().add((((y0.get(i+1) - y0.get(i)) / h) - (h/3) * (spline.getC(i+1) + 2 * spline.getC(i))));
        }


//----------------- calculation spline
        for (int i = 0; i < n; i++)
            spline.valueSpline1(x0.get(i));


        for (int i = 0; i < n-1; i++)
            spline.valueSpline2(x1.get(i));



        for (int i = 0, j = 0; i < n-1; i++, j+=2) {
            System.out.printf("%s%-10f%s%-20.5f%s%-20.5f%s%.5f%n", "x = ", x0.get(i), "y = ", y0.get(i), "S = ", spline.getValue1().get(i), "|y - S1| = ", abs(y0.get(i) - spline.getValue1().get(i)));
            System.out.printf("%s%-10f%s%-20.5f%s%-20.5f%s%.5f%n", "x = ", x1.get(i), "y = ", y1.get(i), "S = ", spline.getValue2().get(i), "|y - S2| = ", abs(y1.get(i) - spline.getValue2().get(i)));
        }
        System.out.printf("%s%-10f%s%-20.5f%s%-20.5f%s%.5f%n", "x = ", x0.get(n-1), "y = ", y0.get(n-1), "S = ", spline.getValue1().get(n-1), "|y - S1| = ", abs(y0.get(n-1) - spline.getValue1().get(n-1)));

//        for (int i = 0, j = 0; i < n-1; i++, j+=2){
//
//            System.out.println("x"+j + "\ty = "+y0.get(i) + "\tS = " + spline.getValue1().get(i) + "\t|y - S1| = "+ abs(y0.get(i) - spline.getValue1().get(i)));
//            System.out.println("x"+(j+1) + "\ty = "+y1.get(i) + "\tS = " + spline.getValue2().get(i) + "\t|y - S2| = "+ abs(y1.get(i) - spline.getValue2().get(i)));
//        }
//        System.out.println("x"+18 + "\ty = "+y0.get(n-1) + "\tS = " + spline.getValue1().get(n-1) + "\t|y - S1| = "+ abs(y0.get(n-1) - spline.getValue1().get(n-1)));


    }



}
