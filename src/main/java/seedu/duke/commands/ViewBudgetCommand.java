package seedu.duke.commands;

import seedu.duke.data.Budget;

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
        List<Budget> allBudgets = budgetList.getBudgetList();

        for (Budget budget: allBudgets) {
            System.out.println("[\\\\]"
                    + count  + "." + budget.getCategory() + " " + budget.getDescription() + " "
                            + budget.getAmount());
            count++;
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
