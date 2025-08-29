package Sliding_Windows;

import java.util.ArrayList;
import java.util.List;

/**
 * 713. Subarray Product Less Than K
 * Given an array of integers nums and an integer k, return the number of contiguous subarrays where the product of all the elements in the subarray is strictly less than k.
 * Example 1:
 *
 * Input: nums = [10,5,2,6], k = 100
 * Output: 8
 * Explanation: The 8 subarrays that have product less than 100 are:
 * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
 * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 0
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 */
public class SubArrayProductLessThanK {
    // Not complete yet think like leet code 209 it is a variable size window question think like leetcode only
    public static void main(String[] args) {
        int[] nums = {10, 5, 2, 6};
        int k = 100;

        System.out.println(optimize(k, nums));
    }

    public  static int optimize(int target, int[] arr) {
        // Edge case: If the target is 1 or less, no product of positive integers can be smaller.
        if (target <= 1) {
            return 0;
        }
        List<List<Integer>> ans = new ArrayList<>();
        int n = arr.length;
        int count = 0;
        long product = 1; // Use long to prevent overflow

        int i = 0; // This is the left pointer of the window
        int j = 0; // This is the right pointer of the window

        // This single loop will handle both expanding and shrinking the window
        while (j < n) {
            // 1. Expand the window by including the element at the right pointer 'j'

            product *= arr[j];

            // 2. Shrink the window from the left if the product is too large
            // This inner loop is the key correction. It ensures the window is always valid.
            while (product >= target) {
                product /= arr[i];
                i++;
            }

            // 3. Add the count of new valid subarrays.
            // The number of new subarrays ending at 'j' is the length of the current window.
            count += (j - i + 1);

            // 4. Move the right pointer to continue expanding the window for the next iteration
            j++;
        }
        System.out.println(ans);
        return count;
    }
}





