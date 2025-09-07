package DynamicProgramming;

/**
 * 1143. Longest Common Subsequence
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 *
 *
 * Example 1:
 *
 * Input: text1 = "abcde", text2 = "ace"
 * Output: 3
 * Explanation: The longest common subsequence is "ace" and its length is 3.
 * Example 2:
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3
 * Explanation: The longest common subsequence is "abc" and its length is 3.
 * Example 3:
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0
 * Explanation: There is no such common subsequence, so the result is 0.
 *
 *
 * Constraints:
 *
 * 1 <= text1.length, text2.length <= 1000
 * text1 and text2 consist of only lowercase English character
 */
public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(longestCommonSubsequence_2(text1, text2, 0, 0, text1.length() - 1, text2.length() - 1));
        StringBuilder s1 = new StringBuilder(text1);
        StringBuilder s2 = new StringBuilder(text2);
        longestCommonSubsequence_3(s1, s2, 0, 0, s1.length() - 1, s2.length() - 1);
        int[][] dp= new int[text1.length()][text2.length()];
        for(int i =0;i<dp.length;i++)for(int j =0;j<dp[0].length;j++)dp[i][j]=-1;
         longestCommonSubsequence_4(s1,s2,0,0,text1.length()-1,text2.length()-1,dp);


    }

    /*
    ALGO : WE WILL TRAVERSE BOTH STRINGS FROM N-1 TO 0 AND IF LAST ELEMENTS WERE COMMON THEN 1+LCS(M-2 TO 0 OF S1 ,N-2 TO 0 OF S2)
    AND IF NOT COMMON THEN AKE TWO CALLS LCS(M-3 TO 0 OF S1 ,N-2 TO 0 OF S2) AND LCS(M-2 TO 0 OF S1 ,N-3 TO 0 OF S2)
    Longest pallindromic subsequence (more famous question)
     */
    public static int longestCommonSubsequence_1(String a, String b) {
        if (a.length() == 0 || b.length() == 0) return 0;
        int m = a.length(), n = b.length();
        if (a.charAt(m - 1) == b.charAt(n - 1))
            return 1 + longestCommonSubsequence_1(a.substring(0, m - 1), b.substring(0, n - 1));
        else
            return Math.max(longestCommonSubsequence_1(a, b.substring(0, n - 1)), longestCommonSubsequence_1(a.substring(0, m - 1), b));
        // in else send one string as it is and for the other string just remove one character
    }

    /*
    T.C =0(2^N)
    S.C = ALSO VERY BAD AS WE ARE USING S.SUBSTRING EACH TIME FORMING A NEW STRING
     */
    public static int longestCommonSubsequence_2(String s1, String s2, int s1i, int s2i, int s1e, int s2e) {
        /* Since strings are passed by value we can use stringBuilder as passed by reference as on each call
        when we passed string on parameter a new string is formed same case
         */
        if (s1i > s1e || s2i > s2e) return 0;
        if (s1.charAt(s1e) == s2.charAt(s2e)) return 1 + longestCommonSubsequence_2(s1, s2, s1i, s2i, s1e - 1, s2e - 1);
        else
            return Math.max(longestCommonSubsequence_2(s1, s2, s1i, s2i, s1e, s2e - 1), longestCommonSubsequence_2(s1, s2, s1i, s2i, s1e - 1, s2e));
        /*
        T.C =O(2^N)=> WHERE N = MIN(S1.LENGTH(),S2.LENGTH()) in worst case no common so each will call two calls and calls will be till
        n = min(s1.length(),s2.length())
        S.C =O(still bad as when we passed string in parameter it still works the same memory waste
        Optimize version of 1
         */
    }

    public static int longestCommonSubsequence_3(StringBuilder s1, StringBuilder s2, int s1i, int s2i, int s1e, int s2e) {
        /* Since strings are passed by value we can use stringBuilder as passed by reference as on each call
        when we passed string on parameter a new string is formed same case. Instead of String builder we can use character array
        also
         */
        if (s1i > s1e || s2i > s2e) return 0;
        if (s1.charAt(s1e) == s2.charAt(s2e)) return 1 + longestCommonSubsequence_3(s1, s2, s1i, s2i, s1e - 1, s2e - 1);
        else
            return Math.max(longestCommonSubsequence_3(s1, s2, s1i, s2i, s1e, s2e - 1), longestCommonSubsequence_3(s1, s2, s1i, s2i, s1e - 1, s2e));
        /*
        T.C =O(2^N)
        S.C =O(1) BY USING STRING BUILDER
         */
    }

    /*
    NOW WE HAVE TO USE JUST THINK S1 IS GOING FROM M-1 TO 0 AND S2 IS GOING FROM N-1 TO 0 SO WE CAN FORM A DP

     */
    public static int longestCommonSubsequence_4(StringBuilder s1, StringBuilder s2, int s1i, int s2i, int s1e, int s2e, int[][] dp) {
        /* Since strings are passed by value we can use stringBuilder as passed by reference as on each call
        when we passed string on parameter a new string is formed same case. Instead of String builder we can use character array
        also
         */
        if (s1i > s1e || s2i > s2e) return 0;//  or we can write if (s1e<0||s2e<0) return 0;
        if(dp[s1e][s2e]!=-1) return dp[s1e][s2e];
        if (s1.charAt(s1e) == s2.charAt(s2e))
            return dp[s1e][s2e] = 1 + longestCommonSubsequence_4(s1, s2, s1i, s2i, s1e - 1, s2e - 1,dp);
        else// s1e and s2e is used as s1i and s2i are constant
            return dp[s1e][s2e] = Math.max(longestCommonSubsequence_4(s1, s2, s1i, s2i, s1e, s2e - 1,dp), longestCommonSubsequence_4(s1, s2, s1i, s2i, s1e - 1, s2e,dp));
    }
    /*
    T.C =O(S1.LENGTH()*S2,LENGTH())---> REPRESENT NUMBER 0F UNIQUE CALLS
    S.C =O(S1.LENGTH()*S2.LENGTH()(EXTRA ARRAY) + FIND THE STACK SPACE)
     */
    public int tabulation(String s1, String  s2){
        int[][] dp = new int[s1.length()][s2.length()];
        for(int i =0;i<dp.length;i++){
            for(int j =0;j<dp[0].length;j++){
                if(s1.charAt(i)==s2.charAt(j))dp[i][j]=1+((i>0&&j>0)?dp[i-1][j-1]:0);
                else dp[i][j] = Math.max(((i>0)?dp[i-1][j]:0),((j>0)?dp[i][j-1]:0));
            }
        }
        return dp[s1.length()-1][s2.length()-1];
        /*
         T.C =O(S1.LENGTH()*S2,LENGTH())
         S.C =O(S1.LENGTH()*S2.LENGTH())
         */
    }
// To avoid base case and ternary operator we are increasing the size of the array by (m+1)*(n+1)
public int tabulation_1(String s1, String  s2){
    int[][] dp = new int[s1.length()+1][s2.length()+1];
    // To avoid base case or ternary operator we increase the size of the array and fill the first row and first column with base case
    // my thought process
    for(int i =1;i<dp.length;i++){
        for(int j =1;j<dp[0].length;j++){
            if(s1.charAt(i-1)==s2.charAt(j-1))dp[i][j]=1+dp[i-1][j-1];
            else dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
        }
    }
    return dp[s1.length()][s2.length()];
        /*
         T.C =O(S1.LENGTH()+1*S2,LENGTH()+1)
         S.C =O(S1.LENGTH()+1*S2.LENGTH()+1)
         */
}
}
