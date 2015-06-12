package java.Task01;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * Created by Still on 11.06.2015.
 */
public class Catalan {

    public static BigInteger factorial(int n)
    {
        // Calculate factorial value
        BigInteger ret = BigInteger.ONE;
        {
            for (int i = 1; i <= n; ++i) ret = ret.multiply(BigInteger.valueOf(i));
            return ret;
        }
    }

    public static void main(String []args){

        System.out.println("Input number");

        //integer input from the user
        Scanner a = new Scanner(System.in);
        if(a.hasNextInt()) {
            int n = a.nextInt();

            //formula of  Catalan (2 * n)! / (n! * (n + 1)!)
            //for clarity, is divided into 2 lines
            BigInteger znam = factorial(n).multiply(factorial(n+1));
            BigInteger result = factorial(2*n).divide(znam);

            System.out.println(result);
        }else System.out.println("you entered is not an integer");
    }

}


