package DynamicProgramming;

import java.util.Arrays;
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
        int [] dp = new int[n+1];// we want n also and 0 also so n+1
        Arrays.fill(dp,-1);
        dp[0]=1;
        dp[1]=0;
        System.out.println(optimize(n,dp));
        System.out.println(tabulation(n));
    }
    public static int de_arrangement(int n){
        if(n==0) return 1;
        /*
         It is necessary as otherwise whole answer will become zero if this is in logical just remove the case n==0
         instead just put n==2 and return 1 which means I can de - arrange in 1 way
         */
        if(n==1) return 0;// for n ==1  items it is impossible to de arrange so zero
     return (n-1)*(de_arrangement(n-1)+de_arrangement(n-2));
       /*means we had chosen an element out of n so, we had n-1 elements left we choose n-1 elements
        and, we had two cases either the swap with numbers then we had n-2 elements left or either we had chosen
        and, we choose not to swap so that element had n-1 option left rest understand yourself

        n-1C1(f(n-1)+f(n-2))
        */

    }
/*==========================================DP : RECURSION + MEMOIZATION ===================================================================*/
    public  static int optimize(int n,int[] dp){
        if(dp[n]!=-1) return dp[n];// no base case we had already prefilled it
        else return dp[n]= (n-1)*(optimize(n-1,dp)+optimize(n-2,dp));
    }
/*==========================================DP : RECURSION + MEMOIZATION ======================================================================*/



public static int tabulation(int n){
    int [] dp = new int[n+1];
    dp[0] =1;
    dp[1] =0;
    for(int i =2;i<dp.length;i++)dp[i]= (i-1)*(dp[i-1]+dp[i-2]);
    return dp[n];
}

}
