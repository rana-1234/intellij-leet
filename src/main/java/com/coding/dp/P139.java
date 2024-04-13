package com.coding.dp;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P139 {

    public static class Trie {
        public Trie[] child;
        public boolean isWord;

        public Trie() {
            child = new Trie[26]; // For 0 to 25 children
            isWord = false;
        }
    }

    public static void insertWord(Trie root, String word) {
        if (word.isEmpty() || word.isBlank()) {
            return;
        }

        for (int i = 0; i < word.length() - 1; i++) {
            int childNodeIndex = ((int) word.charAt(i)) - 97;
            if (root.child[childNodeIndex] == null) {
                root.child[childNodeIndex] = new Trie();
            }
            root = root.child[childNodeIndex];
        }
        int childNodeIndex = ((int) word.charAt(word.length() - 1)) - 97;
        if (root.child[childNodeIndex] == null) {
            root.child[childNodeIndex] = new Trie();
        }
        root.child[childNodeIndex].isWord = true;
    }
    public static boolean canBeSeparated(String line, Trie root) {
        if( line.isEmpty()){
            return true;
        }
        int i = 0;
        Trie tempRoot = root;
        boolean isPossible = false;
        while (i < line.length()) {
            int childNodeIndex = ((int) line.charAt(i)) - 97;
            if (tempRoot.child[childNodeIndex] == null) {
                return false;
            } else if (tempRoot.child[childNodeIndex].isWord) {
                //Here might be the space
                isPossible = canBeSeparated(line.substring(i + 1), root);
                if (isPossible) {
                    return true;
                }
                else{
                    tempRoot = tempRoot.child[childNodeIndex];
                }
            } else {
                tempRoot = tempRoot.child[childNodeIndex];
            }
            i++;
        }
        return false;
    }

    public boolean isPossible(String s, List<String> wordDict){
        // Fast Method, Decision tree based on the words

        if( s.isEmpty()){
            return true;
        }

        boolean [] dp = new boolean[s.length() + 1];
        dp[s.length()] = true;
        for ( int i = s.length() - 1 ; i >= 0 ; i-- ){
            for (String word : wordDict){
                if(s.substring(i, Math.min(s.length(),i+word.length())).equals(word)){
                    dp[i] = dp[i+word.length()];
                    if (dp[i]){
                        break;
                    }
                }
                else{
                    dp[i] = false;
                }
            }
        }
        return dp[0];
    }

    Set<String> dictSet = new HashSet<>();
    int [] dp ;
    String text;

    public boolean wordBreakByDPAtI(int x){

        if(dp[x] != -1 ){
            return dp[x] == 1 ? true : false;
        }

        boolean canBreak = false;
        for ( int i = x ; i < text.length()  && !canBreak ; i++){
            if (dictSet.contains(text.substring(x, i+1))){
                canBreak = wordBreakByDPAtI(i+1);
            }
        }

        dp[x] = canBreak ? 1 : 0;
        return canBreak;
    }

    public boolean wordBreakByDP(String s, List<String> wordDict){
        int n = s.length();
        text = s;
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[n] = 1; // empty string always true
        wordDict.stream().forEach(x -> dictSet.add(x));
        return wordBreakByDPAtI(0);
    }

    public boolean wordBreak(String s, List<String> wordDict) {

//         Trie root = new Trie();
//         for ( String word : wordDict){
//             insertWord(root, word);
//         }
//        return canBeSeparated(s, root);


//            Other approach, top down
//            Start matching the word from i = 0 , to i = x
//
//            if ( str[0...x] is present in word)
//                call the same function to check from (x+1, n) in the dict
//                if it returns true, return true
//                or try other possibilities



         return wordBreakByDP(s, wordDict);

        /*
            // Bottom up approach for the same function
            return this.isPossible(s,wordDict);

         */

    }

    public static void main(String[] args) {

        System.out.println(new P139().wordBreak("leetcode", List.of("leet", "code")));

    }
}
