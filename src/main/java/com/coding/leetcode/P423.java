package com.coding.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class P423 {
    // TODO Complete this.
    public int minMutation(String start, String end, String[] bank) {
        int change = 0;
        Set<String> s = new HashSet<>(Arrays.stream(bank).toList());
        for(int i = 0 ; i < start.length(); i++){
            if( start.charAt(i) != end.charAt(i)){
                String find = start.substring(0, i) + end.charAt(i) + start.substring(i+1);
                if( s.contains(find)){
                    change++;
                }
                else {
                    return -1;
                }
            }
        }
        return change;
    }

    public static void main(String[] args) {
        System.out.println(new P423().minMutation("AAAAACCC", "AACCCCCC", new String[]{"AAAACCCC","AAACCCCC","AACCCCCC"}));
    }
}
