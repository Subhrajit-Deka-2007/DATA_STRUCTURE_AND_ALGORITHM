package Graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Given an undirected graph with V vertices and E edges, represented as a 2D vector edges[][], where each entry edges[i] = [u, v] denotes an edge between vertices u and v, determine whether the graph contains a cycle or not. The graph can have multiple component.
 *
 * Examples:
 *
 * Input: V = 4, E = 4, edges[][] = [[0, 1], [0, 2], [1, 2], [2, 3]]
 * Output: true
 * Explanation:
 *
 * 1 -> 2 -> 0 -> 1 is a cycle.
 * Input: V = 4, E = 3, edges[][] = [[0, 1], [1, 2], [2, 3]]
 * Output: false
 * Explanation:
 *
 * No cycle in the graph.
 * Constraints:
 * 1 ≤ V ≤ 105
 * 1 ≤ E = edges.size() ≤ 10
 */

public class CycleDetectionUndirectedGraph {
    static class P {
        int node;
        int parent;

        P(int node, int parent) {
            this.node = node;
            this.parent = parent;
        }

    }


    static class Solution {

        class P {
            int node;
            int parent;

            P(int node, int parent) {
                this.node = node;
                this.parent = parent;
            }
        }

        public boolean isCycle(int V, int[][] edges) {
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }

            for (int[] edge : edges) {
                adj.get(edge[0]).add(edge[1]);
                adj.get(edge[1]).add(edge[0]);
            }
            System.out.println(adj);
            boolean[] vis = new boolean[V];
            // Loop to handle disconnected components of the graph
//                for (int i = 0; i < V; i++) {
//                    if (!vis[i]) {
//                        if (bfs(i, vis, adj)) {
//                            return true; // Cycle found in one of the components
//                        }
//                    }
//                }
//                return false; // No cycle found in any component
            boolean x = false;
            for (int i = 0; i < V; i++) {
                if (!vis[i]) x = dfs(i, vis, adj, -1);
                System.out.println("x "+x);
                if (x) return x;
            }
            return x;
        }

        public boolean bfs(int startNode, boolean[] vis, List<List<Integer>> adj) {
            Queue<P> q = new ArrayDeque<>();
            q.add(new P(startNode, -1)); // Start with -1 as parent
            vis[startNode] = true;
            System.out.println(adj);
            while (!q.isEmpty()) {
                P current = q.remove();
                int currentNode = current.node;
                int parentNode = current.parent;
                System.out.println("curr " + currentNode + " parent " + parentNode);
                for (int neighbor : adj.get(currentNode)) {
                    // If the neighbor is not visited, visit it and add to queue
                    if (!vis[neighbor]) {
                        System.out.println(" neighbour" + neighbor);
                        vis[neighbor] = true;
                        q.add(new P(neighbor, currentNode));
                    }
                    // If the neighbor IS visited AND it's NOT the parent, we found a cycle
                    else if (neighbor != parentNode) {
                        System.out.println(" neighbor " + neighbor + " parentNode " + parentNode);
                        return true;
                    }
                }
            }
            return false;
        }
/*
T.C =O(V+2E)
A.S = QUEUE+ VIS (SIZE OF VISITED IS V)
 */


        public static void main(String[] args) {
            Solution obj = new Solution();
            int[][] edges = {{0, 1}, {1, 2}, {1,3},{2,4},{3,4},{4,5}};
            obj.isCycle(6, edges);
        }

        public boolean dfs(int start, boolean[] vis, List<List<Integer>> adj, int parent) {
            vis[start] = true;
            for (int ele : adj.get(start)) {
                if (!vis[ele]){
                    if(dfs(ele, vis, adj, start)) return true;
                }
                else if (vis[ele] && ele != parent)return true;
            }
            return false;
        }
    }
}
