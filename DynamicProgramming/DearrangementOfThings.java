package DynamicProgramming;

import java.util.Scanner;

/**
 * Count De-arrangements (Permutation such that no element appears in its original position).
 * 10330
 * Jul 28, 2021
 * Q:
 * You are given N balls numbered from 1 to N and there are N baskets numbered from 1 to N in front of you, the ith basket is meant for the ith ball. Calculate the number of ways in which no ball goes into its respective basket.
 * Eg
 *
 * Input: 3
 * Output: 2
 * Explaination: The (number-basket) pairs are
 * [(1-3),(2-1),(3-2)] and [(1-2),(2-3),(3-2)].
 */
public class DearrangementOfThings {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(" Enter the number of elements ");
        int n = sc.nextInt();
        System.out.println(de_arrangement(n));
    }
    public static int de_arrangement(int n){
        if(n==0) return 1;// it is necessary as otherwise whole answer will become zero
        if(n==1) return 0;// for n items it is impossible to de arrange zero
     return (n-1)*(de_arrangement(n-1)+de_arrangement(n-2));
       /*means we had chosen an element out of n so, we had n-1 elements left we choose n-1 elements
        and, we had two cases either the swap with numbers then we had n-2 elements left or either we had chosen
        and, we choose not to swap so that element had n-1 option left rest understand yourself

        n-1C1(f(n-1)+f(n-2))
        */

    }
}
