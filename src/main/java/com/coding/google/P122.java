package com.coding.google;

public class P122 {

    public int maxProfit(int[] prices) {

        int n = prices.length;
        if ( n <= 1 ){
            return 0;
        }

        int maxProfit = 0;
        for(int i = 0 ; i < n-1 ; i++ ){
            if ( prices[i+1] > prices[i]){
                maxProfit += prices[i+1] - prices[i];
            }
        }
        return maxProfit;
    }


    public static void main(String[] args) {
        System.out.println(new P122().maxProfit(new int[]{7,1,5,3,6,4}));
    }
}
