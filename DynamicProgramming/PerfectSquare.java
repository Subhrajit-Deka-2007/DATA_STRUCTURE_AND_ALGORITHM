package DynamicProgramming;

import java.util.Arrays;

/**
 * 279. Perfect Squares
 * Given an integer n, return the least number of perfect square numbers that sum to n.
 *
 * A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.
 *
 *
 *
 * Example 1:
 *
 * Input: n = 12
 * Output: 3
 * Explanation: 12 = 4 + 4 + 4.
 * Example 2:
 *
 * Input: n = 13
 * Output: 2
 * Explanation: 13 = 4 + 9.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 104
 */
public class PerfectSquare {
    public static void main(String[] args) {

    }
}
class Solution {
    public int numSquares(int n) {
        int[] dp = new int[n + 1];// as we want from 1 to n so we are choosing n+1 we don't want zero indexes
        // as our minimum reach is n =1
        Arrays.fill(dp, -1);
        return recursion_memoization(n, dp);
    }


    /**
     * ---------------------------------Recursive Solution---------------------------------------------------
     */
    public int recursion(int n) {
        if (isPerfect(n)) return 1;// if it already a perfect sqaure then return 1
        int min = n;
        /*
         as answer is 1+1+---+1 it is the also an answer but it is worst answer it cannot cross this answer
         this is the maximum answer the answer cannot be more then this .
          */
        for (int i = 1; i <= n / 2; i++) {
            int count = recursion(i) + recursion(n - i);
            min = Math.min(min, count);
        }


        return min;
    }

    public boolean isPerfect(int n) {
        int sqrt = (int) Math.sqrt(n);
        return (sqrt * sqrt == n);
    }
    /*
    T.C = Each function is calling n times => 1+n^2+n^3+------+n^n => ~O(N^N)
    S.C =O(N) WHERE N ARE THE LEVELS A GENERIC TREE IS FORMED .N^N MENAS EACH LEVEL IT IS INCREASING BY N TIMES IF IT IS 2^N MEANS ON EACH LEVEL THE CALL ARE INCREASING BY 2

    */
    /*-------------------------Recursive solution ends here -----------------------------------------------*/

/*================================DP : RECURSION + MEMOIZATION =======================================================*/
    public int recursion_memoization(int n, int[] dp) {
        if (isPerfect(n)) return 1;
        if (dp[n] != -1) return dp[n];
        int min = Integer.MAX_VALUE;
        // for(int i= 1;i<=n/2;i++){
        //     int count = recursion_memoization(i,dp)+recursion_memoization(n-i,dp);
        //     min = Math.min(min,count);
        // } Instead of this sir has written if we are using this then t.c was o(n^2)
        for (int i = 1; i * i <= n; i++) {
            int count = recursion_memoization(i * i, dp) + recursion_memoization(n - i * i, dp);
            min = Math.min(min, count);// After writing this t.c became o(n*sqrt(n)) on each call for loop is running sqrt(n) times
        }
        return dp[n] = min;
    }
/*======================================= DP :TABULATION ==================================================================================*/
public int tabulation(int n){
    int [] dp = new int [n+1];
    for(int i =1;i<=n;i++){
        if(isPerfect(i))dp[i]=1;
        else{
            int min = n;
            for(int j =1;j*j<=i;j++){
                int count = dp[j*j]+dp[i-j*j];
                min = Math.min(min,count);

            }
            dp[i] = min;
        }
    }
    return dp[n];
    /*
    T.C =O(N*sqrt(n))
    S.C =O(N+1)
     */
}
public int tabulation_2(int n){
    int [] dp = new int [n+1];
    for(int i =1;i<=n;i++){
        if(isPerfect(i))dp[i]=1;
        else{
            int min = n;
            for(int j =1;j<=i/2;j++){
                int count = dp[j]+dp[i-j];
                min = Math.min(min,count);

            }
            dp[i] = min;
        }
    }
    return dp[n];
}
/*
BECAUSE IT IS TABULATION  IT PROVES IT  IS BETTER THAN RECURSION+ MEMOIZATION OR RECURSION WE ARE TAKING IT TO I/2
BECAUSE IN RECURSION ALSO IF WE CALL ALSO SOME TIME ALSO GO THERE .
T.C =O(N^2)
S.C =O(N+1) STILL IT RUNS IN THE LEET CODE
 */
    // WE HAVE TWO METHODS FOR TABULATION 
}
/**3ms solution present in leetcode
class Solution {
    /*Using Lagrange’s Four-Square Theorem
    *every natural number can be represented as
    *the sum of four integer squares.

//So the answer is always between 1 to 4
public boolean isSquare(int n) {
    int x = (int) Math.sqrt(n);
    return x * x == n;
}
public int numSquares(int n) {
    //if number is perfect square itself then answer is 1
    if (isSquare(n)) return 1;

    //Two square check
    for (int i = 1; i * i <= n; i++) {
        if (isSquare( n - i * i)) return 2;
    }

    /*
     *Legendre’s 3-square theorem
     *A positive integer n can be written as the sum of three squares
     *if and only if n is not of the form: n = 4^k*(8b+7)


    int m = n;
    //(m & 3) checks last 2 bits, if those bits are 00 then number is divisible by 4
    while ((m & 3) == 0) m = m >> 2;   // divide by 4, m >>= 2 is m = m / 4

    /*
     *Legender's theorem*
     *(m & 7) checks the last 3 bits (i.e., m % 8).
     *If remainder is 7, then m has the form 8b + 7.
     *Combined with Step 1, that means the original n was of the form 4^k(8b+7).

    if ((m & 7) == 7) return 4;

    //if 1, 2, and 4 are checked so ans is 3 otherwise
    return 3;
}
}
 */