package com.coding.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class P102 {

    public List<List<Integer>> levelOrder(TreeNode root) {

        if( root == null){
            return new ArrayList<>();
        }
        //BFS
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(null);
        List<Integer> line = new ArrayList<>();
        while(q.size() > 0 ){
            TreeNode curr = q.remove();
            if( curr == null){
                ans.add(line);
                if(q.size() > 0 ){
                    line = new ArrayList<>();
                    q.add(null);
                }
            }
            else{
                line.add(curr.val);
                if (curr.left != null){
                    q.add(curr.left);
                }
                if( curr.right != null ){
                    q.add(curr.right);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {

    }
}
