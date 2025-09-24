package Graphs;

/**
 * If there are n nodes and each node connects to remaining n-1 th nodes then  n-1+n-2+n-3+------+1 =o(n*(n-1)/2)
 * and instead an edge is formed by combining two nodes so, we can generate a formula nC2 = (n*(n-1))/2 think about it how it works
 * it, works fine so total [ Number of edges = O(n*(n-1)/2) ] as number of connections are unique once a node is connected it will not
 * connect once again so if there are 4 nodes A,B,C,D so answer is 4C2 but connection are counted once if 4 is connected to 2 it is counted on that
 * on 2's turn it will not again connect 4 that is why it works on each turn the edges counted
 */
public class GRAPH {
    public static void main(String[] args) {

    }
}
