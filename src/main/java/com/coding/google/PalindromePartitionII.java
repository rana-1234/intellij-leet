package com.coding.google;

import java.util.Arrays;

public class PalindromePartitionII {

    // https://leetcode.com/problems/palindrome-partitioning-ii/

    int cache [][];
    int n ;
    String str;

    public int minCut(String s) {
        n = s.length();
        cache = new int[n][n];
        for(int i = 0 ; i < n ; i++){
            Arrays.fill(cache[i], -1);
        }
        str = s;
        return getMinCut(0, n-1) - 1;
    }


    private boolean isPalindrome(int start, int end){
        while(start <= end && str.charAt(start) == str.charAt(end)){
            start ++;
            end--;
        }
        return start > end;
    }
    private int getMinCut(int start, int end) {

        if(start > end){
            return 0;
        }

        if( cache[start][end] != -1){
            return cache[start][end]; // Already calculated
        }

        int minAns = Integer.MAX_VALUE;
        for(int i = start ; i <= end ; i++){
            // Put a partition at the index i if possible
            if(isPalindrome(start, i)){
                // Then we can put a partition here
                minAns = Math.min(minAns, 1 + getMinCut(i+1, end));
            }
        }
        return cache[start][end] = minAns;
    }

    public static void main(String[] args) {

        System.out.println(new PalindromePartitionII().minCut("aab"));

    }
}
