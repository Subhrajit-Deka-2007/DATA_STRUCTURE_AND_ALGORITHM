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

    }
    public String  lcs(String a , String b){
        int m = a.length(),n = b.length();
        int [][] dp = new int[m+1][n+1];
        for(int i =1;i<=m;i++){
            for(int j =i;j<=n;j++){
                if(a.charAt(i-1)==b.charAt(j-1))dp[i][j]= 1+dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
        StringBuilder str = new StringBuilder(" ");
        int i =m,j =n;
        while(i>0&&j>0){
            if(a.charAt(i-1)==b.charAt(j-1)){
                str.append(a.charAt(i-1));
                i--;
                j--;// After getting one common we move diagonally
            }
            if(dp[i-1][j]>dp[i][j-1])i--;
                // we had used the lcs code for a purpose for filling the array
                // so we could print the lcs

            else j--;
        }
        str.reverse();// as we are going reverse so at last wew have to reverse the given lcs
        return str.toString();
    }
    public String shortestCommonSupersequence(String str1, String str2) {
        String lcs = lcs(str1,str2);
        // After this we get the longest common subsequence from both strings now the final super sequence string
        int i =0,j =0,k=0;// i is  travelling in string s1 , j travelling in s2 and k travelling in the lcs string
        String scs="";
        while(i<str1.length()&&j<str2.length()&&k<lcs.length()){
            while(str1.charAt(i)!=lcs.charAt(k)){
                scs+=str1.charAt(i);
                i++;
            }
            while(str2.charAt(j)!=lcs.charAt(k)){
                scs+=str2.charAt(j);
                j++;
            }
            scs+=lcs.charAt(k);// we add when all i , j and k are same
            // then also after this we do i++,k++,j++
            i++;j++;k++;
        }
         //in scs add remaining str2 character length if one had reach the end
        while(j<str2.length())scs+=str2.charAt(j++);
        while(i<str1.length())scs+=str1.charAt(i++);

        return scs;
    }
}
