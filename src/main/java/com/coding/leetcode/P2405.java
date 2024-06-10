package com.coding.leetcode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class P2405 {

    public int partitionString(String s) {

        Set<Character> unique = new HashSet<>();
        int n = s.length();
        int ans = 1;
        int j = 0;
        while(j < n){
            if ( unique.contains(s.charAt(j))){
                // start a new one from here
                ans++;
                unique = new HashSet<>();
                unique.add(s.charAt(j));
            }
            else{
                unique.add(s.charAt(j));
            }
            j++;
        }

        return ans;

    }

    public static void main(String[] args) {

    }
}
