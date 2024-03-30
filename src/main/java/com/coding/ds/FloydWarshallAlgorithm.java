package com.coding.ds;

import java.util.Arrays;

public class FloydWarshallAlgorithm {

    /*
        This algorithm is used to find the shortest distance between every pair of the graph

        idea is dist[u][v] = dist[u][k] +  dist[k][v]
            for every k with which we can travel from u to k and k to v.

        So minimum of all these k, would give the minimum distance

     */

    public static final class Graph{
        int n ;
        int [][] cost;

        public Graph(int n){
            this.n = n;
            cost = new int [n][n];
        }

        public void addEdge(int from, int to, int c){
            cost[from][to] = c;
        }

        public int getMinimumCostFrom(int from, int to){

            if ( from == to){
                return -1;
            }

            int [][] minDistance = new int[n][n];
            for(int i = 0 ; i < n ; i++)
                Arrays.fill(minDistance[i], Integer.MAX_VALUE);

            for(int u = 0 ; u < n ; u++){
                for(int v = 0 ; v < n ; v++){
                    for(int k = 0 ; k < n; k++ ){
                        if(cost[u][k] != 0 && cost[k][v] != 0){
                            // there is a connected node from u to v via k
                            minDistance[u][v] = Math.min(minDistance[u][v], cost[u][k] + cost[k][v]);
                        }
                    }
                }
            }

            return minDistance[from][to];
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(4);
        g.addEdge(0,1,1);
        g.addEdge(0,2,10);
        g.addEdge(1,2,4);
        g.addEdge(1,3,5);
        g.addEdge(2,3,3);
        g.addEdge(3,1,4);

        System.out.println(String.format("Distance from 0 to 2 : %s", g.getMinimumCostFrom(2,1)));


    }
}
