package com.coding.leetcode.graph;

import java.util.*;

public class P847 {
    public int shortestPathLength(int[][] graph) {
        int n = graph.length;
        int finalState = (1 << n) - 1;
        int [][] stateMap = new int[n][finalState+1];
        Deque<Map.Entry<Integer, Integer>> deque = new LinkedList<>();
        for(int i = 0 ; i < n ; i++){
            deque.addLast(Map.entry(i, (1 << i ))); // Pushing all the nodes in queue
        }


        int shortestPath = 0;
        while(!deque.isEmpty()){
            shortestPath++;
            int totalNodeInThisLabel = deque.size();
            for(int i = 0 ; i < totalNodeInThisLabel ; i++){
                Map.Entry<Integer, Integer> currentNode = deque.pollFirst();
                int fromNode = currentNode.getKey();
                int currentState = currentNode.getValue();
                for (int nbr : graph[fromNode]){
                    int nextState = currentState | (1 << nbr);
                    if ( nextState == finalState){
                        return shortestPath;
                    }
                    if (stateMap[nbr][nextState] == 0){
                        stateMap[nbr][nextState] = 1;
                        // Add in the queue for next iteration
                        deque.addLast(Map.entry(nbr, nextState));
                    }
                }
            }
        }
        return 0; // In case there is no shortest path
    }


    public static void main(String[] args) {
        {
            int [][] graph = {{1,2,3,4,5,6,7,8,9},{0,2,3,4,5,6,7,8,9},{0,1,3,4,5,6,7,8,9},{0,1,2,4,5,6,7,8,9},{0,1,2,3,5,6,7,8,9},{0,1,2,3,4,6,7,8,9},{0,1,2,3,4,5,7,8,9},{0,1,2,3,4,5,6,8,9},{0,1,2,3,4,5,6,7,9,10},{0,1,2,3,4,5,6,7,8,11},{8},{9}};
            System.out.println(new P847().shortestPathLength(graph));
        }

        {
            int [][] graph = {{1},{0,2,4},{1,3,4},{2},{1,2}};
            System.out.println(new P847().shortestPathLength(graph));
        }
    }
}
