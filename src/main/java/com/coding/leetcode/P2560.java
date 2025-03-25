package com.coding.leetcode;

import java.util.Arrays;

public class P2560 {

    public int minCapability(int[] nums, int k) {
        /*
            Since, we need to find the minimum of the all possible ways that robber can rob given the condition.

            Greedy.
            1. Since we have to rob atleast k houses, we need to find the kth largest in the sequence.

            We would first sort the houses based on indices, and then start picking the k houses, the maximum of it
            would be definitely minimum.
        */
        int n = nums.length;
        int [][] mo = new int[n][2];
        for (int i = 0 ; i < n ; i++){
            mo[i] = new int[]{nums[i], i};
        }

        Arrays.sort(mo, (p, q) -> Integer.compare(p[0], q[0]));

        int counter = 0;
        int [] indices = new int[n];

        for ( int i = 0 ; i < n ; i++){
            int ci = mo[i][1];
            boolean left_adjacent_present = ci - 1 >= 0 && indices[ci-1] == 1;
            boolean righ_adjacent_present = ci + 1 < n  && indices[ci+1] == 1;

            if ( !left_adjacent_present && !righ_adjacent_present){
                counter++;
                indices[ci] = 1;
                if ( counter == k ){
                    return mo[i][0];
                }
            }
        }

        System.out.println("Here n " + n + " and " + k);

        // if its coming here, then k is n/2
        int ans = 0;
        for ( int i = 0 ; i < n ; i+=2 ){
            ans = Math.max(ans, nums[i]);
        }

        return ans;
    }

    public static void main(String[] args) {
        {
            int [] nums = new int[]{5038,3053,2825,3638,4648,3259,4948,4248,4940,2834,109,5224,5097,4708,2162,3438,4152,4134,551,3961,2294,3961,
                1327,2395,1002,763,4296,3147,5069,2156,572,1261,4272,4158,5186,2543,5055,4735,2325,1206,1019,1257,5048,1563,3507,4269,5328,173,5007,2392,967,2768,86,3401,3667,4406,4487,876,1530,819,1320,883,1101,5317,2305,89,788,1603,3456,5221,1910,3343,4597};
            int k = 28;
            P2560 instance = new P2560();
            System.out.println(instance.minCapability(nums, k));
        }
    }
}
