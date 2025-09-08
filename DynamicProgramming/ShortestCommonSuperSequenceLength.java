package DynamicProgramming;

import java.util.ArrayList;

public class ShortestCommonSuperSequenceLength {
    public static void main(String[] args) {
        String s1 ="abac";
        String s2 ="cab";
        // We are finding shortest, common super sequence length we want the minimum length
        int [][] dp = new int[s1.length()][s2.length()];
        for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j]=-1;


        ArrayList<String>s = new ArrayList<>();
        int x =longestCommonSubsequence(s1,s2,0,0,s1.length()-1,s2.length()-1,dp,s);
        x = s1.length()-x+s2.length()-x+x;
        /*
         first find the lcs then from each string remove the lcs part then add them and at last also that lcs part as it is also
         included in the answer

         */
        System.out.println(" The length of the shortest  common super sequence "+ x);
    }
    public static int longestCommonSubsequence(String s1, String s2, int s1i, int s2i, int s1e, int s2e, int[][] dp,ArrayList<String>s ) {
        if (s1i > s1e || s2i > s2e) return 0;//  or we can write if (s1e<0||s2e<0) return 0;
        if (dp[s1e][s2e] != -1) return dp[s1e][s2e];
        if (s1.charAt(s1e) == s2.charAt(s2e)) return dp[s1e][s2e] = 1 + longestCommonSubsequence(s1, s2, s1i, s2i, s1e - 1, s2e - 1, dp, s);

        else// s1e and s2e is used as s1i and s2i are constant
            return dp[s1e][s2e] = Math.max(longestCommonSubsequence(s1, s2, s1i, s2i, s1e, s2e - 1, dp,s), longestCommonSubsequence(s1, s2, s1i, s2i, s1e - 1, s2e, dp,s));
    }
}
