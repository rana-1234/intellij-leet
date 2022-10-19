package com.coding.leetcode;

public class P38 {

    private String countAndSayNumber(String word){
        StringBuilder sb = new StringBuilder();

        for(int i = 0 ; i < word.length(); i++ ){
            int count = 1;
            while(i + 1 < word.length() && word.charAt(i) == word.charAt(i+1)){
                count++;
                i++;
            }
            sb.append(count);
            sb.append(word.charAt(i));
        }

        return sb.toString();
    }

    public String countAndSay(int n) {
        String countAndSay = "1";

        for(int i = 2 ; i <= n ; i++){
            countAndSay = countAndSayNumber(countAndSay);
        }

        return countAndSay;
    }

    public static void main(String[] args) {
        System.out.println(new P38().countAndSay(10));
    }
}
