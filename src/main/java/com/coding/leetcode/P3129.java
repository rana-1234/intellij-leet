package com.coding.leetcode;

public class P3129 {

    int limit;
    int zero;
    int one;


    public int getCount(int prev0, int prev1, int zeroIncludedCount, int oneIncludedCount) {

        if ( zeroIncludedCount == zero && oneIncludedCount == one ) {
            return 1; // valid case
        }

        int ans = 0;
        if ( prev0 < limit && zeroIncludedCount < zero){
            ans += getCount(prev0+1,0,   zeroIncludedCount+1, oneIncludedCount);
        }
        if ( prev1 < limit &&  oneIncludedCount < one){
            ans += getCount(0,prev1+1,zeroIncludedCount, oneIncludedCount+1);
        }

        return ans;
    }
    public int numberOfStableArrays(int zero, int one, int limit) {
        this.limit = limit;
        this.zero = zero;
        this.one = one;
//        return getCount(0, 0, 0, 0);
//
        final int MOD = 1000000007;

        long [][][] dp = new long [one+1][zero+1][2];

        dp[0][0][1] = dp[0][0][1] = 1;
        for (int i = 0 ; i <= one; i++) {
            for (int j = 0 ; j <= zero; j++) {
                for (int k = 0 ; k <= limit; k++) {
                    if ( i - k >= 0){
                        dp[i][j][1] = (dp[i][j][1] + dp[i-k][j][0])%MOD;
                    }
                    if ( j - k >= 0){
                        dp[i][j][0] = (dp[i][j][0] + dp[i][j-k][1])%MOD;
                    }
                }
            }
        }

        return (int) (dp[one][zero][0] + dp[one][zero][1])%MOD;
    }

    public static void main(String[] args) {
        System.out.println(new P3129().numberOfStableArrays(3,4,2));
    }
}
