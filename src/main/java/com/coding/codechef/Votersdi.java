package com.coding.codechef;

import com.coding.io.InputReader;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Votersdi {

    public static void main(String[] args) throws Exception{

        InputReader in = new InputReader();
        int n1 = in.readInt(), n2 = in.readInt(), n3 = in.readInt();
        Map<Integer,Integer> m = new HashMap<>();
        for(int i = 0 ; i < n1; i++)
            m.put(in.readInt(), 1);

        int prev = in.readInt();
        m.put(prev, m.getOrDefault(prev, 0) + 1);
        for(int i = 1 ; i < n2; i++){
            int now = in.readInt();
            if( now != prev)
                m.put(now, m.getOrDefault(now, 0) + 1);
            prev = now;
        }

        prev = in.readInt();
        m.put(prev, m.getOrDefault(prev, 0) + 1);
        for(int i = 1 ; i < n3; i++){
            int now = in.readInt();
            if( now != prev)
                m.put(now, m.getOrDefault(now, 0) + 1);
            prev = now;
        }

        TreeSet<Integer> list = m.entrySet().stream().filter(entry -> entry.getValue() >= 2).map(entry -> entry.getKey()).collect(Collectors.toCollection(new Supplier<>(){
            @Override
            public TreeSet<Integer> get() {
                return new TreeSet<>();
            }
        }));

        System.out.println(list.size());
        for(int voterId : list){
            System.out.println(voterId);
        }
    }
}
