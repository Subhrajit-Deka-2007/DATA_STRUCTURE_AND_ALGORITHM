package Sliding_Windows;

/**
 *
 * Code
 * 209. Minimum Size Subarray Sum
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 * Example 1:
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 * Example 2:
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 * Example 3:
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 *
 *
 * Follow up: If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log(n)).
 */
public class MinSizeSubArraySum {
    public static void main(String[] args) {

    }
}
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        return optimize(target,nums);
    }









    /*-----------------------------------------Optimize approach -------------------------------------------------------------------*/

    public int optimize(int target, int [] arr){
/* we make two pointers i and j where i represent the starting of the window and j represent the end of the window
we move i when we get a valid window and we move j when we are searching for a valid window a valid window is sum >=k
So we move j till we get the sum >= k when we get it means we get the minimum valid window we caluclate its length and  compare minimum length the window's length is the minimum length for the window as it is useless to do j ++ as current windows element sum>=k then again by doing j ++ we are just adding one more elment whose size will be also greaterr then equal to >=k but
it is useless as we had already got the minumum window(length) for that index if we are to find maximum then we can do j++
.Now just do i++ and now check for the given range i and j  but when you shift it just minus that i element form that sum as we don't want ot use loop to calcualte the loop it is aclever trick if not just move j++ if its sum >= k then again do the same think calcualte len min len and repeat the process til j == arr.length
in my copy i can see that i and j moves from 0 to n-1 so both has moved n+n I HAD THOUGHT IT BY MYSELF
T.C =O(N+N)
S.C =O(1)
*/
        int n = arr.length,minLen = Integer.MAX_VALUE;
        int i = 0,j =0,sum =0;
        while(j<n&&sum<target){
            sum+=arr[j++];
/*
first window we are writing it seaparetly to become less complex on writing the code and also like we are not doing any i ++ only j ++ in our first window after getting only first window then we start i++ and j ++ so that is why it is written separately */

        }
        j--;
        int len ;
        while(i<n&&j<n){// Sliding windows
            len = j-i+1;// also we are calculating the length of the first window
            if(sum>=target)minLen = Math.min(minLen,len);
// before moving to i+1 just minus that element denoting that the element is now not a part of that window
            sum-=arr[i];
            i++;
            j++;// we were not adding these line then it was giving error think about it
/* okay i got it as we are in that window itself so when we are searching for the valid window we again add that element
 from where we are starting as we had done j -- when we get valid window then let say after doing i ++ it is not valid okay for searching valid we have to do j++ but j is that window's end point only so when we use the while loop we are again adding
 that jth element in the sum  so it gives us wrong answer we are not talking about i element okay we are not adding the i element again I am talking about the border element j so before using while loop  we should shift its poistion from j+1 then start doing that adding elements  in the sum  variable    Just your brain dude */

            while(j<n&&sum<target){
                sum+=arr[j++];// first window we are writing it seaparetly to become less complex on writing the code

            }
            j--;
        }
// Dry run once
// as when sum >= k j position will be one step forward  to stop it there we have to do j-- ,also it is necessary to do for other windows as if we dont do it will give us wrong answer it is necessaey to keep j in its limit
        if(minLen == Integer.MAX_VALUE)return 0;
        return minLen;
/*
in my copy i can see that i and j moves from 0 to n-1 so both has moved n+n I HAD THOUGHT IT BY MYSELF
T.C =O(N+N)
S.C =O(1)
*/
    }
    /*-----------------------------------------Optimize approach -------------------------------------------------------------------*/








    /*-----------------------------------Brutforce----------------------------------------------------------------------------------*/
    /*
    we will always start from brut force and i had optimize it okay if i get the anser i dodn;t check further i return still
    it is giving me TLE error
    It was giving TLE error but my code is correct
     */
    public int brut_force(int target, int[] nums){
        int j = 0;
        for(int i =1; i<= nums.length;i++){
            j =numOfSubarrays(nums, i,target);
            if(j!=-1) return j;
        }
        j =0;
        return j;
    }
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int sum =0;
        int count = -1;
        for(int i =0; i<k;i++) sum+=arr[i];
        if(sum>=threshold)return k;

        for(int i =1; i<arr.length-k+1;i++){
            sum-=arr[i-1];
            sum+=arr[k+i-1];
            if(sum>=threshold)return k;

        }
        return count;
        /*
         I HAD THOUGHT THE BRUT FORCE BY MYSELF
         Brut force for each k I am checking and k vary from 1 to arr.length
         In  worst case the target number is not present so outer loop will run from 1 to arr.length = n times
         and for each we are traversing n times
         SO T.C => N+N+N+----+N => O(N^2)
         S.C =O(1)

         T.C =O(N^2)
         S.C =O(1)
        */
    }
    /*--------------------------------------------------------Brut force ends------------------------------------------------------*/










    // Sir's Brut force
    public int sir_Brut_Force(int target,int[] arr){
        int n = arr.length, minLen = Integer.MAX_VALUE;
        for(int i =0; i<n;i++){
            int sum =0,j =i;
            while(j<n&&sum<target){
                // the loop can break in two conditions either after adding all elements that we start from a particular index it crosess
                // so we will break at that time or when sum is >= target
                sum+=arr[j++];
            }
            j--;
            // as when sum >= k j position will be one step forward  to stop it there we have to do j-- ,also it is necessary to do for other windows as if we dont do it will give us wrong answer it is necessaey to keep j in its limit
            int len = j -i+1;
            if(sum>=target) minLen = Math.min(minLen,len);
   /*
    when we get we will return it will give error in some cases
    it as there may be possibilities the length we are getting may not be minimum
    as in upcoming ways there is also possibility that the length could be samller so
    */


        }
        if(minLen == Integer.MAX_VALUE) return 0;
        return minLen;
/*
SIRS CODE TIME COMPLEXITY IN WORST CASE WE TRAVERSE N TIMES OUTER LOOP N TIMES AND INNER LOOP
N +N-1+N-2+----+1=>O(N(N-1)/2)=> O(N^2-N/2)
S.C =O(N)
*/
    }
}
/**
 Sub array Product Less than k Leet code 713
 */
