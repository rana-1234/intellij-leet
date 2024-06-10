package com.coding.leetcode;

import java.util.Arrays;

public class P1051 {

    public int heightChecker(int[] heights) {
        int [] expectedHeights = Arrays.copyOf(heights, heights.length);
        Arrays.sort(expectedHeights);
        int ans = 0;
        for (int i = 0 ; i < heights.length; i++){
            if ( expectedHeights[i] != heights[i]){
                ans++;
            }
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
