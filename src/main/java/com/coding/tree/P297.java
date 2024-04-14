package com.coding.tree;

import java.util.Deque;
import java.util.LinkedList;

public class P297 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null){
            return "";
        }

        StringBuilder sb = new StringBuilder();
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(root);

        TreeNode dummyNode = new TreeNode(-1);
        q.addLast(dummyNode);

        while (!q.isEmpty()){

            // Pop the data
            TreeNode node = q.pollFirst();
            if( node == null){
                sb.append("|");
            }
            else if ( node == dummyNode){
                if (!q.isEmpty()){
                    q.addLast(node);
                }
            }
            else{
                sb.append(node.val + "|");
                q.addLast(node.left);
                q.addLast(node.right);
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if( data == ""){
            return null;
        }
        String [] allNodeValues = data.split("\\|");
        int i = 0 ;
        int n = allNodeValues.length;
        TreeNode node = new TreeNode(Integer.parseInt(allNodeValues[i]));
        Deque<TreeNode> q = new LinkedList<>();
        q.addLast(node);
        while ( i < n ){
            TreeNode current = q.pollFirst();
            if ( current == null){
                continue;
            }
            if ( i+1 < n ){
                TreeNode left = null;
                if (allNodeValues[i+1] != ""){
                    left = new TreeNode(Integer.parseInt(allNodeValues[i+1]));
                }
                current.left = left;
                q.addLast(left);
            }
            if (i+2 < n ){
                TreeNode right = null;
                if (allNodeValues[i+2] != ""){
                    right = new TreeNode(Integer.parseInt(allNodeValues[i+2]));
                }
                current.right = right;
                q.addLast(right);
            }
            i += 2;
        }
        return node;
    }

    public static void main(String[] args) {

        P297 p297 = new P297();
        TreeNode root = TreeNode.from(new Integer[] {1, 2, 3, 4,null, 5, 6, 7, null, 8, null, 9, 10});
        String ans = p297.serialize(root);
        System.out.println("Serialized data : " + ans);
        TreeNode reRoot = p297.deserialize(ans);

        System.out.println();

    }
}
