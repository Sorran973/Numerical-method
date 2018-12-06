package lab5;


/**
 * Created by ArtemBulkhak on 15.04.18.
 */
public class lab5 {

    public static void main(String[] args) {
        RungeKutta rungeKutta = new RungeKutta();
        rungeKutta.calcArgs();
        rungeKutta.calcFuncValues();
        rungeKutta.printRes();
    }
}
