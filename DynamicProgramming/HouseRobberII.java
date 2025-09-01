package DynamicProgramming;

/**
 * 213. House Robber II
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have a security system connected, and it will automatically contact the police if two adjacent houses were broken into on the same night.
 *
 * Given an integer array nums representing the amount of money of each house, return the maximum amount of money you can rob tonight without alerting the police.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,3,2]
 * Output: 3
 * Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2), because they are adjacent houses.
 * Example 2:
 *
 * Input: nums = [1,2,3,1]
 * Output: 4
 * Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
 * Total amount you can rob = 1 + 3 = 4.
 * Example 3:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 1000
 */
public class HouseRobberII {
    public static void main(String[] args) {
      int[]  nums = {2,3,2};
        System.out.println(rob(nums));
    }
/*======================================DP : (TOP DOWN) DP RECURSION + MEMOIZATION ==================================================*/
public static int rob(int[] nums) {// here we are going from RIGHT TO LEFT
    if(nums.length==1) return nums[0];
    int [] dp = new int[nums.length+1];
    for(int i = 3;i<=nums.length;i++)dp[i]=-1;
  /*
   Here first we had to move from n-1 th to 1 as if we move from as if we move first from n-2 to 0 then again from
   n-1 th to 1 then the answer present at n-1 th get changed as n-2 included in the range n-1 and
   if we call first n-1 then after n-2 then it will n-2 to 0 so n-1 remain untouched as answer are stored in the last two indexes
   */
    dp[2]=nums[1];// we predefined dp[n-1] th index value
    dp[1]=0;
    take_skip_1(nums,nums.length-1,1,dp);

    for(int i =2;i<nums.length;i++)dp[i]=-1;
    dp[1] = nums[0];dp[0] =0;
    take_skip_1(nums,nums.length-2,0,dp);


    return Math.max(dp[nums.length],dp[nums.length-1]);
}


    public static int take_skip_1(int[] nums, int i, int j, int[] dp) {
        /*base case is no needed dp[i] is already filled so no needed so, it will handle it automatically */
        //    if (i == j) return dp[i] ;
        //    if (i > j) return dp[i];
        if (dp[i+1] != -1){
            return dp[i+1];
        }
        int take = nums[i] + take_skip_1(nums, i -2, j, dp);
        int skip = take_skip_1(nums, i - 1, j, dp);
        return dp[i+1] = Math.max(take, skip);

    }
    /*
    T.C =O(N+N)
    S.C =O(N+N)
     */

// FOR O TO N-2 GOING LEFT AND FOR N-1 TO 1 GOING RIGHT NOT POSSIBLE AS IT WILL IMPACT THE ANSWER STORE IN THE INDEXES AND
// VICE VERSA IS ALSO TRUE


/**--------------------------------------------------------------------------------------------------------------------------------------*/
    public static int rob_2(int[] nums) {// here we are going from left to right
        if(nums.length==1) return nums[0];
        int [] dp = new int[nums.length+1];

        for(int i =0; i<nums.length-2;i++)dp[i]=-1;
        /**
         * for the first call we start from 0 and ends in n-2 so, we fill -1 till n-1 and already defined the n-2th term value in dp
         * and in this call if we are at the n-3th position and, we take it then we move to n+1 th so, we fill the value with 0
         * as this is the maximum value that can reach if starting from 0
         */
        /*
        so we have to make two calls one is from 0th to n-2 and one from 1th house to n-1 th jouse
         as the houses are circular so .

         */
        dp[nums.length-2] = nums[nums.length-2];
        dp[dp.length-1]=-2;
        /*
        just for checking purpose that function starting from 0 ends in n-2 and maximum it can go is n
          and dp[dp.length-1] is written because its size is nums.length+1 so .
         */
        take_skip_2(nums,0,nums.length-2,dp);


        /* Function for 0 to n-2 ends here */


        /*Function from 1 to n-1 start here */
        for(int i =1; i<nums.length-1;i++)dp[i]=-1;// we are filing with 1 to n-2 with -1 as our maximum limit is n-1 so we put n-1 th value in the dp[n-1] th index
        /*
         We had filled it from 1 aas answer will be store at 1 only so we don't have to use an extra variable to
         store the result of the function
         we filled with -1 again as answer can get updated for this call 1 to n-1 so it will call the same things but the
         values can be changed
        */

        dp[nums.length-1]=nums[nums.length-1];// we predefined dp[n-1] th index value
        dp[dp.length-1]=0;
        /*
        for the call 1 to n-1 maximum it can reach is n , so we put the nth index of dp to zero  as we had made dp of n+1 size and
        and nums have size till n so when nums reach n we return the nth index of dp
         */
        take_skip_2(nums,1,nums.length-1,dp);

        return Math.max(dp[0],dp[1]);
    }


    public static int take_skip_2(int[] nums, int i, int j, int[] dp) {
        /*base case is no needed dp[i] is already filled so no needed so, it will handle it automatically */
        //    if (i == j) return dp[i] ;
        //    if (i > j) return dp[i];
        if (dp[i] != -1) return dp[i];
        int take = nums[i] + take_skip_2(nums, i + 2, j, dp);
        int skip = take_skip_2(nums, i + 1, j, dp);
        return dp[i] = Math.max(take, skip);

    }
     /*
    T.C =O(N-1(0 -> N-2)+N-1(1 -> N-1)
    S.C =O(N-1(STACK SPACE) + N+1 (FOR THE DP ARRAY));

     */
/*=========================================DP : Recursion + Memoization (TOP DOWN DP ENDS HERE )==============================================*/

/*=========================================DP : TABULATION ( BOTTOM UP )=======================================================================*/
public int tabulation_1(int[] nums){// going from left to right
    if(nums.length==1) return nums[0];
    if(nums.length ==2) return Math.max(nums[0],nums[1]);
    int [] dp = new int [nums.length-1];
    dp[0] = nums[0];
    dp[1]= Math.max(nums[1]+0,dp[0]);// i had added the zero for a purpose 0] [1 2 ---]
    int val ;
    for(int i =2; i<dp.length;i++){//[0,n-3)=> terms => N-2 terms
        // first filling the array for 0 to n-2
        //take
        dp[i] = Math.max(nums[i]+dp[i-2],dp[i-1]);
    }
    val = dp[dp.length-1];



dp[0] = nums[1];
dp[1]= Math.max(nums[2]+0,dp[0]);
for(int i = 2 ;i<dp.length;i++){
    dp[i] = Math.max(nums[i+1]+dp[i-2],dp[i-1]);
}
return Math.max(dp[nums.length-1],val);
}
/*
T.C =O(N-1+N-1)
S.C =O(N-1)
 */

public int tabulation_2(int[] nums){// going from left to right
    if(nums.length==1) return nums[0];
    if(nums.length ==2) return Math.max(nums[0],nums[1]);
    int [] dp = new int [nums.length-1];
    dp[nums.length-1-1] = nums[nums.length-1-1];
    dp[nums.length-1-2]= Math.max(nums[nums.length-1-2]+0,dp[nums.length-1-1]);// i had added the zero for a purpose 0] [1 2 ---]
    int val ;
    for(int i =nums.length-1-3; i>=0;i--){//[0,n-3)=> terms => N-2 terms
        // first filling the array for 0 to n-2
        //take
        dp[i] = Math.max(nums[i]+dp[i+2],dp[i+1]);
    }
    val = dp[nums.length-dp.length-1];



    dp[nums.length-1-1] = nums[nums.length-1];
    dp[nums.length-1-2]= Math.max(nums[nums.length-2]+0,dp[nums.length-1-1]);
    for(int i = nums.length-1-3 ;i>=0;i--){
        dp[i] = Math.max(nums[i+1]+dp[i+2],dp[i+1]);
    }
    return Math.max(dp[0],val);
}
/*
T.C =O(N-1+N-1)
S.C =O(N-1)
 */
}
