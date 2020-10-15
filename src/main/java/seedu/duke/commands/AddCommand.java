package seedu.duke.commands;

import seedu.duke.data.Transaction;

public class AddCommand extends Command {

    public static final String MESSAGE_SUCCESS = "New transaction added.";

    public static final String COMMAND_WORD = "add";

    private final Transaction toAdd;

    public AddCommand(String description, double amount,
                      String date) {
        this.toAdd = new Transaction(description, amount, date);
    }

    @Override
    public CommandResult execute() {
        try {
            transactionList.addTransaction(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult(e.getMessage());
        }
    }
}
