package com.coding.naukri;


import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution {

    /*

            5
            115 101
            44 75
            73 55
            92 119
            140 140


     */

    public static String [] result;
    public static class Trie {
        Trie [] c;
        boolean isUnique;
        int index;
        boolean isWord;
        int wordIndex;
        public Trie(){
            c = new Trie[26];
            isWord = false;
        }

        public void insert(String data, int index){
            Trie temp = this;
            for (int i = 0 ; i < data.length(); i++){
                char c = data.charAt(i);
                Trie next = temp.c[c - 'a'];
                if ( next == null){
                    next = new Trie();
                    next.isUnique = true;
                    next.index = index;
                    temp.c[c-'a'] = next;
                    temp = next;
                }
                else{
                    next.isUnique = false;
                    temp = next;
                }
            }
            temp.isWord = true;
            temp.wordIndex = index;
        }

        public void collectAllUniquePrefix(String currentPrefix){

            if (isUnique){
                result[index] = currentPrefix;
                return;
            }

            if (isWord){
                result[wordIndex] = currentPrefix;
            }

            for (int i = 0 ; i < 26 ; i++){
                if (c[i] != null){
                    c[i].collectAllUniquePrefix(currentPrefix + (char)(i + (int)'a'));
                }
            }
        }
    }

    public static String[] shortestUniquePrefix(String[] s, int n) {
        // Write your code here

        /*
            Trie use case.
            Put all the words in trie, and then traverse and then for each word traverse just populate the
            prefix
        */

        Trie t = new Trie();
        t.isUnique = false;
        for (int i = 0 ; i < n ; i++ ){
            t.insert(s[i], i);
        }

        result = new String [n];
        t.collectAllUniquePrefix("");
        return result;
    }

    public static class Reader{
        private BufferedReader bf ;
        private StringTokenizer st;

        public Reader(InputStreamReader source){
            bf = new BufferedReader(source);
            st = new StringTokenizer("");
        }

        private String read() throws Exception {
            if ( !st.hasMoreTokens()){
                st = new StringTokenizer(bf.readLine());
            }
            return st.nextToken();
        }

        private String readString() throws Exception{
            return read();
        }

        private int readInt() throws  Exception{
            return Integer.parseInt(read());
        }

        private String readLine() throws Exception{
            return bf.readLine();
        }
    }

    public static void main(String[] args) throws Exception{

        InputStreamReader source = new InputStreamReader(new FileInputStream( "/Users/shashibhushanrana/IdeaProjects/coding/src/main/java/com/coding/naukri/input.txt"));
        Reader reader = new Reader(source);

        int t = reader.readInt();
        while ( t > 0 ){
            int n = reader.readInt();
            String [] data = new String [n];
            for ( int i = 0 ; i < n ; i++){
                data[i] = reader.readString();
            }
            String [] result = shortestUniquePrefix(data, n);
            for ( String prefix : result){
                System.out.println(prefix);
            }
            t--;
        }
    }
}
