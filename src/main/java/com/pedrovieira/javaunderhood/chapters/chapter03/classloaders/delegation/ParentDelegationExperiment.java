package com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.delegation;

public class ParentDelegationExperiment {

    public static void main(String[] args) throws Exception {

        ClassLoader applicationLoader =
                ParentDelegationExperiment.class.getClassLoader();

        System.out.println("=== Application Class Loader ===");
        System.out.println(applicationLoader);

        System.out.println();

        System.out.println("Requesting java.lang.String...");

        Class<?> stringClass =
                applicationLoader.loadClass("java.lang.String");

        System.out.println("Loaded class:");
        System.out.println(stringClass.getName());

        System.out.println("Loaded by:");
        System.out.println(stringClass.getClassLoader());

        System.out.println();

        System.out.println("Requesting application class...");

        Class<?> applicationClass = // Because o hierarchy, even if the applicationLoader called, the Bootstrap loader completed the request
                applicationLoader.loadClass(
                        "com.pedrovieira.javaunderhood.chapters.chapter03.classloaders.delegation.ParentDelegationExperiment"
                );

        System.out.println("Loaded class:");
        System.out.println(applicationClass.getName());

        System.out.println("Loaded by:");
        System.out.println(applicationClass.getClassLoader());
    }
}