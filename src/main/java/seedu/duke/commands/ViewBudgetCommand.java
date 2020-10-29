package seedu.duke.commands;

import seedu.duke.data.Budget;
import seedu.duke.data.Transaction;

import java.util.List;

public class ViewBudgetCommand extends Command {

    public static final String COMMAND_WORD = "budgetview";
    public static final String MESSAGE_SUCCESS = "Above are all budgets entered.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View a list of budgets added to the NUS Expenses Tracker.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {

        int count = 1;
        double total = 0.0;
        List<Budget> allBudgets = budgetList.getBudgetList();
        List<Transaction> allTransactions = transactionList.getTransactionList();;

        for (Budget budget: allBudgets) {
            System.out.println("[\\\\]DESCRIPTION: " + budget.getDescription()
                    + " || Budget : $" + budget.getAmount());

            total = 0.0;
            for (Transaction transaction: allTransactions) {
                if (transaction.getCategory().equals(budget.getDescription())) {
                    total = total + transaction.getAmount();
                }
            }

            System.out.println("[\\\\] Current Spending for CATEGORY "
                    + budget.getDescription() + " is $" + total);

            if (total <= budget.getAmount()) {
                System.out.println("[\\\\] You expenses is on track. Good Job!" + "\n[\\\\]");
            } else {
                System.out.println("[\\\\] You have overspent for this category!" + "\n[\\\\]");
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
