package Greedy;

import java.util.Arrays;
import java.util.Collections;

/**
 * Given a board of dimensions n × m that needs to be cut into n*m squares. The cost of making a cut along a horizontal or vertical edge is provided in two arrays:
 *
 * x[]: Cutting costs along the vertical edges (length-wise).
 * y[]: Cutting costs along the horizontal edges (width-wise).
 * Find the minimum total cost required to cut the board into squares optimally.
 *
 * Examples:
 *
 * Input: n = 4, m = 6, x[] = [2, 1, 3, 1, 4], y[] = [4, 1, 2]
 * Output: 42
 * Explanation:
 *
 * Initially no. of horizontal segments = 1 & no. of vertical segments = 1.
 * Optimal way to cut into square is:
 * • Pick 4 (from x) -> vertical cut, Cost = 4 × horizontal segments = 4,
 *   Now, horizontal segments = 1, vertical segments = 2.
 * • Pick 4 (from y) -> horizontal cut, Cost = 4 × vertical segments = 8,
 *   Now, horizontal segments = 2, vertical segments = 2.
 * • Pick 3 (from x) -> vertical cut, Cost = 3 × horizontal segments = 6,
 *   Now, horizontal segments = 2, vertical segments = 3.
 * • Pick 2 (from x) -> vertical cut, Cost = 2 × horizontal segments = 4,
 *   Now, horizontal segments = 2, vertical segments = 4.
 * • Pick 2 (from y) -> horizontal cut, Cost = 2 × vertical segments = 8,
 *   Now, horizontal segments = 3, vertical segments = 4.
 * • Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 3,
 *   Now, horizontal segments = 3, vertical segments = 5.
 * • Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 3,
 *   Now, horizontal segments = 3, vertical segments = 6.
 * • Pick 1 (from y) -> horizontal cut, Cost = 1 × vertical segments = 6,
 *   Now, horizontal segments = 4, vertical segments = 6.
 * So, the total cost = 4 + 8 + 6 + 4 + 8 + 3 + 3 + 6 = 42.
 * Input: n = 4, m = 4, x[] = [1, 1, 1], y[] = [1, 1, 1]
 * Output: 15
 * Explanation:
 *
 * Initially no. of horizontal segments = 1 & no. of vertical segments = 1.
 * Optimal way to cut into square is:
 * • Pick 1 (from y) -> horizontal cut, Cost = 1 × vertical segments = 1,
 *   Now, horizontal segments = 2, vertical segments = 1.
 * • Pick 1 (from y) -> horizontal cut, Cost = 1 × vertical segments = 1,
 *   Now, horizontal segments = 3, vertical segments = 1.
 * • Pick 1 (from y) -> horizontal cut, Cost = 1 × vertical segments = 1,
 *   Now, horizontal segments = 4, vertical segments = 1.
 * • Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 4,
 *   Now, horizontal segments = 4, vertical segments = 2.
 * • Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 4,
 *   Now, horizontal segments = 4, vertical segments = 3.
 * • Pick 1 (from x) -> vertical cut, Cost = 1 × horizontal segments = 4,
 *   Now, horizontal segments = 4, vertical segments = 4
 * So, the total cost = 1 + 1 + 1 + 4 + 4 + 4 = 15.
 * Constraints:
 * 2 ≤ n, m ≤ 103
 * 1 ≤ x[i], y[j] ≤103
 */
public class MinCostToCutABoardIntoSqaure {
    public static void main(String[] args) {
        int m =6,n =4;
        Integer[] x ={2,1,3,1,4};
        Integer y [] = {4,1,2};
        System.out.println(minCost(n-1,m-1,x,y));
    }
    public static int minCost(int n, int m, Integer[] x, Integer [] y) {
        // code here
         int res =0;
         // sort the horizontal cost in reverse order
         Arrays.sort(x, Collections.reverseOrder());
         Arrays.sort(y,Collections.reverseOrder());
        // Initialize current width as 1
        int hznti =1,vert =1;
        // loop until one or both
        // cost array are processed
        int i=0,j =0;
        while(i<m&&j<n){
            if(x[i]>y[j]){
                res+=x[i]*vert;
                // increase current horizontal
                // part count by 1
                hznti++;
                i++;
            }
            else{
                res+=y[j]*hznti;
                // Increase current vertical
                // part count by 1
                vert++;
                j++;
            }
        }
        // loop for horizontal array
        // if remains
        int total = 0;
        while(i<m)
            total+=x[i++];
            res+=total*vert;
        // loop for vertical array
        // if remains
        total =0;
        while(j<n)
            total +=y[j++];
            res+=total*hznti;
            return res;
    }
}
