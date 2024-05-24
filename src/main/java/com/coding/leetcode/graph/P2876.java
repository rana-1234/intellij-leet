package com.coding.leetcode.graph;

import com.coding.help.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class P2876 {


    int [] visited;
    int [] ans;
    int n;
    List<Integer> edges;
    List<Integer> nodesInCycle;
    boolean dfs(int from, int visitCount){
        if ( visited[from] != 0){
            // Adding first node in the cycle, while returning back we would check for this node
            if ( visited[from] == visitCount){
                nodesInCycle.add(from);
                return true;
            }
            else{
                return false;
            }
        }
        visited[from] = visitCount;
        int to = edges.get(from);
        boolean containsCycle = dfs(to, visitCount);
        if( containsCycle ){
            if ( from == nodesInCycle.get(0)){
                // we reached the node where the cycle had started
                for (int node : nodesInCycle){
                    ans[node] = nodesInCycle.size();
                }
                return false; // returning to parent that this node is no longer visited
            }
            else {
                nodesInCycle.add(from);
                return true;
            }
        }
        else {
            ans[from] = ans[to] + 1;
            return false;
        }
    }
    public int[] countVisitedNodes(List<Integer> edges) {

        // there would be maximum one cycle with n edges in n nodes given graph is not disconnected
        // And there is always one outgoing edge from a node

        // if there is no cycle then, answer can be calculated easily, otherwise if a node is part of cycle,
        // then every node in the cycle will have the same number of nodes

        n = edges.size();
        this.edges = edges;
        visited = new int[n];
        Arrays.fill(visited, 0);
        ans = new int[n];

        for (int i = 0 ; i < visited.length; i++) {
            nodesInCycle = new ArrayList<>();
            if (visited[i]  == 0) {
                dfs(i, i+1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        {
            List<Integer> edges = List.of(1,2,0,0);
            Utils.printIntArray(new P2876().countVisitedNodes(edges));
        }

        {
            List<Integer> edges = List.of(1,2,3,4,0);
            Utils.printIntArray(new P2876().countVisitedNodes(edges));
        }

        {
            List<Integer> edges = List.of(1,2,3,4,5,2,2,1,5);
            Utils.printIntArray(new P2876().countVisitedNodes(edges));
        }
    }
}
