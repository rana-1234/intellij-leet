package com.coding.ds;

import java.util.ArrayList;
import java.util.List;

public class IsGraphStronglyConnected {

    public static List<Integer>[] g;
    public static boolean [] visited;
    public static int visitCount = 0;


    public static void doDfs(int vertex){
        if ( visited[vertex] == false){
            visited[vertex] = true;
            visitCount++;
            for(int nbr : g[vertex]){
                doDfs(nbr);
            }
        }
    }

    public static void transposeGraph(){
        List<Integer> [] tG = new ArrayList[g.length];

        for(int i = 0 ; i < g.length ; i++) tG[i] = new ArrayList<>();

        for(int i = 0 ; i < g.length; i++){
            for (int nbr : g[i]){
                tG[nbr].add(i); // Reverse the edge nbr -> i
            }
            visited[i] = false; // Mark vertices not visited
        }
        visitCount = 0;
        g = tG;

    }

    public static void main(String[] args) {

        // Idea is to if all the nodes are reachable from vertex 1, then in the transpose of the graph(all edges are changed) then there must be path from vertex 1 to all other nodes if graph is SC.

        int n = 5;
        g = new ArrayList[5];
        visited = new boolean[5];

        for(int i = 0 ; i < n ; i++){
            visited[i] = false;
            g[i] = new ArrayList<>();
        }

        List<List<Integer>> edges = List.of(List.of(0,1), List.of(1,2), List.of(2,3), List.of(3,0), List.of(2,4), List.of(4,2));

        edges.forEach(edge -> {
            // Add the edges
            g[edge.get(0)].add(edge.get(1));
        });

        // Do a dfs on the graph and check if all the elements are visited or not

        doDfs(0);

        if ( visitCount != n){
            System.out.println("Graph is not fully connected !");
            return;
        }

        // Otherwise reverseTheGraph
        transposeGraph();

        doDfs(0);

        if(visitCount != n){
            System.out.println("Graph is not fully connected !");
        }

        System.out.println("Given graph is strongly connected !!");
    }
}
