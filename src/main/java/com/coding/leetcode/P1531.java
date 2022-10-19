package com.coding.leetcode;

import java.sql.Time;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

public class P1531 {

    private int n;
    private int [] length;
    private List<Character> characterList;

    private void rle(String s){
        List<Integer> l = new ArrayList<>();
        List<Character> characters = new ArrayList<>();
        for(int i = 0 ; i < s.length(); i++){
            int count = 1;
            characters.add(s.charAt(i));
            while(i+1< s.length() && s.charAt(i) == s.charAt(i+1)){
                i++;
                count++;
            }
            l.add(count);
        }
        length = l.stream().mapToInt(x -> x).toArray();
        characterList = characters;
    }

    private int getMinCount(int ci, int k, int [] removedCount){

        if( ci == n || k == 0){
            // End of the block, all the k has been exhausted
            int ans = 0;
            for(int i = 0 ; i < n ; i++){
                int len = length[i] - removedCount[i];
                if( len == 0){
                    continue;
                }
                char currentChar = characterList.get(i);
                int j = i+1;
                while(j < n ){
                    // skipping the characters which are fully omitted
                    if(length[j] - removedCount[j] == 0) {
                        j++;
                    }
                    else if( characterList.get(j) == currentChar){
                        len += length[j] - removedCount[j];
                        j++;
                    }
                    else{
                        break;
                    }
                }
                i = j-1;
                ans += len == 1 ? 1 :  1 + Integer.valueOf(len).toString().length();
            }
            return ans;
        }

        // Will it make sense to few characters from it, keeping some k left to be removed from other blocks ?
        // I think no, either we should exhaust all k here or remove this entire block itself, or just keep don't remove this block.
        // a10 -> if we remove one character, it will become a9
        // b10 -> removing one character, it will become b9
        // b100 -> b99, So we can remove either entire block or few characters in it such that length get's reduced, or don't remove at all.

        int cntByRemovingThisEntireBlock = Integer.MAX_VALUE;
        // we can remove entire block only
        if(k >= length[ci]){
            // then it make sense
            removedCount[ci] = length[ci];
            cntByRemovingThisEntireBlock = getMinCount(ci+1, k-length[ci], removedCount);
        }

        // or just keep one charcter only because a1 is not allowed, it would be a
        if(length[ci] > 1 && k >= length[ci] - 1){
            removedCount[ci] = length[ci] - 1;
            cntByRemovingThisEntireBlock = Math.min(cntByRemovingThisEntireBlock, getMinCount(ci+1, k-(length[ci] - 1), removedCount));
        }
        // case like b58, b99, etc.
        if(length[ci] > 9 &&  k >= length[ci] - 9){
            removedCount[ci] = length[ci] - 9;
            cntByRemovingThisEntireBlock = Math.min(cntByRemovingThisEntireBlock, getMinCount(ci+1, k-(length[ci] - 9), removedCount));
        }

        // case like b100 (Or 3 digit repetition
        if(length[ci] > 99 && k >= length[ci] - 99){
            removedCount[ci] = length[ci] - 99;
            cntByRemovingThisEntireBlock = Math.min(cntByRemovingThisEntireBlock, getMinCount(ci+1, k-(length[ci] - 99), removedCount));
        }
        removedCount[ci] = 0; // we don't remove any character
        int cntByKeepingThisEntireBlock = getMinCount(ci+1, k, removedCount);
        return Math.min(cntByKeepingThisEntireBlock, cntByRemovingThisEntireBlock);
    }

    public int getLengthOfOptimalCompression(String s, int k) {
        // Minimum length,

        /*
            We can remove at most k characters.
            Question is to which characters should I remove so that resulting string RLE has minimum length
            Another better approach would be to remove characters from block (continuous character )
         */
        rle(s);
        this.n = length.length;
        return getMinCount(0, k, new int[this.n]);
    }
    public static void main(String[] args) {
        Instant start = Instant.now();
        System.out.println(new P1531().getLengthOfOptimalCompression("abcdefghijklmnopqrstuvwxyz", 16));
        System.out.println(Instant.now().getEpochSecond() - start.getEpochSecond());
//        System.out.println(new P1531().getLengthOfOptimalCompression("aabbaa", 2));
//        System.out.println(new P1531().getLengthOfOptimalCompression("aaabcccd", 2));
//        System.out.println(new P1531().getLengthOfOptimalCompression("shashibbhshasmnaaaa", 10));
//        System.out.println(new P1531().getLengthOfOptimalCompression("challlasasasaaaaashahhhhasaaa", 12));
    }
}
