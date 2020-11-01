package seedu.duke.commands;

import seedu.duke.data.*;

public class DeleteBudgetCommand extends Command {

    public static final String COMMAND_WORD = "budgetdelete";
    public static final String MESSAGE_SUCCESS = "Budget Deleted.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Delete Budget List from NUS Expenses Tracker.\n"
            + "Example: " + COMMAND_WORD
            + " 2";


    public DeleteBudgetCommand(int targetIndex) {
        super(targetIndex);
        assert targetIndex >= 0 : "index shouldn't be a negative value";
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyBudget target = getTargetBudget();
            budgetList.removeBudget(target);
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult("Out of index");
        } catch (BudgetList.BudgetNotFound budgetNotFound) {
            return new CommandResult("Budget Not found");
        }
    }
}
