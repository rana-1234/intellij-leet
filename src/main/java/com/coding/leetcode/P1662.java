package com.coding.leetcode;

public class P1662 {

    private static int i1 = 0, b1 = 0;
    private static int i2 = 0, b2 = 0;
    private static String [] s1, s2;

    private static char getNextCharacter(){
        if(s1[b1].length() == i1){
            i1 = 0;
            b1++;
        }
        if( b1 == s1.length){
            return '$';
        }
        return s1[b1].charAt(i1++);
    }

    private static char getNextCharacterA2(){
        if(s2[b2].length() == i2){
            i2 = 0;
            b2++;
        }
        if( b2 == s2.length){
            return '$';
        }
        return s2[b2].charAt(i2++);
    }

    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        s1 = word1;
        s2 = word2;
        i1 = 0;
        i2 = 0;
        b1 = 0;
        b2 = 0;

        char a1 = getNextCharacter(), a2 = getNextCharacterA2();
        while(a1 == a2){
            if( a1 == a2 && a1 == '$'){
                return true;
            }
            a1 = getNextCharacter();
            a2 = getNextCharacterA2();
        }
        return false;
    }

    public static void main(String[] args) {
//        System.out.print(new P1662().arrayStringsAreEqual(new String []{"abc", "d", "defg"}, new String[]{"abcddefg"}));
        System.out.print(new P1662().arrayStringsAreEqual(new String []{"a", "cb"}, new String[]{"ab", "c"}));

    }
}
