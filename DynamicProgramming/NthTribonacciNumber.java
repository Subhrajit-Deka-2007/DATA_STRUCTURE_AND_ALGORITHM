package DynamicProgramming;
/**
 * 1137. N-th Tribonacci Number
 * The Tribonacci sequence Tn is defined as follows:
 *
 * T0 = 0, T1 = 1, T2 = 1, and Tn+3 = Tn + Tn+1 + Tn+2 for n >= 0.
 *
 * Given n, return the value of Tn.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 4
 * Output: 4
 * Explanation:
 * T_3 = 0 + 1 + 1 = 2
 * T_4 = 1 + 1 + 2 = 4
 * Example 2:
 *
 * Input: n = 25
 * Output: 1389537
 *
 *
 * Constraints:
 *
 * 0 <= n <= 37
 * The answer is guaranteed to fit within a 32-bit integer, ie. answer <= 2^31 - 1
 */

import java.util.Scanner;

public class NthTribonacciNumber {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n+1];
        dp[0]=0;
        dp[1] =1;
        dp[2] =1;
        System.out.println(tribonacci_1(n,dp));
        System.out.println( tribonacci(n));
        System.out.println(tabulation_4(n));
        System.out.println(tabulation_3(n,dp));
    }




/*=============================================== ONLY RECURSIVE APPROACH ============================================================*/
    public static  int tribonacci(int n) {
        if(n==0) return 0;
        if( n==1||n==2) return 1;
        else return tribonacci(n-1)+tribonacci(n-2)+tribonacci(n-3);
    }
    /*
    T.C => 3^0+3^1+-----+3^N-1
        =>O(3^N-1/2)
     S.C => O(LOG 3 ((3^N-1)/2) +1 ) =>NUMBER OF LEVELS STACK SPACE
         => total calls = (3 ^ N -1)/2
         => 2x+1 =3^N
         => N = log 3 (2x+1) is the level formula where x is the number of levels  where x is the total number of calls

     */
/*============================================= Recursive approach ends =================================================================*/






/*===============================================DP  : RECURSION + MEMOIZATION ===========================================================*/

    public static  int tribonacci_1(int n,int[] dp) {
        if( n==0||n==1||n==2) return dp[n];
        else return dp[n] = tribonacci_1(n-1,dp)+tribonacci_1(n-2,dp)+tribonacci_1(n-3,dp);
        /*
        T.C =O(N-3)
        S.C =O( RECURSIVE SPACE ( NUMBER OF LEVELS = LOG 3( 2X+1))+ EXTRA ARRAY (N+1))
         */
    }





    // GOING FROM N TO 3 ----> BIG TO SMALL SO TOP TO DOWN







/*=================================DP : RECURSION + MEMOIZATION ENDS HERE (TOP - DOWN DP)=====================================================*/





/*=============================================DP: TABULATION (BOTTOM UP DP) =================================================================*/
public  static int tabulation_3(int n ,int [] dp){
    // we are defining the size of array only when we given n so we can say array size is dynamic given in runtime only
    dp[0]=0;dp[1] =1;dp[2] =1;
    for(int i =3; i<=n;i++)dp[i]= dp[i-1]+dp[i-2]+dp[i-3];
    return dp[n];
    /*
    T.C =O(N-3)
    S.C =O(N)
     */
}
public static  int tabulation_4(int n){
    // optimizing the space
    int v1 = 0;
    int v2 =1;
    int v3 =1;
    int x1 =-1;
    int x2 =-2;
    for(int i =3; i<=n;i++){
        x1=v3;
        x2 =v2;
        v3 = v1+v2+v3;
        v2 =x1;
        v1 =x2;
    }
    return v3;
    /*
    T.C =O(N-3)
    S.C =O(1)
     */
}
/*
GOING FROM 3 TO N SO BOTTOM TO TOP SMALL TO BIG JUST THINK LIKE NUMBER ARE WRITTEN IN VERTICALLY IN ASCENDING ORDER
SO ON THE BASIS OF THAT LOOK TOP DOWN BOTTOM UP

 */
}
