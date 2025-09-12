package DynamicProgramming;
/**

1092. Shortest Common Supersequence

Given two strings str1 and str2, return the shortest string that has both str1 and str2 as subsequences. If there are multiple valid strings, return any of them.

A string s is a subsequence of string t if deleting some number of characters from t (possibly 0) results in the string s.



Example 1:

Input: str1 = "abac", str2 = "cab"
Output: "cabac"
Explanation:
str1 = "abac" is a subsequence of "cabac" because we can delete the first "c".
str2 = "cab" is a subsequence of "cabac" because we can delete the last "ac".
The answer provided is the shortest such string that satisfies these properties.
Example 2:

Input: str1 = "aaaaaaaa", str2 = "aaaaaaaa"
Output: "aaaaaaaa"


Constraints:

1 <= str1.length, str2.length <= 1000
str1 and str2 consist of lowercase English letters
 */
public class PrintShortestCommmonSuperSequence {
    public static void main(String[] args) {
        System.out.println(shortestCommonSupersequence("bbbaaaba", "bbababbb"));
    }
    public static String  lcs(String a , String b){
            // First we fill the table then we print the LCS
            int m = a.length(),n = b.length();
            int [][] dp = new int[m+1][n+1];
            for(int i =1;i<=m;i++){
                for(int j =1;j<=n;j++){
                    if(a.charAt(i-1)==b.charAt(j-1))dp[i][j]= 1+dp[i-1][j-1];
                    else dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
                }
            }
            StringBuilder str = new StringBuilder("");// My mistake was i had given space that is why length was increasing by +1
            int i =m,j =n;
            while(i>0&&j>0){// as we had taken(m+1)*(n+1) we will just stop at the first row and first column
                if(a.charAt(i-1)==b.charAt(j-1)){
                    // we are doing i-1 and j-1 as we are taking (m+1)*(n+1) array so, we have to reduce each step by 1;
                    str.append(a.charAt(i-1));
                    i--;
                    j--;
                }//                                                             [ ]
                else if(dp[i-1][j]>dp[i][j-1])i--;/*                             |
              when the current cell                                  [ ]<---[ ] here in this case upper cell is bigger than left cell ,
              and we hit the case when both character are not same we just shift to the upper  cell so, we do i--, i represent row
              we had used the lcs code for a purpose for filling the array
              so, we could print the lcs
*/
                else j--;
            /*
            We hit the else case when both character are not same and left cell is bigger than upper  cell we just shift to the left so we do
            j-- j represent column and also if both the character are not same but upper and left cell are same we can go ob both either in left
            or in up we choose to go left
             */
            }
            str.reverse();// as we are going reverse so at last we have to reverse the given lcs as the ans we had stored is in the reverse order
            System.out.println(str);
            System.out.println(str.length());

        return str.toString();
    }
    public static String shortestCommonSupersequence(String str1, String str2) {

        String LCS = lcs(str1,str2);
        // Error : LIKE THE LCS LENGTH IS ONE MORE SO WE HAD TO DECREASE IT

        // After this we get the longest common subsequence from both strings now the final super sequence string
        int i =0,j =0,k=0;// i is  travelling in string s1 , j travelling in s2 and k travelling in the lcs string
        String scs="";
        while(k<LCS.length()){//while(i<str1.length()&&j<str2.length()&&k<lcs.length()){
            /*
             Or we can simply write while(k<lcs.length()) as the answer is dependent on it as k passes lcs length  means we had traverse both strings
             and, we had got the answer
             */
            while(str1.charAt(i)!=LCS.charAt(k)){// we don't have to write i<str.length() as it will not cross only and it will give us no
                // error only think why
                // as answer is dependent on LCS
                scs+=str1.charAt(i);
                i++;
            }
            while(str2.charAt(j)!=LCS.charAt(k)){
                scs+=str2.charAt(j);
                j++;
            }
            scs+=LCS.charAt(k);// we add when all i , j and k are same
            // then also after this we do i++,k++,j++
            i++;j++;k++;

        }
         //in scs add remaining str2 character length if one had reach the end

        while(j<str2.length())scs+=str2.charAt(j++);
        while(i<str1.length())scs+=str1.charAt(i++);

        return scs;
    }
//    public static String shortestCommonSupersequence(String a, String b) {
//        String lcs = lcs(a,b);
//        System.out.println(" lcs "+ lcs);
//        // After this we get the longest common subsequence from both strings now the final super sequence string
//        int i =0,j =0,k=0;// i is  travelling in string s1 , j travelling in s2 and k travelling in the lcs string
//        String scs="";
//
//        while (k < lcs.length()) {
//            while (a.charAt(i) != lcs.charAt(k)) {
//                scs+=a.charAt(i++);
//            }
//            while (b.charAt(j) != lcs.charAt(k)) {
//                scs+=b.charAt(j++);
//            }
//            scs+=lcs.charAt(k);
//            i++;
//            j++;
//            k++;
//        }
//
//        // Append remaining characters
//        while (i < a.length()) scs+=a.charAt(i++);
//        while (j < b.length()) scs+=b.charAt(j++);
//
//        return scs;
//}
/** Correct one
 * class Solution {
 *     /**
 *      * This is the main function from the first photo.
 *      * It uses the LCS string to stitch together the supersequence.
 *
 *

    public String shortestCommonSupersequence(String a, String b) {
 *         // First, get the Longest Common Subsequence string
 *String lcs = lcs(a, b);
 *
 *         // Pointers for string 'a', string 'b', and the 'lcs' string
 *int i = 0;
 *int j = 0;
 *int k = 0;
 *
 *         // Using String concatenation as shown in the photo
 *String scs = "";
 *
 *         // Iterate through the LCS string, using it as a guide
 *while (k < lcs.length()) {
 *             // Add characters from 'a' that are not in the LCS
 *while (a.charAt(i) != lcs.charAt(k)) {
 *scs += a.charAt(i);
 *i++;
 *}
 *
 *             // Add characters from 'b' that are not in the LCS
 *while (b.charAt(j) != lcs.charAt(k)) {
 *scs += b.charAt(j);
 *j++;
 *}
 *
 *             // Add the common character from the LCS once
 *scs += lcs.charAt(k++);
 *i++;
 *j++;
 *
 *}
 *
 *         // Add any remaining characters from the end of string 'a'
 *while (i < a.length()) {
 *scs += a.charAt(i);
 *i++;
 *}
 *
 *         // Add any remaining characters from the end of string 'b'
 *while (j < b.length()) {
 *scs += b.charAt(j);
 *j++;
 *}
 *
 *return scs;
 *}
 *
 * /**
   This helper function finds the Longest Common Subsequence string.
 The logic is from the second photo you provided.


    private String lcs(String a, String b) {
 *int m = a.length();
 *int n = b.length();
 *int[][] dp = new int[m + 1][n + 1];
 *
 *         // Part 1: Fill the DP table to find the length of the LCS
 *for (int i = 1; i <= m; i++) {
 *for (int j = 1; j <= n; j++) {
 *if (a.charAt(i - 1) == b.charAt(j - 1)) {
 *dp[i][j] = 1 + dp[i - 1][j - 1];
 *} else {
 *dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
 *}
 *}
 *}
 *
 *         // Part 2: Backtrack through the DP table to build the LCS string
 *StringBuilder str = new StringBuilder();
 *int i = m, j = n;
 *while (i > 0 && j > 0) {
 *if (a.charAt(i - 1) == b.charAt(j - 1)) {
 *str.append(a.charAt(i - 1));
 *i--;
 *j--;
 *} else if (dp[i - 1][j] > dp[i][j - 1]) {
 *i--;
 *} else {
 *j--;
 *}
 *}
 *
 *         // The string is built backwards, so we must reverse it
 *return str.reverse().toString();
 *}
 *
}
 */
/*
T.C =(O(M*N)(FOR TABULATION)+(M*N) FOR LCS+AND M+N FOR MERGING THE SCS)
    =O((M*N)+(M*N)+(M+N))
S.C =O(M*N)
 */
}
