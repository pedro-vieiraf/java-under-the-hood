package com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.dynamic;

public class DynamicLoadingExperiment {

    public static void main(String[] args) throws Exception {

        System.out.println("Program started");

        System.out.println();

        System.out.println("Loading Plugin dynamically...");

        // Requests the JVM to load and initialize the class during runtime.
        Class<?> pluginClass = Class.forName(
                "com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.dynamic.Plugin"
        );

        System.out.println();

        System.out.println("Class loaded:");
        System.out.println(pluginClass.getName());

        System.out.println();

        System.out.println("Loaded by:");
        System.out.println(pluginClass.getClassLoader());
    }

}