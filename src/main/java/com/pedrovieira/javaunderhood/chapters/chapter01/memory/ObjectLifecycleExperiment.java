package com.pedrovieira.javaunderhood.chapters.chapter01.memory;

public class ObjectLifecycleExperiment {

    public static void main(String[] args) {

        User user = new User("Pedro");

        user = null;

        System.out.println("Object is now eligible for GC");
    }
}
