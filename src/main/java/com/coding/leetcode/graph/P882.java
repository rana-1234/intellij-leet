package com.coding.leetcode.graph;

import java.util.*;

public class P882 {

    List<Map.Entry<Integer, Integer>> g [];

    public int reachableNodes(int[][] edges, int maxMoves, int n) {
        int totalMaximumNodesPossible = n;
        g = new ArrayList [totalMaximumNodesPossible+1];
        for(int i = 0 ; i < totalMaximumNodesPossible+1  ; i++) {
            g[i] = new ArrayList<>();
        }
        int assigner = n;

        for(int [] edge: edges) {
            int from = edge[0];
            int to  = edge[1];
            int intermediate = edge[2];

            g[from].add(Map.entry(to, intermediate));
            g[to].add(Map.entry(from, intermediate));

        }

        int ans = 0;

        Set<Integer> visited = new HashSet<>();
        // Based on moves
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((x,y)->y.getKey() - x.getKey());
        pq.add(Map.entry(maxMoves, 0));
        while(!pq.isEmpty()){
            Map.Entry<Integer, Integer> entry = pq.poll();
            int currentNode = entry.getValue();
            int movesAvailable = entry.getKey();
            if (!visited.contains(currentNode) && movesAvailable > 0 && currentNode < n){
                for (Map.Entry<Integer, Integer> nbr : g[currentNode]){
                    int destinationNode = nbr.getKey();
                    if(visited.contains(destinationNode)){
                        continue;
                    }
                    int costToDestinationNode = nbr.getValue();
                    if (movesAvailable <= costToDestinationNode){
                        // it means we can't reach to the destination node, but we can visit all the nodes in between
                        ans += movesAvailable;
                        // However we would add a new node here


                        if( movesAvailable != costToDestinationNode) {
                            int newNode = assigner++; // Getting the node number and assigning next
                            if ( destinationNode < n)
                                g[destinationNode].add(Map.entry(newNode, costToDestinationNode - movesAvailable));
                        }
                    }
                    else{
                        // otherwise we can visit all the nodes in between and reach to this node
                        ans += costToDestinationNode; // Not adding this node as visited
                        // Since this node is reached, we would definitely reach to this node and would add it into
                        // the visited ser, so while returning the answer we would add the visited set size
                        pq.add(Map.entry( movesAvailable - costToDestinationNode - 1,destinationNode));
                    }
                }
            }
            if (currentNode < n)
                visited.add(currentNode);
        }

        return ans + visited.size();
    }

    public static void main(String[] args) {

        {
            int [][] edges = {{0,2,0},{1,3,1},{0,1,0},{1,4,0},{0,4,0},{2,4,4},{2,3,6},{0,3,8},{3,4,1},{1,2,4}};
            int maxMove = 4;
            int n = 5;
            System.out.println(new P882().reachableNodes(edges, maxMove, n));
        }

        {
            int [][] edges = {{3,4,8},{0,1,3},{1,4,0},{1,2,3},{0,3,2},{0,4,10},{1,3,3},{2,4,3},{2,3,3},{0,2,10}};
            int maxMove = 7;
            int n = 5;
            System.out.println(new P882().reachableNodes(edges, maxMove, n));
        }

        {
            int [][] edges = {{1,2,4},{1,4,5},{1,3,1},{2,3,4},{3,4,5},{0,1,1}};
            int maxMove = 7;
            int n = 5;
            System.out.println(new P882().reachableNodes(edges, maxMove, n));
        }


        {
            int [][] edges = {{0,1,10},{0,2,1},{1,2,2}};
            int maxMove =6;
            int n = 3;
            System.out.println(new P882().reachableNodes(edges, maxMove, n));
        }

        {
            int [][] edges = {{1,2,4},{1,4,5},{1,3,1},{2,3,4},{3,4,5},{0,1,1}};
            int maxMove = 30;
            int n = 5;
            System.out.println(new P882().reachableNodes(edges, maxMove, n));
        }
    }
}
