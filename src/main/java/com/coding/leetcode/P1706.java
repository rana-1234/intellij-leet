package com.coding.leetcode;

import com.coding.help.Utils;

public class P1706 {

    private int [][] g ;
    private int n , m;
    private int ifBallFalls(int ci, int cj){
        //If ball reaches to n row, it means it passes through
        if( ci == n){
            return cj;
        }

        if( ci < 0 || cj < 0 || ci >= n || cj >= m){
            return -1;
        }

        if( g[ci][cj] == 1){
            // it will fall right side of the cell,
            if (cj+1 < m && g[ci][cj+1] == 1){
                // V shape won't be formed and ball will pass through to next cell.
                return ifBallFalls(ci+1, cj+1);
            }
        }
        else{
            // it will fall to the left side of the cell.
            if( cj - 1 >= 0 && g[ci][cj-1] == -1){
                return ifBallFalls(ci+1, cj-1);
            }
        }
        return -1;
    }

    public int[] findBall(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        g = grid;
        int [] ans = new int [m];

        for(int i = 0 ; i < m ; i++){
            ans[i] = ifBallFalls(0, i);
        }

        return ans;
    }

    public static void main(String[] args) {

        int [][] grid = new int [][]{{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1},{1,1,1,1,1,1},{-1,-1,-1,-1,-1,-1}};
        int [] ans = new P1706().findBall(grid);
        Utils.printIntArray(ans);
    }
}
