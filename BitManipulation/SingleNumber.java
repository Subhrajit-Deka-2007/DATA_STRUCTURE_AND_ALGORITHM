package BitManipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

/**
 * 136. Single Number
 * Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
 *
 * You must implement a solution with a linear runtime complexity and use only constant extra space.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [2,2,1]
 *
 * Output: 1
 *
 * Example 2:
 *
 * Input: nums = [4,1,2,1,2]
 *
 * Output: 4
 *
 * Example 3:
 *
 * Input: nums = [1]
 *
 * Output: 1
 *
 *
 *
 * Constraints:
 *
 * 1 <= nums.length <= 3 * 104
 * -3 * 104 <= nums[i] <= 3 * 104
 * Each element in the array appears twice except for one element which appears only once
 */
public class SingleNumber {
    public static void main(String[] args) {

    }

    public static int singleNumber(int[] nums) {

        /*---------------------------------------------- Brut Force -----------------------------------------------------------------------------------*/

        Arrays.sort(nums);
        int ele = nums[0];
        int count = 0;
        for (int i = 1; i < nums.length; i++) {
            if (ele == nums[i]) count++;
            else if (ele != nums[i] && count >= 1) {
                ele = nums[i];
                count = 0;
            } else return ele;
        }
        return ele;
    }

/*
T.C FOR SORTING = O(N LOG N + N(FOR CHECKING ))
T.C => O(N LOG N )
S.C => O(log n ) is used for sorting the array

*/
    /*============================================ Brut force ended =====================================================================================*/









    /*============================================= Sir's BrutForce Solution ==============================================================================*/
    public int singleNumber_2(int[] nums) {
        boolean flag = false;
        for (int i = 0; i < nums.length; i++) {
            flag = false;
            for (int j = 0; j < nums.length; j++) {
                if (i == j) continue;
                if (nums[i] == nums[j]) {
                    flag = true;
                    break;
                }
            }
            if (!flag) return nums[i];
        }
        return 324;
    }
    /*
    T.C =O(N^2)
    S.C =O(1)
     */






/*============================================ 3rd Method ======================================================================================*/
public int singleNumber_3(int [] nums){
    Arrays.sort(nums);
    int i =0;
    while(i<nums.length-1){
        if(nums[i]!=nums[i+1])return nums[i];
        i+=2;
    }
    return nums[nums.length-1];
}
/*
T.C =O(N LOG N + N)
S.C = O(LOG N) FOR SORTING SPACE IS NEEDED
 */
/*========================================================================================================================================================*/






/*=================================================== 4TH METHOD ==============================================================================*/
public int singleNumber_4(int [] nums){
    HashMap<Integer,Integer> map = new HashMap<>();
    for(int i =0;i<nums.length;i++){
        if(map.containsKey(nums[i]))map.remove(nums[i]);
        else map.put(nums[i],0);

    }
    for(int k :map.keySet())return k;
    return 0;
    /*
    T.C =O(N)
    S.C =O(N)

     */
}
/*==================================================== Method 4 ends =============================================================================*/






/*==================================================== M -5  ======================================================================================*/
public int singleNumber_5(int [] nums){
    // Using HashSet
    HashSet<Integer>set = new HashSet<>();
    for(int i =0;i<nums.length;i++){
        if(set.contains(nums[i]))set.remove(nums[i]);
        else set.add(nums[i]);

    }
    for(int i :set)return i;
    return 0;
}
/*
T.C =O(n)
S.C =O(N)
 */
/*==================================================== M-5 ends ========================================================================================*/
public int singleNumber_6(int [] nums){
    int ele = 0;
    for(int i =0;i<nums.length;i++) ele =ele^nums[i];

    return ele;
}

}
/*
T.C =O(n)
S.c =o(1)
 */




