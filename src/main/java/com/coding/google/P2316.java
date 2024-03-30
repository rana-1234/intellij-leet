package com.coding.google;

import java.util.ArrayList;
import java.util.List;

public class P2316 {

    //https://leetcode.com/problems/count-unreachable-pairs-of-nodes-in-an-undirected-graph/


    public long getNodeCountInDfsTree(int root, boolean [] visited, List<Integer> [] adjList){

        visited[root] = true;
        int ans = 1;
        for ( int nbr :  adjList[root]){
            if ( !visited[nbr]){
                ans += getNodeCountInDfsTree(nbr, visited, adjList);
            }
        }
        return ans;
    }


    public long countPairs(int n, int[][] edges) {
        // Undirected graph.
        // So total number of connected components we have to find
        // Node(comp1)*Node(comp2)....Node(comp3) would be the ans

        boolean [] visited = new boolean[n];
        List<Integer> [] adjList = new ArrayList[n];
        for(int i = 0 ; i < n ; i++){
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < edges.length; i++){
            int from = edges[i][0], to = edges[i][1];
            adjList[from].add(to);
            adjList[to].add(from);
        }

        long remainingNode = n;
        long ans = 0;
        for (int i = 0 ; i < n ; i++){
            if ( visited[i] == false) {
                long ccCount = getNodeCountInDfsTree(i, visited, adjList);
                remainingNode -= ccCount;
                ans += remainingNode*ccCount;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        P2316 p = new P2316();

        int [][] edges = {{0,1},{0,2},{1,2}};
        int n = 3;

        long ans = p.countPairs(3, edges);
        System.out.println(ans);
    }
}
