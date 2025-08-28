package Sliding_Windows;
/**
1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold

Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k and average greater than or equal to threshold.

Example 1:

Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
Output: 3
Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of size 3 have averages less than 4 (the threshold).
Example 2:

Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
Output: 6
Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.


Constraints:

1 <= arr.length <= 105
1 <= arr[i] <= 104
1 <= k <= arr.length
0 <= threshold <= 104
 */
public class NumberofSubArraysOfSizeK {
    public static void main(String[] args) {
         NumberofSubArraysOfSizeK obj = new NumberofSubArraysOfSizeK();
       int [] arr = {2,2,2,2,5,5,5,8};
       int k = 3;
       int threshold = 4;
         obj.numOfSubarrays(arr,k,threshold);
    }
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        //My solution
        int sum =0;
        int count =0;
        for(int i =0; i<k;i++) sum+=arr[i];
        if(sum/k>=threshold)count++;
        for(int i =1; i<arr.length-k+1;i++){
            sum-=arr[i-1];
            sum+=arr[k+i-1];
            if(sum/k>=threshold)count++;

        }
        return count;
    }
    /*
    T.C =O(N)
    S.C =O(1)
    */
}
/* Oms solution
// Sliding Window
// O(n)
class Solution {
    static {
        for(int i = 0; i <= 300; i++){
            numOfSubarrays(new int[0], 0,0);
        }
    }

    public static int numOfSubarrays(int[] arr, int k, int threshold) {
        int n = arr.length;

        int sum = 0;
        for (int i = 0; i < k; ++i) {
            sum += arr[i];
        }

        int ans = 0;
        int minSum = k * threshold;
        for (int i = 0; i <= (n - k); ++i) {
            if (sum >= minSum) {
                ans += 1;
            }

            if (i < (n - k)) {
                sum += (arr[i + k] - arr[i]);
            }
        }

        return ans;
    }
}
 */

