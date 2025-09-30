package Graphs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 743. Network Delay Time

 You are given a network of n nodes, labeled from 1 to n. You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi), where ui is the source node, vi is the target node, and wi is the time it takes for a signal to travel from source to target.

 We will send a signal from a given node k. Return the minimum time it takes for all the n nodes to receive the signal. If it is impossible for all the n nodes to receive the signal, return -1.



 Example 1:


 Input: times = [[2,1,1],[2,3,1],[3,4,1]], n = 4, k = 2
 Output: 2
 Example 2:

 Input: times = [[1,2,1]], n = 2, k = 1
 Output: 1
 Example 3:

 Input: times = [[1,2,1]], n = 2, k = 2
 Output: -1


 Constraints:

 1 <= k <= n <= 100
 1 <= times.length <= 6000
 times[i].length == 3
 1 <= ui, vi <= n
 ui != vi
 0 <= wi <= 100
 All the pairs (ui, vi) are unique. (i.e., no multiple edges.)
 */
class PAIR implements Comparable<PAIR>{
    int node ;
    int time;
    PAIR(int node, int time){
        this.time = time;
        this.node = node;
    }
    // we are making a class of pair so when we insert in Priority Queue it does sorting so, we have to use comparator
    public int compareTo(PAIR p){
        if(this.time==p.time)return this.node -p.node;
        return this.time - p.time;
        // or we can write Integer.compare(this.time,p.time);
    }
}


public class NetworkDelayTime {
    public static void main(String[] args) {

    }
    public int networkDelayTime(int[][] times, int n, int k) {

        List<List<PAIR>> adj = new ArrayList<>();// [(-,-),(-,-),(-,)---]
        for(int i =0;i<n+1;i++)adj.add(new ArrayList<>());
        int u,v,time;
        for(int i =0;i<times.length;i++){
             u = times[i][0];
             v = times[i][1];
            time = times[i][2];
            adj.get(u).add(new PAIR(v,time));
        }
        /*                                     Dijkestra Algorithm                                                             */
        /* First make an array then we don't need 0 index as it is o based indexing and mark the source tower to zero */
        int [] ans = new int [n+1];// H/w use map
        Arrays.fill(ans,Integer.MAX_VALUE);
        ans[0] = 0;
        ans[k]=0;
        // Now make a priority Queue we can also use queue but if we use queue time complexity is bad
        PriorityQueue<PAIR> pq = new PriorityQueue<>();
        pq.add(new PAIR(k,0));
        while(!pq.isEmpty()){
            PAIR top = pq.remove();
            int node = top.node;
             time = top.time;
             if(time>ans[node])continue;// Little optimize
             for(PAIR p : adj.get(node)){// Node represent index or the node it contains the elements which are connected to it
                 int totalTime = top.time+p.time;
                 if(totalTime<ans[p.node]){
                     ans[p.node]=totalTime;
                     pq.add(new PAIR(p.node,totalTime));
                 }
             }
        }
        int max =Integer.MIN_VALUE;
        for(int i =1;i<ans.length;i++) {
            if (ans[i] == Integer.MAX_VALUE) return -1;
            max = Math.max(max, ans[i]);
        }
        return max;
    }
    /*
    For Dijkestra algorithm we need an answer array
    Space needed for Adjacency List = O(V+2E) // EACH EDGES IS COMING TWICE 0---1 SO 0 CONTAINS 1 AND 1 CONTAINS 0
    and V is for the node which is not connected to other node
    T.C =O(V+E)
    S.C =O(PRIORITY Queue)+ ans array
    IF THERE ARE N NODES AND EACH NODE WILL CONNECT THE REMAINING N-1 NODE SO I CAN SAY MY OBSERVATION
    S.C =O(N-1+N+1) =O(2N)
    time complexity and space complexity calculated by me
     */
}
