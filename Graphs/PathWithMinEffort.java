package Graphs;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1631. Path With Minimum Effort
 * You are a hiker preparing for an upcoming hike. You are given heights, a 2D array of size rows x columns, where heights[row][col] represents the height of cell (row, col). You are situated in the top-left cell, (0, 0), and you hope to travel to the bottom-right cell, (rows-1, columns-1) (i.e., 0-indexed). You can move up, down, left, or right, and you wish to find a route that requires the minimum effort.
 *
 * A route's effort is the maximum absolute difference in heights between two consecutive cells of the route.
 *
 * Return the minimum effort required to travel from the top-left cell to the bottom-right cell.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: heights = [[1,2,2],[3,8,2],[5,3,5]]
 * Output: 2
 * Explanation: The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
 * This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
 * Example 2:
 *
 *
 *
 * Input: heights = [[1,2,3],[3,8,4],[5,3,5]]
 * Output: 1
 * Explanation: The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
 * Example 3:
 *
 *
 * Input: heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * Output: 0
 * Explanation: This route does not require any effort.
 *
 *
 * Constraints:
 *
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10
 */
class Triplet implements Comparable<Triplet>{
    int row;
    int col;
    int effort;
    Triplet(int row, int col, int effort){
        this.row = row;
        this.col = col;
        this.effort = effort;
    }
    // we want the sorting inside the heap done on the basis of distance
    public int compareTo(Triplet p ){
        if(this.effort == p.effort ) return this.row- p.row; // or we can do on the basis of col also it completely depends on us
        return this.effort-p.effort;
    }


}
public class PathWithMinEffort {
    public static void main(String[] args) {

    }

    public int minimumEffortPath(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] ans = new int[m][n];
        for(int i =0;i<heights.length;i++)for(int j =0;j<heights[0].length;j++)ans[i][j] = Integer.MAX_VALUE;
        ans[0][0] = 0;
        // Making a min heap of <Triplet>
        PriorityQueue<Triplet> pq = new PriorityQueue<>();
        pq.add(new Triplet(0, 0, 0));
        while (!pq.isEmpty()) {
            Triplet top = pq.remove();
            int row = top.row;
            int col = top.col;
            int effort = top.effort;
            if(row==m-1 && col == n-1) break;
/**----------------------------------------------------------------------------------------------------------------------------------*/
            if (row > 0) {
                // going up -->
                int e = Math.abs(heights[row][col] - heights[row - 1][col]);
                e = Math.max(e, effort);
                if (ans[row - 1][col] > e) {
                    ans[row - 1][col] = e;
                    pq.add(new Triplet(row - 1, col, e));
                }
            }
/**------------------------- Going Up finished -------------------------------------------------------------------------------------*/

/** ------------------------------------------ We are going left --------------------------------------------------------------------*/
            if (col - 1 >= 0) {// or we can write col >0 We are going left
                int e = Math.abs(heights[row][col] - heights[row][col - 1]);
                e = Math.max(e, effort);
                if (ans[row][col - 1] > e) {
                    ans[row][col - 1] = e;
                    pq.add(new Triplet(row, col - 1, e));
                }
            }
/**---------------------------------------------- Left -------------------------------------------------------------------------------*/

            if (row + 1 < heights.length) {// going down and also  we can write row<m-1
                int e = Math.abs(heights[row][col] - heights[row + 1][col]);
                e = Math.max(e, effort);
                if (ans[row + 1][col] > e) {
                    ans[row + 1][col] = e;
                    pq.add(new Triplet(row + 1, col, e));
                }
            }
            if (col + 1 < heights[0].length) {//  Going right and, also we can write the condition col<n-1
                int e = Math.abs(heights[row][col] - heights[row][col + 1]);
                e = Math.max(e, effort);
                if (ans[row][col + 1] > e) {
                    ans[row][col + 1] = e;
                    pq.add(new Triplet(row, col + 1, e));
                }
            }
        }
        return ans[m-1][n-1];
    }
/**                                        Improve code                                                                              */
    public int minimumEffortPath_1(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        int[][] ans = new int[m][n];
        for(int i =0;i<heights.length;i++)for(int j =0;j<heights[0].length;j++)ans[i][j] = Integer.MAX_VALUE;
        ans[0][0] = 0;
        // Making a min heap of <Triplet>
        PriorityQueue<Triplet> pq = new PriorityQueue<>();
        pq.add(new Triplet(0, 0, 0));
        int [] r = {-1,0,1,0};
        int [] c = {0,-1,0,1};
        while (!pq.isEmpty()) {
            Triplet top = pq.remove();
            int row = top.row;
            int col = top.col;
            int effort = top.effort;
            if (row == m - 1 && col == n - 1) break;
            for (int i = 0; i < 4; i++) {
                int newRow = row + r[i];
                int newCol = col + c[i];
                if (newRow < 0 || newCol <0 || newRow > m - 1 || newCol > n - 1) continue;
                int e = Math.abs(heights[row][col] - heights[newRow][newCol]);
                e = Math.max(e, effort);
                if (ans[newRow][newCol] > e) {
                    ans[newRow][newCol] = e;
                    pq.add(new Triplet(newRow, newCol, e));
                }
            }
/**----------------------------------------------------------------------------------------------------------------------------------*/
        }
        return ans[m-1][n-1];
    }
}
