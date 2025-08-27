package Prefix_Sum_Technique;

/**
 *
 238. Product of Array Except Self
 Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].

 The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.

 You must write an algorithm that runs in O(n) time and without using the division operation.



 Example 1:

 Input: nums = [1,2,3,4]
 Output: [24,12,8,6]
 Example 2:

 Input: nums = [-1,1,0,-3,3]
 Output: [0,0,9,0,0]


 Constraints:

 2 <= nums.length <= 105
 -30 <= nums[i] <= 30
 The input is generated such that answer[i] is guaranteed to fit in a 32-bit integer.


 Follow up: Can you solve the problem in O(1) extra space complexity? (The output array does not count as extra space for space complexity analysis.)
 */
public class ProductOfArrayExceptSelf {
    public static void main(String[] args) {
        ProductOfArrayExceptSelf d = new ProductOfArrayExceptSelf();
        int[] nums = {1,2,3,4};
        //d.productExceptSelf(nums);
        //d.basicApproach(nums);
        d.sirs_method(nums);
    }

    public int[] productExceptSelf(int[] nums) {
        //return optimize_1;
        return optimize_2(nums);
    }

    public int[] optimize_1(int[] nums) {
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        prefix[0] = nums[0];
        suffix[nums.length - 1] = nums[nums.length - 1];
        for (int i = 1; i < nums.length; i++) prefix[i] = prefix[i - 1] * nums[i];

        for (int i = nums.length - 2; i > -1; i--) suffix[i] = suffix[i + 1] * nums[i];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) nums[i] = suffix[i + 1];
            else if (i == nums.length - 1) nums[i] = prefix[nums.length - 2];
            else nums[i] = prefix[i - 1] * suffix[i + 1];
        }
        return nums;
       /*
       T.C = O(N(FOR PREFIX)+N(FOR SUFFIX)+N(FOR PUTITNG BACK))
       S.C =O(N(FOR PREFIX)+N(FOR SUFFIX))
       WE CAN OPTIMIZE IT USING AN ONLY ONE EXTRA SPACE AND REUSING THE OTHER THAT IS GIVEN IN THE QUESTION
       */
    }

    public int[] optimize_2(int[] nums) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) prefix[i] = prefix[i - 1] * nums[i];

        for (int i = nums.length - 2; i > -1; i--) nums[i] *= nums[i + 1];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) nums[i] = nums[i + 1];
            else if (i == nums.length - 1) nums[i] = prefix[nums.length - 2];
            else nums[i] = prefix[i - 1] * nums[i + 1];
        }
        return nums;
    }

    /*
    T.C =O(N+N+N)
    S.C =O(N) ONLY FOR PREFIX SUM WE ARE USING AN EXTRA ARRAY
    */
/*
import java.util.Arrays;
class Solution {
    static{
        for(int i=0;i<500;i++){
            productExceptSelf(new int[]{0});
        }
    }
    public static int[] productExceptSelf(int[] nums) {
        int n=nums.length;
        int ans[]=new int[n];
        ans[0]=1;
        for(int i=1;i<n;i++){
            ans[i]=ans[i-1]*nums[i-1];
        }
        int suffix=1;
        for(int i=n-2;i>=0;i--){
            suffix*=nums[i+1];
            ans[i]*=suffix;
        }
        return ans;
    }
}
0 M.S SOLUTION
*/
    public int [] basicApproach(int [] nums){
        int product =1;
        int zero =0;
        // method using division operator
        for (int i = 0; i < nums.length; i++){

            if(nums[i]!=0) product*=nums[i];
            else zero ++;

        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0&& zero ==0) nums[i] = product/ nums[i];
            else if(nums[i]==0&&zero<2) nums[i] = product;
            else nums[i] =0;
        }
        for(int i = 0;i<nums.length;i++){
            System.out.println(nums[i]+" ");
        }
        return nums;
    }
    /*
      T.C =O(N(FOR CALCULATING THE PRODUCT)+N(FOR PUTTING THE VALUES))
    S.C =O(1)
    bUT WE ARE TOLD THAT WE DONT HAVE TO DO THIS METHOD
    */



    public int[] sirs_method(int[] nums){
        // we will make a prefix product array
        int [] prefix_product = new int [nums.length];
        prefix_product[0] =1;
        for(int i =1; i<nums.length;i++){
            prefix_product[i]=prefix_product[i-1]*nums[i-1];// o(N)
            System.out.print(prefix_product[i]+" ");
        }
        for(int i = nums.length-2;i>=0;i--)prefix_product[i]= prefix_product[i+1]*nums[i+1];//O(n)
        /*
        T.C =0(N+N)
        S.C =0(N)
         */
        System.out.println();
        for(int i =0;i<prefix_product.length;i++) System.out.print(prefix_product[i]+" ");
        return prefix_product;
    }

}


