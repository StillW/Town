package java.Task03;

import java.math.BigInteger;

/**
 * Created by Still on 11.06.2015.
 */
public class Factorial {

    public static void main(String[] args) {

        BigInteger fact = BigInteger.ONE;

        // Calculate factorial value
        for (int i = 1; i <= 100; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
        }
        System.out.println("100!=" + fact);

        BigInteger t= fact,a;
        int sum=0;

        // calculate sum for each digit in factorial result
        while(fact!=BigInteger.ZERO){
            a= fact.mod(BigInteger.valueOf(10));
            sum+=a.intValue();

            // move factorial result for getting next digit
            fact=fact.divide(BigInteger.valueOf(10));
        }
        System.out.println("The sum of digits 100!  = " +sum);
    }
}

