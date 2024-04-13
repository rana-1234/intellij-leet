package com.coding.dp;

import java.util.Arrays;

public class P300 {

    public int lengthOfLIS(int[] nums) {

        int n = nums.length;
        int dp [] = new int [n];

        Arrays.fill(dp, 1);

        int ans = 1;
        for(int i = 1 ; i < n ; i++ ){
            for(int j = 0 ; j < i ; j++ ){
                if ( nums[i] > nums[j]){
                    dp[i] = Math.max(1+dp[j], dp[i]);
                }
            }
            ans = Math.max(ans,dp[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P300().lengthOfLIS(new int[]{10,9,2,5,3,7,101,18}));
    }
}
