package Graphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;

/**
 *

 1584. Min Cost to Connect All Points

 You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i] = [xi, yi].

 The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.

 Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path between any two points.



 Example 1:


 Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 Output: 20
 Explanation:

 We can connect the points as shown above to get the minimum cost of 20.
 Notice that there is a unique path between every pair of points.
 Example 2:

 Input: points = [[3,12],[-2,5],[-4,1]]
 Output: 18


 Constraints:

 1 <= points.length <= 1000
 -106 <= xi, yi <= 106
 All pairs (xi, yi) are distinct
 */
public class MinCostToConnectAllPoints {
    public static void main(String[] args) {

    }

    class Triplet implements Comparable<Triplet> {
        int node;// node means node index
        int parent;
        int dst;

        Triplet(int node, int parent, int dst) {
            this.node = node;
            this.parent = parent;
            this.dst = dst;
        }

        public int compareTo(Triplet t) {
            if (this.dst == t.dst) return this.node - t.node;
            return this.dst - t.dst;
        }
    }

    class Solution {
        public int minCostConnectPoints(int[][] points) {
            // No need for adjacency list okay we will use the given points array
/** ============================================= PRIMS ALGORITHM ==================================================================*/
            PriorityQueue<Triplet> pq = new PriorityQueue<>();
            pq.add(new Triplet(0, -2, 0));
            int sum = 0;
            boolean[] vis = new boolean[points.length];// we have nodes from 0 to n-1

            while (!pq.isEmpty()) {
                Triplet top = pq.remove();
                int node = top.node;
                if (vis[node]) continue;
                int parent = top.parent;
                int dist = top.dst;
                sum += dist;
/**  remember the algo the thing we pop out we insert in MST ans also mark it true here we are not marking true and instead of forming an MST we are addding the answer in sum variable */
                vis[node] = true;
/** we can go from all the other node except itself as each node is connected to reamining all other node */
                for (int i = 0; i < points.length; i++) {

                    if (i == node || i == parent) continue;
    /* as each node are connected to all other remaining node so we don;t need an adjacent list we are travelling thbe whole thing except the node itself the node represent the index
 so we are traverisng the whole array except itself */
                    if (vis[i]) continue;
                    // the edges connected to that point already visited we will no visit we will continue
                    int x1 = points[node][0];
                    int y1 = points[node][1];
                    int x2 = points[i][0];
                    int y2 = points[i][1];
                    int md = Math.abs(x2 - x1) + Math.abs(y2 - y1);
                    pq.add(new Triplet(i, node, md));
                }
            }
            return sum;
        }

    }

    /**
     * ===================================  Let's do it by Kruskal Algorithm  ===========================================================
     */
    class TRIPLET implements Comparable<TRIPLET> {
        int U;// node means node index
        int V;
        int md;

        TRIPLET(int U, int V, int md) {
            this.U = U;
            this.V = V;
            this.md = md;
        }

        public int compareTo(TRIPLET t) {
            if (this.md == t.md) return this.U - U;
            return this.md - t.md;
        }

    }

    public int minCostConnectPoints_1(int[][] points) {
        int n = points.length;// we have n points and it is 0 based indexing from 0 to n-1
        int[] parent = new int[n];
        int[] size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
        PriorityQueue<TRIPLET> pq = new PriorityQueue<>();// first we have to sort the edge list for kruskal algorithm
        // Now put the points in the Min Heap
        for (int u = 0; u < n; u++) {
            for (int v = u + 1; v < n; v++) {
                // as [1,1,wt] represent a point and edge is []---[] so we had done this
                int x1 = points[u][0];
                int y1 = points[u][1];
                int x2 = points[v][0];
                int y2 = points[v][1];
                int md = Math.abs(x2 - x1) + Math.abs(y2 - y1);
                pq.add(new TRIPLET(u, v, md));// Here u and v are not parent they represent edge okay
            }
        }// Instead of min heap we can use ArrayList or Array
        int cost = 0;
        while (!pq.isEmpty()) {
            TRIPLET top = pq.remove();
            int u = top.U;
            int v = top.V;
            int md = top.md;
            if (leader(u, parent) != leader(v, parent)) {
                // then only we take the edge in the mst
                cost += md;
                union(u, v, parent, size);
            }

        }
        return cost;
    }

    int leader(int u, int[] parent) {
        if (parent[u] == u) return u;
        else return parent[u] = leader(parent[u], parent);
        /* we will also make the leader as parent on backtracking we call it path compression */
    }

    void union(int u, int v, int[] parent, int[] size) {
        u = leader(u, parent);
        v = leader(v, parent);
        if (u != v) {
            /* We only do union when leader are different and ,also we do union on the basis of size */
            if (size[u] > size[v]) {
                parent[v] = u;
                size[u] += size[v];
            } else {
                parent[u] = v;
                size[v] += size[u];
            }
        }
    }

/** t.c =o(n*n(inside it we are adding on min heap as adding t.c is o(log n) where n = size of the heap as each node is connected to the
 * remaining node so number of edges is ~n^2 by using nC2 formula we can calculate the number of edges
 *  T.C =O(n*(log 1 + log 2 +-----+log n^2){as insertion of heap takes log n time }+ removing t.c is log n and there are n^2 edges so
 *  calculate on the basis of that
 *  S.C =O(N^2) EDGES IN PRIORITY QUEUE AFTER THE INSERTION IN THE QUEUE + SIZE ARRAY +PARENT ARRAY + RECURSIVE STACK SPACE O(1)
 *  2 LEVEL TREE
 */
/**====== RUNNING KRUSKAL ALGORITHM USING ARRAYLIST INSTEAD OF HEAP AS IT TAKES LOG N TIME FOR INSERTION AND REMOVAL =============*/

class EDGE implements Comparable<EDGE> {
    int U;// node means node index
    int V;
    int md;

    EDGE(int U, int V, int md) {
        this.U = U;
        this.V = V;
        this.md = md;
    }

    public int compareTo(EDGE t) {
        if (this.md == t.md) return this.U - U;
        return this.md - t.md;
    }

}
public int minCostConnectPoints_2(int[][] points) {
    int n = points.length;// we have n points and it is 0 based indexing from 0 to n-1
    int[] parent = new int[n];
    int[] size = new int[n];
    for (int i = 0; i < n; i++) {
        parent[i] = i;
        size[i] = 1;
    }
    ArrayList<EDGE> list = new ArrayList<>();// first we have to sort the edge list for kruskal algorithm
    // Now put the points in the Min Heap
    for (int u = 0; u < n; u++) {
        for (int v = u + 1; v < n; v++) {
            // as [1,1,wt] represent a point and edge is []---[] so we had done this
            int x1 = points[u][0];
            int y1 = points[u][1];
            int x2 = points[v][0];
            int y2 = points[v][1];
            int md = Math.abs(x2 - x1) + Math.abs(y2 - y1);
            list.add(new EDGE(u, v, md));// Here u and v are not parent they represent edge okay
        }
        /* Now that T.C of that nested loop get reduce to o(N^2) */
    }
    int cost = 0;
    Collections.sort(list);

    for(int i = 0; i<list.size(); i++) {
        EDGE top = list.get(i);
        int u = top.U;
        int v = top.V;
        int md = top.md;
        if (leader_1(u, parent) != leader_1(v, parent)) {
            // then only we take the edge in the mst
            cost += md;
            union_1(u, v, parent, size);
        }

    }
    return cost;
}


    int leader_1(int u, int[] parent) {
        if (parent[u] == u) return u;
        else return parent[u] = leader(parent[u], parent);
        /* we will also make the leader as parent on backtracking we call it path compression */
    }

    void union_1(int u, int v, int[] parent, int[] size) {
        u = leader(u, parent);
        v = leader(v, parent);
        if (u != v) {
            /* We only do union when leader are different and ,also we do union on the basis of size */
            if (size[u] > size[v]) {
                parent[v] = u;
                size[u] += size[v];
            } else {
                parent[u] = v;
                size[v] += size[u];
            }
        }
    }
    /* T.C =O(N^2 FROM N^2 LOG N )+ ALSO   FOR THE SORTING OF ARRAY LIST  T.C N^2 LOG N
    S.C = SAME
     */
}