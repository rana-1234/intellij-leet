package com.coding.ds;

import java.util.*;

public class KruskalAlgorithm {

    public static class Graph{

        List<Map.Entry<Integer, Map.Entry<Integer, Integer>>> edgesWithCost;

        public int node;

        public Graph(int n){
            node = n;
            edgesWithCost = new ArrayList<>();
        }

        public void addEdge(int from, int to, int cost){
            edgesWithCost.add(Map.entry(cost, Map.entry(from,to)));
        }


        public List<List<Integer>> getSpanningTree(){
            /*
                Idea is to just pick minimum weight edges unless any cycle is formed.
                A cycle would be formed when edge u-v is added to the answer if u and v has the same parent.

                Same parent means,
                    We would use Union Data Structure here, and when the edge with vertex u and v belongs to same union then this edge forms a cycle and hence can't be added.

             */

            // sort the edges based on the cost
            Collections.sort(edgesWithCost, (key1, key2)->{return key1.getKey() - key1.getKey();});
            UnionSet unionSet = new UnionSet(node);
            List<List<Integer>> edges = new ArrayList<>();

            for(Map.Entry<Integer, Map.Entry<Integer,Integer>> costEdge : edgesWithCost){
                int u = costEdge.getValue().getKey();
                int v = costEdge.getValue().getValue();

                int rootU = unionSet.getRoot(u);
                int rootV = unionSet.getRoot(v);

                if( rootV != rootU){
                    // otherwise there would be a cycle
                    edges.add(List.of(u,v));
                    unionSet.merge(rootU, rootV);
                }
            }

            return edges;

        }

    }

    public static void main(String[] args) {
        PrimsAlgorithm.Graph g = new PrimsAlgorithm.Graph(3);
        g.addEdge(0,1,5);
        g.addEdge(1, 2, 3);
        g.addEdge(0,2,1);
        System.out.println(String.format("Spanning tree of the graph %s", g.getSpanningTree()));

        PrimsAlgorithm.Graph g2 = new PrimsAlgorithm.Graph(9);
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
