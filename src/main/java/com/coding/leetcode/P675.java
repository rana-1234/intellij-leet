package com.coding.leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class P675 {

    int n;
    List<List<Integer>> forest;
    Map.Entry<Integer, Map.Entry<Integer, Integer>> [] getDirections(int i, int j){
        Map.Entry<Integer, Map.Entry<Integer, Integer>> [] returns = new Map.Entry[4];
        int m = forest.get(i).size();
        int index = 0;
        if (i+1 < n){
            returns[index++] = Map.entry(forest.get(i+1).get(j), Map.entry(i+1, j));
        }
        if (j+1 < m){
            returns[index++] = Map.entry(forest.get(i).get(j+1), Map.entry(i, j+1));
        }
        if ( i-1 >= 0){
            returns[index++] = Map.entry(forest.get(i-1).get(j), Map.entry(i-1, j));
        }
        if (j-1 >= 0){
            returns[index++] = Map.entry(forest.get(i).get(j-1), Map.entry(i, j-1));
        }
        while(index < 4){
            returns[index++] = Map.entry(-1, Map.entry(-1,-1));
        }
        return returns;
    }

    public int dfs(int startI, int startJ){

        int currentHeight = forest.get(startI).get(startJ);
        int ans = currentHeight > 1 ? 1 : 0;
        List<Integer> row = forest.get(startI);
        row.set(startJ, -1);// Currently exploring
        Map.Entry<Integer, Map.Entry<Integer, Integer>> [] directions = getDirections(startI, startJ);
        int totalCountFromChild = 0;
        for ( int i = 0 ; i < 4 ; i++){
            if ( directions[i].getKey() >= 1){
                if ( directions[i].getKey() == 1){
                    // I can go to this direction
                    totalCountFromChild = Math.max(totalCountFromChild, dfs(directions[i].getValue().getKey(), directions[i].getValue().getValue()));
                }
                else if ( directions[i].getKey() > currentHeight){
                    totalCountFromChild = Math.max(totalCountFromChild, dfs(directions[i].getValue().getKey(), directions[i].getValue().getValue()));
                }
            }
        }

        ans += totalCountFromChild;
        // resetting this so that it can be explored by other path
        forest.get(startI).set(startJ, currentHeight);
        return ans;
    }

    public int cutOffTree(List<List<Integer>> forest) {
        this.forest = forest;
        n = forest.size();
        int totalNumberOfTress = 0;
        for(List<Integer> t : forest){
            for ( int tree : t){
                if ( tree > 1){
                    totalNumberOfTress++;
                }
            }
        }
        int totalTreeCut = dfs(0, 0);
        return totalTreeCut == totalNumberOfTress ? totalTreeCut : -1;
    }

    public static void main(String[] args) {
        {
           List<List<Integer>> forest = List.of(List.of(1,2,3),List.of(0,0,4),List.of(7,6,5));
            System.out.println(new P675().cutOffTree(forest));
        }
    }
}
