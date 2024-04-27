package com.coding.leetcode;

public class P100286 {

    public static boolean checkSquare(char [][] grid){

        return (grid[0][0] == grid[0][1] && grid[0][0] == grid[1][0] && grid[0][0] == grid[1][1]) ||
                (grid[0][1] == grid[0][2] && grid[0][1] == grid[1][1] && grid[0][1] == grid[1][2]) ||
                (grid[1][0] == grid[1][1] && grid[1][0] == grid[2][0] && grid[1][0] == grid[2][1]) ||
                (grid[1][1] == grid[1][2] && grid[1][1] == grid[2][1] && grid[1][1] == grid[2][2]);
    }

    public boolean canMakeSquare(char[][] grid) {
        boolean result = checkSquare(grid);

        for (int i = 0 ; i < 3 && !result ; i++ ){
            for(int j = 0 ; j < 3 && !result; j++ ){
                char [][] newGrid = new char[3][3];

                for(int k = 0 ; k < 3 ; k++){
                    for (int l = 0 ; l < 3; l++){
                        newGrid[k][l] = grid[k][l];
                    }
                }

                newGrid[i][j] = grid[i][j] == 'B' ? 'W' : 'B';
                result = checkSquare(newGrid);
            }
        }

        return result;

    }

    public static void main(String[] args) {
        char [][] grid = {{'B','B','B'},{'B','W','B'},{'B','B','B'}};
        System.out.println(new P100286().canMakeSquare(grid));

    }
}
