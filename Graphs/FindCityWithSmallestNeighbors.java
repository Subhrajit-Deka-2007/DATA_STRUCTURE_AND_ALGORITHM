package Graphs;

/**
 *

 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance

 There are n cities numbered from 0 to n-1. Given the array edges where edges[i] = [fromi, toi, weighti] represents a bidirectional and weighted edge between cities fromi and toi, and given the integer distanceThreshold.

 Return the city with the smallest number of cities that are reachable through some path and whose distance is at most distanceThreshold, If there are multiple such cities, return the city with the greatest number.

 Notice that the distance of a path connecting cities i and j is equal to the sum of the edges' weights along that path.



 Example 1:



 Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 Output: 3
 Explanation: The figure above describes the graph.
 The neighboring cities at a distanceThreshold = 4 for each city are:
 City 0 -> [City 1, City 2]
 City 1 -> [City 0, City 2, City 3]
 City 2 -> [City 0, City 1, City 3]
 City 3 -> [City 1, City 2]
 Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
 Example 2:



 Input: n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
 Output: 0
 Explanation: The figure above describes the graph.
 The neighboring cities at a distanceThreshold = 2 for each city are:
 City 0 -> [City 1]
 City 1 -> [City 0, City 4]
 City 2 -> [City 3, City 4]
 City 3 -> [City 2, City 4]
 City 4 -> [City 1, City 2, City 3]
 The city 0 has 1 neighboring city at a distanceThreshold = 2.


 Constraints:

 2 <= n <= 100
 1 <= edges.length <= n * (n - 1) / 2
 edges[i].length == 3
 0 <= fromi < toi < n
 1 <= weighti, distanceThreshold <= 10^4
 All pairs (fromi, toi) are distinct
 */
public class FindCityWithSmallestNeighbors {
    class Solution {
        public int findTheCity(int n, int[][] edges, int t) {
            // We have to make an adjacency matrix ehich later get converted to ans array
            int [][] dist = new int [n][n];
            for(int i =0;i<n;i++)for(int j =0;j<n;j++)dist[i][j]=Integer.MAX_VALUE;

            // Now first lets make it adjacency matrix
            for(int i =0;i<edges.length;i++){
                int u = edges[i][0];
                int v = edges[i][1];
                int wt = edges[i][2];
                dist[u][v]=wt;
                dist[v][u]=wt;

            }
            // for(int [] arr : edges){// Each 2 d array represent an array and each array size is 3
            //     int u = arr[0];
            //     int v = arr[1];
            //     int wt = arr[2];
            //     dist[u][v] = wt;
            //     dist[v][u] = wt ;
            // }
            // Instead of doing that first for loop thing We can do this

            // Floyd Warshall Algo :
            for(int k =0;k<n;k++){
                for(int i =0;i<n;i++){
                    if(i==k) continue;
                    for(int j =0;j<n;j++){
                        if(j==k) continue;// i --> row, j --> col
                        if(dist[i][k]!=Integer.MAX_VALUE&& dist[k][j]!=Integer.MAX_VALUE)// Both should not be integer max value as it can give us integeroverflow error as we are adding something to it so we are using &&
                            dist[i][j] = Math.min(dist[i][j],dist[i][k]+dist[k][j]);
                    }
                }
            }
            int minCity =-1;
            int minCount  = Integer.MAX_VALUE;
            for(int i =0;i<n;i++){
                int count =0;
                for(int j =0;j<n;j++){
                    if(i==j) continue;// Ignore the diagonal elements that is going to itself
                    if(dist[i][j]<=t)count++;
                }
                if(count<=minCount){
                    minCount = count;
                    minCity =i;
                }
            }
            return minCity;
        }
    }
}
