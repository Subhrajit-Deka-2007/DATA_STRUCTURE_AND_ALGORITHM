package Graphs;

import java.util.*;

/**
 *

 210. Course Schedule II

 There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

 For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.



 Example 1:

 Input: numCourses = 2, prerequisites = [[1,0]]
 Output: [0,1]
 Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
 Example 2:

 Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 Output: [0,2,1,3]
 Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
 So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 Example 3:

 Input: numCourses = 1, prerequisites = []
 Output: [0]


 Constraints:

 1 <= numCourses <= 2000
 0 <= prerequisites.length <= numCourses * (numCourses - 1)
 prerequisites[i].length == 2
 0 <= ai, bi < numCourses
 ai != bi
 All the pairs [ai, bi] are distinct.
 */
public class CourseScheduleII {
    public static void main(String[] args) {
      CourseScheduleII obj = new CourseScheduleII();
      int [][] pre ={{1,0}};

    }
    public int[] findOrder(int numCourses, int[][] pre) {
        List<List<Integer>> adj = new ArrayList<>();
        for(int i =0;i<numCourses;i++)adj.add(new ArrayList<>());
        // Now form a graph given [a,b] means it should be b -->a
        int a,b;
        int [] indegree = new int [numCourses];
        for(int i =0;i<pre.length;i++){
            a = pre[i][0];
            b = pre[i][1];// b-->a
            adj.get(b).add(a);
            indegree[a]++;
        }
        // Kahn's Algorithm

     /*
      way to fill the indegree for(int i =0;i<adj.size();i++)indegeree[i]= adj.get(i).size();
      */


        /* Kahn's Algorithm: In kahn's algorithm we don't need visited array at all  */
        Queue<Integer> q = new LinkedList<>();
        int []  ans = new int [numCourses];
        int k =0;
        int count =0;
        for(int i =0;i<numCourses;i++){
            if(indegree[i]==0)q.add(i);
            else count ++;
        }
        System.out.println(count);
        if(count == numCourses) return new int[0];



        while(!q.isEmpty()){
            int front = q.remove();
            /* we initially put the elements whose in degree is zero okay now we are doing operations on the elements that are connected to it */
            ans[k++]= front;
            for(int ele:adj.get(front)){
                indegree[ele]--;
                if(indegree[ele]==0) q.add(ele);

            }
        }
        if(k==numCourses)return ans;
        return new int[0];
    }

    class Solution {
        // Solve using DFS
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            // 1. Build the adjacency list
            List<List<Integer>> adj = new ArrayList<>();
            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>());
            }
            for (int[] pre : prerequisites) {
                adj.get(pre[1]).add(pre[0]);
            }

            // 2. Initialize visited array and result list/stack
            int[] visited = new int[numCourses]; // 0=Unvisited, 1=Visiting, 2=Visited
            Stack<Integer> stack = new Stack<>();

            // 3. Call DFS for each course to handle disconnected components
            for (int i = 0; i < numCourses; i++) {
                if (visited[i] == 0) {
                    if (!dfs(i, adj, visited, stack)) {
                        return new int[0]; // Cycle detected, return empty array
                    }
                }
            }

            // 4. Convert stack to result array
            int[] result = new int[numCourses];
            int i = 0;
            while (!stack.isEmpty()) {
                result[i++] = stack.pop();
            }
            return result;
        }

        private boolean dfs(int course, List<List<Integer>> adj, int[] visited, Stack<Integer> stack) {
            visited[course] = 1; // Mark as "Visiting"

            for (int neighbor : adj.get(course)) {
                if (visited[neighbor] == 1) {
                    return false; // Cycle detected
                }
                if (visited[neighbor] == 0) {
                    if (!dfs(neighbor, adj, visited, stack)) {
                        return false; // Propagate cycle detection
                    }
                }
            }

            visited[course] = 2; // Mark as "Visited"
            stack.push(course); // Add to topological order
            return true;
        }
    }





    private void dfs(int i, int[] ans, List<List<Integer>> adj, int[] k, int[] vis, boolean[] flag) {
        // If a cycle has been detected by a different path, stop all work.
        if (!flag[0]) return;

        vis[i] = 1; // 1 = VISITING

        for (int ele : adj.get(i)) {
            if (vis[ele] == 0) { // If neighbor is UNVISITED
                dfs(ele, ans, adj, k, vis, flag);
                // After the recursive call, check if it found a cycle
                if (!flag[0]) return;
            } else if (vis[ele] == 1) { // If neighbor is VISITING
                // This is a back-edge, we found a cycle.
                flag[0] = false;
                return;
            }
            // If vis[ele] == 2 (VISITED), we do nothing.
        }

        vis[i] = 2; // 2 = VISITED
        ans[k[0]++] = i; // Add to answer in post-order
    }
    public int[] findOrder_3(int numCourses, int[][] pre) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < pre.length; i++) {
            int a = pre[i][0];
            int b = pre[i][1]; // b-->a
            adj.get(b).add(a);
        }

        int[] ans = new int[numCourses];
        int[] vis = new int[numCourses]; // 0=Unvisited, 1=Visiting, 2=Visited
        int[] k = new int[1]; // To pass index by reference
        boolean[] flag = {true}; // To pass cycle-check by reference

        // Call DFS for each unvisited node (handles disconnected graphs)
        for (int i = 0; i < numCourses; i++) {
            if (vis[i] == 0) {
                dfs(i, ans, adj, k, vis, flag);
                // If the flag is set to false, a cycle was found, so break.
                if (!flag[0]) break;
            }
        }

        // If a cycle was found (flag=false) or not all courses were visited (k[0]!=numCourses)
        if (!flag[0] || k[0] != numCourses) {
            return new int[0];
        }

        // Reverse the answer array (since DFS gives post-order)
        int i = 0;
        int j = numCourses - 1;
        while (i < j) {
            ans[i] = ans[i] + ans[j];
            ans[j] = ans[i] - ans[j];
            ans[i] = ans[i] - ans[j];
            i++;
            j--;
        }

        return ans;
    }
}
