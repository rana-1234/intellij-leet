package com.coding.leetcode;

public class P1492 {

    public int kthFactor(int n, int k) {
       int factorCounter = 0;
       for (int i = 1 ; i <= n ; i++){
           if ( n % i == 0){
               factorCounter++;
               if ( factorCounter == k){
                   return i;
               }
           }
       }

       return -1;

    }

    public static void main(String[] args) {

    }
}
