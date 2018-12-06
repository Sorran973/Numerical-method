package lab1;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * Created by ArtemBulkhak on 07.02.18.
 */
public class lab1 {



    public static ArrayList main(int n, ArrayList<Double> d) {

//        int n;
//        Scanner sc = new Scanner (System.in);
//        System.out.println("n = ");
//        n = sc.nextInt();

        ArrayList<Double> b = new ArrayList<>();
        ArrayList<Double> a = new ArrayList<>();
        ArrayList<Double> c = new ArrayList<>();
//        ArrayList<Double> d = new ArrayList<>();
        ArrayList<Double> alpha = new ArrayList<>();
        ArrayList<Double> beta = new ArrayList<>();

//        for (int i = 0; i < n; i++){
//            System.out.println("b(" + i + ")=");
//            b.add(sc.nextDouble());
//        }
//        for (int i = 0; i < n-1; i++){
//            System.out.println("a(" + i + ")=");
//            a.add(sc.nextDouble());
//        }
//        for (int i = 0; i < n-1; i++){
//            System.out.println("c(" + i + ")=");
//            c.add(sc.nextDouble());
//        }
//        for (int i = 0; i < n; i++){
//            System.out.println("d(" + i + ")=");
//            d.add(sc.nextDouble());
//        }

        /*
                0 1 2    n-1 n
                . . . . . . .
                . 4 1 . . . .   <---
                . 1 4 1 . . .
                . . 1 4 1 . .
                . . . 1 4 1 .
                . . . . 1 4 .
                . . . . . . .
            */

        for (int i = 0; i < n; i++){
            b.add(4.0);
        }
        for (int i = 0; i < n-1; i++){
            a.add(1.0);
        }
        for (int i = 0; i < n-1; i++){
            c.add(1.0);
        }


        alpha.add(- c.get(0) / b.get(0));
        beta.add(d.get(0) / b.get(0));
        for (int i = 1; i < n-1; i++) {
            alpha.add(-c.get(i) / (a.get(i-1) * alpha.get(i-1) + b.get(i)));
            beta.add((d.get(i) - a.get(i-1) * beta.get(i-1)) / (a.get(i-1) * alpha.get(i-1) + b.get(i)));
        }

        beta.add((d.get(n-1) - a.get(n-2)*beta.get(n-2)) / (a.get(n-2)* alpha.get(n-2) + b.get(n-1)));

        ArrayList<Double> x = new ArrayList<>();

        x.add(beta.get(n-1));
        for (int i = 2; i <= n; i++) {
            x.add(alpha.get(n-i) * x.get(i-2) + beta.get(n-i));
        }

        Collections.reverse(x);

        return x;


    }
}
