package com.coding.leetcode.tree;

import com.coding.help.Utils;
import com.coding.tree.TreeNode;
import com.sun.source.tree.Tree;

import java.util.*;

public class P1932 {

    Map<Integer, TreeNode> rootNodes;
    void performPossibleMerge(TreeNode root){

        if ( root == null){
            return;
        }
        if (root.left == null){
            // we can add child in left subtree
            if (rootNodes.containsKey(root.val)) {
                TreeNode mergingNode = rootNodes.get(root.val);
                rootNodes.remove(root.val);
                root.left = mergingNode.left;
                if ( root.right == null){
                    root.right = mergingNode.right;
                }
            }
        }
        if (root.right == null) {
            // we can add child in right subtree
            if (rootNodes.containsKey(root.val)){
                TreeNode mergingNode = rootNodes.get(root.val);
                rootNodes.remove(root.val);
                root.right = mergingNode.right;
                if ( root.left == null){
                    root.left = mergingNode.left;
                }
            }
        }
        performPossibleMerge(root.left);
        performPossibleMerge(root.right);
    }
    public boolean ifBst(TreeNode root, int leftR, int rightR){
        if( root == null)
            return true;

        if( root.val < rightR && root.val > leftR)
            return ifBst(root.left, leftR, root.val) && ifBst(root.right, root.val, rightR);

        return false;
    }
    public TreeNode canMerge(List<TreeNode> trees){
        rootNodes = new HashMap<>();
        for(TreeNode root : trees){
            rootNodes.put(root.val, root);
        }

        for (TreeNode root : trees){
            if (rootNodes.containsKey(root.val)){
                rootNodes.remove(root.val);
                performPossibleMerge(root);
                rootNodes.put(root.val, root);
            }
        }

        if (rootNodes.size() == 1 && ifBst(rootNodes.entrySet().iterator().next().getValue(), Integer.MIN_VALUE, Integer.MAX_VALUE)){
            return rootNodes.entrySet().iterator().next().getValue();
        }
        else{
            return null;
        }
    }

    public static void main(String[] args) {

        {
            P1932 solution = new P1932();

            TreeNode root1 = new TreeNode(3);
            root1.left = new TreeNode(2);
            root1.right = new TreeNode(6);

            TreeNode root2 = new TreeNode(5);
            root2.left = new TreeNode(3);
            root2.right = new TreeNode(8);

            Utils.printArray(TreeNode.toArray(solution.canMerge(List.of(root1, root2))));
        }


        {
            P1932 solution = new P1932();

            TreeNode root1 = new TreeNode(2);
            TreeNode root2 = new TreeNode(1);
            root2.right = new TreeNode(2);
            Utils.printArray(TreeNode.toArray(solution.canMerge(List.of(root1, root2))));
        }
    }
}
