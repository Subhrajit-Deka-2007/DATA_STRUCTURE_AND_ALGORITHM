package Graphs;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

/**
 *
 841. Keys and Rooms
 There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
 When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
 Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, return true if you can visit all the rooms, or false otherwise.



 Example 1:

 Input: rooms = [[1],[2],[3],[]]
 Output: true
 Explanation:
 We visit room 0 and pick up key 1.
 We then visit room 1 and pick up key 2.
 We then visit room 2 and pick up key 3.
 We then visit room 3.
 Since we were able to visit every room, we return true.
 Example 2:

 Input: rooms = [[1,3],[3,0,1],[2],[0]]
 Output: false
 Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.


 Constraints:

 n == rooms.length
 2 <= n <= 1000
 0 <= rooms[i].length <= 1000
 1 <= sum(rooms[i].length) <= 3000
 0 <= rooms[i][j] < n
 All the values of rooms[i] are unique.
 */
public class KeysandRoom {
    public static void main(String[] args) {

    }
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // when we are given 2d lists like this means  we are given adjacency list means
            int [] count = {0};
            int n = rooms.size();
            boolean [] vis = new boolean[n];
            vis[0] = true;
            count[0]++;
            bfs(0,rooms,vis,count);
            return(count[0]==vis.length);
        }
        public void bfs(int start, List<List<Integer>> adj , boolean [] vis , int [] count ){
            Queue<Integer> q = new ArrayDeque<>();
            q.add(start);
            int front ;
            while(!q.isEmpty()){
                front = q.remove();
                for(int ele :adj.get(front)){
                    if(!vis[ele]){
                        count[0]++;
                        vis[ele] = true;
                        q.add(ele);
                    }
                }
            }
        }
/**
 T.C =O(V+E) Directed Graph
 N-1 + N+N+N+-----+N // N-1 FOR INSERTION IN WORST CASE ALL DOOR CONTAINS ALL ELEMENTS
 KEY FIRSTWILL INSERT ALL N-1 KEYS AND AFTER THAT FOR EACH N-1 ELEMENTS THE FOR LOOP WILL RUN N TIMES
 N-1 +(N-1)*N
 N-1 + N^2 -N
 O(N^2 -1)
 A.S =O(Visited Array + Queue )
 =O(N)+o(n) let one element of a list contain all elemnt so let say all element enter in QUEUE so t.c =o(n)
 */

    }

