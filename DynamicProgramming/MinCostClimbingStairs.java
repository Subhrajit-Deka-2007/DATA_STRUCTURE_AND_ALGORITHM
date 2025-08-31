package DynamicProgramming;

/**
 746. Min Cost Climbing Stairs
 You are given an integer array cost where cost[i] is the cost of ith step on a staircase. Once you pay the cost, you can either climb one or two steps.

 You can either start from the step with index 0, or the step with index 1.

 Return the minimum cost to reach the top of the floor.



 Example 1:

 Input: cost = [10,15,20]
 Output: 15
 Explanation: You will start at index 1.
 - Pay 15 and climb two steps to reach the top.
 The total cost is 15.
 Example 2:

 Input: cost = [1,100,1,1,1,100,1,1,100,1]
 Output: 6
 Explanation: You will start at index 0.
 - Pay 1 and climb two steps to reach index 2.
 - Pay 1 and climb two steps to reach index 4.
 - Pay 1 and climb two steps to reach index 6.
 - Pay 1 and climb one step to reach index 7.
 - Pay 1 and climb two steps to reach index 9.
 - Pay 1 and climb one step to reach the top.
 The total cost is 6.


 Constraints:

 2 <= cost.length <= 1000
 0 <= cost[i] <= 99
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        int[] cost = {1,2,3,4,50,6};
        System.out.println(tabulation_4(cost));
    }
/*================================Recursive Method starts here ===========================================================================*/
    /*----------------------Recursive method going from 0 or 1 to n-1 or n-2 ------------------------------------------*/
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        minCost(cost, 0, dp);
        int y = Math.min(dp[0], dp[1]);
        for (int i = 0; i < dp.length; i++) System.out.print(dp[i] + " ");
        System.out.println();
        return y;
    }

    public int minCost(int[] cost, int idx, int[] dp) {
        if (idx == cost.length - 1 || idx == cost.length - 2) return dp[idx] = cost[idx];
        if (dp[idx] != 0) return dp[idx];
        return dp[idx] = cost[idx] + Math.min(minCost(cost, idx + 1, dp), minCost(cost, idx + 2, dp));
    }
    /*
    T.C = FOR EACH STAIR WE ARE CALLING TWO TIMES SO A BINARY TREE WILL BE FORMED AND WE HAVE TWO STARTING POINT TWO BINARY TREES
    WILL BE FORMED T.C =O(2^N+2^N)=> O(2*(2^N))=>O(2^(N+1)
    S.C =O( RECURSIVE SPACE =>O(LOG N)) WHERE LOG N IS THE NUMBER OF LEVELS
     */


    /*-------------------------------------Recursive Method  going from n -1 to n-2 to 0 or 1 --------------------------------------------------------------------------------------------------

        public int minCostClimbingStair(int[] cost) {
            int n = cost.length;
            return Math.min(minCst(cost,n-1),minCst(cost,n-2));
        }
        public int minCst(int[] cost,int idx){
            if(idx == 0||idx == 1) return cost[idx];
            return cost[idx]+Math.min(minCost(cost,idx-1),minCost(cost,idx-2));
        }
    }
    /*---------------------------------------------------------------------------------------------------------------------------------------*/
 /*=================================Recursive method ends here ===============================================================================*/






/*========================================DP" RECURSION + MEMOIZATION  Going from 0 or 1 to n-1 or n-2 ================================================*/
    public static int minCostClimbingStair(int[] cost) {
        // WE ARE GOING LIKE THIS                         --------------------------> IF WE ASSUME IN ARRAY LIKE CALLS ARE GETTING LIKE IT
        // AND AFTER THE CALL FINISHED IT WILL STORE HERE ^ AS FROM HERE THE CALL WAS CALLED SO WEE COMPARE THE
        // DP[0] AND DP[1]
        int[] dp = new int[cost.length];
        dp[cost.length - 1] = cost[cost.length - 1];
        dp[cost.length - 2] = cost[cost.length - 2];
        for (int i = 0; i < dp.length-2; i++) dp[i]=-1;
        minCos(cost, 0, dp);
        for (int i = 0; i < dp.length; i++) System.out.print(dp[i] + " ");
        return Math.min(dp[0], dp[1]);
    }

    public static  int minCos(int[] cost, int idx, int[] dp) {
        if (idx == cost.length - 1 || idx == cost.length - 2) return dp[idx];
        if (dp[idx] != -1) return dp[idx];
        return dp[idx] = cost[idx] + Math.min(minCos(cost, idx + 1, dp), minCos(cost, idx + 2, dp));
    }
    /*
    T.C =O(N) WHERE N IS THE NUMBER OF STAIRS WE FIRST MOVE IDX+1 means we move all stairs TYPE SEE THE FUNCTION THEN AFTER THIS RETURN THE ARRAY IS ALREADY FILL
    S.C =O(N(where n = number of stairs STACK SPACE)+N(FOR DP ARRAY)
    THE ith index of an array ,array[i] shows that current stairs value + min_path from its [index+1,n-1th ] index
    in this case i had put the last two index value because for the n-1 th index its value +min of top stair i.e. nth which we consider zero
    and for n-2 th index we are putting its value + min value of the n-1th index and the nth top stair which is(+ve,0)
    so n-2 th stair value is that stair value + 0

    we are working on this as we have two ends points and when we are n-2th only then why we will go to n-1 th as we want the minimum
    we directly take jump from n-2 th to n two steps as the algo is simple its own value +Minimum(idx+1 stir value,idx+2 th stair value)
    n-2 th stair value + ( n-1 th stair value,0) so obviously we will choose 0
    if we want the maximum  then the case was different


    why i had put the values of n-1 th and n-2 th stair already for n-1 th stair its value + minimum(n,n+1)=>minimum(0,0)
    n is the destination and n+1 th does not exist so, we consider it zero
    for n-2th stair its value and minimum(n-1th stair value,nth stair value)=> minimum(n-1th it is +ve or ,0) =< still it is zero
     */

    public static int minCostClimbing(int[] cost) {
        /*
        WE ARE GOING LIKE THIS <------------------------    IF WE ASSUME IN ARRAY LIKE CALLS ARE GETTING LIKE IT
         AND AFTER THE CALL FINISHED IT WILL STORE HERE ^ AS FROM HERE THE CALL WAS CALLED SO WEE COMPARE THE
         DP[N-1] AND DP[N-2] IN RECURSIVE TREE IT IS TOP TO BOTTOM FOR MORE DRY RUN IN THE COPY AND DRAW DIAGRAMS
         I CANNOT EX[LAIN ANY LONGER BUT ARR[I] STORE ITS VALUE + [FROM ITS NEXT STAIR TO LAST STAIR] MINIMUM PATH  VALUE
         I AM WRITING NEXT STAIR VALUE THAT DOES NOT MEAN I AM INCLUDING NEXT STAIR VALUE IT MAY OR MAY NOT I AM JUST
         TRYING TO EXPLAIN SOMETHING EXPLAIN YOURSELF REMEMBER IT
         */

        int[] dp = new int[cost.length];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < dp.length; i++) dp[i]=-1;
        min(cost, cost.length-1, dp);
        for (int i = 0; i < dp.length; i++) System.out.print(dp[i] + " ");
        System.out.println();
        return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }

    public static  int min(int[] cost, int idx, int[] dp) {
        if (idx == 0 || idx == 1) return dp[idx];
        if (dp[idx] != -1) return dp[idx];
        return dp[idx] = cost[idx] + Math.min(minCos(cost, idx -1, dp), minCos(cost, idx - 2, dp));
    }
    /*
    T.C =O(N)
    S.C =O(N+N)
     */
/*==========================================DP : RECURSION + MEMOIZATION ==========================================================*/



/*==========================================DP :TABULATION ============================================================================*/
    public static int tabulation_1(int [] cost){
        int[] dp = new int [cost.length];
        // for this method I am moving from left to right ---------------------------->
        dp[0]=cost[0];
        dp[1] = cost[1];
        for(int i =2; i<cost.length;i++) dp[i]= cost[i]+Math.min(dp[i-1],dp[i-2]);
        return Math.min(dp[cost.length-1],dp[cost.length-2]);
        /*
        T.C =O(N-2)
        S.C =O(N)
         */
    }


public static int tabulation_2(int [] cost){
        int [] dp = new int [cost.length];
        // for this case we are moving from right to left <-------------------------------
         dp[cost.length-1] = cost[cost.length-1];
         dp[cost.length-2] = cost[cost.length-2];
         for(int i = cost.length -3;i>=0;i--) dp[i]= cost[i]+Math.min(dp[i+1],dp[i+2]);
         return Math.min(dp[0],dp[1]);
         /*
         T.C =O(N-2)
         S.C =O(N)
          */
         }




public static int tabulation_3(int [] cost){
        // IN THIS METHOD I AM GOING FROM LEFT TO RIGHT
        int stair_1 = cost[0];
        int stair_2 = cost[1];
        int x =-1;
        for(int i =2;i<cost.length;i++){
            x =stair_2;
            stair_2=cost[i]+Math.min(stair_1,stair_2);
            stair_1 = x;
            System.out.println(" After stair1   "+ stair_1+"    stair2   "+ stair_2);
        }
        return Math.min(stair_1,stair_2);
    }
/*
T.C = O(N-2)
S.C =O(1)
 */


public static int tabulation_4(int [] cost){
    // IN THIS METHOD I AM GOING FROM LEFT TO RIGHT
    int stair_1 = cost[cost.length-1];
    int stair_2 = cost[cost.length-2];
    int x =-1;
    for(int i =cost.length-3;i>=0;i--){
        x =stair_2;
        stair_2=cost[i]+Math.min(stair_1,stair_2);
        stair_1 = x;
        System.out.println(" After stair1   "+ stair_1+"    stair2   "+ stair_2);
    }
    return Math.min(stair_1,stair_2);
}
/*
T.C = O(N-2)
S.C =O(1)
 */




/*==============================================Tabulation Ends Here ===========================================================================*/
}