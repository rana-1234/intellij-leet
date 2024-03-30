package com.coding.ds;

import java.util.ArrayList;
import java.util.List;

public class ArticulationPoint {

    // The vertex in a Connected Graph, such that removing the vertex from the graph, Graph becomes disconnected

    // To find the cut vertices (AP), idea is that, suppose an edge with vertex u - v where v is child of u in the DFS tree of graph, if there does not exist a back edge from the DFS tree rooted from v, then the u is an AP.
    // Or if u is the root of DFS tree, and it has more than one child in DFS tree, then u (root) is also a cut vertex, since removing the root would disconnect the graph into number of child's component

    public static class Graph{
        public int n;// Number of nodes
        public List<Integer> [] adjList ;
        public int [] discoveryTime; // time when discovery had started
        public int [] minimumBackEdgeDiscoveryTime; // represents the node which can be discovered via back edge
        public int [] parent; // parent of node
        public int [] childCount; // childCount of a node
        public int time ;

        public Graph(int n){
            this.n = n;
            adjList = new ArrayList[n];
            time = -1;
            discoveryTime = new int[n];
            minimumBackEdgeDiscoveryTime = new int[n];
            childCount = new int[n];
            parent = new int[n];
            for(int i = 0 ; i < n; i++) {
                adjList[i] = new ArrayList<>();
                parent[i] = -1;
                minimumBackEdgeDiscoveryTime[i] = Integer.MAX_VALUE;
                discoveryTime[i] = -1;
                childCount[i] = 0;
            }
        }

        public void addEdge(int u, int v){
            // undirected graph
            adjList[u].add(v);
            adjList[v].add(u);
        }

        public void dfs(int u, int v){
            // u is the parent of current node
            if (discoveryTime[v] == -1){
                time++;
                discoveryTime[v] = time;
                minimumBackEdgeDiscoveryTime[v] = time;
                parent[v] = u; // add the parent
                if  (u != -1 ) childCount[u]++;
                for (int nbr : adjList[v]){
                    if ( nbr != u){
                        // Not going back to parent itself
                        dfs(v, nbr);
                        // this we are updating later after exploring the nbr. So, this tells if there is any back edge from this subtree rooted at nbr, so we can travel from v to nbr using that back edge only
                        minimumBackEdgeDiscoveryTime[v] = Math.min(minimumBackEdgeDiscoveryTime[v], minimumBackEdgeDiscoveryTime[nbr]);
                    }
                }
            }
            else{
                // this is a back edge condition where we are visiting a node from u to v (already visited earlier and not the parent)
                // root node
                if ( u != -1)
                    minimumBackEdgeDiscoveryTime[v] = Math.min(minimumBackEdgeDiscoveryTime[v], discoveryTime[u]); // when the ancestor was discovered

            }
        }


        public List<Integer> getCutVertices(){
            dfs(-1, 0);// -1 is the parent of root node 0;
            List<Integer> ans = new ArrayList<>();

            if(childCount[0] > 1 ){
                ans.add(0);
            }

            for(int i = 1 ; i < n ; i++){
                // This tells that there is no back edge from this node subtree to any of its ancestor
                int u = parent[i];
                int v = i;
                // There is a back edge from subtree rooted at v to ancestor of v if discovery time of u is greater than minimumBackEdgeDiscoveryTime of v.

                if(u != -1 && u != 0 && discoveryTime[u] <= minimumBackEdgeDiscoveryTime[v]){
                    ans.add(u);
                }
            }

            return ans;
        }

    }



    public static void main(String[] args) {
        int v1 = 5;
        Graph g1 = new Graph(v1);

        g1.addEdge(1, 0);
        g1.addEdge(0, 2);
        g1.addEdge(2, 1);
        g1.addEdge(0, 3);
        g1.addEdge(3, 4);

        System.out.println(String.format("Cut vertices : %s", g1.getCutVertices()));

        int v2 = 4;
        Graph g2 = new Graph(v2);
        g2.addEdge(0, 1);
        g2.addEdge(1, 2);
        g2.addEdge(2, 3);

        System.out.println(String.format("Cut vertices : %s", g2.getCutVertices()));


        int v3 = 7;
        Graph g3 = new Graph(v3);
        g3.addEdge(1, 2);
        g3.addEdge(0, 1);
        g3.addEdge( 2, 0);
        g3.addEdge( 1, 3);
        g3.addEdge( 1, 4);
        g3.addEdge( 1, 6);
        g3.addEdge( 3, 5);
        g3.addEdge( 4, 5);

        System.out.println(String.format("Cut vertices : %s", g3.getCutVertices()));
    }
}
