package com.coding.leetcode;

import com.coding.tree.ListNode;

public class P19 {
    public int getSize(ListNode head){
        if ( head == null)
            return 0;
        return 1 + getSize(head.next);
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        // System.out.println(head.val);
        int s = getSize(head);
        int indexFromStart = s - n;
        if ( indexFromStart == 0){
            return head.next;
        }
        // System.out.println(head.val + " n :" + n + " s : " + s );
        if ( indexFromStart < 0 ){
            return head;
        }

        ListNode temp = head;
        int i = 1;
        while(i < indexFromStart){
            temp = temp.next;
            i++;
        }

        if ( temp.next != null){
            temp.next = temp.next.next;
        }
        return head;

    }

    public static void main(String[] args) {

    }
}
