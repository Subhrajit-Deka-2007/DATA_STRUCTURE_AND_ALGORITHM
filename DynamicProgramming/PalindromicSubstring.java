package DynamicProgramming;

import java.util.ArrayList;

/**
 *
 647. Palindromic Substrings

 Given a string s, return the number of palindromic substrings in it.

 A string is a palindrome when it reads the same backward as forward.

 A substring is a contiguous sequence of characters within the string.



 Example 1:

 Input: s = "abc"
 Output: 3
 Explanation: Three palindromic strings: "a", "b", "c".
 Example 2:

 Input: s = "aaa"
 Output: 6
 Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".


 Constraints:

 1 <= s.length <= 1000
 s consists of lowercase English letters.
 */
public class PalindromicSubstring {
    public static void main(String[] args) {

        System.out.println(" The number of palindromic substrings of the given string are "+brut_force("aaa"));
    }
/*========================================= TABULATION METHOD =========================================================================*/
public int countSubstrings(String s) {
    int n = s.length();
    int [][] dp = new int[n][n];
    int count =0;
    int i ;
    int j ;
    for(int k =0;k<n;k++){// outer loop
        i =0;j=k;
        while(j<n){// fir each loop we will not cross the n if we move diagonally also see the diagram
            if(i==j){count++;
                dp[i][j]=1;// this is for the one length
            }
            else if(j==i+1){// this for 2 length substring
                if(s.charAt(i)==s.charAt(j)){
                    dp[i][j]=1;
                    count++;
                    // if not same we let it remain zero
                }
            }
            else{// now this case is for length > 2
                if(s.charAt(i)==s.charAt(j)){
                    if(dp[i+1][j-1]==1){
                        dp[i][j]=1;
                        count++;
                    }
                    // if it is not equal  then we had no business on that just leve it as it is zero only
                    // by default
                }
            }

            i++;j++;

        }
    }
    return count;
}
/*
T.C =O(N^2)
S.C =O(N^2)
h/w longest palindromic substring leet code 5 same concept used copy paste
dp on strings finished
 */


    /*============================================== Brut Force =================================================================================*/

  // ALGO : GENERATE ALL SUBSTRINGS THEN FOR EACH SUBSTRING WE WILL FIND THE PALINDROME


    public static int brut_force(String s ){
        ArrayList<String>ans = new ArrayList<>();
        String str ="";
        for(int i =0;i<s.length();i++){for(int j =i;j<s.length();j++) ans.add(str+=s.charAt(j));
            str="";
        }
        System.out.println(ans );
        int count =0;
        for(int i =0;i<ans.size();i++)if(palindrome(ans.get(i)))count++;
        return count ;
    }
    public static boolean  palindrome(String s){
        if(s.length()==1) return true;
        int i =0;
        int j =s.length()-1;
        while(i<j) if(s.charAt(i++)!=s.charAt(j--))return false;
        return true;
    }
    /*
    T.C =O(N+N-1+N-3+-----+1)=O(N(N-1)/2)=> O((N^2-N)/2){FOR SUBSTRING }+PALINDROME IF WE CONSIDER ITS LENGTH BE N AND THERE ARE N SUBSTRINGS THEN T.C =N/2
        O(N*N.2)
        =O((N^2-N)/2+(N*(N/2))
     S.C =O(2^N) TOTAL NUMBER OF SUBSETS
     BRUT FORCE
     */
/*========================================== Brut Force ends =======================================================================================*/
}
