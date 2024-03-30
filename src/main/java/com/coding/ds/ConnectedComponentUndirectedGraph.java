package com.coding.ds;

import java.util.ArrayList;
import java.util.List;

public class ConnectedComponentUndirectedGraph {

    // Given a un-directed graph find all the connected components

    public static List<List<Integer>> connectedComponents = new ArrayList<>();
    public static List<Integer> currentConnectedComponent = new ArrayList<>();
    public static List<Integer>[] adjList;

    public static void getConnectedComponentFromSource(int vertex, int [] visited){
        if( visited[vertex] == 1){
            return ;
        }
        visited[vertex] = 1;
        currentConnectedComponent.add(vertex);
        for (int currentNode : adjList[vertex]){
            getConnectedComponentFromSource(currentNode, visited);
        }
    }

    public static void main(String[] args) {

        int nodeCount = 5;
        adjList = new ArrayList[nodeCount+1];
        List<List<Integer>> edges = List.of(List.of(1,0), List.of(2,1), List.of(3,4));

        for(int i = 0 ; i <= nodeCount; i++){
            adjList[i] = new ArrayList<>();
        }

        // make graph from edges
        for(List<Integer> edge : edges){
            // Un-Directed Graph
            adjList[edge.get(0)].add(edge.get(1));
            adjList[edge.get(1)].add(edge.get(0));
        }

        // We can use either BFS or DFS to findOut all.

        int [] visited = new int[nodeCount+1];
        for(int i = 0; i < nodeCount; i++ ){
            currentConnectedComponent = new ArrayList<>();
            getConnectedComponentFromSource(i, visited);
            if (!currentConnectedComponent.isEmpty()){
                connectedComponents.add(currentConnectedComponent);
            }
        }

        for(List<Integer> connectedComp : connectedComponents){
            System.out.println(String.format("Current Connected Component : %s", connectedComp));
        }

    }

}
