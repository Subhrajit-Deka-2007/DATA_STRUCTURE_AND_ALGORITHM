package Graphs;

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
    class Triplet implements Comparable<Triplet>{
        int node;// node means node index
        int parent;
        int dst;
        Triplet(int node, int parent, int dst ){
            this. node = node;
            this.parent = parent;
            this.dst = dst;
        }
        public int compareTo(Triplet t){
            if(this.dst==t.dst) return this.node -t.node;
            return this.dst - t.dst;
        }
    }
    class Solution {
        public int minCostConnectPoints(int[][] points) {
            // No need for adjacency list okay we will use the given points array
/** ============================================= PRIMS ALGORITHM ==================================================================*/
            PriorityQueue<Triplet> pq = new PriorityQueue<>();
            pq.add(new Triplet(0,-2,0));
            int sum =0;
            boolean [] vis = new boolean [points.length];// we have nodes from 0 to n-1

            while(!pq.isEmpty()){
                Triplet top = pq.remove();
                int node = top.node;
                if(vis[node])continue;
                int parent = top.parent;
                int dist = top.dst;
                sum +=dist;
/**  remember the algo the thing we pop out we insert in MST ans also mark it true here we are not marking true and instead of forming an MST we are addding the answer in sum variable */
                vis[node]= true;
/** we can go from all the other node except itself as each node is connected to reamining all other node */
                for(int i =0;i<points.length;i++){

                    if(i==node||i==parent) continue;
    /* as each node are connected to all other remaining node so we don;t need an adjacent list we are travelling thbe whole thing except the node itself the node represent the index
 so we are traverisng the whole array except itself */
                    if(vis[i])continue;
                    // the edges connected to that point already visited we will no visit we will continue
                    int x1 = points[node][0];
                    int y1 = points[node][1];
                    int x2 = points[i][0];
                    int y2 = points[i][1];
                    int md =Math.abs(x2-x1)+Math.abs(y2-y1);
                    pq.add(new Triplet(i,node,md));
                }
            }
            return sum ;
        }

    }
}
