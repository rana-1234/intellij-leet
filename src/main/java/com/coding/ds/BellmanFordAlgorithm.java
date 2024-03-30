package com.coding.ds;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BellmanFordAlgorithm {

    // This algorithm finds the minimum distance from single Source to all other vertices in graph.
    // Its slower than Dijkstra but its capable of getting the minimum distance for negative edges as well.
    // Also, it fails where there is negative cycle in graph

    /*
        Idea is to relax every edges, whereas in Dijkstra we used to relax all nodes, so when there is negative edges it used to get the shortest path.

        V-1 times, we have to relax the edge.
           Relaxing the edge means, let's say edge is u-(w)->v
           then
            dist[v] = dist[v] < dist[u] + w ? dist[u] + w : dist[v]

        Relax one more times to detect negative cycle
            if there is no changes in minimumDistances, then there is no negative edge cycle present. Otherwise there would have been change in the nodes of negative cycles

     */

    public static final class Graph{
        int node;
        List<List<Integer>> edges;

        public Graph(int n){
            this.node = n;
            edges = new ArrayList<>();
        }

        public void addEdge(int from, int to, int cost){
            edges.add(List.of(from, to, cost));
        }

        public int getShortestDistanceFrom(int src, int to){

            int [] minimumDistance = new int[node];
            Arrays.fill(minimumDistance, Integer.MAX_VALUE);

            minimumDistance[src] = 0;

            for(int i = 1 ; i < node ; i++){
                for (List<Integer> values : edges){
                    if ( minimumDistance[values.get(0)] != Integer.MAX_VALUE && minimumDistance[values.get(0)] + values.get(2) < minimumDistance[values.get(1)]){
                        minimumDistance[values.get(1)] = minimumDistance[values.get(0)] + values.get(2);
                    }
                }
            }

            // Now let's detect for negative cycle.

            for (List<Integer> values : edges){
                if ( minimumDistance[values.get(0)] != Integer.MAX_VALUE && minimumDistance[values.get(0)] + values.get(2) < minimumDistance[values.get(1)]){
                   // it means there is negative cycle in graph
                    return -1;
                }
            }

            return minimumDistance[to];
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(5);
        g.addEdge(0,1,-1);
        g.addEdge(0,2,4);
        g.addEdge(1,2,3);
        g.addEdge(1,3, 2);
        g.addEdge(1,4,2);
        g.addEdge(3,2,5);
        g.addEdge(3,1,1);
        g.addEdge(4,3,-3);

        System.out.println(String.format("Minimum distance from source 0 to 3 is %s", g.getShortestDistanceFrom(0, 3)));

        Graph g2 = new Graph(4);
        g2.addEdge(1, 0, -10);
        g2.addEdge(0, 2, 10);
        g2.addEdge(2, 1, -2);
        g2.addEdge(2,3,14);

        System.out.println(String.format("Minimum distance from source 0  2 is %s", g2.getShortestDistanceFrom(0, 3)));
    }
}
