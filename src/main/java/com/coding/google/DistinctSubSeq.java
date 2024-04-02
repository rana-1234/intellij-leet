package com.coding.google;

public class DistinctSubSeq {
    // https://leetcode.com/problems/distinct-subsequences/

    String str;
    String pat;

    int cache[][];

    public int getDistinctSubSequence(int strStart, int patStart) {

        /*
            str[i....j]
            pat[i....j]
         */

        // Base case

        if( cache[strStart][patStart] != -1 ){
            return cache[strStart][patStart];
        }

        if (patStart == pat.length()){
            // We hav a subsequence
            return 1;
        }

        if ( strStart == str.length()){
            return 0;
        }

        int matchResult = 0;
        int nonMatchResult = 0;
        if ( str.charAt(strStart) == pat.charAt(patStart)) {
            // It means we have a character match here, we can match rest of the string

            matchResult = getDistinctSubSequence(strStart + 1, patStart + 1) + getDistinctSubSequence(strStart + 1, patStart);
        }
        else {
            nonMatchResult = getDistinctSubSequence(strStart + 1, patStart);
        }

        return cache[strStart][patStart] = matchResult + nonMatchResult;
    }

    public int numDistinct(String s, String t) {
        str = s;
        pat = t;
        cache = new int[s.length() + 1][t.length() + 1];
        for(int i = 0 ; i < cache.length; i++){
            for(int j = 0 ; j < cache[0].length; j++)
                cache[i][j] = -1;
        }

        return getDistinctSubSequence(0,0);

    }

    public static void main(String[] args) {
        System.out.println(new DistinctSubSeq().numDistinct("babgbag", "bag"));
    }
}
