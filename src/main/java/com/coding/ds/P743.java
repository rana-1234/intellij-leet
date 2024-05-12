package com.coding.ds;

import java.util.*;

public class P743 {

    public int networkDelayTime(int[][] times, int n, int k) {

        List<Map.Entry<Integer, Integer>> [] g = new ArrayList [n+1];
        for(int i = 0 ; i <= n ; i++){
            g[i] = new ArrayList<>();
        }

        // Add all edges
        for(int [] time : times){
            g[time[0]].add(Map.entry(time[1], time[2]));
        }

        // source node is k;

        boolean [] visited = new boolean[n+1];
        int [] minTimeToReachSignal = new int[n+1];
        Arrays.fill(minTimeToReachSignal, Integer.MAX_VALUE);

        Arrays.fill(visited, false);
        Deque<Integer> deque = new LinkedList<>();

        minTimeToReachSignal[k] = 0;
        visited[k] = true;

        PriorityQueue<Map.Entry<Integer, Integer>> minDistNode = new PriorityQueue<>((x,y) -> x.getKey() - y.getKey());
        minDistNode.add(Map.entry(0, k));
        while(!minDistNode.isEmpty()){
            Map.Entry<Integer, Integer> current = minDistNode.poll();
            visited[current.getValue()] = true;
            // Now add all the nodes adjacent to
            for(Map.Entry<Integer, Integer> nbr : g[current.getValue()]){
                if (!visited[nbr.getKey()] && minTimeToReachSignal[nbr.getKey()] > current.getKey() + nbr.getValue()){
                    minTimeToReachSignal[nbr.getKey()] = current.getKey() + nbr.getValue();
                    minDistNode.add(Map.entry(minTimeToReachSignal[nbr.getKey()], nbr.getKey()));
                }
            }
        }

        int maxTime = 0;
        for(int i = 1 ; i <= n ; i++){
            if ( minTimeToReachSignal[i] == Integer.MAX_VALUE){
                return -1;
            }
            if ( maxTime < minTimeToReachSignal[i]){
                maxTime = minTimeToReachSignal[i];
            }
        }
        return maxTime;
    }

    public static void main(String[] args) {
        {
            int[][] edges = {{2, 1, 3}, {2, 3, 1}, {3, 4, 1}};
            int n = 4;
            int k = 2;

            System.out.println(new P743().networkDelayTime(edges, n, k));
        }

        {
            int[][] edges = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
            int n = 4;
            int k = 3;
            System.out.println(new P743().networkDelayTime(edges, n, k));
        }

        {
            int[][] edges = {{1,2,1}};
            int n = 2;
            int k = 1;

            System.out.println(new P743().networkDelayTime(edges, n, k));
        }

        {
            int[][] edges = {{1,2,1},{2,3,2},{1,3,4}};
            int n = 3;
            int k = 1;

            System.out.println(new P743().networkDelayTime(edges, n, k));
        }

    }
}
