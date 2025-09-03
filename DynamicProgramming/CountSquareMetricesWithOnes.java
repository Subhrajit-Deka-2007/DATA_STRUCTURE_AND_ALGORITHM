package DynamicProgramming;

/**
 * 1277. Count Square Sub matrices with All Ones

 * Given a m * n matrix of ones and zeros, return how many square sub matrices have all ones.
 *
 *
 *
 * Example 1:
 *
 * Input: matrix =
 * [
 *   [0,1,1,1],
 *   [1,1,1,1],
 *   [0,1,1,1]
 * ]
 * Output: 15
 * Explanation:
 * There are 10 squares of side 1.
 * There are 4 squares of side 2.
 * There is  1 square of side 3.
 * Total number of squares = 10 + 4 + 1 = 15.
 * Example 2:
 *
 * Input: matrix =
 * [
 *   [1,0,1],
 *   [1,1,0],
 *   [1,1,0]
 * ]
 * Output: 7
 * Explanation:
 * There are 6 squares of side 1.
 * There is 1 square of side 2.
 * Total number of squares = 6 + 1 = 7.
 *
 *
 * Constraints:
 *
 * 1 <= arr.length <= 300
 * 1 <= arr[0].length <= 300
 * 0 <= arr[i][j] <= 1
 */
public class CountSquareMetricesWithOnes {
    public static void main(String[] args) {

    }
public int countSquares(int[][] arr){
        int m = arr.length,n = arr[0].length,count =0;
        for(int i =0;i<m;i++){
            for(int j =0;j<n;j++){
                if(arr[i][j]==0)continue;
                if(i!=0&&j!=0){// we are neither in first row or first column
                    arr[i][j]+=Math.min(arr[i-1][j],Math.min(arr[i][j-1],arr[i-1][j-1]));
                }
                count+=arr[i][j];
            }
        }
        return count;
}
/*
T.C =O(M*N)
S.C =O(1)
 */
}
