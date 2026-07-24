package com.pedrovieira.javaunderhood.chapters.chapter01.memory;

public class ReferenceAssignmentExperiment {
    public static void main(String[] args) {

        User userA = new User("Pedro");

        User userB = userA;

        userB.name = "João";

        System.out.println(userA.name);
        System.out.println(userB.name);
    }
}
