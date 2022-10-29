package com.coding.leetcode;

import java.util.Arrays;
import java.util.Map;

public class P2136 {

    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        // Minimum days would be definitely Maximum(plant[i] + growTime[i])
        /*
            Whose growTime is more, we would plan them first.
            Would it work ?
            Let's try

            3  2
            2  3

            correct
            2 + 3 = 5
            5 + 2 = 7

            wrong
            3 + 2 = 5
            5 + 3 = 8
         */

        int n = plantTime.length;
        Map.Entry<Integer, Integer> [] plantGrowTime = new Map.Entry[n];
        for(int i = 0 ; i < n ; i++){
            plantGrowTime[i] = Map.entry(plantTime[i], growTime[i]);
        }

        Arrays.sort(plantGrowTime, (p1, p2)->{
            if( p1.getValue() == p2.getValue()){
                return p2.getKey() - p1.getKey();
            }
            return p2.getValue() - p1.getValue();
        });

        int ans = 0, totalPlantDay = 0;
        for(int i = 0 ; i < n ; i++){
            totalPlantDay += plantGrowTime[i].getKey();
            ans = Math.max(ans, totalPlantDay + plantGrowTime[i].getValue());
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P2136().earliestFullBloom(new int [] {1,4,3}, new int[]{2,3,1}));
        System.out.println(new P2136().earliestFullBloom(new int [] {3,2}, new int[]{2,3}));
    }
}
