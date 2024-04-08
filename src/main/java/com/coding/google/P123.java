package com.coding.google;

import java.util.Collections;
import java.util.PriorityQueue;

public class P123 {

    public int maxProfit(int[] prices) {

        int n = prices.length;

        if ( n <= 1 ){
            return 0;
        }

        // Divide graph in two sections

        int [] maxProfitFromLeft = new int [n];
        maxProfitFromLeft[0] = 0;
        int leftMinPriceSeen = prices[0];
        for (int i = 1 ; i < n ; i++){
            maxProfitFromLeft[i] = Math.max(prices[i] - leftMinPriceSeen,maxProfitFromLeft[i-1]);
            leftMinPriceSeen = Math.min(prices[i], leftMinPriceSeen);

        }

        int [] maxProfitFromRight = new int [n];
        maxProfitFromRight[n-1] = 0;
        int rightMaxPriceSeen = prices[n-1];

        for(int i = n-2; i >= 0 ; i--){
            maxProfitFromRight[i] = Math.max(rightMaxPriceSeen - prices[i], maxProfitFromRight[i+1]);
            rightMaxPriceSeen = Math.max(prices[i], rightMaxPriceSeen);
        }

        int maxProfitByDoingTwoTransactions = 0;

        for(int i = 0 ; i < n-1 ; i++){
            maxProfitByDoingTwoTransactions = Math.max(maxProfitByDoingTwoTransactions, maxProfitFromLeft[i] + maxProfitFromRight[i]);
        }
        return maxProfitByDoingTwoTransactions;

    }

    public static void main(String[] args) {
        System.out.println(new P123().maxProfit(new int[]{1,2,3,4,5}));
    }
}
