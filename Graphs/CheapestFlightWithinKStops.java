package Graphs;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
 * Output: 700
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
 * Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
 * Example 2:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 * Output: 200
 * Explanation:
 * The graph is shown above.
 * The optimal path with at most 1 stop from city 0 to 2 is marked in red and has cost 100 + 100 = 200.
 * Example 3:
 *
 *
 * Input: n = 3, flights = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 * Output: 500
 * Explanation:
 * The graph is shown above.
 * The optimal path with no stops from city 0 to 2 is marked in red and has cost 500.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 100
 * 0 <= flights.length <= (n * (n - 1) / 2)
 * flights[i].length == 3
 * 0 <= fromi, toi < n
 * fromi != toi
 * 1 <= pricei <= 104
 * There will not be any multiple flights between two cities.
 * 0 <= src, dst, k < n
 * src != dst
 */



public class CheapestFlightWithinKStops {
    public static void main(String[] args) {

    }
    class Pair{
        int to;
        int price;
        Pair(int to, int price){
            this.to = to;
            this.price = price;
        }
    }
    class Triplet implements Comparable<Triplet>{
        int node;
        int cost;
        int stops;
        Triplet(int node,int cost, int stops){
            this.node = node;
            this.cost = cost;
            this.stops = stops;

        }
        public int compareTo(Triplet p){
            if(this.stops == p.stops) return this.node - p.node;
            return this.stops-p.stops;
        }

    }
    class Solution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            List<List<Pair>> adj = new ArrayList<>();
            for(int i =0;i<n;i++)adj.add(new ArrayList<>());
            for(int i =0;i<flights.length;i++){
                int from = flights[i][0],to = flights[i][1],price = flights[i][2];
                adj.get(from).add(new Pair(to,price));
            }
            int [] ans = new int [n];
            Arrays.fill(ans,Integer.MAX_VALUE);
            ans[src]=0;
            // PriorityQueue<Triplet>pq = new PriorityQueue<>();
            /** Instead of priority queue we can use Queue as we had observe that we use dijkestra (modified version ) on the basis of the stops then we can see that element which get inserted first get remove first so instead of priority queue we can use Queue  */
            Queue<Triplet> pq = new LinkedList<>();
            pq.add(new Triplet(src,0,0));
            while(!pq.isEmpty()){
                Triplet top = pq.remove();
                int node = top.node,cost = top.cost,stops = top.stops ;
                //No need for this below line
                //  if(node == dst)return ans[node];// instead of cost we replace that with this
                if(stops==k+1) continue;// It is not destination but stop is k+1 that means we are in wrong path skip it
                for(Pair p : adj.get(node)){
                    int totalCost = cost+p.price;
                    if(totalCost<ans[p.to]){
                        ans[p.to]= totalCost;
                        pq.add(new Triplet(p.to,totalCost,stops+1));
                    }
                }
            }
            if(ans[dst]==Integer.MAX_VALUE) return -1;
            return ans[dst];
        }
    }
}
