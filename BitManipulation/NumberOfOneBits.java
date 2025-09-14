package BitManipulation;

import java.util.Scanner;

/**
 * 191. Number of 1 Bits
 * Easy
 * Topics
 * premium lock icon
 * Companies
 * Given a positive integer n, write a function that returns the number of set bits in its binary representation (also known as the Hamming weight).
 *
 *
 *
 * Example 1:
 *
 * Input: n = 11
 *
 * Output: 3
 *
 * Explanation:
 *
 * The input binary string 1011 has a total of three set bits.
 *
 * Example 2:
 *
 * Input: n = 128
 *
 * Output: 1
 *
 * Explanation:
 *
 * The input binary string 10000000 has a total of one set bit.
 *
 * Example 3:
 *
 * Input: n = 2147483645
 *
 * Output: 30
 *
 * Explanation:
 *
 * The input binary string 1111111111111111111111111111101 has a total of thirty set bits.
 *
 *
 *
 * Constraints:
 *
 * 1 <= n <= 231 - 1
 *
 *
 * Follow up: If this function is called many times, how would you optimize it?
 */
public class NumberOfOneBits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Enter the number ");
        int n = sc.nextInt();
        System.out.println("The number of one' that the decimal number "+n+" contains "+one_s(n));
    }
    public static int one_s(int num){
        int count =0;
        while(num!=0){
            if(num%2!=0)count++;
            num =num>>1;
        }
        return count;
        /*
        T.C =O(31)
        S.C =O(1)
         */
    }
    /*
    m2 : WE WILL BE TURN OFFING THE RIGHTMOST SET BIT BY DOING (N&N-1) TILL THE WHOLE NUMBERS BECOME 0
     */
    public static int count_3(int n){
        int count =0;
        while(n!=0){
            /*
            It is entering means it is not zero and, obviously it will contain one no need to worry on doing count ++
            means it is a non-zero number
             */
            count++;
            n=(n&n-1);
        }
        return count;
        /*
        T.C = O(32) WHEN ALL 32 BIT ARE ONE
        S.C = O(1)
         */
    }

}
