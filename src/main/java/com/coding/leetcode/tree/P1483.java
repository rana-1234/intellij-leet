package com.coding.leetcode.tree;

import java.util.Arrays;

public class P1483 {


    public static class TreeAncestor {

        int [][] parentAtHeight;
        int n;

        public TreeAncestor(int n, int[] parent) {
            int maxHeight = 0;
            this.n = n;
            int temp = n;
            while(temp > 0){
                temp >>= 1;
                maxHeight++;
            }

            parentAtHeight = new int[n][maxHeight+1];

            for (int i = 0 ; i < n ; i++){
                Arrays.fill(parentAtHeight[i], -1);
            }

            for (int i = 0 ; i < n ; i++){
                parentAtHeight[i][0] = parent[i];
            }

            for (int height = 1 ; height <= maxHeight ; height++){
                for (int i = 0 ; i < n ; i++){
                    if ( parentAtHeight[i][height-1] != -1 ){
                        parentAtHeight[i][height] = parentAtHeight[parentAtHeight[i][height-1]][height-1];
                    }
                }
            }

        }

        public int getKthAncestor(int node, int k) {
            // k == 110
            int height = 0;
            while(k > 0 && node != -1){
                if ((k & 1) > 0 ){
                    node = parentAtHeight[node][height];

                }
                height++;
                k >>= 1;
            }
            return node;
        }
    }

    public static void main(String[] args) {

        {
            TreeAncestor t = new TreeAncestor(7, new int[]{-1, 0, 0, 1, 1, 2, 2});
            System.out.println(t.getKthAncestor(3, 1));
            System.out.println(t.getKthAncestor(5, 2));
            System.out.println(t.getKthAncestor(0, 0));
            System.out.println(t.getKthAncestor(6, 1));
            System.out.println(t.getKthAncestor(6, 2));
            System.out.println(t.getKthAncestor(1, 2));
            System.out.println(t.getKthAncestor(2, 0));



        }
    }

}
