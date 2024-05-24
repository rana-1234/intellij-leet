package com.coding.leetcode.graph;

import java.util.Arrays;

public class P913 {

    int [][] graph;
    static boolean mouseTurn;
    int nodes;

    int [][][] dp;

    int playMatch(int moveCount, int mousePosition, int catPosition){

        if (dp[moveCount][mousePosition][catPosition] != -1) {
            return dp[moveCount][mousePosition][catPosition]; // Already calculated
        }

        if (mouseTurn){
            // Make mouse to move and make mouseTurn  = false, so that cat can play
            boolean isMouseWinning = false;
            boolean canMatchBeDrawn = false;

            for(int nextNode : graph[mousePosition]){
                mouseTurn = false;
                int matchResult = playMatch(moveCount+1, nextNode, catPosition);
                if ( matchResult == 1){
                    isMouseWinning = true;
                }
                else if (matchResult == 0){
                    // Mouse will jump to node nextNode to draw the game if it can't win
                    canMatchBeDrawn = true;
                }
            }
            dp[moveCount][mousePosition][catPosition] = isMouseWinning ? 1 : (canMatchBeDrawn ? 0 : 2);
        }
        else{
            boolean isCatWinning = false;
            boolean canMatchBeDrawn = false;

            // if any of the moves gives cat benefit, cat would be winning

            for(int nextNode : graph[catPosition]){
                if ( nextNode != 0){
                    mouseTurn = true;
                    // cat can't jump to the 0th node
                    int matchResult = playMatch(moveCount+1, mousePosition, nextNode);
                    if ( matchResult == 2){
                        isCatWinning = true;
                    }
                    else if (matchResult == 0){
                        // Cat will jump to node nextNode to draw the game if it can't win
                        canMatchBeDrawn = true;
                    }
                }
            }
            dp[moveCount][mousePosition][catPosition] = isCatWinning ? 2 : (canMatchBeDrawn ? 0 : 1);
        }
        return dp[moveCount][mousePosition][catPosition];
    }

    public int catMouseGame(int[][] graph) {

        // Mouse starts at 1 and goes first
        // Cat starts at 2, and goes second
        // 0 is the hole where mouse wants to enter

        this.graph = graph;
        this.nodes = graph.length;
        dp = new int[2*nodes + 1][nodes][nodes]; // dp[moveCount][mousePosition][catPosition]

        for(int i = 0 ; i < 2*nodes ; i++){
            for(int j = 0 ; j < nodes ; j++){
                for(int k = 0 ; k < nodes ; k++){
                    dp[i][j][k] = -1;
                }
            }
        }

        // dp[0..2n][0][0..n] = Mouse position is zero and hence mouse wins, 1 (Moise wins)
        // dp[0..2n][i][i] = Mouse position same as cat position, 2 (Cat wins)
        // dp[2*n-1][i][j] = Match draws, since mouse and cat both are again going to repeat same position

        for(int i = 0 ; i < nodes ; i++){
            for (int j = 0 ; j < nodes ; j++){
                dp[2*nodes][i][j] = 0; // draw condition
            }
        }

        for(int i = 0 ; i <= 2*nodes ; i++){
            for (int j = 0 ; j < nodes ; j++){
                dp[i][j][j] = 2;
            }
        }

        for(int i = 0 ; i <= 2*nodes ; i++){
            for (int j = 0 ; j < nodes ; j++){
                // Mouse wins
                dp[i][0][j] = 1;
            }
        }

        int mousePosition = 1;
        int catPosition = 2;
        int moveCount = 0;
        mouseTurn  = true;

        return playMatch(moveCount, mousePosition, catPosition);

    }

    public static void main(String[] args) {

        {
            int [][] g = {{5,7,9},{3,4,5,6},{3,4,5,8},{1,2,6,7},{1,2,5,7,9},{0,1,2,4,8},{1,3,7,8},{0,3,4,6,8},{2,5,6,7,9},{0,4,8}};
            System.out.println(new P913().catMouseGame(g));
        }

        {
            int [][] g = {{2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}};
            System.out.println(new P913().catMouseGame(g));
        }

        {
            int [][] g = {{1,3},{0},{3},{0,2}};
            System.out.println(new P913().catMouseGame(g));
        }

    }
}
