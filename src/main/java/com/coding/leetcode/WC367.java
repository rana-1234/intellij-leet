package com.coding.leetcode;

import java.util.Map;

public class WC367 {


    // Problem 1 and 3 with different input size
    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {


        // O(n) complexity
        int n = nums.length;

        Map.Entry<Integer, Integer> [] maxTillIndex = new Map.Entry [n];
        Map.Entry<Integer, Integer> [] minTillIndex = new Map.Entry [n];

        maxTillIndex[0] = minTillIndex[0] = Map.entry(nums[0], 0);
        for(int i = 1 ; i < n ; i++){
            if(maxTillIndex[i-1].getKey() < nums[i]){
                maxTillIndex[i] = Map.entry(nums[i], i);
            }
            else{
                maxTillIndex[i] = maxTillIndex[i-1];
            }

            if (minTillIndex[i-1].getKey() > nums[i]){
                minTillIndex[i] = Map.entry(nums[i], i);
            }
            else{
                minTillIndex[i] = minTillIndex[i-1];
            }
        }


        for(int i = indexDifference ; i < n ; i++){
            if (Math.abs(nums[i] - maxTillIndex[i-indexDifference].getKey()) >= valueDifference){
                return new int[]{i, maxTillIndex[i-indexDifference].getValue()};
            }
            if(Math.abs(nums[i] - minTillIndex[i-indexDifference].getKey()) >= valueDifference){
                return new int[]{i, minTillIndex[i-indexDifference].getValue()};
            }
        }

        return new int []{-1,-1};
    }

    // Problem 2
    public String shortestBeautifulSubstring(String s, int k){

        int i = -1, j = -1;
        String ans = "";
        int n = s.length();
        int cnt = 0;

        while(j < n ){
            ++j;
            if (s.charAt(j) == '1') cnt++;
            if( i >= 0 && s.charAt(i) == '1') cnt--;
            if ( cnt == k){
                // We found a string

            }
            else if ( cnt > k ){
                // we need to go back to the ith index till cnt becomes less
                i++;
            }
        }
        return ans;

    }




    // Problem 4 : 2906. Construct Product Matrix
    public int[][] constructProductMatrix(int[][] grid){


        return null;

    }


    public static void main(String[] args) {
        WC367 problem = new WC367();
        int [] ans = problem.findIndices(new int [] {5,1,4,1}, 2, 4);
        System.out.println(String.format("Ans : [%s,%s]", ans[0], ans[1]));
    }
}
