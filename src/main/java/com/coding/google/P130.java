package com.coding.google;

import java.util.function.Function;

public class P130 {

    public int n, m;

    public void dfs(char [][] board, int r, int c){
        if (r < 0 || r >= n || c < 0 || c >= m || board[r][c] != 'O') {
            return;
        }
        board[r][c] = 'P';
        dfs(board, r+1, c);
        dfs(board, r-1, c);
        dfs(board, r, c-1);
        dfs(board, r, c+1);
    }

    public void solve(char[][] board) {

        n = board.length;
        m = board[0].length;

        if ( n < 3 || m < 3 ){
            return ;
        }


        for(int i = 0 ;  i < n ; i++){
            dfs(board, i, 0);
            dfs(board, i, m-1);
        }

        for(int j = 0 ; j < m ; j++){
           dfs(board, 0, j);
           dfs(board, n-1, j);
        }

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if ( board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
                else if ( board[i][j] == 'P' ){
                    board[i][j] = 'O';
                }
            }
        }

    }

    public static void main(String[] args) {

        char [][] ar =
                {{'O','X','X','O','X'},
        {'X','O','O','X','O'},
        {'X','O','X','O','X'},
        {'O','X','O','O','O'},
        {'X','X','O','X','O'}};

        new P130().solve(ar);

    }
}
