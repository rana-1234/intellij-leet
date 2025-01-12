package com.coding.leetcode;

import java.util.BitSet;

public class P908BitSet {


    String s;
    int n;
    int mod = (int) 1e9 + 7;

    public int getAllPerm(int i, int lastSet,  BitSet tracker){

        if ( i == n+1){
            return 1;
        }

        int res = 0;
        for ( int j = 0 ; j <= n ; j++){
            if (!tracker.get(j) && ((s.charAt(i-1) == 'D' && j < lastSet) || (s.charAt(i-1) == 'I' && j > lastSet))){
                tracker.set(j);
                res += getAllPerm(i+1, j, tracker);
                res %= mod;
                tracker.clear(j);
            }
        }
        return res;
    }

//    public int numPermsDISequence(String s) {
//        this.s = s;
//        n = s.length();
//
//        int res = 0;
//        BitSet bitSet = new BitSet();
//        for ( int i = 0 ; i <= n ; i++){
//            bitSet.set(i);
//            res += getAllPerm(1, i, bitSet);
//            bitSet.clear(i);
//        }
//        return res;
//    }
//
//
//
//    int mod = 1_000_000_007;

    public int numPermsDISequence(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1]; // dp[i][j] = ways to arrange i elements ending at position j.

        // Base case: Fill dp[0][j] = 1 since any single element satisfies.
        for (int j = 0; j <= n; j++) {
            dp[0][j] = 1;
        }

        // Fill the DP table
        for (int i = 1; i <= n; i++) {
            if (s.charAt(i - 1) == 'D') { // Decreasing
                int suffixSum = 0;
                for (int j = n - i; j >= 0; j--) {
                    suffixSum = (suffixSum + dp[i - 1][j + 1]) % mod;
                    dp[i][j] = suffixSum;
                }
            } else { // Increasing
                int prefixSum = 0;
                for (int j = 0; j <= n - i; j++) {
                    prefixSum = (prefixSum + dp[i - 1][j]) % mod;
                    dp[i][j] = prefixSum;
                }
            }
        }

        // Result is sum of all permutations of length n
        int res = 0;
        for (int j = 0; j <= n; j++) {
            res = (res + dp[n][j]) % mod;
        }
        return res;
    }


    public static void main(String[] args) {
        {
            P908BitSet solution = new P908BitSet();
            String s = "DID";
            System.out.println(solution.numPermsDISequence(s));
        }
    }
}
