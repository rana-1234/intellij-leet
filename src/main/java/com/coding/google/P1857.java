package com.coding.google;

import java.util.ArrayList;
import java.util.List;

public class P1857 {
//https://leetcode.com/problems/largest-color-value-in-a-directed-graph/

    public static List<Integer> [] adjList ;
    public static String nodeColor;
    public static boolean [] exploring;
    public static boolean [] visited;

    public static  int maxAns = 0;
    public void doDfsWithPassingColorValueToChild(int root, int [] colorFrequency){

        visited[root] = true;
        exploring[root] = true;
        int color = nodeColor.charAt(root) - 'a';
        colorFrequency[color]++;
        if(colorFrequency[color] > maxAns){
            maxAns = colorFrequency[color];
        }

        for(int child : adjList[root]){

            if( maxAns == -1){
                // if there is loop already found, stop here
                return ;
            }

            if (exploring[child]){
                // this is the case when we have cycle or loop
                maxAns = -1;
                return ;
            }

            doDfsWithPassingColorValueToChild(child, colorFrequency);
        }

        // we are no longer exploring this node
        colorFrequency[color]--;
        exploring[root] = false;

    }


    public int largestPathValue(String colors, int[][] edges) {
        int n = colors.length();
        adjList = new ArrayList [n];
        visited = new boolean[n];
        exploring = new boolean[n];
        nodeColor = colors;
        int [] colorFrequency = new int[26];

        for(int i = 0 ; i < n; i++){
            adjList[i] = new ArrayList<>();
            visited[i] = false;
            exploring[i] = false;
        }

        for(int i = 0 ; i < edges.length; i++){
            adjList[edges[i][0]].add(edges[i][1]); // 0->1
        }



        for(int i = 0 ; i < n ; i++) {
            if( visited[i] == false) {
                doDfsWithPassingColorValueToChild(i, colorFrequency);
            }
        }

        return maxAns;
    }


    public static void main(String[] args) {
        int [][] edge = {{0,2},{3,0},{1,3},{4,1}};
        String color = "bbbhb";
        System.out.println("Colors maximum in valid path : " + new P1857().largestPathValue(color, edge));

    }
}
