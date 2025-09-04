package DynamicProgramming;

public class Zero_One_Knapsack {
/*============================================= Recursion ====================================================================================*/
public static int profit(int idx, int [] wt,int[] price,int capacity,int[][] dp){
        if(idx ==price.length) return 0;
        if(dp[idx][capacity]!=-1)return dp[idx][capacity];
      /*
      we will check three things on a current item if the item capacity is less than or equal to the
      capacity if the item capacity is greater  than given capacity of bag then just give it
      we had three option if the item capacity is less than equal to the given capacity then
      i) we had two options either we take it or leave it
      ii) if the item capacity is more than the given capacity we skip it
       */
        int skip = profit(idx+1,wt,price,capacity,dp);
        if(wt[idx]>capacity) return dp[idx][capacity]= skip;// we will just return skip
        int take = price[idx]+profit(idx+1,wt,price,capacity-wt[idx],dp);
        return dp[idx][capacity] = Math.max(take,skip);
        /*
        T.C =O(2^N) EACH HAS TWO OPTION TAKE IT OR SKIP IT IF EVERY ITEM WEIGHT<= CAPACITY FOR ITEM WEIGHT>CAPACITY WE JUST RETURN IT
        time complexity is 2^n if we use only recursion solution
        S.C =O(N*C) WHERE N ARE THE ITEMS GIVEN STACK SPACE -------------(N)
                                                                         |
                                                                         |
                                                                         |(C)




        Recursion + Memoization
        T.C =O(N*C) UNIQUE CALLS SIZE OF DP ARRAY
        S.C =O(N*C)
        WE CAN DO THE QUESTION WITH TABULATION  ALSO
         */
    }
/*========================================== Recursion Ends =========================================================================================*/
    public static void main(String[] args) {
        int[] price=    {5,3,9,16};
        int[] wt ={1,2,8,10};
        int c =8;
        /*
        i = 0 to n-1
        c = c to 0 (capacity) so we will make 2d dp
        To check the question if it is  1D DP OR 2D DP JUST CHECK THE PARAMETERS , HOW MANY PARAMETERS are changing on the basis of that
        we came to know it is 1D DP or 2D DP
         */
        int[][] dp = new int [price.length][c+1];
        /*
         as  c and zero both are included [0--->c]
         we can also do dp[c+1][price.length]  then return dp[c][i] it will also have to  changed
         */
        for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;i++)dp[i][j]=-1;
        System.out.println(profit(0,wt,price,c,dp));
    }
}
