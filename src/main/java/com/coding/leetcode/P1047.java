package com.coding.leetcode;

import java.util.Stack;

public class P1047 {

    public String removeDuplicates(String s) {

        Stack<Character> st = new Stack<>();
        st.push(s.charAt(0));
        for(int i = 1 ; i < s.length(); i++){
            if(!st.isEmpty() && st.peek().equals(s.charAt(i))){
                st.pop();
            }
            else{
                st.push(s.charAt(i));
            }
        }

        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()){
            sb.append(st.pop());
        }

        return sb.reverse().toString();
    }

    public static void main(String[] args) {

    }
}
