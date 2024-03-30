package com.coding.leetcode;


import java.util.Arrays;

public class P1473 {

    public static int requiredTarget ;
    public static int MAX_VALUE = Integer.MAX_VALUE;
    public static int dp[][][] ;

    public int getMinCost(int [] houses, int currentHouse, int prevHouseColor, int target, int [][] costs){

        if ( target > requiredTarget){
            return MAX_VALUE; // We can't use this configuration for coloring house
        }

        if ( currentHouse == houses.length && target == requiredTarget) {
            return 0;
        }

        if( currentHouse == houses.length){
            return MAX_VALUE; // We can't use this configuration for coloring house
        }

        if ( dp[currentHouse][prevHouseColor][target] != -1){
            return dp[currentHouse][prevHouseColor][target];
        }

        if ( houses[currentHouse] != 0){
            // House is already colored
            return dp[currentHouse][prevHouseColor][target] = getMinCost(houses, currentHouse+1, houses[currentHouse], target + (houses[currentHouse] != prevHouseColor ? 1 : 0), costs);
        }
        else{
            int minCost = MAX_VALUE;
            for (int i = 0; i < costs[currentHouse].length; i++){
                minCost = Math.min(minCost, costs[currentHouse][i] + getMinCost(houses, currentHouse+1, i+1, target + (i+1 != prevHouseColor ? 1 : 0), costs));
            }
            return dp[currentHouse][prevHouseColor][target] = minCost;  // We can't use this configuration for coloring house
        }

    }

    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        // Current neighbourhood
        requiredTarget = target;
        dp = new int[m][n+1][target+1];
        for ( int i = 0 ; i < m; i++){
            for ( int j = 0 ; j <= n; j++){
                Arrays.fill(dp[i][j], -1);
            }
        }
        int ans = getMinCost(houses, 0, 0, 0, cost);
        return ans == MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        {
            P1473 s = new P1473();
            int[] houses = {0, 2, 1, 2, 0}; // 2, 2, 1, 2, 2
            int[][] cost = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
            /*
                 c1  c2
              h0 1, 10,
              h1 10, 1,
              h2 10, 1,
              h3 1, 10,
              h4 5, 1
             */
            int m = 5;
            int n = 2;
            int target = 3;
            System.out.println(s.minCost(houses, cost, m, n, target));
        }

        {
            P1473 s = new P1473();
            int[] houses = {0, 0, 0, 0, 0};
            int [][] cost = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
            int m = 5;
            int n = 2;
            int target = 3;
            System.out.println(s.minCost(houses,cost,m,n,target));
        }
    }
}
