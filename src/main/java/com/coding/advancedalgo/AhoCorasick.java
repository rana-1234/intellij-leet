package com.coding.advancedalgo;

import java.util.Deque;
import java.util.LinkedList;

public class AhoCorasick {

    // Thia algorithm is used for the pattern matching efficiently
    // https://cp-algorithms.com/string/aho_corasick.html
    // Its like trie only with one additional information in node, that if the next character is not present as a child
    // of this node, then use the failureLink to start matching.
    // The failureLink points to the node, which is the longest suffix node
    // Meaning, from root, if you are at node "abcde" and after e, there is no node to travel, then there would be failure link
    // which would point to the longest prefix node, in this case ["bcde" or "cde", or "de" or "e" or "" (root)]

    public static class Trie{
        Trie [] c;
        Trie failureLink;
        boolean isWord;

        public Trie(){
            c = new Trie[26];
            failureLink = null;
            isWord = false;
        }

        public void insert(String word){
            Trie temp = this;
            for ( char a : word.toCharArray()){
                if ( temp.c[a - 'a'] == null){
                    temp.c[a-'a'] = new Trie();
                }
                temp = temp.c[a-'a'];
            }
            temp.isWord = true;
        }

        public void buildFailureLinks(){
            Trie temp = this; // root not would have no failure link
            Deque<Trie> deque = new LinkedList<>();
            for ( int i = 0 ; i < 26; i++ ){
                if ( temp.c[i] != null){
                    // The immediate child failure link, would point to root only
                    temp.c[i].failureLink = this;
                    deque.addLast(temp.c[i]);
                }
            }

            // Doing BFS to find the failure link for all nodes

            while(!deque.isEmpty()) {
                Trie current = deque.pollFirst();
                // Process all of its child
                for ( int i = 0 ; i < 26; i++ ) {
                    if ( current.c[i] != null){
                        // Processing this child of the currentNode;
                        // So this is coming as abcd - (e) -> e, so we need to go to bcd
                        Trie fallback = current.failureLink;
                        // The best would be go to the failureLink of parent and check if there is any child of that node
                        // which is same as this node value.
                        // E.g. abc -> Let say we get the c failureLink pointing to a node bc.
                        // Now, if there is d, next, and we need to calculate the failureLink of d node here, we would simply
                        // go to its parent which would point to bc, and then check if there is a child d or not, if yes, then
                        // that would be the failureLink,
                        // But if not, then we would go bc (c's) failureLink, and find it, if there is any child with d,
                        while(fallback != null && fallback.c[i] == null){
                            fallback = fallback.failureLink;
                            // So, now pointing to the failureLink of the next node.
                        }
                        current.c[i].failureLink = fallback == null ? this : fallback.c[i]; // if fallback is null, then
                        // fallback is one of the nodes in Trie
                        deque.addLast(current.c[i]); // Add this child for processing
                    }
                }
            }
        }
    }

    public static void findAllWordsInWord(String [] wordList, String word){
        Trie root = new Trie();
        for ( String w : wordList ){
            root.insert(w);
        }
        root.buildFailureLinks();
        Trie current = root;
        int i = 0;
        for ( char c : word.toCharArray()){

            // Check if next transition is not available then follow the failure link
            // Basically we are avoiding the match from root,
            while(current != null && current.c[c-'a'] == null ){
                current = current.failureLink;
            }
            current = current == null ? root : current.c[c-'a']; // Go to the next child
            if ( current.isWord ){
                System.out.println("Found a match at index " + i + " with char " + c);
            }
            i++;
        }
    }


    public static void main(String[] args) {

        {
            String [] allWords = new String[] {"shas", "shashi", "bhu", "shan", "rana", "neha"};
            String word = "helloshashibhushanranaareyouthere";
            findAllWordsInWord(allWords, word);
        }
    }
}
