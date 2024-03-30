package com.coding.leetcode;

import java.util.*;

public class P2646 {

    public static List<Integer>[] g;
    public static int [] freq;
    public static int [] par;

    public static int [] price;

    public static HashMap<Map.Entry<Integer, Long>, Integer> dp;

    public void dfs(int root, int parent){
        par[root] = parent;
        for(int child : g[root]){
            if ( child != parent){
                dfs(child, root);
            }
        }
    }

    public static Stack<Integer> getPathTillRoot(int node){
        Stack<Integer> stack = new Stack<Integer>();

        if ( node == -1 ){
            return stack;
        }

        // Go all the way upto the root
        while(node != -1 ){
            stack.push(node);
            node = par[node];
        }

        return stack;
    }

    public static int getMinimumTotalPrice(int ci, long bitMask) {
        if ( ci == freq.length){
            return 0;
        }

        // we can either half the price of this node or not

        if ( dp.containsKey(Map.entry(ci, bitMask))){
            return dp.get(Map.entry(ci, bitMask));
        }

        if (((bitMask >> ci) & 1) == 1){
            // We can't half the price
            int ans = freq[ci] * price[ci] + getMinimumTotalPrice(ci+1, bitMask);
            dp.put(Map.entry(ci, bitMask), ans);
            return ans;
        }
        else{
            int costWithout = freq[ci] * price[ci] + getMinimumTotalPrice(ci+1, bitMask);
            for(int child : g[ci]){
                bitMask |=  (1 << child);
            }
            int costWith = freq[ci] * price[ci]/2 + getMinimumTotalPrice(ci+1, bitMask);
            // undo the changes
            for(int child : g[ci]){
                bitMask &= ~(1 << child);
            }
            int ans = Math.min(costWith, costWithout);
            dp.put(Map.entry(ci, bitMask), ans);
            return ans;
        }
    }

    public int minimumTotalPrice(int n, int[][] edges, int[] price, int[][] trips) {

        this.price = price;
        g = new ArrayList[n];
        for(int i = 0 ; i < n ; i++) {
            g[i] = new ArrayList<>();
        }
        dp = new HashMap<>();

        par = new int[n];
        Arrays.fill(par,-1);

        freq = new int[n];
        Arrays.fill(freq,0);

        for(int i = 0 ; i < edges.length ; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            g[u].add(v);
            g[v].add(u);
        }

        dfs(0, -1);

        for(int i = 0 ; i < trips.length ; i++){
            int start =  trips[i][0];
            int end = trips[i][1];

            if( start == end){
                freq[start]++;
                continue;
            }

            Stack<Integer> pathTillRootFromStart = getPathTillRoot(start);
            Stack<Integer> pathTillRootFromEnd = getPathTillRoot(end);
            int lca = -1;

            while(!pathTillRootFromStart.isEmpty() && !pathTillRootFromEnd.isEmpty() && pathTillRootFromStart.peek() == pathTillRootFromEnd.peek()){
                lca = pathTillRootFromStart.pop();
                pathTillRootFromEnd.pop();
            }

            freq[lca]++;

            while(!pathTillRootFromStart.isEmpty()){
                freq[pathTillRootFromStart.pop()]++; // frequency of node in path [start, end]
            }

            while(!pathTillRootFromEnd.isEmpty()){
                freq[pathTillRootFromEnd.pop()]++; // frequency of node in path [start, end]
            }

        }

        // Now just maximize this array
        return getMinimumTotalPrice(0,  0);
    }

    public static void main(String[] args) {

        {
            P2646 p2646 = new P2646();
            int n = 4;
            int[][] edges = {{0,1},{1,2},{1,3}};
            int[] price = {2,2,10,6};
            int[][] trips = {{0,3},{2,1},{2,3}};
            System.out.println(p2646.minimumTotalPrice(n, edges, price, trips));
        }

        {
            P2646 p2646 = new P2646();
            int n = 2;
            int[][] edges = {{0,1}};
            int[] price = {2,2};
            int[][] trips = {{0,0}};
            System.out.println(p2646.minimumTotalPrice(n, edges, price, trips));
        }

        {
            P2646 p2646 = new P2646();
            int n = 10;
            int[][] edges = {{0,1},{0,2},{0,3},{0,4},{0,5},{0,6},{0,7},{0,8},{0,9}};
            int[] price = {2,2,2,2,2,2,2,2,2,2};
            int[][] trips = {{0,9},{0,8},{0,7},{0,6},{0,5},{0,4},{0,3},{0,2},{0,1}};
            System.out.println(p2646.minimumTotalPrice(n, edges, price, trips));
        }

        {

            P2646 p2646 = new P2646();
            int n = 40;
            int [][] edges = {{0,28},{6,29},{7,34},{8,5},{5,20},{9,12},{12,3},{13,11},{14,32},{18,3},{3,20},{22,15},{15,28},{26,25},{25,20},{20,17},{27,16},{28,2},{31,2},{2,21},{21,23},{23,4},{4,35},{32,19},{33,39},{34,10},{10,11},{11,16},{16,17},{17,1},{1,24},{24,30},{30,19},{19,39},{35,29},{29,38},{36,38},{37,39},{38,39}};

            int [] price = {4,14,4,8,26,26,12,6,10,30,30,28,2,20,8,26,10,30,18,30,18,30,16,14,18,6,20,24,20,18,8,4,12,30,12,6,30,22,28,8};
            int [][] trips = {{10,15},{5,21},{16,28},{0,31},{13,37},{22,27},{13,7},{23,10},{7,4},{0,11},{35,20},{7,12},{16,15},{21,6},{7,4},{5,25},{10,22},{10,1},{20,8},{20,23},{38,39},{20,2}};

            System.out.println(p2646.minimumTotalPrice(n, edges, price, trips));
        }

    }
}
