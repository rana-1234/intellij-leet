package com.coding.google;

import java.util.ArrayList;
import java.util.List;

public class P386 {

    // https://leetcode.com/problems/lexicographical-numbers/


    public void createPatternFrom(int start , List<Integer> ans , int n){

        // This function will create pattern from start, like start0, start1, start2...
        // start = {1, .... 9}
        if ( start > n ){
            return;
        }
        ans.add(start);
        for(int i = 0 ; i <= 9 ; i++){
            // this will create 1, 10,
            createPatternFrom(start*10+i, ans, n);
        }

    }

    public List<Integer> lexicalOrder(int n) {
        // Pattern is 1, 10, 100, 1000 ....

        List<Integer> ans = new ArrayList<>();

        for(int i = 1 ; i <= 9 ; i++){
            createPatternFrom(i, ans, n);
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(String.format("For n = 13 : %s", new P386().lexicalOrder(13)));
    }
}
