package seedu.duke;

import java.util.Scanner;


public class NusExpenses {
    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What is your name?");

        Scanner in = new Scanner(System.in);
        System.out.println("Hello " + in.nextLine());

        //Start the Expense Tracker.
        new NusExpenses().run();

    }

    public void run() {
        boolean isExit = false;
        while (!isExit) {
            try {
                switch ("") {
                    case "EXIT":
                        isExit = true;
                        break;
                    case "ADD":
                        break;
                    case "VIEW":
                        break;
                    case "DELETE":
                        break;
                    case "SEARCH":
                        break;
                    case "TOTAL":
                        break;
                    default:
                        break;
                }
            } catch (Exception e) {
            }

        }
        System.exit(0);
    }
}