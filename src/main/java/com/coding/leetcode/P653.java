package com.coding.leetcode;

import com.coding.help.Utils;
import com.coding.tree.TreeNode;

import java.util.HashSet;
import java.util.Set;

public class P653 {

    public static Set<Integer> required = new HashSet<>();
    public boolean findTarget(TreeNode root, int k) {
        if( root == null){
            return false;
        }

        if ( required.contains(root.val)){
            return true;
        }
        required.add(k-root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

    public static void main(String[] args) {
        Integer [] bst = {5,3,6,2,4,null,7};
        TreeNode treeNode = TreeNode.from(bst);
        int target = 28;
        System.out.println(new P653().findTarget(treeNode, target));
    }
}
