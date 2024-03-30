package com.coding.google;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P30 {

    public static Map<Integer, String> tokens;
    public static Map<String, Integer> wordCount;

    public List<Integer> findSubstring(String s, String[] words) {

        int n = words.length;
        int wl = words[0].length();
        int sLen = s.length();
        List<Integer> ans = new ArrayList<>();

        if (n*wl > sLen){
            return ans;
        }


        tokens = new HashMap<>();
        wordCount = new HashMap<>();
        boolean tokenPresentFrom [] = new boolean[sLen];

        // Hash all the words
        for (String word : words)
            wordCount.put(word, wordCount.getOrDefault(word, 0)+1);


        for ( int i = 0, j = wl ; j < sLen ; i++, j++) {
            tokens.put(i, s.substring(i, j));
            tokenPresentFrom[i] = wordCount.get(tokens.get(i)) != null;
        }


        for(int i = 0 ; i < sLen - n*wl; i++){
            int j = i;
            boolean found = true;
            Map<Integer, Integer> wordRequiredCount = new HashMap<>();
            while( j < n && found){
                boolean tokenPresent = tokenPresentFrom[j];
                int requiredCount = wordRequiredCount.getOrDefault(j, 0) + 1;
                if (tokenPresent && wordCount.getOrDefault(tokens.get(j), 0) < requiredCount ){
                    found = false;
                }
                else{

                }
                j += wl;
            }

        }
        return ans;
    }

    public static void main(String[] args) {

        System.out.println(String.format("Total valid indices : %s", new P30().findSubstring("barfoofoobarthefoobarmanb", new String[]{"bar","foo","the"})));



    }
}
