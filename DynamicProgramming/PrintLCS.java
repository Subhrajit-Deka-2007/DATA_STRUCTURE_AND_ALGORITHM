package DynamicProgramming;

public class PrintLCS {
    public static void main(String[] args) {
         String s ="Subhrajit Deka";
         String s2 ="Software Engineer";
         PrintLCS obj = new PrintLCS();
        System.out.println(obj.lcs(s,s2));
    }
    public int lcs(String a , String b){
        // First we fill the table then we print the LCS
        int m = a.length(),n = b.length();
        int [][] dp = new int[m+1][n+1];
        for(int i =1;i<=m;i++){
            for(int j =1;j<=n;j++){
                if(a.charAt(i-1)==b.charAt(j-1))dp[i][j]= 1+dp[i-1][j-1];
                else dp[i][j] = Math.max(dp[i][j-1],dp[i-1][j]);
            }
        }
        StringBuilder str = new StringBuilder(" ");
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
        return dp[m][n];
    }
}
