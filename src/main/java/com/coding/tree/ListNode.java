package com.coding.tree;

import java.util.ArrayList;
import java.util.List;

public class ListNode {
      public int val;
      public ListNode next;
      public ListNode() {}
      public ListNode(int val) { this.val = val; }
      public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static ListNode from(Integer[] arr) {
          if (arr.length == 0){
              return null;
          }
          ListNode head = new ListNode(arr[0]);
          ListNode temp = head;
          for(int i = 1 ; i < arr.length ; i++){
              temp.next = new ListNode(arr[i]);
              temp = temp.next;
          }
          return head;
    }

    public static Integer[] to(ListNode val) {
        List<Integer> ans = new ArrayList<>();

        while(val != null){
            ans.add(val.val);
            val = val.next;
        }

        return ans.toArray(Integer []::new);
    }
}
