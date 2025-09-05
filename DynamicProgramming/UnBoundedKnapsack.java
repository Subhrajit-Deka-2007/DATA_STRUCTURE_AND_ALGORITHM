package DynamicProgramming;

public class UnBoundedKnapsack {
    public static void main(String[] args) {
        int[] price = {6, 3, 9, 25};
        int[] wt = {2, 2, 8, 9};
        int c = 9;
        System.out.println(profit(0,wt,price,c));// Here in the recursion solution we will try all possible combinations
    }
/*============================================= Recursive solution ====================================================================================*/
    public static int profit(int idx, int[] wt, int[] price, int capacity) {
        if (idx == price.length) return 0;
        int skip = profit(idx + 1, wt, price, capacity);
        if (wt[idx] > capacity) return  skip;
        int take = price[idx] + profit(idx, wt, price, capacity - wt[idx]);
        /*
         Not idx+1 as we can pick items how much we want

        Here in unbounded knapsack we can pick the item as much as possible
         */
        return  Math.max(take, skip);
        /*
        T.C >0(2^N) GREATER THEN 2^N
        S.C = O(N*(C+1)) T.C IS O(C) STACK SPACE IF THE WEIGHT OF THE COSTLY ITEM IS 1
         */
    }
/*======================================Recursive solution ends here ==========================================================================*/

/*======================================= DP : TOP DOWN APPROACH(Recursion+ Memoization) ===================================================================================*/
public static int memoization(int idx,int[] wt,int [] price,int capacity,int[][] dp) {
    if (idx == price.length) return 0;
    if (dp[idx][capacity] != -1) return dp[idx][capacity];
    int skip = profit(idx + 1, wt, price, capacity);
    if (wt[idx] > capacity) return dp[idx][capacity] = skip;
    // one time will come this condition will get hit and call made by take will stop
    int take = price[idx] + profit(idx, wt, price, capacity - wt[idx]);
    // so as we can choose an item how many we want and inside this we will also get the option of skip
        /*
         Not idx+1 as we can pick items how much we want
         Here in unbounded knapsack we can pick the item as much as possible
         */
    return dp[idx][capacity] = Math.max(take, skip);
}
/*
T.C =0(N*(C+1))
S.C =O(N*(C+1))
 */
/*===================================== DP : RECURSION + MEMOIZATION ==========================================================================*/
}

