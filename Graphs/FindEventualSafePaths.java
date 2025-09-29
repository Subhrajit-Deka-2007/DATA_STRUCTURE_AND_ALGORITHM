package Graphs;

import java.util.*;

/**
 * 802. Find Eventual Safe States

 * There is a directed graph of n nodes with each node labeled from 0 to n - 1. The graph is represented by a 0-indexed 2D integer array graph where graph[i] is an integer array of nodes adjacent to node i, meaning there is an edge from node i to each node in graph[i].
 *
 * A node is a terminal node if there are no outgoing edges. A node is a safe node if every possible path starting from that node leads to a terminal node (or another safe node).
 *
 * Return an array containing all the safe nodes of the graph. The answer should be sorted in ascending order.
 *
 *
 *
 * Example 1:
 *
 * Illustration of graph
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 * Explanation: The given graph is shown above.
 * Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
 * Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.
 * Example 2:
 *
 * Input: graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
 * Output: [4]
 * Explanation:
 * Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
 *
 *
 * Constraints:
 *
 * n == graph.length
 * 1 <= n <= 104
 * 0 <= graph[i].length <= n
 * 0 <= graph[i][j] <= n - 1
 * graph[i] is sorted in a strictly increasing order.
 * The graph may contain self-loops.
 * The number of edges in the graph will be in the range [1, 4 * 104]
 */
public class FindEventualSafePaths {
    public static void main(String[] args) {

    }
    public List<Integer> eventualSafeNodes(int[][] graph) {
        /* turn the graph into reverse graph(ajancency list of reverse graph ) then apply kahn's algorithm
         we can also find the nodes which are part of cycle we will put
         all the elments that are not part of cycle into hasset then the reamining elements
         which are not in hashset are the elements which are part of the cycle
         */
            int n = graph.length;
            List<List<Integer>> adj = new ArrayList<>();
            for(int i =0;i<n;i++)adj.add(new ArrayList<>());
            int [] indegree = new int[n];
            for(int i =0;i<n;i++){
                for(int ele : graph[i]){// Here we are using nested loop because the size of the arrays are not fixed so we have to use an extra in previous question each size of the array is 2 */
                    // in original graph eges is from i -->ele reverse will be ele-->i
                    adj.get(ele).add(i);
                    // ele --> i so we increase i 's indegree
                    indegree[i]++;
                }
            }


            // Now Apply Kahn's Algorithm
            Queue<Integer> q = new ArrayDeque<>();
            for(int i =0;i<n;i++)if(indegree[i]==0)q.add(i);
            List<Integer> ans = new ArrayList<>();
            int front ;

            while(!q.isEmpty()){
                front = q.remove();
                ans.add(front);
                for(int ele : adj.get(front)){
                    indegree[ele]--;
                    if(indegree[ele]==0) q.add(ele);
                }
            }
            Collections.sort(ans);
            return ans;
        }
    }

