package com.coding.leetcode;

import com.coding.io.InputReader;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class P76 {

    public String minWindow(String s, String t) {

        if( t.length() > s.length()){
            return "";
        }
        Map<Character, Integer> m = new HashMap<>();
        Map<Character, Integer> ref = new HashMap<>();
        for(int i = 0 ; i < t.length(); i++) {
            ref.put(t.charAt(i), ref.getOrDefault(t.charAt(i), 0) + 1);
        }

        int characterFound = 0;
        int required = t.length();
        int i = -1, j = 0;
        int si = -1, sj = Integer.MAX_VALUE;

        while(j < s.length()) {
            m.put(s.charAt(j), m.getOrDefault(s.charAt(j), 0) + 1);
            if (ref.containsKey(s.charAt(j)) && ref.get(s.charAt(j)) == m.get(s.charAt(j))) {
                characterFound += ref.get(s.charAt(j));
                if (characterFound == required) {
                    break;
                }
            }
            j++;
        }

        // we have window i to j which contains the string
        if( j == s.length()){
            return ""; // Not possible
        }
        si = i;
        sj = j;

        while(j < s.length()){
            while(i < j){
                i++;
                m.put(s.charAt(i), m.getOrDefault(s.charAt(i), 0) - 1);
                if( ref.containsKey(s.charAt(i)) && m.get(s.charAt(i)) < ref.get(s.charAt(i))){
                    // we have reduced one character here
                    break;
                }
                if( j - i < sj - si){
                    si = i;
                    sj = j;
                }
            }

            // let's add the character now.
            while(j < s.length()){
                j++;
                if( j == s.length()){
                    break;
                }
                m.put(s.charAt(j), m.getOrDefault(s.charAt(j), 0) + 1);
                if( ref.containsKey(s.charAt(j)) && ref.get(s.charAt(j)) == m.get(s.charAt(j))){
                    break;
                }
            }
        }
        return s.substring(si+1, sj+1);
    }

    public static void main(String[] args) throws Exception{
        InputReader in = new InputReader(new InputStreamReader(new FileInputStream("/Users/shashibhushanrana/IdeaProjects/coding/src/main/resources/leetcode")));
        System.out.println(new P76().minWindow(in.readLine(), in.readLine()));
        System.out.println(new P76().minWindow("a", "a"));
        System.out.println(new P76().minWindow("a", "aa"));
    }
}
