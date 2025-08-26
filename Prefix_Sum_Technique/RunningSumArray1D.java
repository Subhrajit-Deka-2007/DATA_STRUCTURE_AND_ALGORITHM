package Prefix_Sum_Technique;


/**
 1480. Running Sum of 1d Array
 Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).

 Return the running sum of nums.

 Example 1:

 Input: nums = [1,2,3,4]
 Output: [1,3,6,10]
 Explanation: Running sum is obtained as follows: [1, 1+2, 1+2+3, 1+2+3+4].
 Example 2:

 Input: nums = [1,1,1,1,1]
 Output: [1,2,3,4,5]
 Explanation: Running sum is obtained as follows: [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1].
 Example 3:

 Input: nums = [3,1,2,10,1]
 Output: [3,4,6,16,17]

 Constraints:

 1 <= nums.length <= 1000
 -10^6 <= nums[i] <= 10^
 */
public class RunningSumArray1D {

    public static void main(String[] args) {
        int [] nums ={1,2,3,4};
        nums = runningSum(nums);

        for(int i =0; i<nums.length;i++) System.out.print(nums[i]+" ");

    }
    public static  int[] runningSum(int[] nums) {
        /*
        Algo : We are using a for loop from 1 to  n-1 and, we will make changes on the given array itself
         */
        for (int i =1; i<nums.length;i++) nums[i]+=nums[i-1];
        return nums;
    }
/*
T.C =O(N-1){WE ARE TRAVERSING FROM 1 TO N-1 TOTAL TERMS => N-1 [1 ,N-1]
S.C =O(1) { WE CHANGE THE GIVEN ARRAY ITSELF}
 */
}
/*
Leetcode 724 : Home work : Find Pivot Index
 */

