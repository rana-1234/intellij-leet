package com.coding.leetcode.graph;

import java.util.Arrays;

public class P765 {

    // Simple brute-force and greedy one
    public int minSwapsCouples(int[] row) {
        int n = row.length;
        if ( n <= 2){
            return 0;
        }
        int [] indexOf = new int[n];

        for(int i = 0 ; i < n ; i++){
            indexOf[row[i]] = i;
        }

        int minSwap = 0;

        for(int i = 1 ; i < n ; i+=2){
            int prev = row[i-1];
            if ((prev & 1) > 0 ){
                // this is odd number, its pair would be prev-1;
                if ( row[i] != prev-1){
                    // swap is needed
                    int num = row[i];
                    int indexOfPair = indexOf[prev-1];
                    row[indexOfPair] = num;
                    indexOf[num] = indexOfPair;
                    row[i] = prev-1;
                    minSwap++;
                }
            }
            else{
                // its even number
                if ( row[i] != prev+1){
                    int num = row[i];
                    int indexOfPair = indexOf[prev+1];
                    row[indexOfPair] = num;
                    indexOf[num] = indexOfPair;
                    row[i] = prev+1;
                    minSwap++;
                }
            }
        }

        return minSwap;
    }

    public static void main(String[] args) {
        {
            int [] nums = {2,1,3,4,5,0};
            System.out.println(new P765().minSwapsCouples(nums));
        }
    }
}
