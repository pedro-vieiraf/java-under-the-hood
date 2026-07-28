package com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.hierarchy;

import java.util.ArrayList;

public class ClassLoaderHierarchyExperiment {

    public static void main(String[] args) {

        ClassLoader applicationLoader =
                ClassLoaderHierarchyExperiment.class.getClassLoader();

        System.out.println("=== Application Class Loader ===");
        System.out.println(applicationLoader);

        System.out.println();

        System.out.println("=== Platform Class Loader ===");
        System.out.println(applicationLoader.getParent());

        System.out.println();

        System.out.println("=== Bootstrap Class Loader ===");
        System.out.println(applicationLoader.getParent().getParent());

        System.out.println();

        System.out.println("=== JDK Classes ===");

        System.out.println("String:");
        System.out.println(String.class.getClassLoader());

        System.out.println();

        System.out.println("ArrayList:");
        System.out.println(ArrayList.class.getClassLoader());
    }
}