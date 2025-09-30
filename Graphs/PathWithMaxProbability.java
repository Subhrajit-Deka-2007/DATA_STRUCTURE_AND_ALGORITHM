package Graphs;

import java.util.*;

/**
 * 1514. Path with Maximum Probability
 *
 * You are given an undirected weighted graph of n nodes (0-indexed), represented by an edge list where edges[i] = [a, b] is an undirected edge connecting the nodes a and b with a probability of success of traversing that edge succProb[i].
 *
 * Given two nodes start and end, find the path with the maximum probability of success to go from start to end and return its success probability.
 *
 * If there is no path from start to end, return 0. Your answer will be accepted if it differs from the correct answer by at most 1e-5.
 *
 *
 *
 * Example 1:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.2], start = 0, end = 2
 * Output: 0.25000
 * Explanation: There are two paths from start to end, one having a probability of success = 0.2 and the other has 0.5 * 0.5 = 0.25.
 * Example 2:
 *
 *
 *
 * Input: n = 3, edges = [[0,1],[1,2],[0,2]], succProb = [0.5,0.5,0.3], start = 0, end = 2
 * Output: 0.30000
 * Example 3:
 *
 *
 *
 * Input: n = 3, edges = [[0,1]], succProb = [0.5], start = 0, end = 2
 * Output: 0.00000
 * Explanation: There is no path between 0 and 2.
 *
 *
 * Constraints:
 *
 * 2 <= n <= 10^4
 * 0 <= start, end < n
 * start != end
 * 0 <= a, b < n
 * a != b
 * 0 <= succProb.length == edges.length <= 2*10^4
 * 0 <= succProb[i] <= 1
 * There is at most one edge between every two nodes.
 */


public class PathWithMaxProbability {
    public static void main(String[] args) {

    }

    class A implements Comparable<A>{
        int node;
        double prob;
        A(int node, double prob){
            this.node = node;
            this.prob = prob;
        }
        public int compareTo(A p){
            if(this.prob == p.prob)return this.node -p.node;// as these are integer
            return Double.compare(this.prob,p.prob);// (int)(this.prob-prob); gives wrong answer
        }
    }
    class Solution {
        public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
            /**                                     DIJKESTRA ALGORITHM                                                                     */
            List<List<A>> adj = new ArrayList<>();
            for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
            for (int i = 0; i < edges.length; i++) {
                int u = edges[i][0];
                int v = edges[i][1];
                double prob = succProb[i];
                adj.get(u).add(new A(v, prob));
                adj.get(v).add(new A(u, prob));
                // because it is undirected Graph
            }
            /*--- Dijesktra algo started */
            double[] ans = new double[n];
            ans[start] = 1.00;
            PriorityQueue<A> pq = new PriorityQueue<>(Collections.reverseOrder());// Max Heap
            pq.add(new A(start,1.0));
            while (!pq.isEmpty()) {
                A top = pq.remove();
                int node = top.node;
                double prob = top.prob;
                if (top.prob < ans[node]) continue;
                for (A p : adj.get(node)) {
                    double totalProb = top.prob * p.prob;
                    if (totalProb > ans[p.node]) {
                        ans[p.node] = totalProb;
                        pq.add(new A(p.node, totalProb));
                    }
                }
            }
            return ans[end];
        }

    }
}
