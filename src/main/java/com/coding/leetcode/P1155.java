package com.coding.leetcode;

public class P1155 {
    public int numRollsToTarget(int n, int k, int target) {
        if( target < n || target > n*k){
            // Not possible
            return 0;
        }

        // dp[i][j] = number of ways to get sum j, by i dice
        /*
            dp[0][j] = 0 for all j
            dp[i][0] = 0 for all i



         */

        int mod = 1000000007;
        int [][] dp = new int [n+1][target+1];
        for(int i = 0 ; i <= target ; i++){
            dp[0][i] = 0;
        }

        for (int i = 0 ; i <= n ; i++){
            dp[i][0] = 0;
        }

        for(int j = 1 ; j <= Math.min(k,target) ; j++) {
            dp[1][j] = 1;
        }

        for(int i = 2 ; i <= n ; i++){
            for(int j = 1; j <= target; j++){
                if(i*k >= j && j >= i ){
                    for(int p = 1 ; p <= k ; p++){
                        // Getting p in this roll
                        if( j-p >= 0)
                            dp[i][j] = (dp[i][j] +  dp[i-1][j-p])%mod; // by adding one more dice, we can get sum 1 to k, we can get the ways by adding one more role
                    }
                }
                else{
                    // Obvious
                    dp[i][j] = 0;
                }
            }
        }

        return dp[n][target];
    }

    public static void main(String[] args) {
        System.out.println(new P1155().numRollsToTarget(1,6,3));
    }
}
