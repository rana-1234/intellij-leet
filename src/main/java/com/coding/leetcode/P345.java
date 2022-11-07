package com.coding.leetcode;

import java.util.Set;

public class P345 {

    public String reverseVowels(String s) {
        char [] word = s.toCharArray();
        Set<Character> vowels = Set.of('a','e','i','o','u','A','E','I','O','U');
        int i = 0, j = word.length;
        while(i < j){
            if (vowels.contains(word[i])){
                j--;
                while(j > i){
                    if ( vowels.contains(word[j])){
                        break;
                    }
                    j--;
                }

                if( i < j){
                    char temp = word[i];
                    word[i] = word[j];
                    word[j] = temp;
                }
            }
            i++;
        }
        return new String(word);
    }

    public static void main(String[] args) {
        System.out.println(new P345().reverseVowels("shashibhushanrana"));
    }
}
