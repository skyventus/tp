package seedu.duke.commands;

import seedu.duke.data.Budget;

public class AddBudgetCommand extends Command {

    public static final String COMMAND_WORD = "budgetadd";
    private static final String MESSAGE_SUCCESS = "New Budget has been Added";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a budget to the NUS Expenses Tracker.\n"
            + "Parameters: CATEGORY DESCRIPTION $AMOUNT\n"
            + "Example: " + COMMAND_WORD
            + " daily/food $20.00";


    private final Budget toAdd;

    public AddBudgetCommand(String category, String description, double amount) {
        this.toAdd = new Budget(category, description, amount);
    }


    @Override
    public CommandResult execute() {
        try {
            budgetList.addBudget(toAdd);
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (Exception e) {
            return new CommandResult(e.getMessage());
        }
    }
}
