package com.coding.leetcode.tree;

import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

public class P2603 {

    int [] coins;
    boolean [] subtreeContainsGoldCoin;
    int n;
    HashSet<Integer> g [];

    boolean dfs(int root, int parent){

        boolean containsCoin = coins[root] == 1;
        List<Integer> nodesToRemove = new ArrayList<Integer>();
        for(int nbr : g[root]){
            if ( nbr != parent){
                boolean containsAnyCoin = dfs(nbr, root);
                if (!containsAnyCoin){
                    // remove this edge
                    nodesToRemove.add(nbr);
                }
                else{
                   containsCoin = true;
                }
            }
        }

        for(int nbr : nodesToRemove){
            g[nbr].remove(root);
            g[root].remove(nbr);
        }
        return subtreeContainsGoldCoin[root] = containsCoin;
    }

    public int countEdge(int root, int parent){
        int ans = 0;

        for(int nbr: g[root]){
            if ( nbr != parent){
                ans += 1 + countEdge(nbr, root);
            }
        }

        return ans;
    }

    public int collectTheCoins(int[] coins, int[][] edges) {
        // We would not consider the subtree rooted at any node i, which is not containing any coin
        // then trim all leaf node (a node containing coin) with two edges above. since we can collect it from
        // two edges above
       // then we need to collect all the coins and come back to the same node, for which every edge must be travelled
        // twice. so answer would be 2*edges.

        this.coins = coins;
        n = coins.length;
        g = new HashSet[n];
        subtreeContainsGoldCoin = new boolean[n];

        for(int i = 0 ; i < n ; i++) {
            g[i] = new HashSet<>();
        }

        for(int [] edge : edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        // remove the subtree not containing gold coin
        boolean anyNodeContainsGoldCoin = false;
        for(int i = 0  ; i < n ; i++) {
            if ( coins[i] == 1){
                dfs(i, -1); // start from the node containing a coin
                anyNodeContainsGoldCoin = true;
                break;
            }
        }

        if( !anyNodeContainsGoldCoin ){
            return 0;
        }

        for(int j = 1 ; j <= 2 ; j++){
            List<Integer> leaves = new ArrayList<Integer>();
            for(int i = 0 ; i < n ; i++){

                if (g[i].size() == 1){
                    // remove this node
                    leaves.add(i);
                }
            }

            for(int i = 0 ; i < leaves.size(); i++){
                try {
                    int y = leaves.get(i);
                    int x = g[y].stream().findFirst().get();
                    g[x].remove(y);
                    g[y].remove(x);
                }
                catch (Exception e){
                    // ignore
                }
            }
        }

        // Now count all the edges

        int ans = 0;
        for(int i = 0 ; i < n ; i++){
            if ( subtreeContainsGoldCoin[i] && g[i].size() >= 1 ){
                ans = countEdge(i, -1);
                break;
            }
        }

        return ans*2;
    }

    public static void main(String[] args) {

        {
            int [] coins = {1,0,0,1};
            int [][] edges = {{0,1},{1,2},{2,3}};
            System.out.println(new P2603().collectTheCoins(coins, edges));
        }

        {
            int [] coins = {1,0,0,0,0,1};
            int [][] edges = {{0,1},{1,2},{2,3},{3,4},{4,5}};
            System.out.println(new P2603().collectTheCoins(coins, edges));
        }

        {
            int [] coins = {0,0,0,1,1,0,0,1};
            int [][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5},{5,6},{5,7}};
            System.out .println(new P2603().collectTheCoins(coins, edges));

        }
    }
}
