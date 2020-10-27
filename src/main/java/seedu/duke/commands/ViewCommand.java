package seedu.duke.commands;



import seedu.duke.data.Transaction;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ViewCommand extends Command {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final String COMMAND_WORD = "view";
    public static final String MESSAGE_SUCCESS = "Above are all transaction entered.";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View a list of transactions added to the NUS Expenses Tracker.\n"
            + "Example: " + COMMAND_WORD;

    private Date startDate;
    private Date endDate;
    private String category;

    public ViewCommand(String category) {
        this.category = category;
    }

    public ViewCommand(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute() {
        List<Transaction> allTransactions;
        if (this.category != null && !this.category.isEmpty()) {
            if(Transaction.enumContains(this.category.toUpperCase())) {
                allTransactions = transactionList.getTransactionsByCategory(this.category.toUpperCase());
                return new CommandResult("Displaying category: " + this.category.toUpperCase() + " \n"
                        + String.format(MESSAGE_SUCCESS), allTransactions);
            }
        } else {
            allTransactions = transactionList.getTransactionsWithinPeriod(this.startDate, this.endDate);
            String timePeriod = transactionList.getDatePeriodString(this.startDate, this.endDate);
            return new CommandResult("Displaying : " + timePeriod + " \n"
                    + String.format(MESSAGE_SUCCESS), allTransactions);
        }
        return new CommandResult("Incorrect View Command");
    }
}
