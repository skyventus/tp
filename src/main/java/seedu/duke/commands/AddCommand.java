package seedu.duke.commands;

import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.Transaction;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "New transaction added.";

    private static Logger logger = Logger.getLogger("Foo");

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a transaction to the NUS Expenses Tracker.\n"
            + "Parameters: DESCRIPTION $AMOUNT DATE...\n"
            + "Example: " + COMMAND_WORD
            + " Lunch at Com2 $4.50 14-Oct-2020";

    private final Transaction toAdd;

    public AddCommand(String description, double amount,
                      Date date, String category) {
        logger.log(Level.INFO, "Executing Add Command...");
        this.toAdd = new Transaction(description, amount, date,category);
    }

    @Override
    public CommandResult execute() {
        try {
            transactionList.addTransaction(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            System.out.println("Add command execute");
            return new CommandResult(e.getMessage());
        }
    }
}
