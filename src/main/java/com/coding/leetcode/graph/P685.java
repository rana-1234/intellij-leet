package com.coding.leetcode.graph;

import com.coding.help.Utils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P685 {

    List<Integer> [] g;
    public static class UnionFind{


        int [] parent;
        int [] rank;

        public UnionFind(int n){
            parent = new int[n];
            rank = new int[n];
            Arrays.fill(parent, 0);
            Arrays.fill(rank, 0);
        }

        private int findParent(int node){
            if ( parent[node] == 0){
                return node;
            }
            return parent[node] = findParent(parent[node]);
        }

        boolean addNode(int u, int v){
            int parentOfU = findParent(u);
            int parentOfV = findParent(v);

            if ( parentOfU == parentOfV){
                return false; // Already in the set
            }

            if ( rank[parentOfU] >= rank[parentOfV]){
                parent[parentOfV] = parentOfU;
                if ( rank[parentOfV] == rank[parentOfV]) {
                    rank[parentOfU]++;
                }
            }
            else {
                parent[parentOfU] = parentOfV;
            }
            return true;
        }

    }
    int dfs(int root, int parent){
        int totalVisitedNode = 1;
        for(int to : g[root]){
            if ( to != parent ){
                totalVisitedNode += dfs(to, root);
            }
        }
        return totalVisitedNode;
    }
    public boolean checkGraphConnected(int [][] edges, int n,  int removeFrom, int removeTo){

        g = new ArrayList [n];
        for(int i = 0 ; i < n ; i++){
            g[i] = new ArrayList<>();
        }

        for(int [] edge : edges){
            int from = edge[0];
            int to = edge[1];
            if ( from == removeFrom && to == removeTo){
                continue;
            }

            g[from].add(to);
            g[to].add(from);
        }

        return dfs(removeFrom, -1) == n-1; // total number of nodes

    }
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length + 1;
        int [] parentOf = new int[n]; // There can be maximum two edges
        Arrays.fill(parentOf, 0);

        for (int [] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if ( parentOf[to] != 0){
                // Validate two edges, which one can be removed
                // if removing this edge keeps the graph connected, then this would be the ans
                // otherwise, previous edge must be the answer
                return checkGraphConnected(edges, n, from, to) ?edge : new int [] {parentOf[to], to};
            }
            parentOf[to] = from;
        }

        // it would be possible only when there is a cycle in the graph with root node, and above method won't be able
        // to detect condition that parent[node] > 1, accept for root node


        // to find the first edge which is causing this cycle we can make use of union find

        UnionFind uf = new UnionFind(n);
        for(int [] edge : edges){
            if ( !uf.addNode(edge[0], edge[1])){
                return edge;
            }
        }

        return new int [] {-1,-1};

    }

    public static void main(String[] args) {

        {
            int [][] edges = {{2,1},{3,1},{4,2},{1,4}};
            Utils.printIntArray(new P685().findRedundantDirectedConnection(edges));
        }


        {
            int [][] edges = {{1,2},{1,3},{2,3}};
            Utils.printIntArray(new P685().findRedundantDirectedConnection(edges));
        }

        {
            int [][] edges = {{1,2},{2,3},{3,4},{4,1},{1,5}};
            Utils.printIntArray(new P685().findRedundantDirectedConnection(edges));
        }
    }
}
