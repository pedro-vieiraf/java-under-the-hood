package com.pedrovieira.javaunderhood.chapters.chapter02.startup.classloading;

public class ClassLoadingExperiment {

    public static void main(String[] args) throws Exception {

        System.out.println("Program started");

        System.out.println("Loading class without initialization:");

        ClassLoader classLoader = ClassLoadingExperiment.class.getClassLoader();

        Class<?> loadedClass = Class.forName(
                "com.pedrovieira.javaunderhood.chapters.chapter02.startup.classloading.SampleClass",
                false,
                classLoader
        );

        System.out.println("Class loaded: " + loadedClass.getName());

        System.out.println();

        System.out.println("Initializing class:");

        Class.forName(
                "com.pedrovieira.javaunderhood.chapters.chapter02.startup.classloading.SampleClass"
        );
    }
}