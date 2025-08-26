package Prefix_Sum_Technique;

/**
 *
 724. Find Pivot Index
 Given an array of integers nums, calculate the pivot index of this array.

 The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to the sum of all the numbers strictly to the index's right.

 If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left. This also applies to the right edge of the array.

 Return the leftmost pivot index. If no such index exists, return -1.



 Example 1:

 Input: nums = [1,7,3,6,5,6]
 Output: 3
 Explanation:
 The pivot index is 3.
 Left sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11
 Right sum = nums[4] + nums[5] = 5 + 6 = 11
 Example 2:

 Input: nums = [1,2,3]
 Output: -1
 Explanation:
 There is no index that satisfies the conditions in the problem statement.
 Example 3:

 Input: nums = [2,1,-1]
 Output: 0
 Explanation:
 The pivot index is 0.
 Left sum = 0 (no elements to the left of index 0)
 Right sum = nums[1] + nums[2] = 1 + -1 = 0


 Constraints:

 1 <= nums.length <= 104
 -1000 <= nums[i] <= 100
 */
public class PivotIndex {
    public static void main(String[] args) {

    }
}
class Solution {
    public int pivotIndex(int[] nums) {

        // brut force traversing each element
        //return brut_force(nums);

        // optimize using extra space
        // return   optimize(nums);


        // MORE OPTIMIZE APPROACH WITHOUT I=USING EXTRA SPACE
        return more_optimize(nums);
    }
    public int optimize(int [] nums){
        /*
        Algo : So we will make two array prefix and suffix and start comparing from n-1 or from to both and
        if there is such index then in both and prefix there will be a element whise value will be same that element index is our
        answer
        It works because we are told that we have to divide into two parts such that its left side element ssum == rightside      elements sum so it works perfectly prefix sum and suffix hold this concept vey clearly
        so

        */
        // LIITE MORE OPTIMIZE WE CAN CHANGE THE QUESTION ARRAY ITSELF FOR PREFIX OR SUFFIX ARRAY
        int [] prefix = new int [nums.length];
        int[] suffix = new int [nums.length];
        prefix[0] = nums[0];
        suffix[nums.length-1] = nums[nums.length-1];
        int i =1;
        int j = nums.length - 2;
        while(i<nums.length&&j>=0){
            prefix[i]=prefix[i-1]+nums[i++];
            suffix[j]= suffix[j+1]+nums[j--];
        }
        for( i =0; i<nums.length;i++) if(prefix[i] == suffix[i]) return i;

        return -1;
    }

    /*
    T.C =
    =>FOR FILLING THE PREFIX AND SUFFIX = 2N AS ON EACH TERATION WE ARE DOING 2 OPERATIONS SO 2+2+--+2 =2N
    => ANOTHER N FOR CHECKING IT IS VALID OR NOT IN WORST CASE IF ELEMENT IS NOT THERE THEN WE TRAVEL N
    T.C => 0(2N+N)=> O(3N)
    S.C => 0(2N)
    8*/
    public int brut_force(int [] nums){
        int n = nums.length;
        for(int i =0; i<n;i++){
            int leftSum =0; int rightSum = 0;
            for(int j =0 ;j<i;j++) leftSum+=nums[j];
            for(int j =i+1;j<n;j++) rightSum+= nums[j];
            if(leftSum ==rightSum) return i;
        }
        return -1 ;
    }
    /*
    T.C =O((N-1)*N)// FOR EACH N ELEMENT WE ARE TRAVERSING N-1 TIMES SO T.C =O(N^2-N)
    S.C =O(1)
    */
    public int more_optimize(int[] nums){
        // IF WE ARE GIVEN WE CANNOT TAKE an extra array then take an extra array
        int n = nums.length;
        for(int i =1; i<n;i++)nums[i]+=nums[i-1];

        for(int i =0; i<n;i++){
            int leftSum = 0;
            if(i>0)leftSum = nums[i-1];// FOR HANDLING ZEROTH INDEX
            int rightSum = nums[n-1]-nums[i];
            if(leftSum ==rightSum)return i;
            // if this true means both side sum should be true if it is true for 0th index then
            // its leftSum is zero so its rightSum should be zero

            // h/w : leetcode 2640
        }
        return -1;
    }
   /*
   T.C =O(N+N)
   S.C =O(1)

   */
}
