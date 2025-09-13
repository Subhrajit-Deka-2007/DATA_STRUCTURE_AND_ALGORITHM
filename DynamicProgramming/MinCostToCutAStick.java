package DynamicProgramming;

import java.util.Arrays;

/**
 *
 Code
 1547. Minimum Cost to Cut a Stick

 Given a wooden stick of length n units. The stick is labelled from 0 to n. For example, a stick of length 6 is labelled as follows:


 Given an integer array cuts where cuts[i] denotes a position you should perform a cut at.

 You should perform the cuts in order, you can change the order of the cuts as you wish.

 The cost of one cut is the length of the stick to be cut, the total cost is the sum of costs of all cuts. When you cut a stick, it will be split into two smaller sticks (i.e. the sum of their lengths is the length of the stick before the cut). Please refer to the first example for a better explanation.

 Return the minimum total cost of the cuts.



 Example 1:


 Input: n = 7, cuts = [1,3,4,5]
 Output: 16
 Explanation: Using cuts order = [1, 3, 4, 5] as in the input leads to the following scenario:

 The first cut is done to a rod of length 7 so the cost is 7. The second cut is done to a rod of length 6 (i.e. the second part of the first cut), the third is done to a rod of length 4 and the last cut is to a rod of length 3. The total cost is 7 + 6 + 4 + 3 = 20.
 Rearranging the cuts to be [3, 5, 1, 4] for example will lead to a scenario with total cost = 16 (as shown in the example photo 7 + 4 + 3 + 2 = 16).
 Example 2:

 Input: n = 9, cuts = [5,6,1,4,2]
 Output: 22
 Explanation: If you try the given cuts ordering the cost will be 25.
 There are much ordering with total cost <= 25, for example, the order [4, 6, 5, 2, 1] has total cost = 22 which is the minimum possible.


 Constraints:

 2 <= n <= 106
 1 <= cuts.length <= min(n - 1, 100)
 1 <= cuts[i] <= n - 1
 All the integers in cuts array are distinc
 */
public class MinCostToCutAStick {
    public static void main(String[] args) {
        tabulation();
    }

    class Solution {

        /*========================================================== Recursive Solution ==============================================================*/
        public int minCost(int n, int[] cuts) {
            int[] arr = new int[cuts.length + 2];

            int i;
            for (i = 0; i < cuts.length; i++) arr[i] = cuts[i];
            arr[i++] = 0;
            arr[i] = n;

         /* or we can simply do for(int i =0;i<cuts.length;i++)arr[i]=cuts[i]
         and then arr[i++]=0; arr[i] = n;
         // as we will use sorting after that no problem
          */
            Arrays.sort(arr);
            return cost(1, arr.length - 2, arr);
        }

        public int cost(int i, int j, int[] arr) {
            if (i > j) return 0;
            /*
            As we can perform cut when i==j as at i ==j the below condition occurs
               []|[]
               0 1 2
             */
            int min = Integer.MAX_VALUE;
            for (int k = i; k <= j; k++) {
                int len = arr[j + 1] - arr[i - 1];
                int totalCost = cost(i, k - 1, arr) + cost(k + 1, j, arr) + len;
                min = Math.min(min, totalCost);
            }
            return min;
        }
    }
/*
T.C =CUBIC
S.C = EXPONENTIAL
 */

    /*==========================================================Recursive Solution ends ==============================================================================*/


    /*========================================================== DP =================================================================================*/

    /**
     * FOR DP WE HAVE TO SEE HOW MANY VARIABLES ARE CHANGING IT IS i and j so, it is 2D DP
     * i start from 1 and go maximum n-2 at i==j it reach at n-2 so the i can go from [1 to n-2] so we need a size of n-1 as it will contain index from
     * [0 to n-2] and j starting from n-2 and goes till 1 (when i==j) so same size n-1
     */

    public int memo(int n, int[] cuts) {
        int[] arr = new int[cuts.length + 2];

        int i;
        for (i = 0; i < cuts.length; i++) arr[i] = cuts[i];
        arr[i++] = 0;
        arr[i] = n;
        Arrays.sort(arr);
        // i = 1 to m-2 || j = m-2 to 1
        int[][] dp = new int[arr.length - 1][arr.length - 1];// lets take one row and one column extra
        for (i = 0; i < arr.length - 1; i++) for (int j = 0; j < arr.length - 1; j++) dp[i][j] = -1;
        return cost_2(1, arr.length - 2, arr, dp);

    }

    public int cost_2(int i, int j, int[] arr, int[][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != -1) return dp[i][j];
            /*
            As we can perform cut when i==j as at i ==j the below condition occurs
               []|[]
               0 1 2
             */
        int min = Integer.MAX_VALUE;
        for (int k = i; k <= j; k++) {
            int len = arr[j + 1] - arr[i - 1];
            int totalCost = cost_2(i, k - 1, arr, dp) + cost_2(k + 1, j, arr, dp) + len;
            min = Math.min(min, totalCost);
        }
        return dp[i][j] = min;
    }

    /*=============================================== Tabulation ==========================================================================*/
    public static int tabulation() {
       int n =7;
        int[]cuts = {1,3,4,5};
        int[] arr = new int[cuts.length + 2];

        int i;
        for (i = 0; i < cuts.length; i++) arr[i] = cuts[i];
        arr[i++] = 0;
        arr[i] = n;
        Arrays.sort(arr);
        int[][] dp = new int[arr.length-1][arr.length-1];

    /*
     In memo i  was moving from 1 to m-2 || j moving from m-2 to 1  traverse will happen from m-2 to 1 for i and
      j from 1 to m-2 for traversing but for logic if it is from n-1 to 0 then first do it from 0 to n-2 and on the basis of that
      change the logic of the code and after that see where i and j is moving and use nested loop opposite to that
     */
        for (i = arr.length-2; i >=1; i--) {
            for (int j = 1; j < arr.length - 1; j++) {
                if (i > j) {
                    dp[i][j] = 0;
                    continue;
                }
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int len = arr[j + 1] - arr[i - 1];

                    int totalCost = dp[i][k - 1] + ((k+1>j)?0:dp[k + 1][j]) + len;// k+1 >j means i>j
                    min = Math.min(min, totalCost);
                }
                dp[i][j]= min;
            }
        }
        for(i =0;i<dp.length;i++) {
            for (int j = 0; j < dp.length; j++) {
                System.out.print(dp[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(dp[1][arr.length-2]);

        return dp[1][arr.length - 2];
    }
}

