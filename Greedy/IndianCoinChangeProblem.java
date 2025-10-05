package Greedy;

/**
 * Q) Given a set of coins and value , we have to find the minimum number of coins which satisfies the value. Supply of each value of coin
 * is infinite .
 */
public class IndianCoinChangeProblem {
    public static void main(String[] args) {
        int target_amount = 87;
        int [] coins ={1, 2, 5, 10, 20, 50, 100};
        // Here index and target is changing index : 0 to n-1 and target : t to 0
        long [][] dp = new long[coins.length][target_amount+1];
        for(int i =0;i<dp.length;i++)for(int j =0;j<dp[i].length;j++)dp[i][j]=-1;
        System.out.println(" dp.length "+dp.length+" column "+dp[0].length);
        long x = take_skip_1(0,target_amount,coins,dp);
        System.out.println(" The minimum coins needed to make "+target_amount+" is "+x);


    }
    public static long take_skip_1(int idx,int amount,int [] coins,long [][] dp){
        if (idx == coins.length) {
            if (amount == 0) return 0;
            else return Integer.MAX_VALUE;
        }
        if(dp[idx][amount]!=-1) return dp[idx][amount];
        long skip = take_skip_1(idx + 1, amount,coins,dp);
       // if (amount - coins[idx] < 0) return dp[idx][amount]=skip; or we can do this instead of the below two lines
        long take =Integer.MAX_VALUE;
        if(amount>=coins[idx])take = 1 + take_skip_1(idx,amount - coins[idx], coins,dp);
        return dp[idx][amount] = Math.min(skip, take);
    }
}
