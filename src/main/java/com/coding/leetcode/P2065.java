package com.coding.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class P2065 {

    List<Map.Entry<Integer, Integer>> [] g;
    int n;
    int [] values;
    int ans;

    void getMaximalPathQuality(int from, int timeRemaining, int maxCollectedSoFar){

        // we would explore only paths where we can reach

        if ( from == 0){
            // somehow if we reach to this point again, we would update this
            this.ans = Math.max(this.ans, maxCollectedSoFar);
        }

        int ans = values[from]; // collect the answer from here
        values[from] = 0; // this would not be added if we reach to this node again

        for (Map.Entry<Integer, Integer> to : g[from]){
            if ( timeRemaining >= to.getValue() ){
                getMaximalPathQuality(to.getKey(), timeRemaining - to.getValue(), maxCollectedSoFar + ans);
            }
        }

        values[from] = ans; // When we are done exploring undo the values

    }
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {

        n = values.length;
        g = new ArrayList[n];

        for (int i = 0 ; i < n ; i++) {
            g[i] = new ArrayList<>();
        }
        this.values = values;

        for (int [] edge: edges) {
            int from = edge[0];
            int to = edge[1];
            int cost = edge[2];

            g[from].add(Map.entry(to, cost));
            g[to].add(Map.entry(from, cost));
        }
        ans  = 0;

        int localAns = values[0]; // will be collected anyways always
        values[0] = 0;
        int returnAns = 0;
        for(Map.Entry<Integer, Integer> nbr : g[0]) {
            // Explore from all the children
            // Populate all the values first
            this.ans = localAns;
            getMaximalPathQuality(nbr.getKey(), maxTime-nbr.getValue(), localAns); // Exploring via this path
            returnAns = Math.max(returnAns, this.ans);
        }
        return returnAns;
    }

    public static void main(String[] args) {
//        {
//            int [] values = {0,32,10,43};
//            int [][] edges = {{0,1,10},{1,2,15},{0,3,10}};
//            int maxTime = 75;
//            System.out.println(new P2065().maximalPathQuality(values, edges, maxTime));
//        }

//        {
//            int [] values = {1,2,3,4};
//            int [][] edges = {{0,1,10},{1,2,11},{2,3,12},{1,3,13}};
//            int maxTime = 50;
//            System.out.println(new P2065().maximalPathQuality(values, edges, maxTime));
//        }

        {
            int [] values = {95};
            int [][] edges = {};
            int maxTime = 83;
            System.out.println(new P2065().maximalPathQuality(values, edges, maxTime));
        }
    }
}
