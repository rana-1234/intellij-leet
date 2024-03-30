package com.coding.ds;

import java.util.*;

public class DijkstraAlgorithm {

    public static class Graph{
        int node;
        List<Map.Entry<Integer,Integer>> [] adjList;

        public Graph(int n){
            this.node = n;
            adjList = new ArrayList [n];
            for(int i = 0 ; i < node; i++){
                adjList[i] = new ArrayList<>();
            }
        }


        public void addEdge(int from, int to, int cost){
            adjList[from].add(Map.entry(to, cost));
        }

        public int shortestDistanceBetween(int u, int v){
            // Idea is to start from node u, then update the total distance of all the nodes connected to it (like prims) if this is having less cost. Next pick the node which has minimum distance. Mark the node as visited.

            if(u == v){
                return 0;
            }

            int [] minimumCost = new int[node];
            boolean [] visited = new boolean[node];
            Arrays.fill(minimumCost, Integer.MAX_VALUE);

            minimumCost[u] = 0;
            PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((p,q)->p.getKey()-q.getKey());
            pq.add(Map.entry(0,u));
            while(!pq.isEmpty()){
                Map.Entry<Integer,Integer> entry = pq.poll();
                int currentNode = entry.getValue();
                int currentCost = entry.getKey();

                if( visited[currentNode]){
                    continue;
                }
                visited[currentNode] = true;

                if( currentNode == v){
                    return minimumCost[currentNode]; // got the distance here
                }

                for(Map.Entry<Integer,Integer> edge : adjList[currentNode]){
                   int otherEnd = edge.getKey();
                   int edgeCost = edge.getValue();

                   if(!visited[otherEnd] && edgeCost + currentCost < minimumCost[otherEnd]){
                       minimumCost[otherEnd] = edgeCost + currentCost;
                       pq.add(Map.entry(minimumCost[otherEnd], otherEnd));
                   }
                }
            }

            return -1; // if there is no path
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.addEdge(0,1,10);
        g.addEdge(0,2,20);
        g.addEdge(1,2,5);

        System.out.println("Minimum Distance between 0 and 2 is : " + g.shortestDistanceBetween(0,2));
    }
}
