package com.coding.leetcode;

public class P1328 {

    public String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if( n == 1){
            return "";
        }
        int i = 0 ;
        for(i = 0; i < n && palindrome.charAt(i) == 'a'; i++);

        // aba aba or ab a ba or aa a aa

        if( i == n || (n % 2 == 1 && i == n/2)){
            // all a case
            return palindrome.substring(0, n-1) + "b";
        }
        else{
            return palindrome.substring(0,i) + "a" + palindrome.substring(i+1);
        }
    }

    public static void main(String[] args) {
        System.out.println(new P1328().breakPalindrome("abccba").equals("aaccba"));
        System.out.println(new P1328().breakPalindrome("a").equals(""));
        System.out.println(new P1328().breakPalindrome("aaaaaa").equals("aaaaab"));
        System.out.println(new P1328().breakPalindrome("aba").equals("abb"));
        System.out.println(new P1328().breakPalindrome("abccba").equals("aaccba"));

    }
}
