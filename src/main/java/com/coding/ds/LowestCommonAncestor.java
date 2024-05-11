package com.coding.ds;

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestor {
    // Here we would use euler path to calculate LCA
    // root is always 1

    static List<Integer> [] tree;
    static int [] depth;
    static List<Integer> eulerTour = new ArrayList<>();
    static int [] startedAt;
    static int [] endedAt;
    static int [][] rangeMin; // Node which is at minimum depth from index i to j

    static void dfs(int root, int par, int dep){
        eulerTour.add(root);
        startedAt[root] = eulerTour.size() - 1; // it s added in last
        depth[root] = dep;
        for(int nbr : tree[root]){
            if ( nbr != par ){
                dfs(nbr, root, dep+1);
                eulerTour.add(root); // Add when call comes back
            }
        }
        eulerTour.add(root);
        endedAt[root] = eulerTour.size() - 1;
    }

    public static int getLCA(int n,int [][] edges, int u, int v){

        if( u == v){
            return u;
        }

        tree = new ArrayList [n+1];
        depth = new int[n+1];
        startedAt = new int [n+1];
        endedAt = new int [n+1];
        for(int i = 0 ; i <= n; i++){
            tree[i] = new ArrayList<>();
        }

        for(int [] edge : edges){
            tree[edge[0]].add(edge[1]);
            tree[edge[1]].add(edge[0]);

        }

        dfs(1, 0, 0); // parent,, O(N)
        int eulerSize = eulerTour.size();
        rangeMin = new int[eulerSize][eulerSize]; // O(N*N)

        for(int i = 0; i < eulerSize; i++){
            rangeMin[i][i] = eulerTour.get(i);
            int dep = depth[eulerTour.get(i)];
            int node = eulerTour.get(i);
            for(int j = i+1; j < eulerSize; j++){
                if ( depth[eulerTour.get(j)] < dep){
                    node = eulerTour.get(j);
                    rangeMin[i][j] = node;
                    dep = depth[node];
                }
                else{
                    rangeMin[i][j] = node;
                }
            }
        }

        int from = startedAt[u];
        int to = startedAt[v];

        if ( from > to ){
            int temp = from;
            from = to;
            to = temp;
        }
        return rangeMin[from][to]; // O(1). If query is really big and N is less, than this would be best approach.
    }

    public static void main(String[] args) {

        {
            int [][] edges = {{1,2},{2,3},{2,4},{1,5},{5,6},{6,7},{5,8},{8,9},{8,10}};
            int n = 10;
            int u = 9, v = 6;
            System.out.println(getLCA(n, edges, u, v));
        }

    }

}
