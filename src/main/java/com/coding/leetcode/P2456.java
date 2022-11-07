package com.coding.leetcode;

import java.util.*;

public class P2456 {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        TreeMap<String, Map<String, Long>> creator = new TreeMap<>();

        int n = creators.length;
        long maxPopularity = -1;
        List<List<String>> actors = new ArrayList<>();

        for(int i = 0 ; i < n ; i++){
            Map<String, Long> currentVideos = creator.getOrDefault(creators[i], new HashMap<>());
            currentVideos.put(ids[i], 1l*views[i]);
            creator.put(creators[i], currentVideos);
        }

        List<List<String>> ans = new ArrayList<>();

        for(Map.Entry<String, Map<String, Long>> entry : creator.entrySet()){
            long pop = -1;
            long cumPop = 0;
            TreeSet<String> popVideo = new TreeSet<>();
            for(Map.Entry<String, Long> videoPop : entry.getValue().entrySet()){
                cumPop += videoPop.getValue();
                if (pop == videoPop.getValue()){
                    popVideo.add(videoPop.getKey());
                    popVideo.remove(popVideo.last());
                }
                else if(pop < videoPop.getValue()){
                    pop = videoPop.getValue();
                    popVideo = new TreeSet<>();
                    popVideo.add(videoPop.getKey());
                }
            }
            List<String> cu = new ArrayList<>();
            cu.add(entry.getKey());
            cu.addAll(popVideo);
            if( cumPop == maxPopularity){
                ans.add(cu);
            }
            else if( cumPop > maxPopularity){
                ans = new ArrayList<>();
                ans.add(cu);
                maxPopularity = cumPop;
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new P2456().mostPopularCreator(new String[]{"alice","bob","alice","chris"}, new String[]{"one","two","three","four"}, new int[]{5,10,12,4}));
        System.out.println(new P2456().mostPopularCreator(new String[]{"a"}, new String[]{"a"}, new int[]{0}));
    }
}
