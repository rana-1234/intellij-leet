package com.coding.leetcode;

public class P1832 {

    public boolean checkIfPangram(String sentence) {

        if( sentence.length() < 26){
            return false;
        }

        boolean [] arr = new boolean[26];
        int counter = 0;
        for(int i = 0 ; i < sentence.length(); i++){
            if( arr[sentence.charAt(i) - 'a'] == false){
                arr[sentence.charAt(i)-'a'] = true;
                counter++;
            }
        }

        return counter == 26;

    }
    public static void main(String[] args) {
        System.out.println(new P1832().checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
    }
}
