package com.coding.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class P2977 {

    public static class Trie{
        Trie [] children;
        List<Map.Entry<String, Integer>> wordOfConversion;
        public Trie(){
            children = new Trie[26];
            wordOfConversion = new ArrayList<>();
        }

        public void insert(String word, int cost, String wordOfConversion){
            int n = word.length();
            Trie temp = this;
            for (int i = 0  ; i < n ; i++){
                if ( temp.children[word.charAt(i) - 'a'] == null){
                    temp.children[word.charAt(i) - 'a'] = new Trie();
                }
                temp = temp.children[word.charAt(i) - 'a'];
            }
            temp.wordOfConversion.add(Map.entry(wordOfConversion, cost));
        }

        public Map<Integer, List<Map.Entry<String, Integer>>> findAll(int from){

            Map<Integer, List<Map.Entry<String, Integer>>> allLengthMatches = new HashMap<>();

            Trie temp = this;

            while(temp != null && from < n && temp.children[source.charAt(from) - 'a'] != null) {
                temp = temp.children[source.charAt(from) - 'a'];
                if (temp.wordOfConversion.size() > 0){
                    allLengthMatches.put(temp.wordOfConversion.get(0).getKey().length(), temp.wordOfConversion);
                }
                from++;
            }
            return allLengthMatches;
        }


    }

    public Trie root;
    static String source;
    static String target;

    static int n;

    public long getMinCost(int from){

        if ( from == n){
            return 0;
        }

        if ( from > n ){
            return Long.MAX_VALUE;
        }

        /*
            If source substring matches with target substring, no conversion is needed
         */
        int i ;
        for (i = from ; i < n && source.charAt(i) == target.charAt(i); i++);
        // i would point to first mismatched character

        boolean isPossible = i > from;
        long ansFromMatched = Long.MAX_VALUE;
        if (isPossible) {
             ansFromMatched = Math.min(getMinCost(i), ansFromMatched);
        }
        long ansFromConversion = Long.MAX_VALUE;

        for (Map.Entry<Integer, List<Map.Entry<String, Integer>>> entry : root.findAll(from).entrySet()) {
            int length = entry.getKey();
            List<Map.Entry<String, Integer>> changedToCost = entry.getValue();
            for ( Map.Entry<String, Integer> changedCostEntry : changedToCost){
                String changedValue = changedCostEntry.getKey();
                int p = 0;
                int q = from;
                while(p < length && q < n && changedValue.charAt(p) == target.charAt(q)){
                    p++;
                    q++;
                }

                if ( p == length){
                    // it means we can substitute this string
                    long ansFromRest = getMinCost(q);
                    if ( ansFromRest != Long.MAX_VALUE){
                        // then its not posible
                        ansFromConversion = Math.min(ansFromConversion, ansFromRest + changedCostEntry.getValue());
                    }
                }
            }
        }
        return Math.min(ansFromMatched, ansFromConversion);
    }

    public long minimumCost(String source, String target, String[] original, String[] changed, int[] cost) {
        root = new Trie();
        for (int i = 0 ; i < original.length; i++){
            root.insert(original[i], cost[i], changed[i]);
        }

        this.source = source;
        this.target = target;
        this.n = source.length();
        long ans = getMinCost(0);
        return ans == Long.MAX_VALUE ? -1 : ans;
    }

    public static void main(String[] args) {
//        {
//            String [] original = {"bcd","defgh"};
//            String [] changed = {"ddd","ddddd"};
//            int [] cost = new int[] {100, 200};
//            System.out.println(new P2977().minimumCost("abcdefgh", "addddddd", original, changed, cost));
//        }

        {
            String [] original = {"bcd","fgh","thh"};
            String [] changed = {"cde","thh","ghh"};
            int [] cost = new int[] {1,3,5};
            System.out.println(new P2977().minimumCost("abcdefgh", "acdeeghh", original, changed, cost));

            /*
                "a"
                "bcd" -> "cde" -> 1
                "e"
                "fgh" -> "thh" -> 3
             */
        }

    }
}
