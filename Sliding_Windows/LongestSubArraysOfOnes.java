package Sliding_Windows;

/**
 1493. Longest Subarray of 1's After Deleting One Element

 Given a binary array nums, you should delete one element from it.

 Return the size of the longest non-empty subarray containing only 1's in the resulting array. Return 0 if there is no such subarray.

 Example 1:

 Input: nums = [1,1,0,1]
 Output: 3
 Explanation: After deleting the number in position 2, [1,1,1] contains 3 numbers with value of 1's.
 Example 2:

 Input: nums = [0,1,1,1,0,1,1,0,1]
 Output: 5
 Explanation: After deleting the number in position 4, [0,1,1,1,1,1,0,1] longest subarray with value of 1's is [1,1,1,1,1].
 Example 3:

 Input: nums = [1,1,1]
 Output: 2
 Explanation: You must delete one element.


 Constraints:

 1 <= nums.length <= 105
 nums[i] is either 0 or 1
 */
public class LongestSubArraysOfOnes {
    public static void main(String[] args) {

    }

        public int longestSubarray(int[] arr) {
            int n = arr.length, i =0, j =0;
            int z =0;// line 4 -7 added remove once and see why this is for
            for(int ele :arr){
                if(ele ==0) z++;//we are using this for line 43
            }
            if(z==0) return n-1;// this is for the case when there is only one and no zeroes and, it is mandatory that we have to remove an element if there is only one then also
            int zeros = 0,maxLen =0;
            while(i<n&&arr[i]==0)i++;// move 'i' to 1st 1
           // if(i==n) return 0; no need to write this okay as line 40 -43 it will handle it
            j =i;
            while(j<n){
                if(arr[j]==1)j++;
                else{// arr[j] == 0
                    if(zeros == 0){
                        j++;
                        zeros++;
                    }
                    else{
                        // zeros ==1 we are not changing zeros wee are keeping it one only after changing it to 1 from zero we are maiantaing that only
                        int len = j-i-1;
                        maxLen = Math.max(maxLen,len);
                        j++;
                        while(i<n&&arr[i]==1)i++;
                        i++;
                    }

                }
            }
            if(zeros ==0) return j-i;// this is the case when there is no zero in the middle
            int len = j-i-1;// this for when j will be == n and it will break the loop
            maxLen = Math.max(maxLen,len);
            return maxLen;
        }
/*
T.C =O(N+N)
S.C =O(1)
*/
    }