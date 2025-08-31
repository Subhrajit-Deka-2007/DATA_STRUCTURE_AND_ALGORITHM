package Sliding_Windows;

/**
 *
 Code
 1052. Grumpy Bookstore Owner

 tHE QUESTION IS NOT LIKE VARIABLE SIZE WINDOW IT IS LIKE MAX ELEMENTS IN K SIZE WINDOWS

 There is a bookstore owner that has a store open for n minutes. You are given an integer array customers of length n where customers[i] is the number of the customers that enter the store at the start of the ith minute and all those customers leave after the end of that minute.

 During certain minutes, the bookstore owner is grumpy. You are given a binary array grumpy where grumpy[i] is 1 if the bookstore owner is grumpy during the ith minute, and is 0 otherwise.

 When the bookstore owner is grumpy, the customers entering during that minute are not satisfied. Otherwise, they are satisfied.

 The bookstore owner knows a secret technique to remain not grumpy for minutes consecutive minutes, but this technique can only be used once.

 Return the maximum number of customers that can be satisfied throughout the day.



 Example 1:

 Input: customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], minutes = 3

 Output: 16

 Explanation:

 The bookstore owner keeps themselves not grumpy for the last 3 minutes.

 The maximum number of customers that can be satisfied = 1 + 1 + 1 + 1 + 7 + 5 = 16.

 Example 2:

 Input: customers = [1], grumpy = [0], minutes = 1

 Output: 1



 Constraints:

 n == customers.length == grumpy.length
 1 <= minutes <= n <= 2 * 104
 0 <= customers[i] <= 1000
 grumpy[i] is either 0 or
 */
public class GrumpyBookStoreOwner {
    public static void main(String[] args) {
GrumpyBookStoreOwner obj = new GrumpyBookStoreOwner();
      int[] customers ={1,0,1,2,1,1,7,5};
      int[]  grumpy = {0,1,0,1,0,1,0,1};
      int minutes = 3;
      obj.maxSatisfied(customers,grumpy,minutes);
    }
    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        return helper(customers,grumpy,minutes);
    }
    public int helper(int [] customer, int[] grumpy, int k ){
        int sum = 0;
        int maxSum = Integer.MIN_VALUE;
        int cstmr =0;
        for(int i =0; i<k;i++) {
            if(grumpy[i]==1)sum+=customer[i];
            else cstmr+=customer[i];
        }
        maxSum = Math.max(maxSum,sum);
        System.out.println();
        int n = grumpy.length;
        for(int i =1;i<n-k+1;i++){
            if(grumpy[i-1]==1)sum= sum - customer[i-1];
            if(grumpy[k+i-1]==1)sum+=customer[k+i-1];
            else {
                cstmr +=customer[k+i-1];
            }
            maxSum = Math.max(sum,maxSum);
        }
        return maxSum+cstmr;
    }
    /*
    T.C =O(k+n-k)
       =o(n)
    S.C =O(1)

    FOR ALGO SEE THE COPY AND THE CODE TO KNOW WHAT IS THE ALGORITHM
     */
}
