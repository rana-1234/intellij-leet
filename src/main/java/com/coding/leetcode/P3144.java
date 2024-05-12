package com.coding.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class P3144 {

    String s;
    int [] dp;

    int [][] balanced;

    boolean balanced(int from, int to){

        if( from == to || from == to+1){
            return true;
        }

        if ( balanced[from][to] == 1){
            return true;
        }
        if ( balanced[from][to] == 2){
            return false;
        }

        int [] counter = new int[26];
        Arrays.fill(counter,0);

        int lastVal = 0;
        for(int i = from; i <= to; i++){
            counter[s.charAt(i) - 'a']++;
            lastVal = counter[s.charAt(i) - 'a'];
        }

        for(int i = 0 ; i < 26 ; i++){
            if ( counter[i] != 0 && counter[i] != lastVal){
                balanced[from][to] = 2;
                return false;
            }
        }
        balanced[from][to] = 1;
        return true;
    }

    public int minPart(int from){

        if ( from == s.length()){
            return 0;
        }

        if (dp[from] != -1){
            return dp[from];
        }

        int remainingPart = Integer.MAX_VALUE;
        for (int i = from ; i < s.length() ; i++){
            if (balanced(from, i)){
                remainingPart = Math.min(remainingPart, minPart(i+1));
            }
        }

        return dp[from] = remainingPart == Integer.MAX_VALUE ? remainingPart : 1 + remainingPart;
    }

    public int minimumSubstringsInPartition(String s) {

        this.s = s;
        dp = new int[s.length()];
        balanced = new int[s.length()][s.length()];
        Arrays.fill(dp,-1);
        return minPart(0);

    }

    public static void main(String[] args) {
        System.out.println(new P3144().minimumSubstringsInPartition("abababaccddb"));
    }
}
