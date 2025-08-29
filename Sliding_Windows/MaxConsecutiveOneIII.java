package Sliding_Windows;

/**
 * 1004. Max Consecutive Ones III
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
 * Example 1:
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * Example 2:
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 105
 * nums[i] is either 0 or 1.
 * 0 <= k <= nums.length
 */
public class MaxConsecutiveOneIII {
    public static void main(String[] args) {
     MaxConsecutiveOneIII obj = new MaxConsecutiveOneIII();
       int [] arr= {0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1};
        int k =3;
     obj.longestOnes(arr,k);
    }
        public int longestOnes(int[] arr, int k) {
            int i =0,j =0,n = arr.length;
            int zeros =0;
            int len =0;
            int maxLen = Integer.MIN_VALUE;

            while(j<n){
                if(arr[j]==1)j++;
                else{// means arr[j] ==0;

                    if(zeros<k){
                        zeros++;
                        j++;
                    }

                    else{// means arr[j] ==0 and number of zeros >k then just find the length
                        len = j-i;
                        maxLen= Math.max(len,maxLen);
                        if(arr[i]==1) while(arr[i]==1) i++;

                        else {// see the copy why  I had done it
                            zeros--;
                            i++;
                        }

                    }

                }
            }
            System.out.println(len);
            len = j- i;
            if(zeros<k) return arr.length;// to handle the case where number if zeros are less then k
            // see the code and dry run once see how you had thought took both example and example 2 for dry run
            if(zeros==k)maxLen = Math.max(len,maxLen);
            return maxLen;
        }
    /*
    Congrats dude you had done it by yourself appreciation from my side
    T.C =O(N+N)
    S.C =O(1)
    */
    }


