package Greedy;

import java.util.Arrays;

/**
 * 646. Maximum Length of Pair Chain


 * You are given an array of n pairs pairs where pairs[i] = [lefti, righti] and lefti < righti.
 *
 * A pair p2 = [c, d] follows a pair p1 = [a, b] if b < c. A chain of pairs can be formed in this fashion.
 *
 * Return the length longest chain which can be formed.
 *
 * You do not need to use up all the given intervals. You can select pairs in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: pairs = [[1,2],[2,3],[3,4]]
 * Output: 2
 * Explanation: The longest chain is [1,2] -> [3,4].
 * Example 2:
 *
 * Input: pairs = [[1,2],[7,8],[4,5]]
 * Output: 3
 * Explanation: The longest chain is [1,2] -> [4,5] -> [7,8].
 *
 *
 * Constraints:
 *
 * n == pairs.length
 * 1 <= n <= 1000
 * -1000 <= lefti < righti <= 1000
 */
public class MaximumLengthOfPairChain {
    public static void main(String[] args) {

    }
        public int findLongestChain(int[][] pairs) {
            Arrays.sort(pairs,(a, b)->Integer.compare(a[1],b[1]));
            // this time we will sort on the basis of the end
            int cur = Integer.MIN_VALUE,ans =0;
            for(int [] pair :pairs){
                if(cur<pair[0]){cur =pair[1];
                    ans++;
                }

            }
            return ans;
        }
    }

