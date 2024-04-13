package com.coding.dp;

import java.util.Arrays;

public class P62 {

    int dp[][] = new int[101][101];
    {
        Arrays.stream(dp).forEach(x -> Arrays.fill(x, -1));
    }

    public int uniquePaths(int m, int n) {

        if( m < 1 || n < 1){
            return 0;
        }

        if ( m == 1 && n == 1){
            return 1;
        }

        if ( dp[m][n] != -1 ){
            return dp[m][n];
        }

        return dp[m][n] = uniquePaths(m-1, n) + uniquePaths(m, n-1);

    }

    public static void main(String[] args) {
        System.out.println(new P62().uniquePaths(3, 2));
    }
}
