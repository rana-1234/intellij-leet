package com.coding.leetcode;

import com.coding.other.MyCalendarThree;

public class P732 {
    public static void main(String[] args) {
        MyCalendarThree three = new MyCalendarThree();
        three.book(1,10);
        three.book(2,20);
        three.book(19,20);
        three.book(4,14);
        three.book(5,10);
        System.out.println(three.book(20,40));
    }
}
