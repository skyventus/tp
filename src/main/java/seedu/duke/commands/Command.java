package seedu.duke.commands;


import seedu.duke.common.Constants;
import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;

import java.util.List;

public class Command {

    protected TransactionList transactionList;
    protected List<? extends ReadOnlyTransaction> readOnlyTransaction;
    private int targetIndex = -1;

    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    public static String getMessageForTransactionListShownSummary(
        List<? extends ReadOnlyTransaction> transactionsDisplayed) {
        return String.format(Constants.MESSAGE_PERSONS_LISTED_OVERVIEW, transactionsDisplayed.size());
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    }

    protected ReadOnlyTransaction getTargetTransaction() throws IndexOutOfBoundsException {
        return transactionList.getTransactionList().get(--targetIndex);
    }

    public void setData(TransactionList transactionList, List<? extends ReadOnlyTransaction> readOnlyTransactions) {
        this.transactionList = transactionList;
        this.readOnlyTransaction = readOnlyTransactions;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }


}

