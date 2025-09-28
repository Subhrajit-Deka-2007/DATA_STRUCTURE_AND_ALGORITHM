package Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 785. Is Graph Bipartite?
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 *
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
 * Output: false
 * Explanation: There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.
 * Example 2:
 *
 *
 * Input: graph = [[1,3],[0,2],[1,3],[0,2]]
 * Output: true
 * Explanation: We can partition the nodes into two sets: {0, 2} and {1, 3}.
 *
 *
 * Constraints:
 *
 * graph.length == n
 * 1 <= n <= 100
 * 0 <= graph[u].length < n
 * 0 <= graph[u][i] <= n - 1
 * graph[u] does not contain u.
 * All the values of graph[u] are unique.
 * If graph[u] contains v, then graph[v] contains u.
 */
public class IsGraphBipartite {
    public static void main(String[] args) {

    }
    public boolean isBipartite(int[][] graph) {
            int n = graph.length;
            int [] vis = new int [n];
            Arrays.fill(vis,-1);
            boolean[] ans = {true};// let initially it is true
            // 1 -> red, 0 -> means blue
            // Initially filling the first node with red colour
            for(int i =0;i<n;i++){
                // The graph may contain provincess  so we are using a loop and we have to check bipartitie for all the provincess then all are true then we call it Bipartite Graph
                if(!ans[0])return ans[0];
                if(vis[i]==-1)bfs(i,graph,vis,ans);
            }
            return ans[0];
        }
        public void bfs(int i , int[][] adj , int [] vis,boolean [] ans ){
            vis[i]=0;
            /* we are giving colour to starting point of a provincess */

            Queue<Integer> q = new LinkedList<>();
            q.add(i);// Adding the current node to the queue
            int front;
            // front will represent current elements connected to by using the front as index
            int color;
            while(!q.isEmpty()){
                front = q.remove();
                color = vis[front];
                for(int ele : adj[front]){
                    if(vis[ele]==vis[front]){
/*
If already visited and color is same then it is not bipartite front is the element we remove and ele is the elements that are connected to  it and checking if it is visitred plus if
the removing element colour matches with the element that are connected to it .
*/
                        ans[0] = false;
                        return;
                    }
                    if(vis[ele]==-1){
                        q.add(ele);
                        vis[ele]=1-color;
/*
By removing front we are accessing its element that are connected to it and giving them different color then their parent if already not visited
*/
                    }
                }
            }
        }
    public void dfs(int i ,int [][] adj, int [] vis , boolean [] ans){
        /**
         * The things that I need to write inside the calling function
         *   for(int i =0;i<n;i++){
         *     The graph may contain provinces ,so we are using a loop and, we have to check Bipartite for
         *     all the provinces then all are true then we call it Bipartite Graph
         *             if(!ans[0])return ans[0];
         *               if(vis[i]==-1){
         *                 vis[i] = 0;
         *                 dfs(i,graph,vis,ans);
         *         }
         */
        for(int ele : adj[i]){
            if(vis[ele]==vis[i]){
                ans[0] = false;
                return;
            }
            if(vis[ele]==-1){
                vis[ele] = 1-vis[i];
                dfs(ele,adj,vis,ans);
            }
        }

    }
    }

