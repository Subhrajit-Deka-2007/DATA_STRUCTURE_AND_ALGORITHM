package Prefix_Sum_Technique;

import java.util.Arrays;

public class LongestSubsequenceWithLimitedSum {
    public static void main(String[] args) {

    }
        public int[] answerQueries(int[] nums, int[] queries) {
            // Concept : sorting,prefixSum , Binary Search (Three Concept used )


            // First sort the array --> n log n
            Arrays.sort(nums);
            // Now find the prefix sum of it
            for(int i =1; i<nums.length;i++)nums[i]+=nums[i-1];

            // now userecursive  Binary Search on it
            // int [] count = new int [1];

        /* for(int i =0; i<queries.length;i++){
           count[0] =0;
           queries[i]= BS(0,nums.length-1,queries[i],nums,count);

         }
         */


     /*
    Binary Search Recursive approach
     */
            int n = nums.length;
            int [] ans = new int [queries.length];

            for(int i =0; i<queries.length;i++){
                int lo = 0, hi = n-1;
                while(lo<=hi){
                    int mid = lo+(hi-lo)/2;
                    if(nums[mid]>queries[i]) hi = mid -1;
                    else{// we will update our answer both for less then and equal to element and if greater we just move to [0,mid-1] we just reduce the search space
                        ans[i] = Math.max(ans[i],mid+1);
                        lo = mid+1;
                    }
                }
            }
/*
T.C =O(N LOG N (FOR SORTING)+N(FOR PREFIX SUM)+B.S for each element and total elements in queries is m => M*LOG N )
S.C =O(1); for sorting if built in sort uses quick sort uses log n and n for merge sort
*/


            return ans;
        }

        /*---------------------------------------------------------------------------------------------------------------------*/




// Binary Search Recursive approach -1

        public static int Binary_Search(int lo, int hi, int ele, int[] nums, int[] count) {// Recursive approach
/* as we are not working on function okay
we are working in complete different way we are just working on count [0] we are focusing we are not finding the output with the function return type itself  clever approach but function is returning difffernet we are focussed on only vhanging the pass by
refereence variable
*/
            if (lo > hi) return 0;
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= ele) {
                count[0] = Math.max(count[0], mid + 1);
                if(nums[mid]<ele) return Binary_Search(mid+1, hi, ele, nums, count);
                else return count[0];
            }
            else return Binary_Search(lo,mid-1,ele,nums,count);
            /* we no need to store for this call as it is not necessary as we call it when nums[mid]>ele so its output is not useful to us
             */

        }





/// Binary Search Recursive approach - 2 (here we play with the return type of function )


        public static int BS(int lo, int hi, int ele, int[] nums, int[] count) {// Recursive approach
            // Done with pure function  return type
            if (lo > hi)  return 0;
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] <= ele) count[0] = Math.max(count[0], mid + 1);
            if (nums[mid] < ele) count[0] =Math.max(BS(mid + 1, hi, ele, nums, count),count[0]);
            else if(nums[mid]>ele) BS(lo, mid - 1, ele, nums, count);
            // necessary else if(nums[mid]>ele) as we are making call for nums[mid] == ele unnecessary calls
        /*
        we will use these for call as we call it when nums[mid] >ele and we don't want its result to be our answer or i mean we don't want to include its outcome in the answer
        */

            return count[0];
        /*
        T.C=O(M* LOG N )// FOR M QUERIES EACH TIME WE MAKE CALLS LOG N
        S.C =O(LOG N)RECURSIVE SPACE +log n (for quick sort if built in sort uses quik sort and n for merge sort )
        */
        }
    /*
    Binary Search Recursive approach
     */

    }

