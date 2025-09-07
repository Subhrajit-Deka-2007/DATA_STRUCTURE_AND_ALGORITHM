package DynamicProgramming;


import java.util.ArrayList;
import java.util.List;

/**
 * 2915. Length of the Longest Subsequence That Sums to Target

 * You are given a 0-indexed array of integers nums, and an integer target.
 *
 * Return the length of the longest subsequence of nums that sums up to target. If no such subsequence exists, return -1.
 *
 * A subsequence is an array that can be derived from another array by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3,4,5], target = 9
 * Output: 3
 * Explanation: There are 3 subsequences with a sum equal to 9: [4,5], [1,3,5], and [2,3,4]. The longest subsequences are [1,3,5], and [2,3,4]. Hence, the answer is 3.
 * Example 2:
 *
 * Input: nums = [4,1,3,2,1,5], target = 7
 * Output: 4
 * Explanation: There are 5 subsequences with a sum equal to 7: [4,3], [4,1,2], [4,2,1], [1,1,5], and [1,3,2,1]. The longest subsequence is [1,3,2,1]. Hence, the answer is 4.
 * Example 3:
 *
 * Input: nums = [1,1,5,4,5], target = 3
 * Output: -1
 * Explanation: It can be shown that nums has no subsequence that sums up to 3.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 1000
 * 1 <= nums[i] <= 1000
 * 1 <= target <= 1000
 */
public class LengthoftheLongestSubsequenceThatSumstoTarget {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        nums.add(1000);
//        nums.add(2);
//        nums.add(3);
//        nums.add(4);
//        nums.add(5);
        int target = 1000;
        lengthOfLongestSubsequence_1( nums,  target);
    }
/*=============================================== Recursion ===================================================================================*/
    public static int lengthOfLongestSubsequence(List<Integer> nums, int target) {// RECURSION TRYING OUT ALL POSSIBLE COMBINATIONS
        int ans = (int )take_skip_1(0,nums,target);
        System.out.println(" ans "+ ans);

        if(ans<0)return -1;
        return ans;
    }
    public static  long take_skip_1(int idx, List<Integer>nums, int target) {
        if (idx == nums.size()) {
            if (target == 0) return 0;
            else return Integer.MIN_VALUE;
        }

        long skip = take_skip_1(idx + 1, nums, target);
        if (target-nums.get(idx) < 0) return skip;
        long take = 1 + take_skip_1(idx+1, nums, target - nums.get(idx));
        return Math.max(skip, take);
    }
    /*
    T.C =O(2^N)
    S.C =O(N*(TARGET+1))
     */
/*============================================== Recursion ================================================================================================*/

/*============================================= DP : TOP DOWN (RECURSION + MEMOIZATION )====================================================================*/
public static int lengthOfLongestSubsequence_1(List<Integer> nums, int target) {// Optimizing
    long [][] dp = new long [nums.size()][target+1];
    for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j] =-1;
    int ans = (int )take_skip_2(0,nums,target,dp);
    // i is going from 0 to n-1
     for(int i=0;i<dp.length;i++) {
         for (int j = 0; j < dp[0].length; j++) System.out.print(dp[i][j]+" ");
         System.out.println();
     }
    System.out.println("Next line ");
     tabulation(nums,target);
     if(ans<0)return -1;
    System.out.println("ans "+ans );
    return ans;
}
    public static  long take_skip_2(int idx, List<Integer>nums, int target,long [][] dp ) {
        if (idx == nums.size()) {
            if (target == 0) return 0;
            else return Integer.MIN_VALUE;
        }
        if(dp[idx][target]!=-1) return dp[idx][target];
        long skip = take_skip_2(idx + 1, nums, target,dp);
        if (target-nums.get(idx) < 0) return dp[idx][target]= skip;
        else {long take = 1 + take_skip_2(idx+1, nums, target - nums.get(idx),dp);// we took one element
        return dp[idx][target] = Math.max(skip, take);}
        /*
         We want the longest so, we are returning the longest i.e. who gives it either take will give or skip
         so, we used Math.max(take,skip)
         as in knapsack both skip and take gives the answer
         as skip also form a combination and take also form a combination so, it is true only that both will
         have answers  of length of subsequence and, we decide in Math.max(skip,take)
         */

    }
/*
T.C =0(N*(TARGET+1))
S.C =O(N*(TARGET+1))

In 0/1 this is the general syntax and in Unbound  knapsack we don't do i+1 in take option simple
base case hit means we had got a combination and after skip just check if it is less than
or greater than just little optimize the recursive code but in dp array it is necessary as we form array of it only so
there is probability that we may get array index out of bounds

*/
/*0 M/S SOLUTION
class Solution {
    public int lengthOfLongestSubsequence(List<Integer> nums, int target) {
        int[] dp = new int[target + 1];
        Arrays.fill(dp, Integer.MIN_VALUE / 2);
        dp[0] = 0;
        int sum = 0;
        for (int i = 0; i < nums.size(); i++) {
            int n = nums.get(i);
            sum += n;
            for (int c = Math.min(sum, target); c >= n; c--) {
                dp[c] = Math.max(dp[c], dp[c - n] + 1);
            }
        }
        return dp[target] >= 0 ? dp[target] : -1;
    }
}
 */
public static  int tabulation(List<Integer>nums,int target ){
    long [][] dp = new long [nums.size()][target+1];
    for(int i =0;i<dp.length;i++){
        for(int j =0;j<dp[0].length;j++){
            long skip = ((i>0)?dp[i - 1][j]:((j==0)?0:Integer.MIN_VALUE));
            if (j-nums.get(i) < 0)  dp[i][j]= skip;// as in recursion there was return so here we have to put lese after if
            else {
                //long take = 1+((i>0)?(dp[i-1][j-nums.get(i)]):((j==0)?0:Integer.MIN_VALUE));// no need base case for j-nums.get(i) as we execute else only when nums.get(i)>=0
                long take = (nums.get(i) == j) ? 1 : ((i > 0) ? (1 + dp[i - 1][j - nums.get(i)]) : Integer.MIN_VALUE);
                // the above take was not able to handle some cases
                dp[i][j] = Math.max(skip, take);}
        }
    }
    int ans = (int )dp[nums.size()-1][target];
    for(int i =0;i<dp.length;i++){
        for(int j =0;j<dp[0].length;j++) System.out.print(dp[i][j]+" ");
        System.out.println();
    }
    if(ans<0)return -1;
    return ans;
}



/*
T.C =O(NUMS.SIZE*(TARGET+1))
S.C =O(NUMS.SIZE*(TARGET+1))
 */
public static int lengthOfLongestSubsequence_2(List<Integer> nums, int target){
long [][] dp = new long [nums.size()][target+1];
    for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j] =-1;
    int ans = (int)take_skip_3(0,nums,target,dp);


    if(ans<0)return -1;
    return  ans;
    //return tabulation_3(nums,target);
}
public static  long take_skip_3(int idx, List<Integer>nums, int target,long [][] dp ) {
    if (idx == nums.size()) {

        if (target == 0) return 0;
        else return Integer.MIN_VALUE;
    }
    if(dp[idx][target]!=-1) return dp[idx][target];
    long skip = take_skip_3(idx + 1, nums, target,dp);
    if (target-nums.get(idx) < 0) return dp[idx][target]= skip;
    long take;
    if(nums.get(idx)!=target)take =  1+take_skip_3(idx+1, nums, target - nums.get(idx),dp);/* means we come here only when target-nums(idx)>=0 and here in if it is only case that can hold is target-nums(idx)>0
        only when nums(idx)<target means it alone cannot be equal to the target so it will need to combine
        with other numbers to becmoe equal to target it is smaler then the target it cannot form it will need a friend so i just took that number so i put 1+ function(after returning it may or may not hold)depending on what the function return
        */

    else take =1;// denoting if the number itself is target then take the number
    return dp[idx][target] = Math.max(skip, take);
}
public int tabulation_3(List<Integer>nums,int target ){

    long [][] dp = new long [nums.size()][target+1];
    for(int i =0;i<dp.length;i++){
        for(int j =0;j<dp[0].length;j++){
            long skip = ((i>0)?dp[i - 1][j]:((j==0)?0:Integer.MIN_VALUE));
            if (j-nums.get(i) < 0)  dp[i][j]= skip;// as in recursion there was return so here we have to put lese after if
            else {
                // NEW (FIXED) LINE
                long take = (nums.get(i) == j) ? 1 : ((i > 0) ? (1 + dp[i - 1][j - nums.get(i)]) : Integer.MIN_VALUE);
// Okay on the basis of tis line i had made changes in the recursive code

// the above take line i took it from gemini I changed according to sir but it doenot work




// no need base case for j-nums.get(i) as we execute else only when nums.get(i)>=0
                dp[i][j] = Math.max(skip, take);}
        }
    }
    int ans = (int )dp[nums.size()-1][target];

    if(ans<0)return -1;
    return ans;
}

}

