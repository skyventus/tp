package seedu.duke.commands;


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

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
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

