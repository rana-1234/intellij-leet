package com.coding.google;

public class P121 {

    // https://leetcode.com/problems/best-time-to-buy-and-sell-stock/submissions/1225560587/

    public int maxProfit(int[] prices) {
        int n = prices.length;
        if ( n == 1){
            return 0;
        }

        int minPriceSeen = prices[0];
        int maxProfit = 0;
        for(int i = 1 ; i < n ; i++ ){
            maxProfit = Math.max(maxProfit, prices[i] - minPriceSeen);
            minPriceSeen = Math.min(minPriceSeen, prices[i]);
        }
        return maxProfit;
    }
    public static void main(String[] args) {
        System.out.println(new P121().maxProfit(new int [] {7,1,5,3,6,4}));
    }
}
