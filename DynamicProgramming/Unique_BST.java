package DynamicProgramming;

import java.util.Arrays;
import java.util.Scanner;

/**
 *
 Code
 96. Unique Binary Search Trees
 Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes of unique values from 1 to n.



 Example 1:


 Input: n = 3
 Output: 5
 Example 2:

 Input: n = 1
 Output: 1


 Constraints:

 1 <= n <= 19
 */
public class Unique_BST {
    public static void main(String[] args) {

    }

    public int numTrees(int n) {

        if (n <= 1) return 1;
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) for (int j = 1; j <= i; j++) dp[i] += (dp[j - 1] * dp[i - j]);

        return dp[n];
    }
 /*
 T.C = o(n^2)when i = n
 s.c =o(n)
 Try the recursion By yourself
 */


    public int numTree(int n) {
        // A memoization array to store results for n nodes.
        int[] memo = new int[n + 1];
        return solve(n, memo);
    }

    private int solve(int n, int[] memo) {
        // Base Cases:
        // There's 1 way to make an empty tree (0 nodes)
        // and 1 way to make a tree with a single node (1 node).
        if (n <= 1) {
            return 1;
        }

        // Check if we've already calculated the answer for n.
        if (memo[n] > 0) {
            return memo[n];
        }

        int total = 0;
        // The core logic: iterate through all possible root nodes.
        for (int i = 1; i <= n; i++) {
            // For a root 'i', there are 'i-1' nodes on the left
            // and 'n-i' nodes on the right.
            int leftSubtrees = solve(i - 1, memo);
            int rightSubtrees = solve(n - i, memo);

            // The total for this root is the product of the possibilities.
            total += leftSubtrees * rightSubtrees;
        }

        // Save the result before returning.
        memo[n] = total;
        return total;
    }
}
/* Tried by me
public void  rec (int n,int[] sum,int r){
    if(n==0) return ;
    sum[0]+=rec_uniqueBst(r,n);
    System.out.println(" One call finished ");
    rec(n-1,sum,r);
}

    private int  rec_uniqueBst(int rn, int n ) {
        System.out.println(" rn "+rn + " n "+ n);
    if(rn==1||rn==0) return rn;
    return rec_uniqueBst(n-1,n-1)*rec_uniqueBst(rn-n,rn-n);
    }
// WE CAN SEE HERE ONLY ONE PARAMETER IS CHANGING SO WE ARE USING 1D DP
public int rec_memo(int n){
    int[]dp = new int[n+1];// as both 0 and n is needed
    Arrays.fill(dp,-1);
    for(int i=1 ;i<dp.length;i++){
        dp[i] =rec_memo_uniqueBst(i,n,dp);
        System.out.println(dp[i]+ " ");
    }
    System.out.println(" Number of unique BST's are "+dp[n]);
    return dp[n];
}

    private int  rec_memo_uniqueBst(int i,int n,int[] dp) {
    //    System.out.println(i+" ");
    if(i==1||i==0) return i;
    if(dp[i]!=-1) return dp[i];
     return dp[i] = rec_memo_uniqueBst(i-1,n,dp)+rec_memo_uniqueBst(n-i,n,dp);
    }
}

 */
