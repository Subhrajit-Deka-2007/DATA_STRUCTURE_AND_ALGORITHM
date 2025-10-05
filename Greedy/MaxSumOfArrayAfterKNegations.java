package Greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

/** 1005. Maximize Sum Of Array After K Negations

 Given an integer array nums and an integer k, modify the array in the following way:

 choose an index i and replace nums[i] with -nums[i].
 You should apply this process exactly k times. You may choose the same index i multiple times.

 Return the largest possible sum of the array after modifying it in this way.



 Example 1:

 Input: nums = [4,2,3], k = 1
 Output: 5
 Explanation: Choose index 1 and nums becomes [4,-2,3].
 Example 2:

 Input: nums = [3,-1,0,2], k = 3
 Output: 6
 Explanation: Choose indices (1, 2, 2) and nums becomes [3,1,0,2].
 Example 3:

 Input: nums = [2,-3,-1,5,-4], k = 2
 Output: 13
 Explanation: Choose indices (1, 4) and nums becomes [2,3,-1,5,4].


 Constraints:

 1 <= nums.length <= 104
 -100 <= nums[i] <= 100
 1 <= k <= 104
 */
public class MaxSumOfArrayAfterKNegations {
    /**Optimize  Also if the elements in the array all are +ve && if k is even then we need to do anything at that time we just need to do anything
     * as the array will remain the same */


    public static void main(String[] args) {
        int [] nums ={-2,5,0,2,-2};
        int k = 3;
        Solution obj = new Solution();
        obj.largestSumAfterKNegations_2(nums,k);
    }
}
    class Solution {
        public int largestSumAfterKNegations_1(int[] nums, int k) {
/**======================================= METHOD 1 : USING PRIORITY QUEUE ===========================================================*/
            /* GREEDY APPROACH EACH TIME WE ARE CHOOSING THE MINIMUM ELEMENT */
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            for (int i = 0; i < nums.length; i++) pq.add(nums[i]);
        /*
        lOG 1 + LOG 2 + LOG 3 +--- +LOG N = LOG N!~ N LOG N
        */
            int rmv;
            for (int i = 0; i < k; i++) {
                rmv = pq.remove();
                pq.add(-1 * rmv);
                /* K*(LOG N-1 + LOG N)
                 */
            }
            int sum = 0;
            for (int ele : pq) sum += ele;

            return sum;
        /*
        T.C = N LOG N + K LOG N-1 + K LOG N +O(N(FOR TRACVERSING ))
            = O( (N+K) LOG N + K LOG N-1 + N )
        S.C =O(N)
        */
        }

        public int largestSumAfterKNegations_2(int[] nums, int k) {

            Arrays.sort(nums);// N LOG N
            int minele;
            int minidx;
            while (k-- > 0) {
                minele = nums[0];
                minidx = 0;
                for (int i = 1; i < nums.length; i++) {
                    if (nums[i] < minele) {
                        minele = nums[i];
                        minidx = i;
                    }
                }
                nums[minidx] = -1 * minele;
            }

            int maxsum = 0;
            for (int ele : nums) maxsum += ele;
            return maxsum;
        }
    /*
    T.C =O(N LOG N {FOR SORTING } + K*N +N )
    S.C =O(1)
    */

        public int largestSumAfterKNegations_3(int[] nums, int k) {
            /**
              Optimize  Also if the elements in the array all are +ve && if k is even then we need
              to do anything at that time we just need to do anything
              as the array will remain the same
             */

            Arrays.sort(nums);// N LOG N
            int minele;
            int minidx;
            int pos = 0;
            while (k > 0) {
                pos = 0;
                minele = nums[0];
                minidx = 0;
                if (nums[0] > 0) pos++;
                for (int i = 1; i < nums.length; i++) {
                    if (nums[i] > 0) pos++;
                    if (nums[i] < minele) {
                        minele = nums[i];
                        minidx = i;
                    }

                }
                if (pos == nums.length && k % 2 == 0) break;
                /*
                 ALSO HANDLE CASES WHEN THE GIVEN ARRAY IS ALREADY POSITIVE AND K IS EVEN ALSO LIKE AFTER CONVERTING AN ARRAY
                 DURING THE OPERATION THE ARRAY ELEMENT BECOME +VE AND K IS EVEN THEN NO NEED TO CHECK FURTHER JUST BREAK IT
                 OPTIMIZE
                 */

                k--;
                nums[minidx] = -1 * minele;
            }

            int maxsum = 0;
            for (int ele : nums) maxsum += ele;
            return maxsum;
        }
/** TEACHERS APPROACH
 *  public static int largestSumAfterKNegations(int[] nums, int k) {
 *         // Step 1: Sort the array to handle the most negative numbers first.
 *         Arrays.sort(nums);
 *
 *         int i = 0;
 *         // Step 2: Greedily flip all negative numbers to positive as long as we have k > 0.
 *         while (i < nums.length && nums[i] < 0 && k > 0) {
 *             nums[i] *= -1; // Flip the number.
 *             i++;           // Move to the next element.
 *             k--;           // Decrement the count of remaining flips.
 *         }
 *
 *         // Step 3: If k is still positive and odd, we must perform one last flip.
 *         // To minimize the reduction in sum, we flip the smallest number in the array.
 *         if (k % 2 == 1) {
 *             // Re-sort the array because the original negative numbers,
 *             // now positive, might not be the smallest overall.
 *             Arrays.sort(nums);
 *             // Flip the smallest element (which is at index 0 after sorting).
 *             nums[0] *= -1;
 *         }
 *
 *         // Step 4: Calculate the sum of all elements in the modified array.
 *         int sum = 0;
 *         for (int s : nums) {
 *             sum += s;
 *         }
 *
 *         // Step 5: Return the final calculated sum.
 *         return sum;
 *     }
  */
    }


