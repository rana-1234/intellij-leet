package com.coding.google;

import java.util.*;

public class P785 {

    // https://leetcode.com/problems/is-graph-bipartite/

    public boolean isBipartite(int[][] graph) {

        int node = graph.length;
        List<Integer> []  adjList = new ArrayList [node];
        int [] color = new int[node];

        for(int i = 0 ; i < node; i++ ){
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0 ; i < node; i++){
            for ( int j = 0 ; j < graph[i].length; j++){
                adjList[i].add(graph[i][j]);
            }
        }


        // Let's color the vertices white or black saying white in one set and black in other set.

        // Let's do BFS here

        for (int i = 0 ; i < node; i++ ) {
            // this condition is to handle disconnected graph as well.
            if (color[i] == 0) {

                Deque<Integer> bfsDq = new LinkedList<>();
                bfsDq.add(i); // start from here
                color[i] = 1; // black

                while (!bfsDq.isEmpty()) {
                    int currentVertex = bfsDq.pollFirst();
                    int vertexColor = color[currentVertex];
                    for (int child : adjList[currentVertex]) {
                        // check for vertex color of child
                        if (color[child] == 0) {
                            color[child] = vertexColor == 1 ? 2 : 1;
                            bfsDq.addLast(child);
                        } else if (color[child] == vertexColor) {
                            // Graph can not be bipartite
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int [][] g = new int [][]{{},{2,4,6},{1,4,8,9},{7,8},{1,2,8,9},{6,9},{1,5,7,8,9},{3,6,9},{2,3,4,6,9},{2,4,5,6,7,8}};

        System.out.println("Is Graph bipartite : " + new P785().isBipartite(g));
    }
}
