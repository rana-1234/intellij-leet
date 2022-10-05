package com.coding.leetcode;

import com.coding.help.Utils;
import com.coding.tree.TreeNode;

public class P623 {

    private TreeNode addNodeAtDepth(TreeNode node, int val, int depth, boolean left){
        if( depth == 1){
            if(left){
                return new TreeNode(val, node, null);
            }
            else{
                return new TreeNode(val, null, node);
            }
        }

        if(node == null){
            return null;
        }

        node.left = addNodeAtDepth(node.left, val, depth-1, true);
        node.right = addNodeAtDepth(node.right, val, depth-1, false);
        return node;
    }

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        return addNodeAtDepth(root, val, depth, true);
    }

    public static void main(String[] args) {
        Integer [] tree = {4,2,null,3,1};
        int val = 2;
        int depth = 2;
        Utils.printArray(tree);
        TreeNode modified = new P623().addOneRow(TreeNode.from(tree), val, depth);
        Utils.printArray(TreeNode.toArray(modified));
    }
}
