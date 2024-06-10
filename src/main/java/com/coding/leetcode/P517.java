package com.coding.leetcode;

import java.util.Arrays;

public class P517 {

    public int findMinMoves(int[] machines) {
        int n = machines.length;
        int sum = 0;
        for ( int i = 0 ; i < n ; i++){
            sum += machines[i];
        }

        if ( sum % n != 0){
            return -1;
        }

        int target = sum / n ; // All the machines should have same number of clothes
        int ans = 0;
        int [] moves = new int[n];
        Arrays.fill(moves, 0);
        for(int i = 0 ; i < n-1 ; i++){
            int extraCloth = machines[i] - target;
            machines[i+1] += extraCloth; // moving extra cloth to next machine

            if ( extraCloth > 0 ) {
                // there are extra cloth on the machine which must be moved right side.
                // Why only right side and not left side.
                // this is because we are processing this from left to right and would always put the extra cloth
                // to right side, or would take the required cloth from right side.
                // so we would require those many moves to perform at least
                moves[i] += extraCloth;
            }
            else{
                // So, here we need some extra cloth from the next machine
                moves[i+1] += -extraCloth; // Extra clothes must be moved from right machine to this machine, which
                // would take extraClothes moves extra from the next machine
            }
            // These many moves needs to be performed in order to reach the target at least from the ith Machine
            ans = Math.max(moves[i], ans);
        }

        return Math.max(ans, moves[n-1] + (machines[n-1] - target));
    }

    public static void main(String[] args) {
        {
            System.out.println(new P517().findMinMoves(new int[]{1,3,5}));
        }
        {
            System.out.println(new P517().findMinMoves(new int[]{0,2,0}));
        }
        {
            System.out.println(new P517().findMinMoves(new int[]{1,0,5}));
        }
        {
            System.out.println(new P517().findMinMoves(new int[]{0,3,0}));
        }
    }
}
