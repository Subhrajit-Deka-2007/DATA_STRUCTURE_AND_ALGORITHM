package DynamicProgramming;

/**
 * 416. Partition Equal Subset Sum
 * Given an integer array nums, return true if you can partition the array into two subsets such that the sum of the elements in both subsets is equal or false otherwise.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,5,11,5]
 * Output: true
 * Explanation: The array can be partitioned as [1, 5, 5] and [11].
 * Example 2:
 *
 * Input: nums = [1,2,3,5]
 * Output: false
 * Explanation: The array cannot be partitioned into equal sum subsets.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 200
 * 1 <= nums[i] <= 100
 */
public class PartitionSum {
    public static void main(String[] args) {

    }
    class Solution {
        public boolean canPartition(int[] nums) {
/*
 values os the array are +ve so we can use that conditions
 okay if the whole array sum is odd we cannot divide it into two equal subsets
 */
            int sum =0;
            for(int ele : nums) sum+=ele;
            if(sum%2 !=0) return false;
            int[][] dp = new int[nums.length][sum/2+1];
            for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j]=-1;
            return memoization(0,nums,sum/2,dp);

        }
        public boolean memoization(int idx,int[] arr,int target,int[][]dp ){
            // since in the recursive code we can see two variables index and sum are changing so, we make dp of dp[idx][sum] target =[0 to target]
            // idx =0 to n-1 don't make  boolean dp
            if(idx==arr.length) return(target == 0);
            if(dp[idx][target]!=-1) return (dp[idx][target]==1);
            boolean ans = false;
            boolean skip =  memoization(idx+1, arr, target,dp );
            if(target-arr[idx]<0)ans = skip;
            else{
                boolean pick = memoization(idx+1,arr,target-arr[idx],dp);
                ans = pick||skip;
            }
            if(ans) dp[idx][target] =1;// or dp[idx][target] =ans?1:0;
            else dp[idx][target] =0;
            return ans;
        }
/*
T.C =O(N*(T+1)) but i observe it is o(n) but still for a bigger bound where n*(t+1) are unique calls 
S.C =O(N*(T+1))
 */
        public static boolean  tabulation(int [] nums){

            int sum =0;
            for(int ele : nums) sum+=ele;
            if(sum%2 !=0) return false;
            int target = sum/2;
            int [][] dp = new int[nums.length][target+1];// here columns represent target
            for(int i =0;i<dp.length;i++){
                for(int j =0;j<dp[0].length;j++){
                    /*
                     The j represent the target which is going from 0 to target but during algo
                     we only focussed on the index
                     */
                    boolean  ans = false;
                    boolean skip =(i>0)?(dp[i-1][j]==1):(j==0);// i<1 base case hit there also two conditions
                    if(j-nums[i]<0)ans = skip;
                    else{
                        boolean pick =(i>0)?(dp[i-1][j-nums[i]]==1):(j==0);
                        ans = skip||pick;
                    }
                    dp[i][j] = (ans)?1:0;
                }
            }
            return (dp[nums.length-1][target]==1);
        }
        /*
        T.C =O(N*(T+1))
        S.C =O(N*(T+1))
         */
    }

    /**0 ms solution --> using bitwise concept
     * import java.util.*;
     *
     * class Solution {
     *     public boolean canPartition(int[] nums) {
     *         int sum =0;
     *         for(int num : nums)
     *             sum += num;
     *         if(sum % 2 == 1)
     *             return false;
     *         sum /= 2;
     *         BitSet ans = new BitSet(sum + 1);
     *         ans.set(sum);
     *         for(int num : nums){
     *             if(num > sum)
     *                 continue;
     *             ans.or(ans.get(num , sum+1));
     *             if(ans.get(0))
     *                 return true;
     *         }
     *         return false;
     *     }
     * }
     */
}
