package com.coding.leetcode.graph;

import java.util.*;

public class P1368 {

    int [][] dp;
    int n, m;
    Map.Entry<Integer, Integer> getDefaultDirection(int i, int j, int val){
        switch (val){
            case 1 : return Map.entry(i, j+1);
            case 2 : return Map.entry(i, j-1);
            case 3 : return Map.entry(i+1, j);
            default : return Map.entry(i-1, j);
        }
    }
    boolean isValidDirection(int i, int j){
        return i >= 0 && i <= n-1 && j >= 0 && j <= m-1;
    }
    public int minCost(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        dp = new int[n][m]; // Minimum cost to reach to the destination
        for (int i = 0 ; i < n ; i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][0] = 0;
        Deque<Map.Entry<Integer, Integer>> deq = new LinkedList<>();
        deq.addLast(Map.entry(0,0));

        while(!deq.isEmpty()){
            Map.Entry<Integer, Integer> current = deq.pollFirst();
            int fromI = current.getKey();
            int fromJ = current.getValue();

            for ( int i = 1 ; i <= 4 ; i++){
                Map.Entry<Integer, Integer> to = getDefaultDirection(fromI, fromJ, i);
                int toI = to.getKey();
                int toJ = to.getValue();
                if ( isValidDirection(toI, toJ)){
                    if (i == grid[fromI][fromJ] && dp[toI][toJ] > dp[fromI][fromJ]){
                        // Moving in the same direction which was instructed
                        dp[toI][toJ] = dp[fromI][fromJ];
                        deq.addLast(to);
                    }
                    else if (dp[toI][toJ] > 1 + dp[fromI][fromJ]){
                        // Moving in opposite direction
                        dp[toI][toJ] = 1 + dp[fromI][fromJ];
                        deq.addLast(to);
                    }
                }
            }
        }

        return dp[n-1][m-1];
    }

    public static void main(String[] args) {
        {
            int [][] grid = {{1,1,1,1},{2,2,2,2},{1,1,1,1},{2,2,2,2}};
            System.out.println(new P1368().minCost(grid));
        }

        {
            int [][] grid = {{1,1,3},{3,2,2},{1,1,4}};
            System.out.println(new P1368().minCost(grid));
        }

        {
            int [][] grid = {{1,2},{4,3}};
            System.out.println(new P1368().minCost(grid));
        }
    }
}
