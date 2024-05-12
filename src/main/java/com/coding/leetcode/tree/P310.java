package com.coding.leetcode.tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P310 {

    public List<Integer> [] g;

    int [] largestHeight;
    int [] secondLargestHeight;

    public void dfs(int root, int parent){
        largestHeight[root] = 0;
        for (int nbr : g[root]){
            if ( nbr != parent ){
                dfs(nbr, root);
                if (largestHeight[root] <= 1 + largestHeight[nbr]){
                    secondLargestHeight[root] = largestHeight[root];
                    largestHeight[root] = 1  + largestHeight[nbr];
                }
                else if (secondLargestHeight[root] <= 1 + largestHeight[nbr]){
                    secondLargestHeight[root] = 1 + largestHeight[nbr];
                }
            }
        }

    }

    public void calculateHeightWhenRootIsNode(int root, int parent){

        // First calculate the height of this root, then call for its child

        if ( largestHeight[parent] == 1 + largestHeight[root]) {
            if (largestHeight[root] < 1 + secondLargestHeight[parent]){
                secondLargestHeight[root] = largestHeight[root];
                largestHeight[root] = 1 + secondLargestHeight[parent];
            }
            else if(secondLargestHeight[root] < 1 + secondLargestHeight[parent]) {
                secondLargestHeight[root] = 1 + secondLargestHeight[parent];
            }
        }
        else if (largestHeight[root] < 1 + largestHeight[parent]){
            secondLargestHeight[root] = largestHeight[root];
            largestHeight[root] = 1 + largestHeight[parent];
        }
        else if (secondLargestHeight[root] < 1 + largestHeight[parent]){
            secondLargestHeight[root] = largestHeight[parent] + 1;
        }

        for(int nbr : g[root]){
            if( nbr != parent){
                calculateHeightWhenRootIsNode(nbr, root);
            }
        }

    }

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        g = new ArrayList [n];
        secondLargestHeight = new int[n];
        Arrays.fill(secondLargestHeight,0);
        largestHeight = new int[n];

        for(int i = 0; i < n ; i++){
            g[i] = new ArrayList<>();
        }

        // Create the graph
        for(int [] edge: edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        dfs(0, -1);
        for(int child : g[0]) {
            calculateHeightWhenRootIsNode(child, 0);
        }

        List<Integer> ans = new ArrayList<>();
        ans.add(0);
        int minHeightSoFar = largestHeight[0];

        for(int i = 1 ; i < n ; i++){
            if ( minHeightSoFar == largestHeight[i]){
                ans.add(i);
            }
            else if ( minHeightSoFar > largestHeight[i]){
                ans = new ArrayList<>();
                ans.add(i);
                minHeightSoFar = largestHeight[i];
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        {
            int [][] edges = {{0,1},{1,2},{1,3},{2,4},{3,5},{4,6}};
            int n = 7;
            System.out.println(new P310().findMinHeightTrees(n, edges));
        }

        {
            int [][] edges = {{0,1},{0,2}, {2,3},{3,4},{4,5},{5,6},{6,7},{7,8}};
            int n = 9;
            System.out.println(new P310().findMinHeightTrees(n, edges));
        }

        {
            int [][] edges = {{0,1},{0,6}, {6,7},{7,8},{1,2},{1,3},{2,4},{3,5}};
            int n = 9;
            System.out.println(new P310().findMinHeightTrees(n, edges));
        }

        {
            int n = 6;
            int [][] edges = {{3,0},{3,1},{3,2},{3,4},{5,4}};
            System.out.println(new P310().findMinHeightTrees(n, edges));
        }
    }
}
