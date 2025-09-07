package DynamicProgramming;

/**
 * 516. Longest Palindromic Subsequence
 * Given a string s, find the longest palindromic subsequence's length in s.
 *
 * A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "bbbab"
 * Output: 4
 * Explanation: One possible longest palindromic subsequence is "bbbb".
 * Example 2:
 *
 * Input: s = "cbbd"
 * Output: 2
 * Explanation: One possible longest palindromic subsequence is "bb".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 1000
 * s consists only of lowercase English letters
 */
public class LongestPalindromicSubsequence {
/*
ALGO : WE JUST REVERSE THE GIVEN STRING AND THEN FIND THE LCS OF STRING AND THE REVERSE STRING AS WHEN WE REVERSE THE PALINDROMIC PART
DOES NOT GET REVERSE SO THAT IS WHY THE ALGORITHM IS VALID MEANS THE QUESTION BECOMES LONGEST COMMON SUBSEQUENCE
 */
    public static void main(String[] args) {
        System.out.println(longestPalindromeSubsequence("bbab"));
    }
    public  static int longestPalindromeSubsequence(String s) {
       StringBuilder str = new StringBuilder(s);
       StringBuilder str2 = new StringBuilder(str);
        str2.reverse();
       int [][] dp = new int[str.length()][str2.length()];
       for(int i =0;i<dp.length;i++)
           for(int j =0;j<dp[0].length;j++)dp[i][j]=-1;

      return  longestCommonSubsequence(str,str2,0,0,str.length()-1,str2.length()-1,dp);
    }
    public static int longestCommonSubsequence(StringBuilder s1, StringBuilder s2, int s1i, int s2i, int s1e, int s2e, int[][] dp) {
        /* Since strings are passed by value we can use stringBuilder as passed by reference as on each call
        when we passed string on parameter a new string is formed same case. Instead of String builder we can use character array
        also
         */

        if (s1i > s1e || s2i > s2e) return 0;//  or we can write if (s1e<0||s2e<0) return 0;
        if (dp[s1e][s2e] != -1) return dp[s1e][s2e];
        if (s1.charAt(s1e) == s2.charAt(s2e))
            return dp[s1e][s2e] = 1 + longestCommonSubsequence(s1, s2, s1i, s2i, s1e - 1, s2e - 1, dp);
        else// s1e and s2e is used as s1i and s2i are constant
            return dp[s1e][s2e] = Math.max(longestCommonSubsequence(s1, s2, s1i, s2i, s1e, s2e - 1, dp), longestCommonSubsequence(s1, s2, s1i, s2i, s1e - 1, s2e, dp));
    }
}
