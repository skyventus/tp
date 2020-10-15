package seedu.duke.commands;


import seedu.duke.data.Transaction;
import java.util.List;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Above are all transaction entered.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View a list of transactions added to the NUS Expenses Tracker.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {

        List<Transaction> allTransactions = transactionList.getTransactionList();
        for (int i = 0; i < allTransactions.size(); i++) {
            System.out.println(allTransactions.indexOf(i));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
