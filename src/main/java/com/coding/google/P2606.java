package com.coding.google;

public class P2606 {

    // https://leetcode.com/problems/find-the-substring-with-maximum-cost/

    public int maximumCostSubstring(String s, String chars, int[] vals) {
        int [] values = new int[26];
        for(int i = 0; i < values.length; i++){
            values[i] = i+1;
        }

        for(int i = 0 ; i < chars.length() ; i++){
            values[chars.charAt(i) - 'a'] = vals[i];
        }

        // Same as max sub array sum now

        int maxSoFar = 0;
        int currentSum = 0;

        for(int i = 0 ; i < s.length() ; i++ ){
            currentSum += values[s.charAt(i) - 'a'];
            if ( currentSum > 0 ){
                maxSoFar = Math.max(maxSoFar, currentSum);
            }
            else{
                currentSum = 0;
            }
        }
        return maxSoFar;
    }

    public static void main(String[] args) {
        System.out.println(new P2606().maximumCostSubstring("adaa", "d", new int [] {-1000}));
    }
}
