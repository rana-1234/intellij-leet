package com.coding.leetcode;

import com.coding.other.TimeMap;

public class P981 {
    public static void main(String[] args) {
        TimeMap tm = new TimeMap();
        tm.set("foo","bar",1);
        System.out.println(tm.get("foo",1));
        System.out.println(tm.get("foo",3));
    }
}
