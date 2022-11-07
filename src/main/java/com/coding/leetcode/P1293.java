package com.coding.leetcode;

import java.util.*;

public class P1293 {

    private static class Node{
        int ci, cj, k, s;
        public Node(int ci, int cj, int k, int s) {
            this.ci = ci;
            this.cj = cj;
            this.s = s;
            this.k = k;
        }

        @Override
        public int hashCode(){
            return ci*10 + cj;
        }

        @Override
        public boolean equals(Object o){
            if(o instanceof Node){
                return ((Node)o).ci == this.ci && ((Node)o).cj == this.cj;
            }
            else{
                return false;
            }
        }
    }

    private static int n , m;
    private static int [][] g;

    public int shortestPath(int[][] grid, int k) {
        Deque<Node> q = new LinkedList<>();
        n = grid.length;
        m = grid[0].length;
        g = grid;

        if( n == 1 && m == 1){
            return 0;
        }

        // first node with 0,0 and k
        Node src = new Node(0, 0, k, 1);
        q.addLast(src);
//        Node tst = new Node(0,0, 10, 20);
        Map<Node,Integer> visited = new HashMap<>();
        visited.put(src,k);
//        visited.put(tst, 10);
        int ans = Integer.MAX_VALUE;
        while(!q.isEmpty()){
            Node cn = q.removeFirst();
            List<Node> nbs = getNbs(cn);
            for(Node n : nbs){
                if( n.k < 0){
                    // if we have already exhausted all the k
                    continue;
                }
                if(isDestination(n)){
                    ans = Math.min(ans, n.s);
                    continue;
                }
                if(visited.containsKey(n)){
                    //Here is trick, if we have more k then we can add this node
                    int kPrev = visited.get(n);
                    if(kPrev < n.k){
                       // then we will add this
                       visited.put(n, n.k);
//                       System.out.println("Adding : " + n.ci + "," +  n.cj + ", k = " + n.k);
                       q.add(n);
                    }
                }
                else{
                    q.add(n);
//                    System.out.println("Adding : " + n.ci + "," +  n.cj + ", k = " + n.k);
                    visited.put(n, n.k);
                }
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans-1;
    }

    private boolean isDestination(Node cn) {
        return cn.ci == n-1 && cn.cj == m-1;
    }

    private List<Node> getNbs(Node cn) {
        List<Node> valid = new ArrayList<>();
        int [][] arr = new int [][] {{0,1},{0,-1},{1,0},{-1,0}};
        for(int i = 0 ; i < 4; i++){
            if( cn.ci + arr[i][0] < n && cn.ci + arr[i][0] >= 0 && cn.cj + arr[i][1] < m && cn.cj + arr[i][1] >= 0){
                valid.add(new Node(cn.ci + arr[i][0], cn.cj + arr[i][1], g[cn.ci][cn.cj] == 1 ? cn.k -1 : cn.k, cn.s + 1));
            }
        }
        return valid;
    }

    public static void main(String[] args) {
        int [][] grid = new int [][] {
                {0,1,0},
                {1,1,0},
                {0,0,0},
                {0,1,1},
                {0,0,0}
        };
        int k = 1;
        System.out.println(new P1293().shortestPath(grid, k));
    }
}
