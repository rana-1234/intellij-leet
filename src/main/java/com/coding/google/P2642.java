package com.coding.google;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class P2642 {

    //https://leetcode.com/problems/design-graph-with-shortest-path-calculator/description/

    public static final class Graph{

        private int [][] minimumCost;
        private int n;

        public Graph(int n, int[][] edges) {
            this.n = n;
            minimumCost  = new int[n][n];

            for(int i = 0 ; i < n; i++){
                for(int j = 0 ; j < n ; j++){
                    minimumCost[i][j] = -1; //
                }
            }

            for (int i = 0 ; i < edges.length; i++){
                minimumCost[edges[i][0]][edges[i][1]] = edges[i][2];
            }
        }

        public void addEdge(int[] edge) {
            minimumCost[edge[0]][edge[1]] = edge[2];
        }

        public int shortestPath(int node1, int node2) {
            // Using Dijkstra algorithm we can find the shortest path
            int [] minimumDistance = new int[n];
            boolean [] visited = new boolean[n];
            Arrays.fill(minimumDistance, Integer.MAX_VALUE);

            PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((p,q)->p.getKey()-q.getKey());
            minimumDistance[node1] = 0;
            pq.add(Map.entry(0, node1));

            while(!pq.isEmpty()){
                    Map.Entry<Integer,Integer> entry = pq.poll();
                    int currentNode = entry.getValue();
                    int currentCost = entry.getKey();

                    if( visited[currentNode]){
                        continue;
                    }
                    visited[currentNode] = true;

                    if( currentNode == node2){
                        return minimumDistance[currentNode]; // got the distance here
                    }

                    for(int j = 0 ; j < minimumCost[currentNode].length; j++){
                        if ( minimumCost[currentNode][j] == -1) continue;
                        int otherEnd = j;
                        int edgeCost = minimumCost[currentNode][j];

                        if(!visited[otherEnd] && edgeCost + currentCost < minimumDistance[otherEnd]){
                            minimumDistance[otherEnd] = edgeCost + currentCost;
                            pq.add(Map.entry(minimumDistance[otherEnd], otherEnd));
                        }
                    }
                }

            return -1;

        }

    }

    public static void main(String[] args) {

        List<String> operationList = List.of("Graph","addEdge","addEdge","addEdge","addEdge","addEdge","shortestPath","shortestPath","shortestPath","addEdge","addEdge","addEdge","shortestPath","addEdge","addEdge","shortestPath","shortestPath","shortestPath","addEdge","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","shortestPath","addEdge","shortestPath","shortestPath","shortestPath","addEdge","shortestPath","shortestPath","shortestPath","shortestPath","shortestPath","addEdge","shortestPath","shortestPath","shortestPath");

        int node = 13;
        int [][] edges = {{11,6,84715},{7,9,764823},{6,0,315591},{1,4,909432},{6,5,514907},{9,6,105610},{3,10,471042},{7,10,348752},{5,11,715628},{6,1,973999},{8,7,593929},{7,6,64688},{6,4,741734},{10,1,894247},{9,7,81181},{2,11,75418},{12,2,85431},{7,2,260306},{11,9,640614},{2,3,648804},{4,12,568023},{0,8,730096},{9,11,633474},{3,6,390214},{1,10,117955},{9,8,222602},{10,7,689294}};

        Graph g = new Graph(node, edges);


        int [][] addEdges = {{1,2,36450},{8,0,709628},{2,4,30185},{12,1,21696},{1,8,2553},{4,6,2182},{7,5,206},{5,7,140},{12,6,365184},{3,2,5},{0,11,5},{1,7,5},{3,9,858944},{0,9,4},{4,0,545201},{12,7,4},{4,3,879736},{12,9,629052}};
        int edge = 0;
        int [][] getDistance = {{8,9},{1,11},{3,4},{12,5},{4,8},{7,10},{0,5},{0,8},{11,11},{7,4},{0,12},{3,5},{4,5},{12,9},{9,8},{3,5},{11,8},{4,11},{9,6},{7,10},{2,5},{6,11},{12,2},{9,7},{1,3},{1,0},{4,6},{3,8}};
        int dist = 0;

        for(int i = 1 ; i < operationList.size();i++){
            if( operationList.get(i) == "addEdge"){
                g.addEdge(addEdges[edge]);
                edge++;
            }
            else{
                System.out.println(String.format("Distance between %s and %s is : %s", getDistance[dist][0], getDistance[0][1], g.shortestPath(getDistance[dist][0], getDistance[dist][1])));
                dist++;
            }
        }

    }
}
