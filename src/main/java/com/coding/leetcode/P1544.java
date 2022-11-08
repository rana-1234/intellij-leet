package com.coding.leetcode;

public class P1544 {

    public String makeGood(String s) {
        StringBuilder sb = new StringBuilder();
        boolean isGood = false;

        while (!isGood){
            isGood = true;
            sb = new StringBuilder();
            int i  = 0;
            for(i = 0 ; i < s.length() - 1 ; i++){
                if((s.charAt(i) == s.charAt(i+1) + 32) || (s.charAt(i) == s.charAt(i+1) - 32)){
                    isGood = false;
                    i++;
                }
                else {
                    sb.append(s.charAt(i));
                }
            }
            if( i < s.length()){
                sb.append(s.charAt(i));
            }
            s = sb.toString();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P1544().makeGood("leEeetcode"));
        System.out.println(new P1544().makeGood("abBAcC"));

    }
}
