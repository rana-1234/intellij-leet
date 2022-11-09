package com.coding.leetcode;

import java.util.*;

public class P901 {

    public static class StockSpanner {

        List<Integer> stockPrice;
        List<Integer> stockPriceSpan;

        public StockSpanner() {
            stockPrice = new ArrayList<>();
            stockPriceSpan = new ArrayList<>();
        }
        public int next(int price) {
            int span = getStockPriceSpan(stockPrice.size()-1, price) + 1;
            stockPriceSpan.add(span);
            stockPrice.add(price);
            return span;
        }

        private int getStockPriceSpan(int ci, int price){
            if( ci < 0){
                // last day
                return 0;
            }
            else if( stockPrice.get(ci) <= price){
                // It means we can add the span of this as well
                return stockPriceSpan.get(ci) + getStockPriceSpan(ci-stockPriceSpan.get(ci), price);
            }
            else{
                return 0;
            }
        }
    }

    public static void main(String[] args) {
        StockSpanner sp = new StockSpanner();
        int [] price = {28,14,28};
        for(int i = 0 ; i < price.length; i++){
            System.out.println(sp.next(price[i]));
        }
    }
}
