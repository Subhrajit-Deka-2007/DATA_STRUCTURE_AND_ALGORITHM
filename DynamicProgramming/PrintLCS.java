package DynamicProgramming;

public class PrintLCS {
    public static void main(String[] args) {

    }
    public int lcs(String a , String b){
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
            if(a.charAt(i-1)==b.charAt(j-1))str.append(a.charAt(i-1));
            if(dp[i-1][j]>dp[i][j-1])i--;
            // we had used the lcs code for a purpose for filling the array
            // so we could print the lcs

            else j--;
        }
        str.reverse();// as we are going reverse so at last wew have to reverse the given lcs
        System.out.println(str);
        return dp[m][n];
    }
}
