package com.coding.tree;

import java.util.Arrays;

public class P208 {

    public static class Trie {

        Trie [] children;
        int wordCount;

        public Trie() {
            children = new Trie[26];
            Arrays.fill(children,null);
            wordCount = 0;
        }

        public void insert(String word) {
            Trie root = this;
            int i = 0;
            for(i = 0 ; i < word.length(); i++){
               if (root.children[word.charAt(i) - 'a'] == null){
                   root.children[word.charAt(i) - 'a'] = new Trie();
               }
               root = root.children[word.charAt(i) - 'a'];
            }
            root.wordCount += 1;
        }

        public boolean search(String word) {
            Trie root = this;
            int i = 0;
            for(i = 0 ; i < word.length(); i++){
                if (root.children[word.charAt(i) - 'a'] == null){
                    return false;
                }
                root = root.children[word.charAt(i) - 'a'];
            }
            return root.wordCount > 0;
        }

        public boolean startsWith(String prefix) {
            Trie root = this;
            int i = 0;
            for(i = 0 ; i < prefix.length(); i++){
                if (root.children[prefix.charAt(i) - 'a'] == null){
                    return false;
                }
                root = root.children[prefix.charAt(i) - 'a'];
            }
            return true;
        }
    }

    public static void main(String[] args) {

    }
}
