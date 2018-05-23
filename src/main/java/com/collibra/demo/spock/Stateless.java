package com.collibra.demo.spock;

import lombok.ToString;

@ToString
public class Stateless {

    public int add(int a, int b){
        return a + b;
    }

    private static int max(int a, int b){
        return Math.max(a, b);
    }

}
