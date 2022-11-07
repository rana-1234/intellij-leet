package com.coding.leetcode;

import com.coding.help.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class P212 {

    private char [][] board;
    private boolean [][] used;
    private int n , m ;
    private String check ;


    private boolean wordPresent(int i, int j, int index){

        if( i >= n || j >= m || i < 0 || j < 0 ){
            return false;
        }

        if(used[i][j] == true ){
            return false;
        }
        if(check.charAt(index) != board[i][j]){
            return false;
        }
        used[i][j] = true;
        if( index == check.length() - 1){
            return true;
        }

        boolean x = wordPresent(i+1, j, index+1) || wordPresent(i-1, j , index+1) || wordPresent(i,j+1, index+1) || wordPresent(i, j-1, index+1);
        used[i][j] = false;
        return x;
    }


    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        List<String> ans = new ArrayList<>();
        n = board.length;
        m = board[0].length;
        used = new boolean[n][m];
        Map<Character, List<Map.Entry<Integer, Integer>>> startPosition = new HashMap<>();

        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                List<Map.Entry<Integer,Integer>> res = startPosition.getOrDefault(board[i][j], new ArrayList<>());
                res.add(Map.entry(i,j));
                startPosition.put(board[i][j], res);
            }
        }

        for(String word : words){
            check = word;
            boolean isPresent = false;
            for(Map.Entry<Integer,Integer> start : startPosition.getOrDefault(word.charAt(0), new ArrayList<>())){
                used = new boolean[n][m];
                isPresent = wordPresent(start.getKey(), start.getValue(), 0);
                if( isPresent){
                    ans.add(word);
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        char [][] board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String [] words = {"oath","pea","eat","rain"};

        char [][] board1 = {{'a','b','c'},{'a','e','d'},{'a','f','g'}};
        String [] words1 = {"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"};

        List<String> ans = new P212().findWords(board1, words1);

        Utils.printArray(ans.toArray());
    }
}
