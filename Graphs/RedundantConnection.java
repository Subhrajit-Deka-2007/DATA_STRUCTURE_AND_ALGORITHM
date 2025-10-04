package Graphs;

/**
 * 684. Redundant Connection

 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * You are given a graph that started as a tree with n nodes labeled from 1 to n, with one additional edge added. The added edge has two different vertices chosen from 1 to n, and was not an edge that already existed. The graph is represented as an array edges of length n where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the graph.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of n nodes. If there are multiple answers, return the answer that occurs last in the input.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: edges = [[1,2],[1,3],[2,3]]
 * Output: [2,3]
 * Example 2:
 *
 *
 * Input: edges = [[1,2],[2,3],[3,4],[1,4],[1,5]]
 * Output: [1,4]
 *
 *
 * Constraints:
 *
 * n == edges.length
 * 3 <= n <= 1000
 * edges[i].length == 2
 * 1 <= ai < bi <= edges.length
 * ai != bi
 * There are no repeated edges.
 * The given graph is connected.
 */
public class RedundantConnection {
    public static void main(String[] args) {

    }
    class Solution {
        public int[] findRedundantConnection(int[][] edges) {
            /** Solve using DSU(Disjoint Set Union ) */
            int n = edges.length;
            int [] parent ,size;
            parent = new int[n+1];// as 1 based indexing
            size = new int [n+1];
            for(int i =1;i<n+1;i++){
                parent[i]=i;
                size[i]=1;
            }
            int [] ans = new int [2];
            for(int [] arr:edges){
                int u = arr[0],v = arr[1];
                if(leader(u,parent)==leader(v,parent)){// Cycle detected return the edge
                    // if there is already same so
                    ans[0] =u;
                    ans[1] =v;
                    break;
                }else union(u,v,parent,size);
            }
            return ans;
        }
        /**======================================     DSU START ================================= */
        int leader(int u, int [] parent){
            if(parent[u]==u) return u;
            else return parent[u] = leader(parent[u],parent);
            /* we will also make the leader as parent on backtracking we call it path compression */
        }
        void union(int u, int v,int [] parent,int [] size){
            u = leader(u,parent);
            v = leader(v,parent);
            if(u!=v){
                /* We only do union when leader are different and ,also we do union on the basis of size */
                if(size[u]>size[v]){
                    parent[v]= u;
                    size[u]+=size[v];
                }else{
                    parent[u]=v;
                    size[v]+=size[u];
                }
            }
        }
    }
    /**
     * According to algo if a given edges nodes leader is  same then it is cycle, as initially we consider the leader of the node is the node itself
     *
     * T.C =O(N+N+N)
     * S.C = O(
      */
}

