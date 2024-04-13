package com.coding.dp;

import java.util.Arrays;

public class P213 {

    public static int [] money;
    public static int n ;
    public static int [] dp;
    public int maxMoney2(int index){

        if(index >= n){
            return 0;
        }

        if ( dp[index] != -1 ){
            return dp[index];
        }
        return dp[index] = Math.max(money[index] + maxMoney2(index + 2), maxMoney2(index+1));
    }

    public int rob(int[] nums) {

        if(nums.length == 1){
            return nums[0];
        }
        money = nums;
        n = nums.length - 1;
        dp = new int[n];
        Arrays.fill(dp,-1);
//        return maxMoney(0); // By adding for loops inside function
        int first =  maxMoney2(0);
        n = nums.length;
        dp = new int[n];
        Arrays.fill(dp,-1);
        int second = maxMoney2(1);
        return Math.max(first, second);
    }

    public static void main(String[] args) {
        System.out.println(new P213().rob(new int []{1,2,3}));
    }
}
