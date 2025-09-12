package DynamicProgramming;

import java.util.Arrays;

/**
 *
 Code
 354. Russian Doll Envelopes
 You are given a 2D array of integers envelopes where envelopes[i] = [wi, hi] represents the width and the height of an envelope.

 One envelope can fit into another if and only if both the width and height of one envelope are greater than the other envelope's width and height.

 Return the maximum number of envelopes you can Russian doll (i.e., put one inside the other).

 Note: You cannot rotate an envelope.



 Example 1:

 Input: envelopes = [[5,4],[6,4],[6,7],[2,3]]
 Output: 3
 Explanation: The maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
 Example 2:

 Input: envelopes = [[1,1],[1,1],[1,1]]
 Output: 1


 Constraints:

 1 <= envelopes.length <= 105
 envelopes[i].length == 2
 1 <= wi, h
 */
public class RussianDollEnvelopes {
    public static void main(String[] args) {

    }
}
    class olution {
        public class Envelop implements Comparable<Envelop> {
            int w;
            int h;

            Envelop(int w, int h) {
                this.w = w;
                this.h = h;
            }

            public int compareTo(Envelop e) {
                if (this.w == e.w) return e.h - this.h;// sorting in decreasing order when both row are same
                return this.w - e.w;
            }
        }
        public int maxEnvelopes(int[][] envelopes) {
            int n =envelopes.length;
            Envelop [] arr = new Envelop[n];
            for(int i =0;i<arr.length;i++)arr[i]=new Envelop(envelopes[i][0],envelopes[i][1]);
            Arrays.sort(arr);
            // Now LIS on the column of the envelope we can say LIS on height
            int[] dp = new int[n];
            int max =0;
            for(int i =0;i<n;i++){
                for(int j=0;j<=i-1;j++){
                    if(arr[j].h<arr[i].h)dp[i]=Math.max(dp[i],dp[j]);
                }
                dp[i]+=1;
                max = Math.max(max,dp[i]);
        }
            return max;
    }
    /*
    T.C =O(N(FOR INSERTING )+N LOG N (FOR SORTING)+LIS  =O(N^2))---> THERE IS ANOTHER METHOD FOR LIS THAT IS NOT BY DP IT IS BY BINARY SEARCH
    WHOSE T.C IS LESS(N LOG N ) OUR CODE IS CORRECT BUT SINCE IT IS A HARD LEVEL QUESTION IT DIDN'T GET ACCEPTED
    S.C =O(N+N)
     */
}
