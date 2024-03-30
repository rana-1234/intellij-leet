package com.coding.other;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Solution {
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        {
            List<Integer> buildingCount = List.of(1, 2, 1, 2, 2);
            List<Integer> routerLocation = List.of(3, 1);
            List<Integer> routerRange = List.of(1, 2);

            System.out.println("Test 1 : " + getServedBuildings(buildingCount, routerLocation, routerRange));
        }

        {
            List<Integer> buildingCount = List.of(2,1,1,3);
            List<Integer> routerLocation = List.of(1,2);
            List<Integer> routerRange = List.of(2,0);

            System.out.println("Test 2 : " + getServedBuildings(buildingCount, routerLocation, routerRange));
        }

    }

    public static int getServedBuildings(List<Integer> buildingCount, List<Integer> routerLocation, List<Integer> routerRange){

        List<Integer> currentState = new ArrayList<>();
        for(int i = 0 ; i < buildingCount.size(); i++){
            currentState.add(0);
        }

        for (int i = 0 ; i < routerLocation.size(); i++){
            int currentRouterLocation = routerLocation.get(i) - 1;
            int currentRouterRange = routerRange.get(i);
            int leftEnd = currentRouterLocation - currentRouterRange >= 0 ? currentRouterLocation - currentRouterRange : 0;
            currentState.set(leftEnd, currentState.get(leftEnd) + 1);
            int rightEnd = currentRouterLocation + currentRouterRange + 1 < buildingCount.size() ? currentRouterLocation + currentRouterRange + 1 : buildingCount.size() - 1;
            currentState.set(rightEnd, currentState.get(rightEnd) - 1);
        }


        for(int i = 1 ; i < currentState.size(); i++){
            currentState.set(i, currentState.get(i-1) + currentState.get(i));
        }

        int servedBuildings = 0;
        for(int i = 0 ; i < currentState.size() ; i++){
            servedBuildings += currentState.get(i) >= buildingCount.get(i) ? 1 : 0 ;
        }
        return servedBuildings;
    }
}
