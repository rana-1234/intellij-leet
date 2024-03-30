package com.coding.leetcode;

public class P2926 {

    public static long mod = (long)1e9 + 7;

    public long maxBalancedSubsequenceSum(int[] nums) {

        long ans = Long.MIN_VALUE;
        int n = nums.length;
        long [] dp = new long[n];
        dp[0] = nums[0];

        for ( int i = 1 ; i < n ; i++ ){
            dp[i] = nums[i];
            for(int j = 0 ; j < i ; j++ ){
                // Should I include negative numbers ?
                if (nums[i] >= 0 &&  nums[i] - nums[j] >= i - j && dp[i] < nums[i] + dp[j]){
                    dp[i] = nums[i] + dp[j];
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P2926().maxBalancedSubsequenceSum(new int [] {3,3,5,6}));
        System.out.println(new P2926().maxBalancedSubsequenceSum(new int [] {-2,-1}));
    }
}
