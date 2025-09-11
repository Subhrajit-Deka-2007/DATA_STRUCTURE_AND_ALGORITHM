package DynamicProgramming;

import java.util.ArrayList;

/**
 *
 Code
 300. Longest Increasing Subsequence
 Given an integer array nums, return the length of the longest strictly increasing subsequence.



 Example 1:

 Input: nums = [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 Example 2:

 Input: nums = [0,1,0,3,2,3]
 Output: 4
 Example 3:

 Input: nums = [7,7,7,7,7,7,7]
 Output: 1


 Constraints:

 1 <= nums.length <= 2500
 -104 <= nums[i] <= 104


 Follow up: Can you come up with an algorithm that runs in O(n log(n)) time complexity?
 */
public class Longest_Increasing_Subsequence {
    public static void main(String[] args) {
     head_Of_Brut_Force();

    }
    public static  void Subsequence(int idx,String s, ArrayList<String> ans,String x ){
        // Algo is simple : For each character we had option either take it or skip it
        if(idx == s.length()){// base case means we had got one subsequence
            ans.add(x);
            return ;
        }
        // take
        Subsequence(idx+1,s,ans,x+s.charAt(idx));
        // skip
        Subsequence(idx+1,s,ans,x);
       // we can use rec+ memo as both the parameter that are changing are of different data type
    }

public static void brut_force(int idx,int[] arr,ArrayList<ArrayList<Integer>>ans,ArrayList<Integer>s){
        if(idx==arr.length){
            ans.add(new ArrayList(s));
            return ;

        }
        /* Algo : first we will find all the subsequence and store in arraylist then select the increasing subsequence and returning
         the subsequence with maximum length */
    s.add(arr[idx]);
    brut_force(idx+1,arr,ans,s);
    s.removeLast();
    brut_force(idx+1,arr,ans,s);

}
// Now we had generate all the subsequence now we have to check if it is in increasing order or not
public static int head_Of_Brut_Force(){
    ArrayList<ArrayList<Integer>>ans = new ArrayList<>();
    int [] arr ={1,2,3,4,5,6};
    ArrayList<Integer>s = new ArrayList<>();
    brut_force(0,arr,ans,s);
    System.out.println(ans );
    // We had got the subsequence now we had to check if it is increasing or decreasing
    int max =Integer.MIN_VALUE;
    int x =   check(ans,max);
    System.out.println(" x "+ x);
    return x;
}

    private static int  check(ArrayList<ArrayList<Integer>> ans, int max) {
        int count =0;
        for(int i =0;i<ans.size();i++){
            for(int j =1;j<ans.get(i).size();j++){
                if(ans.get(i).get(j-1)<ans.get(i).get(j))count++;
            }
            if(count == ans.get(i).size()-1){
                max = Math.max(ans.get(i).size(),max);
            }
            count =0;
        }
        return max;
    }
// Brut force done by me don't forget to calculate the time complexity
}
