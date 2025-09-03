package DynamicProgramming;

/**
 64. Minimum Path Sum
 Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right, which minimizes the sum of all numbers along its path.

 Note: You can only move either down or right at any point in time.



 Example 1:


 Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
 Output: 7
 Explanation: Because the path 1 → 3 → 1 → 1 → 1 minimizes the sum.
 Example 2:

 Input: grid = [[1,2,3],[4,5,6]]
 Output: 12


 Constraints:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 200
 0 <= grid[i][j] <= 200
 */
public class MinimumPathSum {
    public static void main(String[] args) {
       int[][]grid = {{1,3,1},{1,5,1},{4,2,1}};
       MinimumPathSum obj = new MinimumPathSum();
        System.out.println(obj.minPathSum(grid));
    }


/*===============================Recursion ==============================================================================*/
    public static int minPathSum_1(int[][] grid) {
        int path =0;
        int [] minPath = new int[1];
        minPath[0]= Integer.MAX_VALUE;
         return helper(0,0, grid,minPath);

    }

    private static int helper(int sr, int sc,int[][]grid,int[] minPath) {

        if (sr == grid.length - 1 && sc == grid[sr].length - 1) return grid[sr][sc];
        if (sr == grid.length || sc == grid[sr].length) return Integer.MAX_VALUE;
        // Important lesson as we are returning min on the edge case we should not return 0 as it is the most minimum path instead we
        // had to return a very greater value this was the main culprit

        int x =  helper(sr, sc + 1, grid, minPath);
        int y = helper(sr + 1, sc, grid, minPath);
        return grid[sr][sc]+ Math.min(x,y);
    }

/*==========================================Recursion ends ==============================================================*/

/*=========================================DP : RECURSION + MEMOIZATION ===================================================*/

    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length+1][grid[0].length+1];


        for(int i =0; i<dp.length;i++)for(int j =0;j<dp[i].length;j++)dp[i][j]=-1;

        for(int i =0; i<dp[dp.length-1].length;i++) dp[dp.length-1][i]=Integer.MAX_VALUE;


        for(int i= 0; i<dp.length;i++)dp[i][dp[i].length-1]=Integer.MAX_VALUE;

        dp[grid.length-1][grid[0].length-1]=grid[grid.length-1][grid[0].length-1];
        return memoization(0,0,grid,dp);
    }

    public static int memoization(int sr, int sc,int[][] grid,int[][]dp){
        if(dp[sr][sc]!=-1) return dp[sr][sc];
        return dp[sr][sc] = grid[sr][sc]+ Math.min(memoization(sr,sc+1,grid,dp),memoization(sr+1,sc,grid,dp));
   /*
   T.C =O(M*N)
   S.C =O(M*N)
    */
    }

/*=========================================DP : RECURSION + MEMOIZATION =========================================================*/
/*==========================================DP : TABULATION METHOD ==========================================================================*/
public int tabulation(int[][] grid){

        for(int j =1;j<grid[0].length;j++)grid[0][j]+=grid[0][j-1];
        for(int j =1;j<grid.length;j++)grid[j][0]+=grid[j-1][0];
        for(int i =1;i<grid.length;i++)for(int j =1;j<grid[i].length;j++)grid[i][j]+=Math.min(grid[i-1][j],grid[i][j-1]);

    return grid[grid.length-1][grid[0].length-1];
    // t.c =o(m*n)
    // s.c =o(1)
}

}
