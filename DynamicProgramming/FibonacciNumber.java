package DynamicProgramming;

import java.util.Scanner;

/**
 * 509. Fibonacci Number
 * The Fibonacci numbers, commonly denoted F(n) form a sequence, called the Fibonacci sequence, such that each number is the sum of the two preceding ones, starting from 0 and 1. That is,
 *
 * F(0) = 0, F(1) = 1
 * F(n) = F(n - 1) + F(n - 2), for n > 1.
 * Given n, calculate F(n).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 2
 * Output: 1
 * Explanation: F(2) = F(1) + F(0) = 1 + 0 = 1.
 * Example 2:
 *
 * Input: n = 3
 * Output: 2
 * Explanation: F(3) = F(2) + F(1) = 1 + 1 = 2.
 * Example 3:
 *
 * Input: n = 4
 * Output: 3
 * Explanation: F(4) = F(3) + F(2) = 2 + 1 = 3.
 *
 *
 * Constraints:
 *
 * 0 <= n <= 3
 */
public class FibonacciNumber {
    public static void main(String[] args) {
        System.out.println("Fibonacci "+fibonacci_opt(9));
    }


    public static int fib(int n) {
        /*
        Number of calls for recursive tree =2^0+2^1+----+2^n-1 => where n = number of levels
        and number of levels = log 2 N + 1
        so total calls are Sum =a*(r^n-1)/(r-1) =>2^m.out.println(fib(4));
        System.out.println("Enter the n ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] arr = new int[n + 1];
        arr[1] = 0;
        arr[2] = 1;// this is for 1th based indexing but size of array still we are taking n+1
        System.out.println(fib_optimize(n, arr));
        System.out.println(0(2^n -1 )/(2-1) =>2^n-1 CALLS
        AND ON EACH CALL T.C = C SO TOTAL T.C =O(2^N - 1)
         */
        if (n == 0 || n == 1) return n;
     /*
      0 and 1 is the initial and n == 1 is for the 1 and n == 0 is for the zero we don't have to write only n == 0
      return 0 no the answer will be 0 okay you understand what i am saying do dry run;

      */
        else return fib(n - 1) + fib(n - 2);
    }


    public static int fib_optimize(int n, int[] arr) {
        // Recursion + Memoization
        // code for 1 based indexing
        if (n == 1 || n == 2) return arr[n];
        /*
         if we want that o store in 1 th index and 1 store in 2 nd index then we have to change the base case
         learn an important lesson if we want 1 based indexing
         */
        if (arr[n] != 0) return arr[n];
        else return arr[n] = fib_optimize(n - 1, arr) + fib_optimize(n - 2, arr);
    }


    public static int fib_opt(int n, int[] arr) {
         /* We call it top down DP recursion + memoization the array is also called look up table
          It is top - down dp recursion +memoization(the process of storing the result in array we call it memoization )
          We use memoization to avoid repetitive calls
          Recursion + Memoization = Top - Down DP because we go from top to bottom
         */
        // code for 0th based indexing
        if (n == 0 || n == 1) return arr[n];
        /*
         if we want that o store in 1 th index and 1 store in 2 nd index then we have to change the base case
         learn an important lesson if we want 1 based indexing
         */
        if (arr[n] != 0) return arr[n];// array size is n+1 okay not n as dry run in copy and think
        else return arr[n] = fib_opt(n - 1, arr) + fib_opt(n - 2, arr);
    }

/*
t.c =o(log n)>o(n)>o(n log n)>o(n^2)>o(n^2 log n) >o(n^3)>>o(2^n)
 */


    public int fibo_tabulation(int n) {
   /*
     Using Bottom Up DP called Tabulation It is called Iterative DP
    Another meaning of DP using previous result to compute new result Or using smaller problem to solve bigger problem
     Tabulation : Iterative or Bottom Up DP */
        if (n <= 1) return n;
        int[] arr = new int[n + 1];
        arr[1]++;
        for (int i = 2; i <= arr.length; i++) arr[i] = arr[i - 1] + arr[i - 2];
        return arr[n];
     /*
     T.C = O(N)
     S.C =O(N)
      */
    }


    public static int fibonacci_opt(int n) {
        // Using only three variables
        if (n <= 1) return n;
        int n1 = 0;
        int n2 = 1;
        int count = 0;
        for (int i = 2; i <= n; i++) {
            count = n1 + n2;
            n1 = n2;
            n2 = count;
        }
        return count;
        /*
        T.C =O(N)
        S.C =0(1)
         */
    }
}
