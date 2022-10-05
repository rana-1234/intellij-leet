package com.coding.tree;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class TreeNode {
      public int val;
      public TreeNode left;
      public TreeNode right;
      public TreeNode() {}
      public TreeNode(int val) { this.val = val; }
      public TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }

      public static @Nullable TreeNode from(@NotNull Integer [] array){
          int n = array.length;
          if(n == 0){
              return null;
          }

          TreeNode root = new TreeNode(array[0]);
          Deque<TreeNode> deque = new LinkedList<>();
          deque.addLast(root);
          for(int i = 1 ; i < n && !deque.isEmpty(); i++){
              TreeNode cr = deque.pollFirst();
              if(array[i] != null){
                  TreeNode crLeft = new TreeNode(array[i]);
                  cr.left = crLeft;
                  deque.addLast(crLeft);
              }
              i++;
              if( i < n ){
                if(array[i] != null){
                    TreeNode crRight = new TreeNode(array[i]);
                    cr.right = crRight;
                    deque.addLast(crRight);
                }
              }
          }
          return root;
      }

      public static Integer [] toArray(TreeNode treeNode){
          if(treeNode == null){
              return new Integer[0];
          }
          List<Integer> ans = new ArrayList<>();
          Deque<TreeNode> deque = new LinkedList<>();
          deque.addLast(treeNode);

          while (!deque.isEmpty()){
              TreeNode cr = deque.pollFirst();
              if(cr != null){
                  ans.add(cr.val);
                  deque.addLast(cr.left);
                  deque.addLast(cr.right);
              }
              else{
                  ans.add(null);
              }
          }
          return ans.toArray(Integer[]::new);
      }
  }

