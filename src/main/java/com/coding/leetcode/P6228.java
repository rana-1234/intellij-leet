package com.coding.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P6228 {

    public List<String> twoEditWords(String[] queries, String[] dictionary) {
        int len  = dictionary[0].length();
        List<String> ans = new ArrayList<>();
        for(String query : queries){
            boolean part = false;
            for(String dict : dictionary){
                int count = 0;
                for(int i = 0 ; i < len && count < 3; i++){
                    if( dict.charAt(i) != query.charAt(i))
                        count++;
                }
                if( count < 3){
                    part = true;
                    break;
                }
            }
            if(part){
                ans.add(query);
            }
        }

        return ans;
    }
    public static void main(String[] args) {

    }
}

