package Graphs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 *

 200. Number of Islands

 Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.

 An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.



 Example 1:

 Input: grid = [
 ["1","1","1","1","0"],
 ["1","1","0","1","0"],
 ["1","1","0","0","0"],
 ["0","0","0","0","0"]
 ]
 Output: 1
 Example 2:

 Input: grid = [
 ["1","1","0","0","0"],
 ["1","1","0","0","0"],
 ["0","0","1","0","0"],
 ["0","0","0","1","1"]
 ]
 Output: 3


 Constraints:

 m == grid.length
 n == grid[i].length
 1 <= m, n <= 300
 grid[i][j] is '0' or '1'.
 */
class Pair{
    int row;
    int col;
    Pair(int row,int col){
        this.row = row;
        this.col = col;
    }
}
public class NumberOfIslands {
    public static void main(String[] args) {
       char[][] grid = {
               {'1', '1', '1'},
               {'0','1', '0'},
               {'1','1','1'}
       };

       NumberOfIslands obj = new NumberOfIslands();
       int x = obj.numIslands(grid);
        System.out.println("Number of Islands "+x );
    }
    public int numIslands(char[][] grid) {
     /*         Solve using BFS          */
        boolean [][] vis = new boolean[grid.length][grid[0].length];
        int [] count ={0};
        for(int i =0;i<grid.length;i++){
         for(int j =0;j<grid[0].length;j++){
             if(grid[i][j]=='1'&&!vis[i][j]){
                // bfs(i,j,vis,grid);
                 dfs(i,j,grid,vis);
                 count[0]++;
             }
             else continue;
         }
     }

     return count[0];
    }
    public void bfs(int row, int col,boolean[][] vis,char [][] grid ){
        /* Here we are using a pair class because to represent an element we need two coordinates i.e. why */
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(row,col));
        vis[row][col] = true;
        Pair remove ;
        while(!q.isEmpty()){
            remove = q.remove();
            row = remove.row;
            col = remove.col;
            if(row+1<grid.length&&grid[row+1][col]=='1'&&!vis[row+1][col]){// Moving Downwards
                q.add(new Pair(row+1,col));
                vis[row+1][col]= true;
            }
            if(row-1>=0&&grid[row-1][col]=='1'&&!vis[row-1][col]){// Moving Upwards
                q.add(new Pair(row-1,col));
                vis[row-1][col]= true;
            }
            if(col+1<grid[0].length&&grid[row][col+1]=='1'&&!vis[row][col+1]){// Moving Right
                q.add(new Pair(row,col+1));
                vis[row][col+1]= true;
            }
            if(col-1>=0&&grid[row][col-1]=='1'&&!vis[row][col-1]){// Moving Left
                q.add(new Pair(row,col-1));
                vis[row][col-1]= true;
            }
        }
    }
/**
 * Here is the final and precise complexity analysis for the "Number of Islands" code.
 *
 * Time Complexity: O(M×N)
 * The time complexity is O(M×N) because every cell in the grid is visited and processed a constant number of times.
 *
 * Space Complexity: O(M×N)
 * The overall space complexity is O(M×N). This is determined by the following:
 *
 * Visited Array: The dominant factor is the boolean[][] visited array, which requires space proportional to the total number of cells (M×N).
 *
 * Queue: The Queue itself has a worst-case space complexity of O(min(M,N)), as its maximum size is limited by the grid's shorter dimension.
 */
private void dfs(int row, int col,char [][] grid , boolean[][] vis){
    vis[row][col] = true;
    if(row+1<=grid.length-1&&grid[row+1][col]=='1'&&!vis[row+1][col])dfs(row+1,col,grid,vis);
    if(row-1>=0&&grid[row-1][col]=='1'&&!vis[row-1][col])dfs(row-1,col,grid,vis);
    if(col+1<=grid[0].length-1&&grid[row][col+1]=='1'&&!vis[row][col+1])dfs(row,col+1,grid,vis);
    if(col-1>=0&&grid[row][col-1]=='1'&&!vis[row][col-1])dfs(row,col-1,grid,vis);
}
    /**
     * Here base case is the if condition Time complexity same little call stack space is used here
     */

}


