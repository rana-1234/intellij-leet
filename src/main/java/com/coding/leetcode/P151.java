package com.coding.leetcode;

public class P151 {

    public String reverseWords(String s) {
        s = s.trim();
        StringBuilder sb = new StringBuilder();
        String[] strings = s.split("\\s+");
        for (int i = strings.length - 1; i >= 1; i--) {
            sb.append(strings[i]);
            sb.append(" ");
        }
        sb.append(strings[0]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P151().reverseWords("the sky is blue"));
    }
}
