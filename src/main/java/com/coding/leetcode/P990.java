package com.coding.leetcode;

import java.util.Arrays;

public class P990 {

    public int [] parent ;

    public int fetchParent(int node){
        if( parent[node] == -1){
            return node;
        }
        return parent[node] = fetchParent(parent[node]); // flatten the set as well
    }

    public boolean equationsPossible(String[] equations) {
        parent = new int[26]; // 'a' to 'z'
        Arrays.fill(parent, -1);

        final String notEqual = "!";

        for(String eq : equations){
            if(!eq.contains(notEqual)){
                // Equal to equation

                int first = eq.charAt(0) - 'a';
                int second = eq.charAt(3) - 'a';

                int firstParent =fetchParent(first);
                int secondParent = fetchParent(second);

                if( firstParent != secondParent){
                    parent[firstParent] = secondParent; // make parent of first to second
                }
            }
        }

        // Now check for unequals
        for(String eq: equations){
            if(eq.contains(notEqual)){
                int first = eq.charAt(0) - 'a';
                int second = eq.charAt(3) - 'a';

                int firstParent = fetchParent(first);
                int secondParent = fetchParent(second);

                if(firstParent == secondParent){
                    // both are not equal, but they belong to same group, and hence its not possible
                    return false;
                }
            }
        }
        return  true;
    }

    public static void main(String[] args) {
        String [] array = {"e==d","e==a","f!=d","b!=c","a==b"};
        System.out.println(new P990().equationsPossible(array));
    }
}
