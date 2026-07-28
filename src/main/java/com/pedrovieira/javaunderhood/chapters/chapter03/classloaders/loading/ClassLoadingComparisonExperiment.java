package com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.loading;

import java.util.ArrayList;
import java.util.HashMap;

public class ClassLoadingComparisonExperiment {

    public static void main(String[] args) {

        printClassLoader("Application Class", // Loaded by the Application Class Loader
                ClassLoadingComparisonExperiment.class);

        printClassLoader("String", // Loaded by the Bootstrap Class Loader
                String.class);

        printClassLoader("ArrayList", // Also loaded by the Bootstrap Class Loader
                ArrayList.class);

        printClassLoader("HashMap", // Also loaded by the Bootstrap Class Loader
                HashMap.class);
    }

    private static void printClassLoader(String label, Class<?> clazz) {

        System.out.println("=== " + label + " ===");

        System.out.println("Class:");
        System.out.println(clazz.getName());

        System.out.println();

        System.out.println("Loaded by:");
        System.out.println(clazz.getClassLoader());

        System.out.println();
    }
}