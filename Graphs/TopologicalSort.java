package Graphs;

import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {
    public static void main(String[] args) {
        // Making a Graph
        /*
        [0]--->[1]--->[2]--->[4]
                |->[3]<-|
         */
        List<List<Integer>> list = new ArrayList<>();
        for(int i =0;i<5;i++)list.add(new ArrayList<>());
        list.get(0).add(1);
        list.get(1).add(2);
        list.get(1).add(3);
        list.get(2).add(3);
        list.get(2).add(4);
        System.out.println(list );
        boolean [] vis = new boolean[5];
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i =0;i<5;i++) if(!vis[i])dfs(i,vis,ans,list);
        System.out.println(" Topological Sort "+ans.reversed());
    }
    public static void dfs(int ele, boolean [] vis, ArrayList<Integer>ans,List<List<Integer>>adj ){
        // Topological Sort using DFS
        vis[ele] = true;
        for(int i : adj.get(ele)) dfs(i,vis,ans,adj);
        ans.add(ele);
    }
    /*                         TOPOLOGICAL SORT USING BFS WHICH IS ALSO CALLED (KAHN'S ALGORITHM )                                              */




}
