package com.pedrovieira.javaunderhood.chapters.chapter01.memory;

public class PrimitiveCopyExperiment {
    public static void main(String[] args) {
        int a = 10;
        int b = a;

        b = 20;

        System.out.println("a: " + a);
        System.out.println("b " + b);

    }
}
