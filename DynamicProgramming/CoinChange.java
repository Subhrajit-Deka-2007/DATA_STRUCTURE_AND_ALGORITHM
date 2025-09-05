package DynamicProgramming;

/**
 * 322. Coin Change
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 *
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 *
 *
 * Example 1:
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * Example 2:
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 * Example 3:
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 231 - 1
 * 0 <= amount <= 104
 */
public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(coinChange(coins, amount));
    }

    public static int coinChange(int[] coins, int amount) {
        return take_skip(0, coins, amount);
    }

    public static int take_skip(int idx, int[] coins, int amount) {
        if (idx == coins.length) {// we had reach here means we got a combination
            if (amount == 0) return 0;// zero means a valid combination
            else return Integer.MAX_VALUE - 1;// as when we add 1 + to it in take it will give us -2147----
            /*
            Not a valid combination as amount has not become zero it will give us an
             error of overflow as we plus with 1 it will cross the upper limit so, it is necessary to use a value
             little less than this
              */
        }

        int skip = take_skip(idx + 1, coins, amount);
        if (amount - coins[idx] < 0) return skip;
        // amount - count[idx] I had already crossed the amount limit if I take it means the coins sum is greater then amount
        int take = 1 + take_skip(idx, coins, amount - coins[idx]);
        return Math.min(skip, take);
        // we will get an answer from skip and then answer from pick then we return that answer which is minimum
    }

    /*=============================== To resolve this issue (we are using long data type) ====================================================*/
    public static long take_skip_1(int idx, int[] coins, int amount) {
        if (idx == coins.length) {
            if (amount == 0) return 0;
            else return Integer.MAX_VALUE;
        }

        long skip = take_skip_1(idx + 1, coins, amount);
        if (amount - coins[idx] < 0) return skip;
        long take = 1 + take_skip_1(idx, coins, amount - coins[idx]);
        return Math.min(skip, take);
    }
/*======================================DP : RECURSION + MEMOIZATION ===============================================================================*/
public int coinChange_2(int[] coins, int amount) {
    // here two variables idx and amount are changind so we make a 2d DP array dp[coins.length][amount+1]
    // amount changing from [0 to amount ] amount also included
    long [][] dp = new long [coins.length][amount+1];
    for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++) dp[i][j]=-1;
    int ans = (int)take_skip_1(0,coins,amount,dp);
    if(ans == Integer.MAX_VALUE) return -1;
    return ans;
}
    public static long take_skip_1(int idx, int[] coins, int amount,long [][] dp) {
        if (idx == coins.length) {
            if (amount == 0) return 0;
            else return Integer.MAX_VALUE;
        }
        if(dp[idx][amount]!=-1) return dp[idx][amount];
        long skip = take_skip_1(idx + 1, coins, amount,dp);
        if (amount - coins[idx] < 0) return skip;
        long take = 1 + take_skip_1(idx, coins, amount - coins[idx],dp);
        return dp[idx][amount] = Math.min(skip, take);
    }
    /*
    T.C =O(N*(AMOUNT+1))// REPRESENT NUMBER OF UNIQUE CALLS
    S.C =O(N*(AMOUNT+1))
     */
/*===================================================DP : RECURSION + MEMO ===============================================================*/
}
