package com.coding.google;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class P2641 {

      public static class TreeNode {
          int val;
          TreeNode left;
          TreeNode right;
          TreeNode() {}
          TreeNode(int val) { this.val = val; }
          TreeNode(int val, TreeNode left, TreeNode right) {
              this.val = val;
              this.left = left;
              this.right = right;
          }
      }



    public static TreeNode replaceValueInTree(TreeNode root) {

        Deque<TreeNode> dq = new LinkedList<>();
        dq.add(root);
        dq.add(null); // to check the end of row
        int sum = 0, currentSum = 0;
        Map<TreeNode, Integer> brotherSum = new HashMap<>();
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        root.val = 0;

        while(!dq.isEmpty()){
            sum = 0;
            while(!dq.isEmpty()){
                TreeNode currentNode = dq.pollFirst();
                if( currentNode == null){
                    break;
                }
                currentNode.val = currentSum - brotherSum.getOrDefault(parent.get(currentNode), 0);
                if(currentNode.left != null) {
                    dq.addLast(currentNode.left);
                    sum += currentNode.left.val;
                    brotherSum.put(currentNode, brotherSum.getOrDefault(currentNode, 0) + currentNode.left.val);
                    parent.put(currentNode.left, currentNode);
                }
                if( currentNode.right != null){
                    dq.addLast(currentNode.right);
                    sum += currentNode.right.val;
                    brotherSum.put(currentNode, brotherSum.getOrDefault(currentNode, 0) + currentNode.right.val);
                    parent.put(currentNode.right, currentNode);
                }
            }
            if(!dq.isEmpty()){
                dq.addLast(null);
            }
            currentSum = sum;
        }
        return root;
    }


    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode();
        treeNode.val = 5;
        treeNode.left = new TreeNode();
        treeNode.left.val = 4;
        treeNode.right = new TreeNode();
        treeNode.right.val = 9;

        treeNode.left.left = new TreeNode();
        treeNode.left.left.val = 1;
        treeNode.left.right = new TreeNode();
        treeNode.left.right.val = 10;

        treeNode.right.right = new TreeNode();
        treeNode.right.right.val = 7;

        System.out.println(String.format("Tree value : %s ", replaceValueInTree(treeNode)));


    }
}
