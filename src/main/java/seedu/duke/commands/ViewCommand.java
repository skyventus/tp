package seedu.duke.commands;


import seedu.duke.data.Transaction;
import java.util.List;

public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Above are all transaction entered.";


    @Override
    public CommandResult execute() {

        List<Transaction> allTransactions = transactionList.getTransactionList();
        int count = 1;
        for (Transaction transaction : allTransactions) {
            System.out.println(
                    count  + "." + transaction.getDate() + " " + transaction.getDescription() + " " + transaction.getAmount());
            count ++;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));

    }
}
