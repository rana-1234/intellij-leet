package com.coding.tree;

import java.util.*;

public class P212 {

    public static class Trie{
        Trie [] children;
        boolean isWord;
        String word;
        int index;

        public Trie(){
            children = new Trie[26];
            isWord = false;
            index = -1;
        }

        public void insert(String word, int index){
            Trie root = this;
            for(int i = 0 ; i < word.length(); i++){
                if( root.children[word.charAt(i) - 'a'] == null ){
                    root.children[word.charAt(i) - 'a'] = new Trie();
                }
                root = root.children[word.charAt(i) - 'a'];
            }
            root.isWord = true;
            root.word = word;
            root.index = index;
        }

    }

    char [][] board;
    boolean [][] visited;
    boolean [] found;
    int n, m;

    public List<String> findWords(char[][] board, String[] words) {
        Trie root = new Trie();
        for(int i = 0 ; i < words.length; i++ ){
            root.insert(words[i], i );
        }
        this.board = board;
        n = board.length;
        m = board[0].length;
        visited = new boolean[n][m];
        found = new boolean[words.length];

        for(int i = 0 ; i < n ; i++ ){
            for(int j = 0 ; j < m ; j++ ){
                Arrays.stream(visited).forEach(x -> Arrays.fill(x, false));
                traverseAndAdd(root, i, j);
            }
        }

        List<String> ans = new ArrayList<>();
        for(int i = 0 ; i < words.length; i++){
            if ( found[i] == true){
                ans.add(words[i]);
            }
        }

        return ans;

    }

    private void traverseAndAdd(Trie root, int i, int j) {

        if( root == null){
            return ;
        }

        if( i < 0 || j < 0 || i >= m || j >= n || visited[i][j]){
            return ;
        }

        visited[i][j] = true;
        if ( root.children[board[i][j] - 'a'] != null){
            // try all possible values now
            traverseAndAdd(root.children[board[i][j] - 'a'], i + 1, j);
            traverseAndAdd(root.children[board[i][j] - 'a'], i - 1, j);
            traverseAndAdd(root.children[board[i][j] - 'a'], i, j+1);
            traverseAndAdd(root.children[board[i][j] - 'a'], i, j-1);

        }

        if( root.isWord ){
            found[root.index] = true;
        }
    }

    public static void main(String[] args) {
        char [][] board = new char[][]
                {
                        {'m','b','c','d','e','f','g','h','i','j','k','l'},
                        {'n','a','a','a','a','a','a','a','a','a','a','a'},
                        {'o','a','a','a','a','a','a','a','a','a','a','a'},
                        {'p','a','a','a','a','a','a','a','a','a','a','a'},
                        {'q','a','a','a','a','a','a','a','a','a','a','a'},
                        {'r','a','a','a','a','a','a','a','a','a','a','a'},
                        {'s','a','a','a','a','a','a','a','a','a','a','a'},
                        {'t','a','a','a','a','a','a','a','a','a','a','a'},
                        {'u','a','a','a','a','a','a','a','a','a','a','a'},
                        {'v','a','a','a','a','a','a','a','a','a','a','a'},
                        {'w','a','a','a','a','a','a','a','a','a','a','a'},
                        {'x','y','z','a','a','a','a','a','a','a','a','a'}};

        List<String> ans = new P212().findWords(board, new String[] { "aaaaaaaarq", "aaaaaaaaas", "aaaaaaaaars"});
        System.out.println(ans);
    }
}
