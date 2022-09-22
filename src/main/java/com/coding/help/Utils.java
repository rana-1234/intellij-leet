package com.coding.help;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {

    public static String reverseWords(String s) {
        return Arrays.stream(s.split(" ")).map(x -> new StringBuilder(x).reverse().toString()).collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {

        System.out.println(reverseWords("Hey Shashi, I love you man !!"));
    }
}
