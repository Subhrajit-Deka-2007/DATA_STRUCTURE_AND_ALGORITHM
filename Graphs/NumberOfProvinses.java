package Graphs;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 547. Number of Provinces
 *
 * There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 *
 * Return the total number of provinces.
 *
 * Example 1:
 *
 *
 * Input: isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * Output: 2
 * Example 2:
 *
 *
 * Input: isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * Output: 3
 *
 *
 * Constraints:
 *
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] is 1 or 0.
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 */
public class NumberOfProvinses {
    public static void main(String[] args) {
        int[][] isConnected = {{1,1,0}, {1,1,0}, {0, 0, 1}};
        Solution s = new Solution();
        int y = s.findCircleNum(isConnected);
        System.out.println(" Number of provinces "+y);
    }
}
    class Solution {
        public int findCircleNum(int[][] adj) {
            int n = adj.length;
            int count = 0;
            boolean[] vis = new boolean[n];
            for (int i = 0; i < n; i++) {
                if (!vis[i]) {// bfs(i, vis, adj);
                    dfs(i, vis, adj);

                    count++;
                }
            }
            return count;
        }

        public void bfs(int i, boolean[] vis, int[][] adj) {
            // Using BFS
            vis[i] = true;
            Queue<Integer> q = new LinkedList<>();
            q.add(i);
            int front;
            while (!q.isEmpty()) {
                front = q.remove();
                for (int j = 0; j < adj.length; j++) {
                    if (adj[front][j] == 1 && !vis[j]) {// means the element is connected to the front && visited[j] = false
                        q.add(j);
                        vis[j] = true;
                    }
                }
            }

        }
        private void dfs(int i , boolean [] vis ,int[][] adj){
            // No base case is needed as there is no error for index out of bounds or anything as we are calling in a calculated way
            vis[i] = true;
            // use dfs on that nodes which are connected to i so for that
            for(int j =0;j<adj.length;j++) if(adj[i][j]==1 && !vis[j] )dfs(j,vis,adj);
            /**
             * Here we want one node will call multiple person so, it is preferable that in starting only we make an adjacency list
             * . So, that more call is not called so, is preferable that convert the adjacency matrix to adjacency lists . Then use DFS
             */
        }
/**====================================== SOLVE USING DSU(DISJOINT SET UNION ) =========================================================*/
// HERE IT IS 1TH BASED INDEXING SO SIZE OF ARRAY WILL BE N+1

public int findCircleNum_2(int[][] isConnected) {
    int n = isConnected.length;// we had find the number of nodes
    int [] parent = new int [n+1];// as 1 base indexing
    /* Initially we consider the nodes are parent of itself */
    for(int i =1;i<=n;i++)parent[i]=i;
    /*
    1)Visualize first as all individual components
    2)Now start joining the nodes
    Here we are not given edge list
     */
    for(int i =0;i<n;i++){
        /*
        Represent the ith node and j represent the nodes that are connected to the ith node in this question
        node is connected to itself so, we will ignore that as 1 based indexing so i+1 and j+1
        */
        for(int j =0;j<n;j++){
            /* Edge is from (i+1 to j+1) as these are 0 based indexing
            if(i==j) // nothing will happen in  union function so just skip it as if both i and j are same then its group leader will
            also be same */
            if(i!=j&&isConnected[i][j]==1)union(i+1,j+1,parent);
        }
    }
   int count =0;
    for(int i =1;i<=n;i++)if(parent[i]==i)count++;
     return count ;
}
public void union(int a, int b, int []parent){
    /*
    Union : Connecting group leader of i+1 and j+1
     Now we want to connect  a and b don't connect a and b connect a group
     and b group means make b' group leader as parent of a's group leader
     */
    int leaderA = find(a,parent);// will find the group leader of a particular node
    int leaderB = find(b,parent);
    /* If both group leader same then we ignore it  */
    if(leaderA != leaderB) parent[leaderB]=leaderA;
    /**
     Now A is the parent and group leader of B // we can do reverse also
     make leader B as the parent and leader of A parent[leaderA] = leader B
     If condition hit means both group leader are different both are from different components just connect it
     */

}
public int find(int a,int[] parent){
    /** We can do this iteratively */
    if(parent[a]==a) return a;
    else return find(parent[a],parent);
}
/* T.c for making parent 0(n) + calling dsu (n*n(dsu))+ finding count
 =>o(n+(n(dsu--> n because of the find function )+n(dsu)+----
 +n(dsu){in worst case all are one so there are n nodes and, it is forming a linear tree of n length +o(n) finding the count
 T.C =O(N+N*N+N)=> O(N+N^2+N) => O(2N + N^2)
 S.C =O( PARENT ARRAY(N+1) + RECURSIVE STACK SPACE 0(N) IF WE ASSUME LINEAR TREE OF N LENGTH )
 T.C =O(2N+N^2)
 S.C =O(N+1+N)
 T.C =O(2N+N^2)
 S.C =O(2N+1)
 we will improve the T.C
 */


/**============== Optimization( reducing the level of the tree by adding new node to the tree whose group size is big ) ======================*/
public int findCircleNum_3(int[][] isConnected) {
    int n = isConnected.length;// we had find the number of nodes
    int [] parent = new int [n+1];// as 1 base indexing
    /* Initially we consider the nodes are parent of itself
    * We are also making a size array as it is necessary for optimization or reduction of levels of the tree such that
    * the find () function time complexity get reduce on finding the leader   */
    int [] size = new int [n+1];
    Arrays.fill(size,1);// Initially all were alone so we will the all elements with 1

    for(int i =1;i<=n;i++)parent[i]=i;
    for(int i =0;i<n;i++){
        /*
        Represent the ith node and j represent the nodes that are connected to the ith node in this question
        node is connected to itself so, we will ignore that as 1 based indexing so i+1 and j+1
        */
        for(int j =0;j<n;j++){
            /* Edge is from (i+1 to j+1) as these are 0 based indexing
            if(i==j) // nothing will happen in  union function so just skip it as if both i and j are same then its group leader will
            also be same */
            if(i!=j&&isConnected[i][j]==1)union_1(i+1,j+1,parent,size);
        }
    }
    int count =0;
    for(int i =1;i<=n;i++)if(parent[i]==i)count++;
    return count ;
}
        public void union_1(int a, int b, int []parent,int[] size){
    /*
    Union : Connecting group leader of i+1 and j+1
     Now we want to connect  a and b don't connect a and b connect a group
     and b group means make b' group leader as parent of a's group leader
     */
            a = find(a,parent);// will find the group leader of a particular node
            b= find(b,parent);
            /* If both group leader same then we ignore it  */
            if(a!=b){
                if(size[a]>size[b]){
                    /*
                    As size of the tree that , A belongs to is greater than the size of the tree that B belongs to
                    so,we make B as the child of A and, we added the size of the B tree to the A tree
                     */
                    parent[b]=a;
                    size[a]+=size[b];
                }
                else{
                    parent[a]=b;
                    size[b]+=size[a];
                }
            }
            /**
             Now A is the parent and group leader of B // we can do reverse also
             make leader B as the parent and leader of A parent[leaderA] = leader B
             If condition hit means both group leader are different both are from different components just connect it
             */
                }
/**
 * T.C =O(N) +[LOG N + LOG N + LOG N +----+LOG N (N TIMES WE ARE CONSIDERING CBT LEVELS LOG N )]+O(N)
 * S.C =O(N (SIZE OF THE PARENT ARRAY )+LOG N(LEVEL OF THE CBT )+N(SIZE OF THE SIZE ARRAY ) )
 * T.C =O(N+N*LOG N + N )
 * S.C =O(N+ LOG N + N )
 */



/** ============================= More optimization from CBT to 2 level tree =========================================================*/
/*
 Here what we are doing we are making every node that belongs to a particular components  we are making group leader as their parent
 so that for finding group leader for a particular node it takes only o(1) time complexity
 */
public int findCircleNum_4(int[][] isConnected) {
    int n = isConnected.length;// we had find the number of nodes
    int [] parent = new int [n+1];// as 1 base indexing
    /* Initially we consider the nodes are parent of itself
     * We are also making a size array as it is necessary for optimization or reduction of levels of the tree such that
     * the find () function time complexity get reduce on finding the leader   */
    int [] size = new int [n+1];
    Arrays.fill(size,1);// Initially all were alone so we will the all elements with 1

    for(int i =1;i<=n;i++)parent[i]=i;
    for(int i =0;i<n;i++){
        /*
        Represent the ith node and j represent the nodes that are connected to the ith node in this question
        node is connected to itself so, we will ignore that as 1 based indexing so i+1 and j+1
        */
        for(int j =0;j<n;j++){
            /* Edge is from (i+1 to j+1) as these are 0 based indexing
            if(i==j) // nothing will happen in  union function so just skip it as if both i and j are same then its group leader will
            also be same */
            if(i!=j&&isConnected[i][j]==1)union_2(i+1,j+1,parent,size);
        }
    }
    int count =0;
    for(int i =1;i<=n;i++)if(parent[i]==i)count++;
    return count ;
}
        public void union_2(int a, int b, int []parent,int[] size) {
    /*
    Union : Connecting group leader of i+1 and j+1
     Now we want to connect  a and b don't connect a and b connect a group
     and b group means make b' group leader as parent of a's group leader
     */
            a = find_1(a, parent);// will find the group leader of a particular node
            b = find_1(b, parent);
            /* If both group leader same then we ignore it  */
            if (a != b) {
                if (size[a] > size[b]) {
                    /*
                    As size of the tree that , A belongs to is greater than the size of the tree that B belongs to
                    so,we make B as the child of A and, we added the size of the B tree to the A tree
                     */
                    parent[b] = a;
                    size[a] += size[b];
                } else {
                    parent[a] = b;
                    size[b] += size[a];
                }
            }
        }
                public int find_1(int a,int[] parent){
                    /** Mostly it will remain 2 level tree */
                    /** We can do this iteratively */
                    if(parent[a]==a) return a;
                    else return parent[a] = find_1(parent[a],parent);// Path compression
                }
/**
 * T.C =O(N) +[1+ 1 +1 +----+1 (N TIMES WE ARE CONSIDERING 2 level  tree 1)]+O(N)
 *  * S.C =O(N (SIZE OF THE PARENT ARRAY )+LOG N(LEVEL OF THE CBT )+N(SIZE OF THE SIZE ARRAY ) )
 *  T.C =O(N+N+N)
 *  S.C =O(N+ 1(RECURSIVE SPACE) + N(SIZE OF THE SIZE ARRAY)
 *  T.C =O(3N)
 *  S.C =O(2N+1)
 *
 *
 *
 *  DSU TIME COMPLEXITY : T.C DEPEND ON WHAT WE ARE GIVEN (ADJ MATRIX, ADJ LIST, EDGE LIST) WE CONSIDER WE ARE GIVEN
 *  ADJ LIST AND WE HAVE E EDGES GIVEN
 *  T.C =O(E*union())=> union() => find() => which is reduce to
 *  every given edge get union for one time so t.c = e*union()
  */
        }


