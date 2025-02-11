package com.coding.leetcode;

import java.util.Arrays;

public class P1547 {

    int solve(int len, int [] cuts, int as, int ae){

        if ( as == ae){
            // Just need to make one cut
            return len;
        }

        if ( as > ae){
            return 0;
        }

        int min_cost = Integer.MAX_VALUE;
        for ( int i = as ; i <= ae; i++){
            int cut_left = solve(cuts[i], cuts, as, i-1);
            int cut_right = solve(len - cuts[i], cuts, i+1, ae);
            int total_cost = cut_left + cut_right + len;
            if ( total_cost < min_cost ){
                min_cost = total_cost;
            }
        }

        return min_cost;
    }

    public int minCost(int n, int[] cuts) {
//        int [] with_boundary = new int[cuts.length+2];
//        int i = 1;
//        for ( int a : cuts){
//            with_boundary[i++] = a;
//        }
//        with_boundary[i] = n;
//        Arrays.sort(with_boundary);
        Arrays.sort(cuts);
        return solve(n, cuts, 0, cuts.length - 1);
    }


    public static void main(String[] args) {
        {
            P1547 p = new P1547 ();
            System.out.println(p.minCost(7, new int[]{1,3,4,5}));
        }
    }
}
