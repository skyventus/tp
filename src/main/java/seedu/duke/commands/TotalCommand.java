package seedu.duke.commands;

import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TotalCommand extends Command {
    public static final String COMMAND_WORD = "total";
    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Date startDate;
    private Date endDate;
    private String category;
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all the transaction in the Expenses Application as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    public TotalCommand() {
    }

    public TotalCommand(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "TotalCommand{}";
    }

    @Override
    public CommandResult execute() {
        double totalAmount = 0.00;
        List<Transaction> allTransactions;
        if (this.startDate == null && this.endDate == null) {
            allTransactions = transactionList.getTransactionList();
        } else {
            allTransactions = transactionList.getTransactionsWithinPeriod(startDate, endDate);
        }
        totalAmount = transactionList.getTotalAmount(allTransactions);

        return new CommandResult(
                String.format("The total amount you have spent so far is $%.2f", totalAmount),
                allTransactions);
    }
}
