package com.coding.leetcode;

import com.coding.help.Utils;

import java.util.*;

public class P692 {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String,Integer> m = new HashMap<>();

        for(String word : words){
            m.put(word, m.getOrDefault(word,0) + 1);
        }
        TreeMap<Integer, TreeSet<String>> treeMap = new TreeMap<>(Collections.reverseOrder());

        for(Map.Entry<String,Integer> entry : m.entrySet()){
            TreeSet<String> wordSet = treeMap.getOrDefault(entry.getValue(), new TreeSet<>());
            wordSet.add(entry.getKey());
            treeMap.put(entry.getValue(), wordSet);
        }

        int i = 1;
        List<String> ans = new ArrayList<>();
        for(Set<String> wordSet : treeMap.values()){
            if( i > k){
                break;
            }
            for(String word : wordSet) {
                if( i > k )
                    break;
                ans.add(word);
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<String> ansList = new P692().topKFrequent(new String [] {"the","day","is","sunny","the","the","the","sunny","is","is"}, 4);
        Utils.printArray(ansList.toArray());

    }
}
