package DynamicProgramming;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class LengthoftheLongestSubsequenceThatSumstoTarget {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        //nums = [1,1,5,4,5], target = 3
        nums.add(1);
        nums.add(1);
        nums.add(5);
        nums.add(4);
        nums.add(5);
        int target = 3;
        System.out.println(lengthOfLongestSubsequence(nums,target));
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
    System.out.println(" ans "+ ans);

    if(ans<0)return -1;
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
        long take = 1 + take_skip_2(idx+1, nums, target - nums.get(idx),dp);
        return dp[idx][target] = Math.max(skip, take);
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
}
