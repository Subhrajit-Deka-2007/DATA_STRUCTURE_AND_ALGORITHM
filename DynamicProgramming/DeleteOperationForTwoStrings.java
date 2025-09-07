package DynamicProgramming;



public class DeleteOperationForTwoStrings {
    public static void main(String[] args) {

    }

    class Solution {
        public int minDistance(String word1, String word2) {
        /*
        ALGO : SIMPLE JUST USED LCS AND THEN MINUS IT FROM BOTH STRINGS AND THEN PLUS IT
        AS EACH SUBTRACTION WILL REPRESENT THAT THE ELEMENT IS NOT COMMON IN BOTH S1 AND S2 AND SIMILARLY WE HAVE TO REMOVE IT FROM BOTH STRINGS SO WE DO {S1.LENGTH()-LCS}+{S2.LENGHT()-LCS}
        will remove the elements that are not present in both and we can say remove elements that are not
        common in both strings
        */
            int[][] dp = new int[word1.length()][word2.length()];
            StringBuilder s1 = new StringBuilder(word1);
            StringBuilder s2 = new StringBuilder(word2);
            for (int i = 0; i < dp.length; i++) for (int j = 0; j < dp[0].length; j++) dp[i][j] = -1;
            int x = longestCommonSubsequence(s1, s2, 0, 0, word1.length() - 1, word2.length() - 1, dp);
            return word1.length() + word2.length() - 2 * x;
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
        /*
        T.C =O(S1.LENGTH()*S2.LENGTH())
        S.C =O(S1.LENGTH()*S2.LENGTH())
         */
    }
}