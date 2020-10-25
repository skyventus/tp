package seedu.duke.commands;


import seedu.duke.data.Transaction;
import seedu.duke.utilities.Parser;

import java.text.SimpleDateFormat;
import java.util.List;

public class ViewCommand extends Command {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Above are all transaction entered.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View a list of transactions added to the NUS Expenses Tracker.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {

        List<Transaction> allTransactions = transactionList.getTransactionList();
        int count = 1;
        for (Transaction transaction : allTransactions) {
            StringBuilder output = new StringBuilder();
            output.append(count + ".");
            if (transaction.getDate() != null) {
                output.append(Parser.sdf.format(transaction.getDate()) + " ");
            }
            output.append(transaction.getDescription() + " "
                    + transaction.getAmount());
            System.out.println(output.toString());
            count++;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
