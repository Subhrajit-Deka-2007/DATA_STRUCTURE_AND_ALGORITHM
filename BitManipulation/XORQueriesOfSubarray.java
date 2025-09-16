package BitManipulation;

/**
 *  1310. XOR Queries of a Subarray
 * You are given an array arr of positive integers. You are also given the array queries where queries[i] = [lefti, righti].
 *
 * For each query i compute the XOR of elements from lefti to righti (that is, arr[lefti] XOR arr[lefti + 1] XOR ... XOR arr[righti] ).
 *
 * Return an array answer where answer[i] is the answer to the ith query.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * Output: [2,7,14,8]
 * Explanation:
 * The binary representation of the elements in the array are:
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * The XOR values for queries are:
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 * Example 2:
 *
 * Input: arr = [4,8,2,10], queries = [[2,3],[1,3],[0,0],[0,3]]
 * Output: [8,0,4,4]
 */
public class XORQueriesOfSubarray {
    public static void main(String[] args) {

    }
    public int[] xorQueries(int[] arr, int[][] queries) {
        /*
         Brut force : Traverse the query array and traverse according to the given queries T.C not good
         if each query is 1 to n and there are m queries m and n are big
         so for outer loop m and for inner loop n so t.c =o(m*n)
         2ND OPTIMIZE FOR THAT WE JUST MAKE AN EXTRA ARRAY TO STORE PREFIX XOR BUT WE CAN DO IT USING EXTRA SPACE ALSO
         */
        int xor = 0;
        // first we have to find the prefix xor of each element
        for(int i =0;i<arr.length;i++){
            xor ^=arr[i];
            arr[i]= xor;
        }
        // Now creating an answer array of size == number of rows in queries as for each query we have to give the answer
        int [] ans = new int [queries.length];
        for(int i =0;i<queries.length;i++)ans[i]=arr[queries[i][1]]^(queries[i][0]>0?arr[queries[i][0]-1]:0);
        return ans ;
    }
    /*
    T.C FOR CREATING PREFIX XOR IS O(N) N = size of the array + FOR FILLING QUERY O(q) => where q = number of queries
    T.C =O(N+q)
    S.C =O(1)
     */

    public int[] brut_force_2(int [] arr, int [][] queries){

            int n = arr.length,m =queries.length;
            int [] ans = new int[m];
            for(int i =0;i<m;i++){
                int left = queries[i][0],right = queries[i][1];
                for(int j = left;j<=right;j++) ans[i]^=arr[j];
            }
            return ans;
            /*
            t.c =o(m*n)
            s.c =o(1)
             */
        }
    }
/**
 * LEETCODE 1486 : XOR OPERATION IN ARRAY H/W => we have to do in o(1) time hint : observation
 */


