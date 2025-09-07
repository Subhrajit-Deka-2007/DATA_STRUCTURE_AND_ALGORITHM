package DynamicProgramming;

/**
 * 1312. Minimum Insertion Steps to Make a String Palindrome
 * Given a string s. In one step you can insert any character at any index of the string.
 *
 * Return the minimum number of steps to make s palindrome.
 *
 * A Palindrome String is one that reads the same backward as well as forward.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "zzazz"
 * Output: 0
 * Explanation: The string "zzazz" is already palindrome we do not need any insertions.
 * Example 2:
 *
 * Input: s = "mbadm"
 * Output: 2
 * Explanation: String can be "mbdadbm" or "mdbabdm".
 * Example 3:
 *
 * Input: s = "leetcode"
 * Output: 5
 * Explanation: Inserting 5 characters the string becomes "leetcodocteel".
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 500
 * s consists of lowercase English letters
 */
public class Min_Insertion_Step_To_Make_A_String_Palindrome {
    public static void main(String[] args) {

    }
    class Solution {
        public int minInsertions(String s) {
        /*
        ALGO : String .length()-LPS
        LPS => of that string represnt how much elements that are currently present in the string are already in palindrome by minusing we get the elements which are not in plaindrome so we need minimum that much of element to make the string palindrome as the LPS elements are already in palindrome no need to add
        them again as we want minimum for maximum just do string + rev(string )
        */
            StringBuilder s1 = new StringBuilder(s);
            StringBuilder s2 = new StringBuilder(s);
            s2.reverse();
            int[][] dp = new int[s1.length()][s2.length()];
            for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j]=-1;
            return s1.length()-longestCommonSubsequence(s1,s2,0,0,s1.length()-1,s2.length()-1,dp);
        }
        public static int longestCommonSubsequence(StringBuilder s1, StringBuilder s2, int s1i, int s2i, int s1e, int s2e, int[][] dp) {
        /* Since strings are passed by value we can use stringBuilder as passed by reference as on each call
        when we passed string on parameter a new string is formed same case. Instead of String builder we can use character array
        also
         */

            if (s1i > s1e || s2i > s2e) return 0;//  or we can write if (s1e<0||s2e<0) return 0;
            if (dp[s1e][s2e] != -1) return dp[s1e][s2e];
            if (s1.charAt(s1e) == s2.charAt(s2e))
                return dp[s1e][s2e] = 1+ longestCommonSubsequence(s1, s2, s1i, s2i, s1e - 1, s2e - 1, dp);
            else// s1e and s2e is used as s1i and s2i are constant
                return dp[s1e][s2e] =  Math.max(longestCommonSubsequence(s1, s2, s1i, s2i, s1e, s2e - 1, dp), longestCommonSubsequence(s1, s2, s1i, s2i, s1e - 1, s2e, dp));
        }
    }
    /*
    T.C = SAME AS OF LPS 0(S1.LENGTH*S2.LENGTH) => NUMBER OF UNIQUE CALLS
    S.C = O(S1.LENGTH*S1.LENGTH)
     */
}
