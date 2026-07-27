package com.pedrovieira.javaunderhood.chapters.chapter02.startup.sequence;

public class StartupExperiment {

    static {
        System.out.println("1 - Static initialization block");
    }

    private static String message = initializeMessage();

    public static void main(String[] args) {
        System.out.println("3 - main() method execution");

        Application app = new Application();

        app.run();
    }

    private static String initializeMessage() {
        System.out.println("2 - Static field initialization");

        return "Application initialized";
    }

    static class Application {

        {
            System.out.println("4 - Object initialization block");
        }

        Application() {
            System.out.println("5 - Constructor execution");
        }

        void run() {
            System.out.println("6 - Application running");
            System.out.println(message);
        }
    }
}