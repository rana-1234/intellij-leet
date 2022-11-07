package com.coding.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P2131 {

    public int longestPalindrome(String[] words) {
        Map<String,Integer> counter = new HashMap<>();
        int n = words.length;
        int ans = 0;
        for(int i = 0 ; i < n; i++){
            // find the individual pairs
            counter.put(words[i], counter.getOrDefault(words[i], 0) + 1);
        }

        boolean foundForMiddle = false;
        for(int i = 0 ; i < n ; i++){
            // Get this word count
            int counter1 = counter.get(words[i]);
            int counter2 = counter.getOrDefault(new StringBuilder(words[i]).reverse().toString(), 0);

            if(words[i].charAt(0) == words[i].charAt(1)){
                // 'aa', counter1 == counter2,
                ans += (counter1/2)*4 ;// total these many pairs
                if( counter1 % 2 == 1){
                    foundForMiddle = true;
                }
                counter.put(words[i], 0);
                continue;
            }
            //emem meme
            ans += Math.min(counter1, counter2)*4;// ans will be doubled for another pair
            counter.put(words[i], 0);
            counter.put(new StringBuilder(words[i]).reverse().toString(), 0);
        }

        return foundForMiddle ? ans + 2 : ans;
    }

    public static void main(String[] args) {
        System.out.println(new P2131().longestPalindrome(new String[]{"em","pe","mp","ee","pp","me","ep","em","em","me"}));
    }
}
