package com.coding.other;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TimeMap {
    private Map<String, TreeMap<Integer, String>> m;
    public TimeMap() {
        m = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        TreeMap<Integer,String> mp = m.get(key);
        if(mp == null){
            mp = new TreeMap<>();
            m.put(key, mp);
        }
        mp.put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        TreeMap<Integer, String> mp = m.get(key);
        String ans = "";
        if( mp != null){
            Map.Entry<Integer, String> entry = mp.floorEntry(timestamp);
            if( entry != null) {
                ans = entry.getValue();
            }
        }
        return ans;
    }
}