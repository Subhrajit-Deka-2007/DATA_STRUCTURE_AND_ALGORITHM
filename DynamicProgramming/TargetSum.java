package DynamicProgramming;

import java.util.Arrays;

/**
 * 494. Target Sum
 * You are given an integer array nums and an integer target.
 *
 * You want to build an expression out of nums by adding one of the symbols '+' and '-' before each integer in nums and then concatenate all the integers.
 *
 * For example, if nums = [2, 1], you can add a '+' before 2 and a '-' before 1 and concatenate them to build the expression "+2-1".
 * Return the number of different expressions that you can build, which evaluates to target.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,1,1,1], target = 3
 * Output: 5
 * Explanation: There are 5 ways to assign symbols to make the sum of nums be target 3.
 * -1 + 1 + 1 + 1 + 1 = 3
 * +1 - 1 + 1 + 1 + 1 = 3
 * +1 + 1 - 1 + 1 + 1 = 3
 * +1 + 1 + 1 - 1 + 1 = 3
 * +1 + 1 + 1 + 1 - 1 = 3
 * Example 2:
 *
 * Input: nums = [1], target = 1
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 20
 * 0 <= nums[i] <= 1000
 * 0 <= sum(nums[i]) <= 1000
 * -1000 <= target <= 1000
 */
public class TargetSum {
    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 1, 1};
        int[] sum ={0};
        for(int i =0;i<nums.length;i++)sum[0]+=nums[i];
        System.out.println("sum "+sum[0]);
        int target = 3;
        ways(0, nums, target);
        // Here we can see two things are changing idx and target idx = 0 to arr.length-1 and
        // the target is going from -sum to sum as we are trying all possible combination  so minimum -*(1+1+--+1) and maximum (1+1+---+1)
    }

    public static int ways(int i, int[] arr, int target) {
        if (i == arr.length) return (target == 0) ? 1 : 0;
        /* we had got one valid combination so return 1 if we had reach i = arr.length that means we had got a combination and it is not valid  as target!=0 so just return 0 represent not a valid combination
         */

// we don't have to put that if condition as number can be positive and negative
        int add = ways(i + 1, arr, target - arr[i]);
        // means we are taking a positive value means we are taking it from target so reduce from the target
        int sub = ways(i + 1, arr, target + arr[i]);
    /* assume like 1+1+1 we want -1 so 1+1+1+1-1 so that is why we plus
     means we are taking a negative value which does not exist then just add it to the target */
        return add + sub;
    /* as add means we are taking +ve and, we are getting some answer
     and if we take -ve we are getting some answers so, we add answers of both */
    }

    /*
    T.C =O(2^N)
    S.C =O(N) N = LENGTH OF THE ARRAY
    */




    public int head_memo(int [] arr, int target){
        int []  sum ={0};
        for(int i =0;i<arr.length;i++)sum[0]+=arr[i];
        // we will convert -sum to sum => sum to 2 sum
        int [][] dp = new int[arr.length][2*sum[0]+1]; // 2*sum+1 represent [0 to 2*sum +1]
        // Now fill the array with -1
        Arrays.fill(dp,-1);
        return memoization(0,arr,target,dp,sum);
    }
    public static int memoization(int i, int[] arr, int target,int[][] dp,int[] sum  ) {
/** Here in this question target is moving from -sum to sum as -ve index is not present so, we can do is -sum+sum to sum +sum
 * [0 to 2 sum ] number of elements 2sum+1 as zero included or Note :We can use hashmap for memoization for 1D ,2D ,0/1 , Unbounded knapsacks
 *
 */
        if (i == arr.length) return (target == 0) ? 1 : 0;// FOR NO BASE CASE WE HAVE TO FILL THIS PREVIOUSLY
        if(dp[i][target+sum[0]]!=0) return dp[i][target+sum[0]];
        /* WE ARE ADDING AN OFFSET SUM TO THE TARGET
         we will not write dp[i][target] as target can be minus also so
         it will give index out of bound error as we took all 1 as -ve then target becomes target+sum if there are 5 ones then
         it becomes 5+3 (target =3) so it will be more than the sum of the elements so, we have to again improve it
         maximum sum of elements 5 and with target it is 8 then why we don't add target also for array sir said no need to do that
         */
        int add = memoization(i + 1, arr, target - arr[i],dp,sum);

        int sub = memoization(i + 1, arr, target + arr[i],dp,sum);

        return dp[i][target+sum[0]]= add + sub;
    }
    /*
    T.C =O(N*(2SUM+1))=>NUMBER OF UNIQUE CALLS
    S.C =O(N*(2SUM+1))
     */
    public int head_memo_2(int [] arr, int target){
        int []  sum ={0};
        for(int i =0;i<arr.length;i++)sum[0]+=arr[i];
        /*we will convert res --> -sum to sum => 0 to 2 sum as we deal with target then if we choose only -1 then target becomes
         target+sum of all the elements so, it will not be -sum to sum it will be  from [target-sum to target+sum] for this we are making
         a variable res ---> which will go from -sum to sum where target-sum<-sum and target+sum>sum so,
         we had to make an extra variable for this .

         */

        int [][] dp = new int[arr.length][2*sum[0]+1]; // 2*sum+1 represent [0 to 2*sum +1]
        // Now fill the array with -1
        for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j] =-1;
        return memoization_2(0,arr,target,0,dp,sum);
    }
    public static int memoization_2(int i, int[] arr,int res, int target,int[][] dp,int[] sum  ) {
/** Here in this question target is not  moving from -sum to sum as when we add we are doing --(minus - minus) from thew target so
 * minimum it can reach is target-sum which is less than -sum and if we are taking -1 then we are doing ++(plus - plus)
 * so maximum it can reach is target + sum which is greater than sum so to avoid we make a extra variable result whose initial value
 * is 0 so, it will go from -sum to sum
 * */

        if (i == arr.length) return (res==target) ? 1 : 0;// FOR NO BASE CASE WE HAVE TO FILL THIS PREVIOUSLY
        if(dp[i][res+sum[0]]!=0) return dp[i][target+sum[0]];

        int add = memoization_2(i + 1, arr, res+arr[i],target,dp,sum);

        int sub = memoization_2(i + 1, arr,res-arr[i], target,dp,sum);

        return dp[i][res+sum[0]]= add + sub;
    }
    /*
    T.C =O(N*(2SUM+1))=>NUMBER OF UNIQUE CALLS
    S.C =O(N*(2SUM+1))
    the dp array will store all possible combination result
    We can use hashmap for -ve indexing
    We can also use Hashmap for memoization as -ve index is there in the question we can use hashmap in the question
    HashMap<Integer(row),target(can be +ve,-ve which represent  column and, it can be +ve, -ve and zero >
    for every HashMap<Integer(row) -->, in its corresponding we can form an array or, we can use a Hash Set
    for every row we can create a row as HashMap<Integer,HashMap<int(can be -ve as well as represent column as in a row there are
    multiple columns and we will store the cell value in the inside the hashmap of hashmap
    Hash Map<int,HashMap<int(-ve ,+ve,0 also),value>> HashMap<row,HashMap<col,cell value>>
    H/w leetcode 2786
     */


}


