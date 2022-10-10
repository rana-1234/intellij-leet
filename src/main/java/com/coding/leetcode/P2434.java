package com.coding.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class P2434 {

    public String robotWithString(String s) {
        int n = s.length();
        int [] sMin = new int [n];

        sMin[n-1] = s.charAt(n-1) - 'a';
        for(int i = n-2; i >=0 ; --i){
            sMin[i] = Math.min(sMin[i+1], s.charAt(i)-'a');
        }


        StringBuilder ans = new StringBuilder();
        Stack<Character> st = new Stack<>();

        for(int i = 0 ; i < n ; i++ ){
            if(i+1 < n && sMin[i+1] >= s.charAt(i) - 'a'){
                ans.append(s.charAt(i));
                while(!st.isEmpty()){
                    // check if previous character can also be appended or not
                    if (sMin[i+1] >= st.peek() - 'a'){
                        ans.append(st.pop());
                    }
                    else{
                        break;
                    }
                }
            }
            else{
                st.push(s.charAt(i));
            }

        }

        while(!st.isEmpty())
            ans.append(st.pop());


        return ans.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P2434().robotWithString("shashibhushanrana").equals("aaaanrnhsuhbihshs"));
        System.out.println(new P2434().robotWithString("bac").equals("abc"));
    }
}
