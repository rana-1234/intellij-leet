package com.coding.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class P100077 {

    public static String [] wordList;
    public static int [] wordGroup;

    public List<String> findLongestSubSequence(int i, Set<Integer> groupTaken){
        if ( i == wordList.length){
            return new ArrayList<>(); // i reached at length
        }

        // Let's take it in the answer if possible
//        if ( )

        // Or Let's not take it in the answer
        return null;

    }

    public List<String> getWordsInLongestSubsequence(int n, String[] words, int[] groups) {
        wordList = words;
        wordGroup = groups;
        return null;

    }


    public static void main(String[] args) {

    }
}
