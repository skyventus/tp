package seedu.duke;

import seedu.duke.commands.Command;
import seedu.duke.commands.CommandResult;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.TotalCommand;
import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.TransactionList;
import seedu.duke.utilities.Parser;
import seedu.duke.utilities.Storage;
import seedu.duke.utilities.Ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


public class NusExpenses {
    private Ui ui;
    private Storage storage;
    private TransactionList transactionList = new TransactionList();
    private List<? extends ReadOnlyTransaction> lastShownList = Collections.emptyList();

    /**
     * Main entry-point for the java.duke.Duke application.
     */
    public static void main(String[] args) {

        //Start the Expense Tracker.
        new NusExpenses().run();

    }

    public void run() {

        Ui ui = new Ui();
        Parser parser = new Parser();

        Command command;
        do {
            String userCommandText = ui.readUserCommand();
            command = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommandInMain(command);
            //ui.showResultToUser(result);
        } while (!ExitCommand.isExit(command));

        boolean isExit = false;
        while (isExit) {
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
                System.out.println(e.getMessage());
            }

        }
        System.exit(0);
    }


    private CommandResult executeCommandInMain(Command command) {
        try {
            command.setData(transactionList, lastShownList);
            CommandResult result = command.execute();
            //storage.save(addressBook);
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}