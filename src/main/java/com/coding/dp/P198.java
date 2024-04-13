package com.coding.dp;

import java.util.Arrays;

public class P198 {

    public static int [] money;
    public static int n ;
    public static int [] dp;

    public int maxMoney(int house){

        if( house >= n){
            return 0;
        }

        if ( dp[house] != -1){
            return dp[house];
        }

        int moneyWithRob = money[house];
        int moneyWithoutRob = 0;

        int maxRob = 0;
        for ( int i = house+2 ; i < n ; i++ ){
            maxRob = Math.max(maxRob,maxMoney(i));
        }
        moneyWithRob += maxRob;
        maxRob = 0;
        for ( int i = house + 1 ; i < n ; i++)
        {
            maxRob = Math.max(maxRob,maxMoney(i));
        }
        moneyWithoutRob += maxRob;

        return dp[house] = Math.max(moneyWithoutRob, moneyWithRob);
    }

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
        money = nums;
        n = nums.length;
        dp = new int[n];
        Arrays.fill(dp,-1);
//        return maxMoney(0); // By adding for loops inside function
        return maxMoney2(0);
    }

    public static void main(String[] args) {

        System.out.println(new P198().rob(new int[]{2,7,9,3,1}));

    }
}
