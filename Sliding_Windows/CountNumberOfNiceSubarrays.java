package Sliding_Windows;

import java.util.HashMap;
import java.util.Map;

/**
 * 1248. Count Number of Nice Subarrays
 * Given an array of integers nums and an integer k. A continuous subarray is called nice if there are k odd numbers on it.
 *
 * Return the number of nice sub-arrays.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,1,2,1,1], k = 3
 * Output: 2
 * Explanation: The only sub-arrays with 3 odd numbers are [1,1,2,1] and [1,2,1,1].
 * Example 2:
 *
 * Input: nums = [2,4,6], k = 1
 * Output: 0
 * Explanation: There are no odd numbers in the array.
 * Example 3:
 *
 * Input: nums = [2,2,2,1,2,2,1,2,2,2], k = 2
 * Output: 16
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 50000
 * 1 <= nums[i] <= 10^5
 * 1 <= k <= nums.length
 */
public class CountNumberOfNiceSubarrays {
    public static void main(String[] args) {

    }

    public int numberOfSubarrays(int[] nums, int k) {
       return method_1(nums,k);
    }

    public int method_1(int[] arr, int k) {
        // The method was pure sliding window SIRS OWN PERSONAL METHOD FROM  NOW ANOTHER METHOD OF THINKING
        int i = 0, j = 0, a = 0, b = 0;// for this question we need four variables and put initially all of them in 0 okay
        int n = arr.length;
        int count = 0;
        int k2 = 0;
        // move i to first odd number
        while (i < n && arr[i] % 2 == 0) i++;
        /*
        for cases if there is only even numbers 4622222 then it will cross that and give us array index out of bound error
         initially we put a in O th only  for that case also as when first number is odd itself then 12444222
         and,we have to put j in kth odd number and put b before the k+1 th odd number
         for finding kth odd number we will make another variable k2 which value we will do ++ when we get an odd number
         it acts as counter
         */
        while (j < n && k2 < k) if (arr[j++] % 2 != 0) k2++;
        if(k2<k) return 0; // when the number of odd numbers in the array are less than k then we return 0 as it is not possible only as if there are no k odd numbers then there is no answer only
        /*
         I am writing like this for each whether the condition
         is true or false j will move as condition will be check always

            we can write   if(arr[j]%2!=0)k2++;
                           if(k2==k) break ;
                                 j++
             as when we are doing j++ we will be one place ahead so just do j--;
             */
        j--;

        /*
        Now we have to put b before the k+1 th odd number  just do b position = j+1 and then move till we didn't get the k+1 th odd number
        but b will be one step ahead just at k+1 th odd number just do b--
         */
        b = j + 1;
        while (b < n && arr[b] % 2 == 0) b++;// b<n as there can be no k+1 th odd number so, it will cross n
        b--;
        // Till now, we had just step up the initial window configuration
        while (b < n) { // as a is the start of the window and b is the end of the window so we will write condition for the main loop with b
            // Sliding window
            count += (i - a + 1) * (b - j + 1);// math
            // as we have to put a after the 1 so for another window we will just put a one step ahead of i before moving i
            a = i + 1;
            i++;// now a and i are in same place
            while (i < n && arr[i] % 2 == 0) i++;
            // now put j in correct place
            j = b + 1;
            // now it's time for b position
            b = j + 1;
            while (b < n && arr[b] % 2 == 0) b++;
            b--;
        }
        return count;
    }
    /*
    T.C =O(4N) IN WORST CASE WE LET ALL VARIABLES MOVE N TIMES THEN T.C =O(4N)
    S.C =O(1)
     */
    public int method_2(int[] arr, int k){
        int a , b =0;
        // Method 2 : Prefix Sum technique
        int n = arr.length,count =0;
        // first step is to make array zeros and one's
        for(int i =0;i<n;)arr[i++]%=2;
        // now turn the array into prefix sum array
        for(int i =1;i<n;i++)arr[i]+=arr[i-1];
        // Now make a hashmap and add all the elements of their prefix sum and add their first index in the map <ele,their first index>
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0; i<n;i++){
            if(!map.containsKey(arr[i]))map.put(arr[i],i);
            a = 0;
            if(map.containsKey(arr[i]-k))a =map.get(arr[i]-k);// this line was added
            b =0;
            if(map.containsKey(arr[i]-k+1))b = map.get(arr[i]-k+1);
            if(arr[i]==k)count+=b-a+1;
            if(arr[i]>k) count+=b-a;

        }
        return count ;
    }
}

