package Graphs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 207. Course Schedule
 Medium
 Topics
 premium lock icon
 Companies
 Hint
 There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

 For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 Return true if you can finish all courses. Otherwise, return false.



 Example 1:

 Input: numCourses = 2, prerequisites = [[1,0]]
 Output: true
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0. So it is possible.
 Example 2:

 Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
 Output: false
 Explanation: There are a total of 2 courses to take.
 To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.


 Constraints:

 1 <= numCourses <= 2000
 0 <= prerequisites.length <= 5000
 prerequisites[i].length == 2
 0 <= ai, bi < numCourses
 All the pairs prerequisites[i] are unique.
 */
public class CourseSchedule {
    public static void main(String[] args) {

    }
    public boolean canFinish(int numCourses, int[][] pre) {
        /* Will be using khan's algo and if the prerequisites froming a cycle then
        we cannot complete the course and if it is not forming a cycle then we cannot complete the  course */

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
        List<Integer> ans = new ArrayList<>();
        int count =0;
        for(int i =0;i<numCourses;i++){
            if(indegree[i]==0)q.add(i);

            else count ++;
        }
        if(count==numCourses)return false;
        while(!q.isEmpty()){
            int front = q.remove();
            /* we initially put the elements whose indegree is zero okay now we are doing operations on the elements that are connected to it */
            ans.add(front);
            for(int ele:adj.get(front)){
                indegree[ele]--;
                if(indegree[ele]==0) q.add(ele);

            }
        }
        return(ans.size()==numCourses);
    }
    /*
T.C =O(V+E) DIRECTED GRAPH
A.S =O(INDEGREE + QUEUE)
*/

}
