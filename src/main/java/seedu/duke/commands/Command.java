package seedu.duke.commands;


import seedu.duke.common.Constants;
import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;
import seedu.duke.data.BudgetList;
import seedu.duke.data.ReadOnlyBudget;

import java.util.List;

public class Command {

    protected TransactionList transactionList;
    protected List<? extends ReadOnlyTransaction> readOnlyTransaction;
    private int targetIndex = -1;

    protected BudgetList budgetList;
    protected List<? extends ReadOnlyBudget> readOnlyBudget;

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
        assert transactionList != null;
        
        this.transactionList = transactionList;
        this.readOnlyTransaction = readOnlyTransactions;
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * Executes the command for Budget and returns the result.
     */
    protected ReadOnlyBudget getTargetBudget() throws IndexOutOfBoundsException {
        return budgetList.getBudgetList().get(--targetIndex);
    }

    public void setBudgetData(BudgetList budgetList, List<? extends ReadOnlyBudget> readOnlyBudget) {
        this.budgetList = budgetList;
        this.readOnlyBudget = readOnlyBudget;
    }

}

