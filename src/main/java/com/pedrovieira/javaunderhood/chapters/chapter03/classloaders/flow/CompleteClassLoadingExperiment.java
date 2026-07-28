package com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.flow;

public class CompleteClassLoadingExperiment {

    public static void main(String[] args) throws Exception {

        System.out.println("1 - Program started");

        System.out.println();

        System.out.println("2 - Requesting DemoClass...");

        Class<?> demoClass = Class.forName(
                "com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.flow.DemoClass"
        );

        System.out.println();

        System.out.println("3 - Class loaded:");
        System.out.println(demoClass.getName());

        System.out.println();

        System.out.println("4 - Class Loader:");
        System.out.println(demoClass.getClassLoader());

        System.out.println();

        DemoClass.greet();
    }

}