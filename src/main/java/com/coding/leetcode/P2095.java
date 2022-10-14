package com.coding.leetcode;

import com.coding.help.Utils;
import com.coding.tree.ListNode;
import jdk.jshell.execution.Util;

import java.util.List;

public class P2095 {

    public ListNode deleteMiddle(ListNode head) {
        if( head.next == null){
            return null;
        }

        if(head.next.next == null){
            head.next = null;
            return head;
        }

        ListNode slow = head;
        ListNode fast = head.next ;

        while(fast != null ){
            if( fast.next == null || fast.next.next == null){
                slow.next = slow.next.next; // removing the middle node
                break;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        return head;
    }

    public static void main(String[] args) {
        Integer [] arr = new Integer [] {1,3,4,5,7};
        ListNode head = ListNode.from(arr);
        ListNode deletedMiddle = new P2095().deleteMiddle(head);
        Integer [] result = ListNode.to(deletedMiddle);
        Utils.printArray(result);
    }
}
