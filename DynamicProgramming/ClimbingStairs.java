package DynamicProgramming;

import java.util.ArrayList;

/**
You are climbing a staircase. It takes n steps to reach the top.

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?



Example 1:

Input: n = 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: n = 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step


Constraints:

1 <= n <= 45
 */
public class ClimbingStairs {
    public static void main(String[] args) {

        ArrayList<ArrayList<String>> ans = new ArrayList<>();
         int a = recursive(3,ans," ",0);
        System.out.println(ans);
        System.out.println(a);
    }

    /*=======================================================DP " Recursion + Memoization====================================================*/
    public static int climbStairs(int n) {

        int[] dp = new int[n + 1];
        dp[dp.length - 1] = 1;
        return optimize(n, 0, dp);
    }

    public static int optimize(int n, int i, int[] dp) {
        if (i == n + 1) return 0;
        if (i == n) return dp[n];
        if (dp[i] != 0) return dp[i];
        else return dp[i] = optimize(n, i + 1, dp) + optimize(n, i + 2, dp);
      /*
      T.C = O(N(number of stairs )
      S.C = O(n(number of stairs recursive stack space )+(n+1){array size})
      */
    }

    /*===================================DP : RECURSION + MEMOIZATION ENDS HERE  ======================================================================*/



    public static int recursive(int n, ArrayList<ArrayList<String>> ans, String s, int i) {
        if (i > n) return 0;
        if (i == n) {
            ArrayList<String> x = new ArrayList<>();
            x.add(" " + s + "-->" + i);
            ans.add(new ArrayList(x));
            return 1;
        }
        return recursive(n, ans, s + "-->" + i, i + 1) + recursive(n, ans, s + "-->" + i, i + 2);
    }
         /*
         for this think like there are n+1 stairs n th be the destination and 0 and 1 be our destination
         so at 0 and 1 we return 1

         t.c = O(2^N-1)
         S.C =O(N(NUMBER OF STAIRS) RECURSIVE SPACE
         */
/*========================================= RECURSION ENDS HERE  ================================================================================*/


/*================================================ DP : BOTTOM UP ============================================================================*/
public int tabulation(int n){
    int [] dp = new int[n+1];
    dp[0]=1;
    if(n>1)dp[1] =1;
    for(int i =2;i<dp.length;i++)dp[i]=dp[i-1]+dp[i-2];
    return dp[n];
        }
/*
T.C =O(N-2)
S.C =O(N)
*/
/*=============================================== DP : BOTTOM UP ===================================================================================*/
    }
