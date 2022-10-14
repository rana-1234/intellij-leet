package com.coding.leetcode;

import com.coding.help.Utils;
import com.coding.tree.ListNode;

public class P237 {

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    public static void main(String[] args) {
        ListNode val = ListNode.from(new Integer [] {1,2,3,4});
        new P237().deleteNode(val);
        Integer [] list = ListNode.to(val);
        Utils.printArray(list);
    }
}
