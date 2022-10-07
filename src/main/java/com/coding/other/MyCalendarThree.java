package com.coding.other;

import java.util.TreeMap;

public class MyCalendarThree {

    private TreeMap<Integer,Integer> booking;
    public MyCalendarThree() {
        booking = new TreeMap<>();
    }

    public int book(int start, int end) {

        // Add this in booking
        booking.put(start, booking.getOrDefault(start, 0) + 1);
        booking.put(end, booking.getOrDefault(end, 0) - 1);
        int maxKBooking = 0;
        int sum = 0;
        for ( int counter : booking.values()){
            sum += counter;
            maxKBooking = Math.max(maxKBooking, sum);
            // at the end sum will be zero, since we have equal numbers of
            // start and end time
        }
        return maxKBooking;
    }
}