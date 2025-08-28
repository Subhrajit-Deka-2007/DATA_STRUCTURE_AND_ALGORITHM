package Prefix_Sum_Technique;

import java.sql.SQLOutput;

/**
 * 2483. Minimum Penalty for a Shop
 * You are given the customer visit log of a shop represented by a 0-indexed string customers consisting only of characters 'N' and 'Y':
 *
 * if the ith character is 'Y', it means that customers come at the ith hour
 * whereas 'N' indicates that no customers come at the ith hour.
 * If the shop closes at the jth hour (0 <= j <= n), the penalty is calculated as follows:
 *
 * For every hour when the shop is open and no customers come, the penalty increases by 1.
 * For every hour when the shop is closed and customers come, the penalty increases by 1.
 * Return the earliest hour at which the shop must be closed to incur a minimum penalty.
 *
 * Note that if a shop closes at the jth hour, it means the shop is closed at the hour j.
 *
 *
 *
 * Example 1:
 *
 * Input: customers = "YYNY"
 * Output: 2
 * Explanation:
 * - Closing the shop at the 0th hour incurs in 1+1+0+1 = 3 penalty.
 * - Closing the shop at the 1st hour incurs in 0+1+0+1 = 2 penalty.
 * - Closing the shop at the 2nd hour incurs in 0+0+0+1 = 1 penalty.
 * - Closing the shop at the 3rd hour incurs in 0+0+1+1 = 2 penalty.
 * - Closing the shop at the 4th hour incurs in 0+0+1+0 = 1 penalty.
 * Closing the shop at 2nd or 4th hour gives a minimum penalty. Since 2 is earlier, the optimal closing time is 2.
 * Example 2:
 *
 * Input: customers = "NNNNN"
 * Output: 0
 * Explanation: It is best to close the shop at the 0th hour as no customers arrive.
 * Example 3:
 *
 * Input: customers = "YYYY"
 * Output: 4
 * Explanation: It is best to close the shop at the 4th hour as customers arrive at each hour.
 *
 *
 * Constraints:
 *
 * 1 <= customers.length <= 105
 * customers consists only of characters 'Y' and 'N'
 */
public class MinimumPenaltyForShop {
    public static void main(String[] args) {
        String s ="YN";
        MinimumPenaltyForShop obj = new MinimumPenaltyForShop() ;
        System.out.println("The good closing hour is "+obj.bestClosingTime(s));
    }


    /*------------------------------------------------------------*/
    public int bestClosingTime(String customer) {

        return brut_force(customer);
    }
    public int brut_force(String customer  ){
        // not working working for some case but not all nice try
        if(customer.length()==1) return 0;
        int penalty =0;
        int prev_penalty = Integer.MAX_VALUE;
        int idx  =-1;
        int j =0;
        //"YYNY"
        for(int i =0; i<customer.length();i++){
            for( j =0; j<i; j++) if(customer .charAt(j)=='N'){penalty++;}
            for(int k =j+1;k<customer.length();k++)if(customer.charAt(k)=='Y'){penalty++;}
            if(prev_penalty>penalty){
                System.out.println(penalty);
                prev_penalty = penalty;
                idx ++;
            }
            penalty =0;
        }
        int x =0;
        for(int i =0; i<customer.length();i++) if(customer.charAt(i)=='N')++x;
        if(x == customer .length()) return 0;


        return idx+1;
    }
    /*------------------------------------------------------------*/
    class Solution {
        public int bestClosingTime(String customer) {
            int n = customer.length();
            int[] pre = new int [n+1];
            // we will make n+1 size array

            // Now fill the prefix array
            for(int i =1; i<=n;i++){// we have n+1 indexes
                pre[i]+= pre[i-1];// making prefix sum for N array
                if(customer.charAt(i-1)=='N')pre[i]+=1;
            }
            // Now lets make the suffix array

            int [] suffix = new int [n+1];
            for(int i =n-1;i>=0;i--){
                suffix[i]+=suffix[i+1];
                if(customer.charAt(i)=='Y') suffix[i]+=1;

            }
            // now merge both prefix and suffix
            int min =Integer.MAX_VALUE;
            int idx  = 0;
            for(int i =0; i<=n;i++){
                pre[i]+=suffix[i];
                if(pre[i]<min){
                    idx =i;
                    min =pre[i];
                }
            }
            return idx;
        }
    /*
    T.C =0(N+N+N)
    S.C =O(N(FOR PREIX ARRAY)+N(FOR SUFFIX ARRAY))// WE CAN DO IT ONE ARRAY I.E. PREFIX ARRAY ITSELF
    */
    }

/*----------------------------------------------------------------------------------------------------------------------------*/
}
/*5M/S SOLUTION
class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        char[] ch = customers.toCharArray();
        int res = 0; // Best closing hour (initially assume close at hour 0)
        int customersLeft = 0; // Tracks the imbalance of 'Y' and 'N'

        // Iterate over each hour
        for (int i = 0; i < n; i++) {
            if (ch[i] == 'Y') {
                // 'Y' means we missed a customer if we close before this hour
                customersLeft++;

                // If this makes customersLeft > 0, it means closing earlier is worse
                if (customersLeft > 0) {
                    res = i + 1;      // Update best closing hour to next hour
                    customersLeft = 0; // Reset counter
                }
            } else {
                // 'N' means we wasted effort staying open → simulate penalty
                customersLeft--;
            }
        }

        return res; // Best hour to close with minimum penalty
    }
}
 */

