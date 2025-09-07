package DynamicProgramming;

/**
 * 72. Edit Distance
 * Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
 *
 * You have the following three operations permitted on a word:
 *
 * Insert a character
 * Delete a character
 * Replace a character
 *
 *
 * Example 1:
 *
 * Input: word1 = "horse", word2 = "ros"
 * Output: 3
 * Explanation:
 * horse -> rorse (replace 'h' with 'r')
 * rorse -> rose (remove 'r')
 * rose -> ros (remove 'e')
 * Example 2:
 *
 * Input: word1 = "intention", word2 = "execution"
 * Output: 5
 * Explanation:
 * intention -> inention (remove 't')
 * inention -> enention (replace 'i' with 'e')
 * enention -> exention (replace 'n' with 'x')
 * exention -> exection (replace 'n' with 'c')
 * exection -> execution (insert 'u')
 *
 *
 * Constraints:
 *
 * 0 <= word1.length, word2.length <= 500
 * word1 and word2 consist of lowercase English letters
 */
public class EditDistance {
    public static void main(String[] args) {

    }

    /*==============================================  Recursion ===============================================================================*/
    public int minDistance(String word1, String word2) {
        StringBuilder a = new StringBuilder(word1);
        StringBuilder b = new StringBuilder(word2);
        int m = a.length(), n = b.length();
        return minSteps(m - 1, n - 1, a, b);
    }

    public int minSteps(int i, int j, StringBuilder a, StringBuilder b) {
        /*
         * Base Case Explanation (My Insight):
         * The goal is to make the two strings identical.
         *
         * if (i == -1): This means the first string has run out of characters (it's empty).
         * To make the empty string match the rest of word2 (which has j+1 characters left),
         * my only choice is to perform j+1 INSERTIONS. After I do that, the strings will
         * be identical, so the problem is solved and no more choices are needed.
         *
         * if (j == -1): This is the opposite. The second string is now empty.
         * To make the rest of word1 (which has i+1 characters left) match the empty string,
         * my only choice is to perform i+1 DELETIONS. After that, the strings are
         * identical and the problem is solved.
         * Also IF BOTH REACHES -1 ANY OF THE EDGE OR BASE  CASE CAN HANDLE SO THE INSERTION WILL BE -1+1 WHICH IS ZERO
         */
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if (a.charAt(i) == b.charAt(j)) return minSteps(i - 1, j - 1, a, b);
        else {
            int del = minSteps(i - 1, j, a, b);
            int ins = minSteps(i, j - 1, a, b);
            int rep = minSteps(i - 1, j - 1, a, b);
            return 1 + Math.min(del, Math.min(ins, rep));
        }
        /*
        t.c = each is making three calls so t.c is o((3^n-1)/2)
        on each call there is two option either one call or three calls
        s.c =o(log 3 n) where n is the number of nodes in the tree and log 3 n are the depth or level of the tree
         */
    }

    /*============================================= Recursion Ends ==============================================================================*/
    /*
    HERE IN THE RECURSION TWO PARAMETERS WERE CHANGING S1 AND S2 CHANGING FROM M-1 TO 0 AND N-1 TO 0
    so, we make a 2D DP of dp[s1.length()][s2.length()] as --> i =m-1 to 0 and j = n-1 to 0
    */
    /*=========================================== DP : RECURSION + MEMOIZATION =======================================================================*/
    public int minDistance_1(String word1, String word2) {
        StringBuilder a = new StringBuilder(word1);
        StringBuilder b = new StringBuilder(word2);
        int m = a.length(), n = b.length();
        int [][] dp = new int [m][n];
        for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j]=-1;
        return minSteps_1(m - 1, n - 1, a, b,dp);
    }

    public int minSteps_1(int i, int j, StringBuilder a, StringBuilder b,int[][] dp) {
        /*
         * Base Case Explanation (My Insight):
         * The goal is to make the two strings identical.
         *
         * if (i == -1): This means the first string has run out of characters (it's empty).
         * To make the empty string match the rest of word2 (which has j+1 characters left),
         * my only choice is to perform j+1 INSERTIONS. After I do that, the strings will
         * be identical, so the problem is solved and no more choices are needed.
         *
         * if (j == -1): This is the opposite. The second string is now empty.
         * To make the rest of word1 (which has i+1 characters left) match the empty string,
         * my only choice is to perform i+1 DELETIONS. After that, the strings are
         * identical and the problem is solved.
         * Also IF BOTH REACHES -1 ANY OF THE EDGE OR BASE  CASE CAN HANDLE SO THE INSERTION WILL BE -1+1 WHICH IS ZERO
         */
        if (i == -1) return j + 1;
        if (j == -1) return i + 1;
        if(dp[i][j]!=-1) return dp[i][j];
        if (a.charAt(i) == b.charAt(j)) return minSteps_1(i - 1, j - 1, a, b,dp);
        else {
            int del = minSteps_1(i - 1, j, a, b,dp);
            int ins = minSteps_1(i, j - 1, a, b,dp);
            int rep = minSteps_1(i - 1, j - 1, a, b,dp);
            return dp[i][j]=  1 + Math.min(del, Math.min(ins, rep));
        }
    }
     /*
    T.C = O(s1.length()*s2.length())
    S.C =O(s1.length()*s2.length())
    */
}
