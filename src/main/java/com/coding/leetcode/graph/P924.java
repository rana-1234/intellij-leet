package com.coding.leetcode.graph;

import java.util.*;

public class P924 {

    boolean [] visited;
    int [][] graph;

    TreeSet<Integer> visitedNodes;
    Set<Integer> initialNodes;
    int n;
    public int visitFrom(int from){
        visited[from] = true;
        int visitCount = 1;
        if ( initialNodes.contains(from)) {
            visitedNodes.add(from);
        }
        for(int i = 0 ; i < graph[from].length ; i++){
            if (graph[from][i] == 1 && visited[i] == false){
                visitCount += visitFrom(i);
            }
        }

        return visitCount;
    }
    public int minMalwareSpread(int[][] graph, int[] initial) {
        this.graph = graph;
        Arrays.sort(initial);
        initialNodes = new HashSet<>();
        for ( int node : initial ){
            initialNodes.add(node);
        }
        n = graph.length;
        visited = new boolean[n];
        Arrays.fill(visited, false);
        boolean firstTime = true;
        int indexNodeToReturnMoreThanOneNode = -1;
        int maxCountSoFarOneNode = Integer.MIN_VALUE;
        int indexNodeToReturnOneNode = -1;
        for(int i = 0 ; i < initial.length ; i++) {
            visitedNodes = new TreeSet<>();
            if (!visited[initial[i]]){
                int visitCount = visitFrom(initial[i]);
                int totalInitialNodeCovered = visitedNodes.size();
                if (totalInitialNodeCovered > 1){
                    // removing any nodes will not make any difference
                    if ( firstTime ){
                        indexNodeToReturnMoreThanOneNode = initial[i];
                    }
                    firstTime = false;
                }
                else {
                    // removing this node save malware spread
                    if ( maxCountSoFarOneNode < visitCount){
                        indexNodeToReturnOneNode = initial[i];
                        maxCountSoFarOneNode = visitCount;
                    }

                }

            }
        }
        return indexNodeToReturnOneNode == -1 ? indexNodeToReturnMoreThanOneNode : indexNodeToReturnOneNode;
    }

    public static void main(String[] args) {

        {
            int [][] graph = {{1,0,0,0},{0,1,0,0},{0,0,1,1},{0,0,1,1}};
            int [] initial = {3,1};
            System.out.println(new P924().minMalwareSpread(graph, initial));
        }

        {
            int [][] graph = {{1,1,0},{1,1,0},{0,0,1}};
            int [] initial = {0,1};
            System.out.println(new P924().minMalwareSpread(graph, initial));
        }

        {
            int [][] graph = {{1,0,0},{0,1,0},{0,0,1}};
            int [] initial = {0,2};
            System.out.println(new P924().minMalwareSpread(graph, initial));
        }

        {
            int [][] graph = {{1,1,1},{1,1,1},{1,1,1}};
            int [] initial = {1,2};
            System.out.println(new P924().minMalwareSpread(graph, initial));
        }

        {
            int [][] graph = {{1,1,0},{1,1,0},{0,0,1}};
            int [] initial = {0,1,2};
            System.out.println(new P924().minMalwareSpread(graph, initial));
        }
    }
}
