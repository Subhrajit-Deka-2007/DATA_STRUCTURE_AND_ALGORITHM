package DynamicProgramming;

/**
 * 62. Unique Paths
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the bottom-right corner.
 *
 * The test cases are generated so that the answer will be less than or equal to 2 * 109.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: m = 3, n = 7
 * Output: 28
 * Example 2:
 *
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 *
 *
 * Constraints:
 *
 * 1 <= m, n <= 100
 */
public class UniquePath {
    public static void main(String[] args) {
      UniquePath obj = new UniquePath();
     //   System.out.println(obj.uniquePaths(3,7));
        int [][] dp = new int [4][8];
        dp[2][6] =1;
       int x = optimize(0,0,3,7,dp);
        System.out.println(x);
        for(int i =0; i<dp.length;i++){
            for(int j =0; j<dp[i].length;j++) System.out.print(dp[i][j]+"      ");
            System.out.println();
        }
    }

    private static int  optimize(int sr, int sc, int er, int ec, int[][] dp) {
        System.out.println(" sr "+ sr+" sc "+ sc);
        if(dp[sr][sc]!=0) return dp[sr][sc];

        if(sr==er||sc==ec)return dp[sr][sc];
        return dp[sr][sc]= optimize(sr,sc+1,er,ec,dp)+optimize(sr+1,sc,er,ec,dp);
        /*
        T.C =O(M*N) {
        LET SAY THE ONE CELL HAS CALLED DRD IN THE FIRST CALLS SO DDR WILL NOT GET CALLED AS R
        AND D ARE ALREADY FILLED IN THE FIRST ITSELF SO T.C CANNOT BE NCP BECAUSE THAT COMBINATION
        WILL NOT FORM ONLY(MEANS THAT FUNCTION WILL NOT GET CALLED ONLY )
         LOGIC SO EACH CELL WILL GET TRAVERSE ONLY ONCE SO FOR RECURSION + MEMOIZATION T.C IS O(M+N)
        S.C =O(M+N)
         */
    }

    /*======================Recursive Solution =============================================================================================*/
    public int uniquePaths(int m, int n) {
      return helper(0,0,m,n);
    }
    public int helper(int sr,int sc,int er,int ec){

        if(sr==er-1&&sc==ec-1) return 1;

        if(sr==er|| sc ==ec) return 0;
        int right = helper(sr,sc+1,er,ec);
        int down = helper(sr+1,sc,er,ec);
        return  right+down;
    }
    /*
    T.C = 2^(M+N) OR NCP => WHERE N = NUMBER OF MOVES AND P = NUMBER OF A PARTICULAR MOVES THIS REPRESENT THAT THE ACCURATE NUMBER OF CALLS
    S.C =O(M+N(CALL STACK)+(M+N(ARRAY)))
     */
/*=========================Recursive solution ends ===================================================================================*/

/*=====================================DP : TABULATION===============================================================================*/
    public int tabulation(int m,int n){
        int [][] dp = new int [m][n];
        for(int i = 0;i<n;i++)dp[0][i]=1;
        for(int i=0;i<m;i++)dp[i][0] =1;
        for(int i =1;i<m;i++) for(int j =1;j<n;j++)dp[i][j]=dp[i-1][j]+dp[i][j-1];
        return dp[m-1][n-1];
    }
    /*
    T.C =O(M*N)
    S.C =O(M*N)
     */
    // as we know the value from all the sides we can do it reverse also both for recursion +memo and tabulation
}
