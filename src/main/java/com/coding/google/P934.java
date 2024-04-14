package com.coding.google;

import java.util.*;

public class P934 {

    int [][] grid ;
    int n , m ;

    public void addAllToQueue(int i, int j, Queue<Map.Entry<Integer, Integer>> q){

        if ( i < 0 || j < 0 || i >= n || j >= m || grid[i][j] == 0 || grid[i][j]== 2){
            return;
        }
        grid[i][j] = 2;
        q.add(Map.entry(i, j));
        addAllToQueue(i+1,j, q);
        addAllToQueue(i-1,j, q);
        addAllToQueue(i,j+1, q);
        addAllToQueue(i,j-1, q);
    }

    public int shortestBridge(int[][] grid) {

        // We would consider this as a graph problem where there would be two
        // disconnected graph, and then find a shortest path to join them
        // We can make use of bfs here, where we would be adding all the adjacent zeroes making one flip,
        // and whenever we encounter any 1, this would be our answer
        /*
           1 1 1 0 0
           1 1 0 0 0
           1 0 0 0 0
           0 0 0 0 2
           0 0 2 2 2
         */

        this.grid = grid;
        n = this.grid.length;
        m = this.grid[0].length;
        Queue<Map.Entry<Integer, Integer>> q = new LinkedList<>();

        for(int i = 0 ; i < n && q.isEmpty(); i++ ){
            for(int j = 0 ; j < m && q.isEmpty() ; j++ ){
                if ( this.grid[i][j] == 1){
                    // Mark all as 2, telling separate island
                    addAllToQueue(i, j, q);
                }
            }
        }

        int minFlip = 0;

        q.add(Map.entry(-1,-1)); // Level separator

        int [][] directions = {{+1, 0}, {-1, 0}, {0, +1}, {0, -1}};

        boolean firstLandFound = false;

        while(!q.isEmpty() && !firstLandFound){
            Map.Entry<Integer, Integer> current = q.remove();
            int si = current.getKey();
            int sj = current.getValue();

            if(si == -1 && sj == -1){
                if ( q.isEmpty()){
                    break;
                }
                minFlip++;
                q.add(current);
            }

            // Push all the valid neighbors

            for(int [] direction : directions) {
                int nextSi = si + direction[0];
                int nextSj = sj + direction[1];

                if ( nextSi < 0 || nextSj < 0 || nextSi >= n || nextSj >= m){
                    continue;
                }

                if (grid[nextSi][nextSj] == 0){
                    q.add(Map.entry(nextSi, nextSj));
                    grid[nextSi][nextSj] = 2; // Marking this as visited
                }
                else if (grid[nextSi][nextSj] == 1){
                    // Found the first 1 of the island. This must be the minimum
                    firstLandFound = true;
                    break;
                }
            };

        }
        return minFlip;
    }


    public static void main(String[] args) {

        int [][] grid = new int[][]{
                {0,0,0,0,0,0,0},
                {1,0,0,0,0,0,0},
                {1,0,0,0,0,0,0},
                {1,1,0,0,0,0,0},
                {0,1,0,1,1,1,0},
                {0,0,0,0,0,0,0},
                {0,0,0,0,0,0,0}
        };

        System.out.println(new P934().shortestBridge(grid));


    }
}
