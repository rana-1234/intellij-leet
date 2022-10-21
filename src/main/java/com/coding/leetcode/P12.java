package com.coding.leetcode;

import java.util.HashMap;
import java.util.Map;

public class P12 {

    private static int[] value = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(num > 0 && i < value.length){
            if (num >= value[i]){
                sb.append(roman[i]);
                num -= value[i];
            }
            else{
                i++;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new P12().intToRoman(1999));
    }
}
