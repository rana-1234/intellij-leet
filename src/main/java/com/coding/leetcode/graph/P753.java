package com.coding.leetcode.graph;

import java.util.HashSet;
import java.util.Set;

public class P753 {

    public Set<String> alreadyPresentState;
    StringBuilder ans;

    public String crackSafe(int n, int k) {
        if ( k == 1 ){
            return "0".repeat(n);
        }

        alreadyPresentState = new HashSet<>();

        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < n-1; i++){
            sb.append("0");
        }

        ans = new StringBuilder();
        alreadyPresentState.add(ans.toString());
        dfs(sb.toString(), k);
        ans.append(sb);
        return ans.toString();
    }

    void dfs(String s, int k){

        for(int i = 0 ; i < k ; i++){
            String nextState = s + i; // let sat i = 1, s = "00" it would calculate 00 + 1 = 001
            if (!alreadyPresentState.contains(nextState)){
                // check possibilities from this state
                alreadyPresentState.add(nextState); // 001
                dfs(nextState.substring(1), k); // Now calculate all the states from "01"
                ans.append(i); // "00" -> "1" => "001"
            }
        }

    }


    public static void main(String[] args) {
        System.out.println(new P753().crackSafe(3, 2));
    }
}
