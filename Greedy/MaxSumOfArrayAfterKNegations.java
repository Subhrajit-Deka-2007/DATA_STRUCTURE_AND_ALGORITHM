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
            for(int i =0;i<nums.length;i++)pq.add(nums[i]);
        /*
        lOG 1 + LOG 2 + LOG 3 +--- +LOG N = LOG N!~ N LOG N
        */
            int rmv;
            for(int i =0;i<k;i++){
                rmv = pq.remove();
                pq.add(-1*rmv);
                /* K*(LOG N-1 + LOG N)
                 */
            }
            int sum =0;
            for(int ele :pq)sum+=ele;

            return sum ;
        /*
        T.C = N LOG N + K LOG N-1 + K LOG N +O(N(FOR TRACVERSING ))
            = O( (N+K) LOG N + K LOG N-1 + N )
        S.C =O(N)
        */
        }
        public  int largestSumAfterKNegations_2(int[] nums, int k) {

                    Arrays.sort(nums);// N LOG N
                    int minele;
                    int minidx;
                    while(k-->0){
                        minele =nums[0];
                        minidx =0;
                        for(int i =1;i<nums.length;i++){
                            if(nums[i]<minele){
                                minele = nums[i];
                                minidx =i;
                            }
                        }
                        nums[minidx]= -1*minele;
                    }

                    int maxsum =0;
                    for(int ele:nums)maxsum+=ele;
                    return maxsum;
                }
    /*
    T.C =O(N LOG N {FOR SORTING } + K*N +N )
    S.C =O(1)
    */
            }


