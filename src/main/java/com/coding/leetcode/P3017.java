package com.coding.leetcode;

import com.coding.help.Utils;

public class P3017 {

    public long[] countOfPairs(int n, int x, int y) {

        long [] ans = new long[n];

        if ( x > y){
            int temp = x;
            x = y;
            y = temp;
        }

        if ( x == y || y == x+1){
            for ( int i = 0 ; i < n ; i++){
                ans[i] = (n-1-i)*2;
            }
            return ans;
        }

        // otherwise
        /*
            Path from 1 -> x -> y -> n = total x + n-y + 1 (node)
            *


            https://leetcode.com/problems/count-the-number-of-houses-at-a-certain-distance-ii/


         */

        return ans;
    }

    public static void main(String[] args) {

        Utils.printLongArray(new P3017().countOfPairs(5,3,4));
        Utils.printLongArray(new P3017().countOfPairs(5,3,3));
        Utils.printLongArray(new P3017().countOfPairs(5,4,3));
        Utils.printLongArray(new P3017().countOfPairs(5,2,4));
    }
}
