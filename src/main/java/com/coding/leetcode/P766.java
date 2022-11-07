package com.coding.leetcode;

public class P766 {

    public boolean isToeplitzMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;

        for(int i = 0 ; i < n ; i++){
            int num = matrix[i][0];
            int k = i+1, j = 1;

            for(; k < n && j < m ; k++, j++){
                if( matrix[k][j] != num)
                    return false;
            }
        }

        for(int i = 0 ; i < m ; i++){
            int num = matrix[0][i];
            int k = 1, j = i+1;
            for(; k < n && j < m; k++, j++){
                if( matrix[k][j] != num)
                    return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int [][] mat = new int[][]{{1,2,3,4}, {5,1,2,3}, {9,5,1,2}};
        System.out.print(new P766().isToeplitzMatrix(mat));
    }
}
