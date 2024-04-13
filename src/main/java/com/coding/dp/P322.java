package com.coding.dp;

import java.util.Arrays;

public class P322 {

    public static int [] ac ;
    public static int n ;
    public static int [][] dp;

    public int getMinimumCoin(int target, int index){


        if(target == 0 ){
            return 0;
        }


        if (index >= n || target < 0 ){
            return Integer.MAX_VALUE;
        }

        if(dp[target][index] != -1 ){
            return dp[target][index];
        }

        int withoutInc = getMinimumCoin(target, index+1);
        int withInc = getMinimumCoin(target - ac[index], index);

        if ( withoutInc == Integer.MAX_VALUE && withInc == Integer.MAX_VALUE){
            return dp[target][index] = Integer.MAX_VALUE;
        }
        else if (withoutInc == Integer.MAX_VALUE){
            return dp[target][index] = withInc+1;
        }
        else if(withInc == Integer.MAX_VALUE){
            return dp[target][index] = withoutInc ;
        }
        else{
            return dp[target][index] = Math.min(1 + withInc, withoutInc);
        }

    }


    public int coinChange(int[] coins, int amount) {
        ac = coins;
        n = coins.length;
        dp = new int[amount+1][n+1];
        Arrays.stream(dp).forEach(x -> Arrays.fill(x, -1));
        int ans = getMinimumCoin(amount, 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new P322().coinChange(new int []{1,2,5}, 10));
    }
}
