package com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.flow;

public class DemoClass {

    static {
        System.out.println("5 - DemoClass static block executed");
    }

    public static void greet() {
        System.out.println("6 - DemoClass is ready to use");
    }

}