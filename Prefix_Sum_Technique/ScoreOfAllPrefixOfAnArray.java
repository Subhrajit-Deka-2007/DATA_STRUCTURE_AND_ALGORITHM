package Prefix_Sum_Technique;

/**
 *

 2640. Find the Score of All Prefixes of an Array

 We define the conversion array conver of an array arr as follows:

 conver[i] = arr[i] + max(arr[0..i]) where max(arr[0..i]) is the maximum value of arr[j] over 0 <= j <= i.
 We also define the score of an array arr as the sum of the values of the conversion array of arr.

 Given a 0-indexed integer array nums of length n, return an array ans of length n where ans[i] is the score of the prefix nums[0..i].



 Example 1:

 Input: nums = [2,3,7,5,10]
 Output: [4,10,24,36,56]
 Explanation:
 For the prefix [2], the conversion array is [4] hence the score is 4
 For the prefix [2, 3], the conversion array is [4, 6] hence the score is 10
 For the prefix [2, 3, 7], the conversion array is [4, 6, 14] hence the score is 24
 For the prefix [2, 3, 7, 5], the conversion array is [4, 6, 14, 12] hence the score is 36
 For the prefix [2, 3, 7, 5, 10], the conversion array is [4, 6, 14, 12, 20] hence the score is 56
 Example 2:

 Input: nums = [1,1,2,4,8,16]
 Output: [2,4,8,16,32,64]
 Explanation:
 For the prefix [1], the conversion array is [2] hence the score is 2
 For the prefix [1, 1], the conversion array is [2, 2] hence the score is 4
 For the prefix [1, 1, 2], the conversion array is [2, 2, 4] hence the score is 8
 For the prefix [1, 1, 2, 4], the conversion array is [2, 2, 4, 8] hence the score is 16
 For the prefix [1, 1, 2, 4, 8], the conversion array is [2, 2, 4, 8, 16] hence the score is 32
 For the prefix [1, 1, 2, 4, 8, 16], the conversion array is [2, 2, 4, 8, 16, 32] hence the score is 64


 Constraints:

 1 <= nums.length <= 105
 1 <= nums[i] <= 109
 */
public class ScoreOfAllPrefixOfAnArray {
    public static void main(String[] args) {
        Soln a = new Soln();
        int [] nums = {2,3,7,5,10};
      long[] num = a.findPrefixScore(nums);

        for(int i =0; i<num.length;i++) System.out.print(num[i]+" ");

    }
}
class Soln {
    public long[] findPrefixScore(int[] nums) {
        long []  ans = new long[nums.length];
        long max =Long.MIN_VALUE;
        for(int i =0; i<nums.length;i++){
            max =Math.max(max,nums[i]);
            ans[i]=nums[i]+max;
        }
        // now  do prefix sum
        for(int i =1;i<ans.length;i++)
            ans[i]+=ans[i-1];
        return ans ;
    }
}
/*
T.C = O(N(FOR CONVERTING THE ARRAY)+N(FOR FINDING THE PREFIX SUM))
S.C = O(1) THE LONG ARRAY WE HAVE TO RETURN IT
*/

/* 1 MS SOLUTION
class Solution {
    static {
        for(int i=0;i<600;i++){
            findPrefixScore(new int[]{});
        }
    }
    public static long[] findPrefixScore(int[] nums) {
        long cover[] = new long[nums.length];
        long sum=0;
        int preMax=0;
        for(int i=0;i<nums.length;i++){
            preMax=Math.max(preMax,nums[i]);
            sum+=nums[i]+preMax;
            cover[i]=sum;
        }
        return cover;
    }
}
1 m/s solution
*/
