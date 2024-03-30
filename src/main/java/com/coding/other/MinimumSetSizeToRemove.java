package com.coding.other;

import java.util.*;

public class MinimumSetSizeToRemove {
    public static int minSetSize(int[] arr) {
        // Count the frequency of each integer
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : arr) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Sort frequencies in descending order
        List<Integer> frequencies = new ArrayList<>(frequencyMap.values());
        Collections.sort(frequencies, Collections.reverseOrder());

        // Remove integers until at least half of the integers are removed
        int setSize = 0;
        int removedCount = 0;
        int totalIntegers = arr.length;
        for (int frequency : frequencies) {
            removedCount += frequency;
            setSize++;
            if (removedCount >= totalIntegers / 2) {
                break;
            }
        }

        return setSize;
    }

    public static void main(String[] args) {
        int[] arr = {3, 3, 3, 3, 5, 5, 5, 2, 2, 7}; // Example array
        System.out.println("Minimum size of set to remove: " + minSetSize(arr));
    }
}
