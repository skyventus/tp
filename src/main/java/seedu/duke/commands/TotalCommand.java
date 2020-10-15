package seedu.duke.commands;

import seedu.duke.data.Transaction;

import java.util.List;

public class TotalCommand extends Command {
    public static final String COMMAND_WORD = "total";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the transaction in the Expenses Application as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public String toString() {
        return "TotalCommand{}";
    }

    @Override
    public CommandResult execute() {
        double totalAmount = 0.00;
        List<Transaction> allTransactions = transactionList.getTransactionList();
        for (Transaction transaction : allTransactions) {
            totalAmount = totalAmount + transaction.getAmount();
        }
        return new CommandResult(
                String.format("The total amount you have spent so far is $%.2f", totalAmount),
                allTransactions);
    }
}
