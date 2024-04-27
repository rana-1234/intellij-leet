package com.coding.google;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class P931 {

    int [][] dp;
    int n, m;
    int [][] matrix;

    int MAX_VALUE = 100000;

    public int minFallingPathSum(int[][] matrix) {
        n = matrix.length;
        m = matrix[0].length;
        this.matrix = matrix;
        dp = new int[n][m];

        Arrays.stream(dp).forEach(row -> Arrays.fill(row, MAX_VALUE));

        int ans = MAX_VALUE;
        for(int i = 0 ; i < m ; i++){
            ans = Math.min(ans, calculateMinPath(0, i));
        }

        return ans;
    }

    private int calculateMinPath(int i, int j) {

        /*
            i, j, we can go i+1, (j-1, j, j+1)
         */

        if ( i < 0 || j < 0 || i >= n || j >= m){
            return MAX_VALUE;
        }

        if (i == n-1){
            return dp[i][j] = matrix[i][j];
        }

        if ( dp[i][j] != MAX_VALUE){
            return dp[i][j];
        }

        dp[i][j] = matrix[i][j] + Collections.min(
                List.of(
                        calculateMinPath(i+1, j-1),
                        calculateMinPath(i+1, j),
                        calculateMinPath(i+1, j+1)));
        return dp[i][j];
    }


    public static void main(String[] args) {
        int [][] matrix = {{2,1,3},{6,5,4},{7,8,9}};

        System.out.println(new P931().minFallingPathSum(matrix));

    }
}
