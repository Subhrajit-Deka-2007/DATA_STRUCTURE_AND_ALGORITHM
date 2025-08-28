package Prefix_Sum_Technique;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**

 * 560. Sub array Sum Equals K

 * Given an array of integers nums and an integer k, return the total number of sub arrays whose sum equals to k.
 *
 * A sub array is a contiguous non-empty sequence of elements within an array.
 *
 * Example 1:
 *
 * Input: nums = [1,1,1], k = 2
 * Output: 2
 * Example 2:
 *
 * Input: nums = [1,2,3], k = 3
 * Output: 2
 *
 * Constraints:
 *
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
/*
Let's take the array: `[10, 20, 30, 40]`

**Valid Sub arrays:**
* `[20, 30]` (This is a contiguous slice, and the order is preserved)
* `[10]` (A single element is a valid sub array)
* `[10, 20, 30, 40]` (The entire array is a sub array of itself)
* `[30, 40]`

**Invalid Sub arrays (These are actually Subsequences):**
* `[10, 30]` (This is **not** a sub array because it skips `20`. It's not contiguous.)
* `[40, 10]` (This is **not** a sub array because the order is changed.)

So, your intuition is spot on. For sub arrays, both contiguity and order are essential.
 */
public class SubarraySumEqualsK {
    public static void main(String[] args) {
     int [] nums ={1,2,3};
     brut_force(nums,3);
    }



    /* brut force generating all sub arrays and checking with each sub array it is equal to or its sum is equal to k we are
     using iterative method fpr generating sub array */
    public static int brut_force(int [] nums,int k ){
        int count = 0;
        int sum =0;
        for(int i =0; i<nums.length;i++){
            for(int j =i; j<nums.length;j++){
               sum += nums[j];
               if(sum == k) count++;
            }
             sum =0;
        }
        System.out.println("count "+count);
        return count;
    }

    /**
     *  /* HAPPY THAT I TRIED MYSELF SUB ARRAYS ARE PAIRS BUT IN SUB ARRAYS ORDER MATTERS
     *     => T.C = N +N-1+----+1 =O(N(N-1)/2)=> O((N^2-N)/2)
     *     S.C =O(1)
     *     */


    public int optimize(int [] arr, int k){
       /*  see the copy for the algo explanation by looking at the code only I can understand the algo

        ALGORITHM : FIRST WE FIND THE PREFIX SUM OF THE ARRAY AFTER FINDING THE PREFIX SUM OF ARRAY
        WE THEN MAKE A HASH MAP WHERE WE STORE < ELEMENT ,FREQUENCY OF THAT ELEMENT > there we check if element exist already by
        doing rem = ele -k if it exists then in count just add that number frequency and if ele =k then first increase its count then
        again check if rem = ele -k => rem = 7-7 = 0 if it exists in the map then adD that 0 elements frequency ALSO IN THE COUNT as [0,7] means 0+7 = k
        it is valid so for each element we have to FIND THE REAMING AND THEN WE HAVE TO CHECK LET SAY WE ARE GIVEN AN ARRAY
        [0,7-----]
        => PREFIX SUM = [0,7---]
        REM FOR ZERO IS -7 EXIST PUT IN MAP WITH (0,1) AND WE GOT 7 JUST INCREASE COUNT BY 1 AND THEN DO REM =7-7 => 0
        EXIST IN MAP THEN ADD IT FREQUENCY TO THE COUNT SO COUNT =2 =>[7],[0,7] SO CHECKING REM IS NECESSARY
         */
        int count =0, n = arr.length;
        // make a prefix sum array
        int ele ;
        for(int i =1; i<n;i++)arr[i]+=arr[i-1];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0; i<n;i++){
            ele =arr[i];
            if(ele==k) count++;
            int rem = ele - k;
            if(map.containsKey(rem))count+= map.get(rem);
            if(map.containsKey(ele)){
                int freq = map.get(ele);
                map.put(ele,freq+1);
            }else{// not in the map from first

                map.put(ele,1);
            }
        }
        return count ;
    }
    /*
    adding , get, remove , set in map all this we do in o(1) time complexity

    T.C = WE ARE FINDING PREFIX SUM N + AND EACH ELEMENT OF PREFIX SUM WE ARE ADDING WE ARE NOT REMOVING SO T.C N
        => O(N+N)
     S.C = O(N)// HASHMAP ALL ARRAY ELEMENTS ONLY GET INSERTED NOT GET REMOVED
     */










    public static int iterative(int [] nums){
        // iterative method of generating sub arrays using two nested loops
        String s = "";
        ArrayList<String> x = new ArrayList<>();
        for(int i =0; i<nums.length;i++){
            for(int j =i; j<nums.length;j++){
                s+=nums[j];
                x.add(s);
            }
            s="";
        }
        System.out.println("Subarrays"+x);
        return x.size();
    }







    // Recursive method of printing sub arrays
    public static void  recursive(int [] nums,int start,int end ,ArrayList<String>x){
        if(end == nums.length) return;
        String s ="";

        helper( nums, start,end,s,x);
        System.out.println();
        recursive(nums,start+1,start+1,x);
    }

    private  static void helper(int[] nums, int start, int end, String s,ArrayList<String>ans) {
        if(end == nums.length)return;
        s+=nums[end];
        ans.add(s);
        helper(nums,start,end+1,s,ans);

    }

      /** This two are part of recursive function */
}
