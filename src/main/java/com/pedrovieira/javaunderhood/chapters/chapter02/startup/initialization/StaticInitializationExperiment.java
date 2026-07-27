package com.pedrovieira.javaunderhood.chapters.chapter02.startup.initialization;

public class StaticInitializationExperiment {

    public static void main(String[] args) {

        System.out.println("Starting program");

        System.out.println("Accessing static field:");

        System.out.println(Counter.value);

        System.out.println("Accessing static field again:");

        System.out.println(Counter.value);
    }

    static class Counter {

        static int value = initialize();

        static {
            System.out.println("Static block executed");
        }

        private static int initialize() {
            System.out.println("Static field initialized");
            return 10;
        }
    }
}