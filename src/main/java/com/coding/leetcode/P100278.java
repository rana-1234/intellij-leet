package com.coding.leetcode;

public class P100278 {



    public long numberOfRightTriangles(int[][] grid) {
        long ans = 0;
        int n = grid.length;
        int m = grid[0].length;
        int [][] prefixSumRight = new int[n][m];
        int [][] prefixSumBottom = new int[n][m];

        for(int i = 0 ; i < n; i++){
            int sum = 0;
            for (int j = 0 ; j < m ; j++){
                sum += grid[i][j];
            }

            for (int j = 0 ; j < m ; j++){
                sum = sum - grid[i][j];
                prefixSumRight[i][j] = sum;
            }

        }

        for ( int j = 0 ; j < m ; j++ ){
            int sum = 0;
            for (int i = 0 ; i < n ; i++){
                sum += grid[i][j];
            }

            for(int i = 0 ; i < n ; i++){
                sum = sum - grid[i][j];
                prefixSumBottom[i][j] = sum;
            }
        }

        for (int i = 0 ; i < n ; i++) {
            for(int j = 0 ; j < m; j++) {
                if ( grid[i][j] == 1){
                    ans += 1L*prefixSumRight[i][j] * prefixSumBottom[i][j];

                    int oneInThisColumn = grid[0][j] == 1 ? prefixSumBottom[0][j] + 1 : prefixSumBottom[0][j];
                    int upOne = oneInThisColumn - prefixSumBottom[i][j] - 1;

                    int oneInThisRow = grid[i][0] == 1 ? prefixSumRight[i][0] + 1 : prefixSumRight[i][0];
                    int flatOnes = oneInThisRow - 1;

                    ans += 1L*upOne*flatOnes;

                    int leftOnes = oneInThisRow - prefixSumRight[i][j] - 1;
                    ans += 1L*leftOnes*prefixSumBottom[i][j];

                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        int [][] grid = {{0,0},{0,1},{1,1}};
        System.out.println(new P100278().numberOfRightTriangles(grid));

    }
}
