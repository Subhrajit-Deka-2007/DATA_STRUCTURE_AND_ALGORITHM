package Prefix_Sum_Technique;


    /**

     303. Range Sum Query - Immutable

     Given an integer array nums, handle multiple queries of the following type:

     Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
     Implement the NumArray class:

     NumArray(int[] nums) Initializes the object with the integer array nums.
     int sumRange(int left, int right) Returns the sum of the elements of nums between indices left and right inclusive (i.e. nums[left] + nums[left + 1] + ... + nums[right]).


     Example 1:

     Input
     ["NumArray", "sumRange", "sumRange", "sumRange"]
     [[[-2, 0, 3, -5, 2, -1]], [0, 2], [2, 5], [0, 5]]
     Output
     [null, 1, -1, -3]

     Explanation
     NumArray numArray = new NumArray([-2, 0, 3, -5, 2, -1]);
     numArray.sumRange(0, 2); // return (-2) + 0 + 3 = 1
     numArray.sumRange(2, 5); // return 3 + (-5) + 2 + (-1) = -1
     numArray.sumRange(0, 5); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3


     Constraints:

     1 <= nums.length <= 104
     -105 <= nums[i] <= 105
     0 <= left <= right < nums.length
     At most 104 calls will be made to sumRang
     */

    /* Brut force  */
    class NumArray_1 {
        int [] arr ;
        public NumArray_1(int[] nums) {
            this.arr= nums;

        }

        public int SUM_RANGE (int left, int right) {
            int sum =0;
            for(int i = left;i<=right;i++) sum += arr[i];
            return sum;
        }
    }
    /**
     Brut force
     T.C =>IF THERE ARE Q QUERIES AND EACH QUERIES TAKE N ON AN AVERAGE THEN T.C = Q*n
     S.C => O(1)
     */






    /*Optimize approach*/
    class NumArray_2{
        int [] nums ;
        public NumArray_2(int[] nums) {
            this.nums = nums;
            for(int i =1; i<nums.length;i++)nums[i]+=nums[i-1];
        }

        public int sumRange(int left, int right) {
            if(left ==0) return nums[right];
            return nums[right]-nums[left-1];
        }
 /*
    Optimize approach  :
    T.C =>For each query we are taking o(1) so for n queries we are taking o(N) time
    S.C => 0(1) we did not use an extra array we just point our array present inside the class to the array passed in constructor
     */
    }

    public class RangeSumQueryImmutable {
        public static void main(String[] args) {

        }
    }



