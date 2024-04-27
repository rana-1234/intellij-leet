package com.coding.google;

import com.coding.tree.ListNode;

import java.util.*;
import java.util.stream.Collectors;

public class P382 {

    public static class Solution {

        ListNode head;

        public Solution(ListNode head) {
           this.head = head;
        }

        public int getRandom() {
            ListNode temp = head;
            int i = 1;
            while(temp != null){
                if( Math.random()%i == 0){
                    break;
                }
                temp = temp.next;
            }
            return head.val;
        }
    }
}
