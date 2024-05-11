package com.coding.leetcode.graph;
import java.util.*;

public class P332 {

    Map<String, List<String>> g;
    Map<String, Map.Entry<Integer,Integer>> sourceToNextIndex;
    List<String> ans;


    void dfs(String source){
        while(sourceToNextIndex.get(source) != null) {
            Map.Entry<Integer, Integer> nextIndex = sourceToNextIndex.get(source);
            if (nextIndex.getKey() < nextIndex.getValue()) {
                String to = g.get(source).get(nextIndex.getKey());
                sourceToNextIndex.put(source, Map.entry(nextIndex.getKey() + 1, nextIndex.getValue()));
                dfs(to);
            }
            else{
                break;
            }
        }
        // All the routes are visited from this node, we can add this route in our ans stack
        ans.add(source);
    }

    public List<String> findItinerary(List<List<String>> tickets) {

        g = new HashMap<>();
        sourceToNextIndex = new HashMap<>();

        Map<String, List<String>> temp = new HashMap<>();
        for(List<String> ticket : tickets){
            String from = ticket.get(0);
            String to = ticket.get(1);

            List<String> currentPossible = temp.getOrDefault(from, new ArrayList<>()); // sorted
            currentPossible.add(to);
            temp.put(from, currentPossible);
        }

        for(Map.Entry<String, List<String>> entry : temp.entrySet()){
            // Populate the map
            Collections.sort(entry.getValue());
            sourceToNextIndex.put(entry.getKey(), Map.entry(0, entry.getValue().size()));
            g.put(entry.getKey(), entry.getValue());
        }

        ans = new ArrayList<>();
        String source = "JFK";
        dfs(source);
        Collections.reverse(ans); // reverse it to get the ans;
        return ans;
    }

    public static void main(String[] args) {
        {
            List<List<String>> tickets = new ArrayList<>();
            tickets.add(List.of("MUC", "LHR"));
            tickets.add(List.of("JFK", "MUC"));
            tickets.add(List.of("SFO", "SJC"));
            tickets.add(List.of("LHR", "SFO"));
//            System.out.println(new P332().findItinerary(tickets));
        }

        {
            List<List<String>> tickets = new ArrayList<>();
            tickets.add(List.of("JFK","SFO"));
            tickets.add(List.of("JFK","ATL"));
            tickets.add(List.of("SFO","ATL"));
            tickets.add(List.of("ATL","JFK"));
            tickets.add(List.of("ATL","SFO"));
//            System.out.println(new P332().findItinerary(tickets));
        }

        {
            List<List<String>> tickets = new ArrayList<>();
            tickets.add(List.of("JFK","KUL"));
            tickets.add(List.of("JFK","NRT"));
            tickets.add(List.of("NRT","JFK"));
            System.out.println(new P332().findItinerary(tickets));
        }

        {

            List<List<String>> tickets = new ArrayList<>();
            tickets.add(List.of("EZE","AXA"));
            tickets.add(List.of("TIA","ANU"));
            tickets.add(List.of("ANU","JFK"));
            tickets.add(List.of("JFK","ANU"));
            tickets.add(List.of("ANU","EZE"));
            tickets.add(List.of("TIA","ANU"));
            tickets.add(List.of("AXA","TIA"));
            tickets.add(List.of("TIA","JFK"));
            tickets.add(List.of("ANU","TIA"));
            tickets.add(List.of("JFK","TIA"));

            System.out.println(new P332().findItinerary(tickets));
        }
    }
}
