package com.coding.leetcode;

public class P1578 {

    public int minCost(String colors, int[] neededTime) {
        // We can take greedy approach to solve this time, but would this work, let's try

        // p....q => All colors are same, then take the ans += (sum - maxTime) => Some == time to remove all balloons


        int ans = 0;
        int n = colors.length();
        for(int i = 0 ; i < n ; i++){
            int j = i+1;
            int maxTime = neededTime[i];
            int sum = neededTime[i];
            while(j < n && colors.charAt(j) == colors.charAt(i)){
                maxTime = Math.max(maxTime, neededTime[j]);
                sum += neededTime[j];
                j++;
            }
            ans += sum - maxTime;
            i = j-1;
        }
        return ans;
    }

    public static void main(String[] args) {

        String colors = "bbbaaa";
        int [] time = {4,9,3,8,8,9};

        System.out.println(new P1578().minCost(colors, time));
    }
}
