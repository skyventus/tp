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

        if (category.toUpperCase().startsWith("M")) {
            this.toAdd = new Budget("MONTHLY", description.toUpperCase(), amount);
        } else if (category.toUpperCase().startsWith("W")) {
            this.toAdd = new Budget("WEEKLY", description.toUpperCase(), amount);
        } else {
            this.toAdd = new Budget("DAILY", description.toUpperCase(), amount);
        }

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
