package BitManipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 260. Single Number III

 * Given an integer array nums, in which exactly two elements appear only once and all the other elements appear exactly twice. Find the two elements that appear only once. You can return the answer in any order.
 *
 * You must write an algorithm that runs in linear runtime complexity and uses only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,1,3,2,5]
 * Output: [3,5]
 * Explanation:  [5, 3] is also a valid answer.
 * Example 2:
 *
 * Input: nums = [-1,0]
 * Output: [-1,0]
 * Example 3:
 *
 * Input: nums = [0,1]
 * Output: [1,0]
 *
 *
 * Constraints:
 *
 * 2 <= nums.length <= 3 * 104
 * -231 <= nums[i] <= 231 - 1
 * Each integer in nums will appear twice, only two integers will appear once.
 */
public class SingleNumberIII {
    public static void main(String[] args) {

    }
    public int[] singleNumber_1(int[] nums){
         /*
        M-1 USING HASHMAP OR HASH SET
        */
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<nums.length;i++){
            if(map.containsKey(nums[i]))map.remove(nums[i]);
            else map.put(nums[i],0);
        }
        int[] arr = new int[2];
        int j =0;
        for(int i : map.keySet())arr[j++]=i;
        return arr;
        /*
        T.C =O(N)// AS WE MAY THINK WE ARE REMOVING THE ELEMENTS ALSO IT SHOULD BE 2N BUT WE ARE REMOVING
        WHEN WE SEE DUPLICATE MEANS INSTEAD OF INSERTING THAT WE ARE JUST REMOVING FOR THAT DUPLICATE SO
        T.C IS O(N)
        S.C =O(N)
        */
    }
    public int[] singleNumber_2(int[] nums){
        // using hashset
        HashSet<Integer> set = new HashSet<>();
        for(int i =0;i<nums.length;i++){
            if(set.contains(nums[i]))set.remove(nums[i]);
            else set.add(nums[i]);
        }
        int[] arr = new int[2];
        int j =0;
        for(int i : set)arr[j++]=i;
        return arr;
    }
    public int[] singleNumber_3(int [] nums){
        boolean flag = false;
        int k=0;
        int [] arr = new int[2];
        for (int i = 0; i < nums.length; i++) {
            flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;
                if (nums[i] == nums[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) arr[k++]=nums[i];
        }
        return arr;
        /*
        T.C =O(N^2)
        S.C =O(1)
        */
    }
    public int[] singleNumber_4(int [] nums){
            Arrays.sort(nums);
            int [] arr = new int [2];
            int k =0;
            int i ;
            for( i = 0;i<nums.length-1;){
                if(nums[i]==nums[i+1]) i +=2;
            /*
            WHEN IT IS EQUAL WE TAKE TWO JUMPS AS GIVEN TWO ELEMENTS APPEAR ONCE AND
            THE REMAINING APPEAR TWICE */
                else{ arr[k++]=nums[i++];
                }
                if(k==2) return arr;// little optimize
            }
            if(k<2)arr[k] =nums[i];
            return arr;
        /*
        T.C =O(N LOG N (FOR SORTING ) + (N))
        S.C =O(LOG N ) FOR USING THE SORTING ALGORITHM
        */
        }
public int[] optimize(int [] nums){
        int xor = 0;
        for(int ele : nums)xor^=ele;
        int rightBitOff = xor^(xor-1);
        int mask = xor^rightBitOff;
        int b1 = 0,b2 = 0;
        for(int ele:nums){
            if((mask&ele)==0)b1^=ele;
            else b2^=ele;
        }
        int [] arr = {b1,b2};
        return arr;
}

    }

