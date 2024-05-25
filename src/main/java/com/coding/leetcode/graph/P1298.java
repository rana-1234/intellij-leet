package com.coding.leetcode.graph;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class P1298 {

    int [] status;
    int [] candies;
    boolean [] boxUsed;
    int [][] keys;
    int [][] containedBoxes;
    Set<Integer> keyFound;
    public int getMaxCandies(Set<Integer> availableOpenedBoxes, Set<Integer> availableUnOpenedBoxes) {
        if( availableOpenedBoxes.isEmpty()){
            // use the keys to open the availableUnOpenedBoxes
            for ( int key : keyFound){
                if (availableUnOpenedBoxes.contains(key)){
                    if (!boxUsed[key]){
                        availableOpenedBoxes.add(key);
                        availableUnOpenedBoxes.remove(key);
                    }
                }
            }

            // remove all the key used
            if ( availableOpenedBoxes.isEmpty()){
                return 0;
            }

            for (int boxKey : availableUnOpenedBoxes){
                // remove all the keys used to open box
                keyFound.remove(boxKey);
            }
            return getMaxCandies(availableOpenedBoxes, availableUnOpenedBoxes);
        }

        int box = availableOpenedBoxes.iterator().next();
        boxUsed[box] = true;
        int candyCount = candies[box];

        // collect all the boxes
        for (int containedBox : containedBoxes[box]){
            // Added all the box
            if (!boxUsed[containedBox]){
                if ( status[containedBox] == 1){
                    availableOpenedBoxes.add(containedBox);
                }
                else{
                    availableUnOpenedBoxes.add(containedBox);
                }
            }
        }

        // collect all the keys
        for ( int key : keys[box]){
            keyFound.add(key);
        }
        availableOpenedBoxes.remove(box);
        return candyCount + getMaxCandies(availableOpenedBoxes, availableUnOpenedBoxes);
    }
    public int maxCandies(int[] status, int[] candies, int[][] keys, int[][] containedBoxes, int[] initialBoxes) {
        this.status = status;
        this.candies = candies;
        this.containedBoxes = containedBoxes;
        this.keys = keys;
        boxUsed = new boolean[status.length];
        Arrays.fill(boxUsed, false);
        keyFound = new HashSet<>();

        Set<Integer> availableOpenedBoxesSet = new HashSet<>();
        Set<Integer> availableUnOpenedBoxesSet = new HashSet<>();

        for(int box : initialBoxes){
            if ( status[box] == 1){
                availableOpenedBoxesSet.add(box);
            }
            else{
                availableUnOpenedBoxesSet.add(box);
            }
        }
        return getMaxCandies(availableOpenedBoxesSet, availableUnOpenedBoxesSet);
    }

    public static void main(String[] args) {
        {
            int [] status = {1,0,1,0};
            int [] candies = {7,5,4,100};
            int [][] keys = {{},{},{1},{}};
            int [][] containedBoxes = {{1,2},{3},{},{}};
            int [] initialBoxes = {0};
            System.out.println(new P1298().maxCandies(status, candies, keys, containedBoxes, initialBoxes));
        }
    }
}
