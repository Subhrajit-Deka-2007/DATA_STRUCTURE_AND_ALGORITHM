package DynamicProgramming;

/**
 * 198. House Robber
 *
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security systems connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 2:
 *
 * Input: nums = [2,7,9,3,1]
 * Output: 12
 * Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
 * Total amount you can rob = 2 + 9 + 1 = 12.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 40
 */
public class HouseRobber {
    public static void main(String[] args) {
     int [] cost={5,15,6,20,22,1};
        int [] dp = new int [cost.length+1];
        for(int i =0; i<=cost.length;i++)dp[i]=-1;
        dp[1] = cost[0];
        dp[0]=0;
        HouseRobber obj = new HouseRobber();
        System.out.println(obj.rob_2(cost));
    }

/*========================================== RECURSIVE APPROACH ==========================================================================*/
    public int rob(int[] nums) {
     return take_skip_1(nums,0,nums.length);
    }
    public int take_skip_1(int[] nums,int i,int j) {
        if (i == j - 1) return nums[i];
        if (i == j) return 0;
        // On each place the robber has two option either rob that house or skip the house
        int take = nums[i] + take_skip_1(nums, i + 2, j);
        int skip = take_skip_1(nums, i + 1, j);
        return Math.max(take, skip);
        /*
        T.C =O(2^N) A TREE IS FORMING EACH HAS TWO OPTIONS
        S.C =O(N)
         */
    }

/*========================================== RECURSIVE APPROACH ENDS HERE =====================================================================*/










/*===========================================DP : RECURSION + MEMOIZATION APPROACH ===============================================================*/
public int rob_1(int[] nums) {// we are going from left to right
    int [] dp = new int [nums.length+1];
    for(int i =0; i<nums.length;i++)dp[i]=-1;
    dp[dp.length-2] = nums[nums.length-1];// or i can write dp[nums.length-1] = nums[nums.length-1]

    /* this is for base cases when i is at n-2 th index when it takes and calls nth which is
    array index out of the bound for that case i had made dp[dp.length-1] and for n-2 th index if it skips
    so it will call the n-1th index for that i had used dp[dp.length-2] it is thinkable okay
     The base cases were when

     */
    dp[dp.length-1]= 0;// or i can write dp[nums.length]
    return take_skip(nums,0,nums.length,dp);
}
    public int take_skip(int[] nums,int i,int j,int[] dp){
      // it is necessary let say we are at n-2th index and, it takes n-1 th then it's the last index return that value of that index


       // i had a dp array of n+1 size , this is for the case when we are at the n-2 th index and the robber decided to skip then it will
        // be at n th index which is not present in the array just return 0


        // On each place the robber has two option either rob that house or skip the house
        if(dp[i]!=-1)return dp[i];// just return it if it is already filled

        int take =nums[i]+take_skip(nums,i+2,j,dp);// JUST THINK SIMPLE IF WE TAKE IT THEN WE CANNOT TAKE THE i+1 th  HOUSE
        int skip = take_skip(nums,i+1,j,dp);// IF WE SKIP IT WE CAN take then we can take the i+1 th house
        /* HERE I HAD NOT WRITTEN DP[i] = nums[i]+take_skip(nums,i+2,j,dp); And DP[i] = take_skip(nums,i+1,j,dp);
        because we lost the value that we got from  take the value will be replaced by the value of the
         skip will return so that makes no change on writing that and also like dp[i] represents maximum till now we get from
         [0th house to ith house ] + conditions that we cannot steal alternative house

         */
        return dp[i]=Math.max(take,skip);// then store the result which is maximum that is coming from by taking or skipping
    }
    /*
    T.C =O(N)
    S.C =O(N(RECURSIVE STACK )+N+1(ARRAY SPACE))
    */








public static int rob_2(int[] nums) {// we are going from right to left making top down dp act as bottom up dp
    int [] dp = new int [nums.length+1];
    for(int i =0; i<=nums.length;i++)dp[i]=-1;
    dp[1] = nums[0];
    dp[0]=0;
    return  take_skip_1(nums,nums.length-1,0,dp);
}
    public static  int take_skip_1(int[] nums,int i,int j,int[] dp){
//        if(i==j ) return dp[i+1];// For TRAVERSING WE ARE TAKING HELP OF NUMS ARRAY
//        if(i==j-1) return dp[0];

        if(dp[i+1]!=-1)return dp[i+1];// no base case is needed as we had already fill it so dp [i+1] get hit and it will return
        // important lesson
        int take =nums[i]+take_skip_1(nums,i-2,j,dp);
        int skip = take_skip_1(nums,i-1,j,dp);
        return dp[i+1]=Math.max(take,skip);
    }
    /*
    T.C =O(N)
    S.C =O(N(RECURSIVE STACK )+N+1(ARRAY SPACE))
    */








    public static  int take_skip_2(int[] nums,int idx,int j,int[] dp,int i ){
        // Using dp array for traversal
        idx =i-1;
      /*  if(idx==j ) return dp[i+1];
       if(idx==j-1) return dp[0];
       The reason base case is not needed as dp [1] and dp[0] already filled so
       the case dp[idx] got hit and returns interesting thing i observe today
       */
        if(dp[idx]!=-1)return dp[idx];
        int take =nums[idx-1]+take_skip_2(nums,idx-2,j,dp,idx-1);
        int skip = take_skip_2(nums,idx-1,j,dp,idx);
        return dp[idx]=Math.max(take,skip);
         /*
    T.C =O(N)
    S.C =O(N(RECURSIVE STACK )+N+1(ARRAY SPACE))
    */

    }

/*============================================ DP : RECURSION + MEMOIZATION ENDS HERE ========================================================*/






/*======================================================= DP : TABULATION =====================================================================*/

}
