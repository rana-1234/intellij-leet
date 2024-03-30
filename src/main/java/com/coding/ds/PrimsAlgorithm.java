package com.coding.ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class PrimsAlgorithm {

    // In an undirected graph (this assumes all nodes are reachable from each other), this algorithm is used to find the Minimum spanning tree.
    // Not in directed graph (because here, all nodes might not be reachable)


    /*
        Idea is to choose any vertex from Graph, explore all of its neighbours, and choose one which is closest to it.


     */

    public static class Graph{
        public List<Map.Entry<Integer,Integer>> [] adjList;
        public int node;

        public Graph(int n){
            node = n;
            adjList = new ArrayList[n];

            for(int i = 0 ; i < n; i++){
                adjList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int from, int to, int cost){
            adjList[from].add(Map.entry(to, cost));
            adjList[to].add(Map.entry(from, cost));
        }


        public List<List<Integer>> getSpanningTree(){
            /*
                Select any vertex in the answer.
                    Then push all the edges connected to this node in Priority Queue with Priority being the cost.
                 Pop from top and take only that edge, whose other vertex is not visited.
                    Keep doing it till the Priority queue is not empty
             */

            List<List<Integer>> edges = new ArrayList<>(); // total number edges in Spanning tree.
            boolean [] visited = new boolean[node];
            int [] parent = new int[node];
            int [] cost = new int[node];

            for(int i = 0 ; i < node; i++) {
                parent[i] = -1;
                cost[i] = Integer.MAX_VALUE;
            }

            PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((p,q)->{return p.getKey() - q.getKey();});
            pq.add(Map.entry(Integer.MAX_VALUE, 0)); // distance 0 from 0

            while(!pq.isEmpty() && edges.size() < node - 1){
                Map.Entry<Integer, Integer> currentNode = pq.poll(); // O(logN)
                int node = currentNode.getValue();
                if(visited[node] == true){
                    continue;
                }

                visited[node] = true;
                // Add this vertex -1, to 0 in ans
                if(parent[node] != -1 ){
                    // Add this edge into the answer
                    edges.add(List.of(parent[node], node));
                }

                for(Map.Entry<Integer,Integer> nbrInfo : adjList[node]){
                    int nbrVertex = nbrInfo.getKey();
                    int nbrCost = nbrInfo.getValue();
                    // Also, we need to check current cost on the node, if it's not less than existing, we would not update it
                    if(!visited[nbrVertex] && cost[nbrVertex] > nbrCost) { // cycle or already taken node, we will ignore the edge
                        parent[nbrVertex] = node;
                        cost[nbrVertex] = nbrCost;
                        pq.add(Map.entry(nbrCost, nbrVertex));
                    }
                }
            }
            return edges;
        }

    }

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.addEdge(0,1,5);
        g.addEdge(1, 2, 3);
        g.addEdge(0,2,1);
        System.out.println(String.format("Spanning tree of the graph %s", g.getSpanningTree()));

        Graph g2 = new Graph(9);
        g2.addEdge(0,1,4);
        g2.addEdge(0,7,8);
        g2.addEdge(7,1,11);
        g2.addEdge(7,6,1);
        g2.addEdge(6,5,2);
        g2.addEdge(6,8,6);
        g2.addEdge(1,2,8);
        g2.addEdge(2,8,2);
        g2.addEdge(2,5,4);
        g2.addEdge(2,3,7);
        g2.addEdge(3,5,14);
        g2.addEdge(5,4,10);
        g2.addEdge(3,4,9);

        System.out.println(String.format("Spanning tree of the graph %s", g2.getSpanningTree()));
    }
}
