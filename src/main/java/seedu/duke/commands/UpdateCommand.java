package seedu.duke.commands;

import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.Transaction;

import java.util.Date;

public class UpdateCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Transaction updated.";

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Update a transaction to the NUS Expenses Tracker.\n"
            + "Parameters: INDEX DESCRIPTION $AMOUNT DATE...\n"
            + "Example: " + COMMAND_WORD
            + "update 1 /a 5 /u Dinner /d 2020-09-02";

    private final Transaction toUpdate;

    public UpdateCommand(int index, String description, double amount,
                         Date date) {
        setTargetIndex(index);
        this.toUpdate = new Transaction(description, amount, date);
    }

    @Override
    public CommandResult execute() {
        try {
            transactionList.updateTransaction(toUpdate, getTargetIndex());
            return new CommandResult(String.format(MESSAGE_SUCCESS, toUpdate));
        } catch (Exception e) {
            return new CommandResult(e.getMessage());
        }
    }
}
