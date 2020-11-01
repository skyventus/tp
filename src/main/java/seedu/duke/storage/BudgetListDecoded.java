package seedu.duke.storage;

import seedu.duke.commands.IncorrectCommand;
import seedu.duke.data.Budget;
import seedu.duke.data.BudgetList;

import seedu.duke.exception.IllegalValueException;
import seedu.duke.utilities.Parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BudgetListDecoded {

    private static Logger logger = Logger.getLogger("Foo");

    public static BudgetList decodeBudget(List<String> encodedBudgets)
            throws IllegalValueException {
        logger.log(Level.INFO, "Decoding your budget from the expenses file.");
        final List<Budget> decodedBudget = new ArrayList<>();
        for (String encodedBudget : encodedBudgets) {
            decodedBudget.add(decodeBudgetFromString(encodedBudget));
        }
        logger.log(Level.INFO, "Decoding completed.");

        return new BudgetList(decodedBudget);
    }

    public static Budget decodeBudgetFromString(String budgetText) {
        try {
            String[] budgetSplit = budgetText.split(" ");
            String date = budgetSplit[0].equals("-") ? "" : budgetSplit[0].trim();

            int catIndex = budgetText.indexOf(" ");
            String category = budgetText.substring(0, catIndex);

            int desIndex = budgetText.indexOf("\\");
            String description = budgetText.substring(catIndex + 1, desIndex);

            //String description = budgetText.substring(
            //        budgetText.indexOf(budgetSplit[1]), budgetText.indexOf("\\") - 1).trim();

            //String category = budgetText.substring(budgetText.indexOf(" ") + 2).trim();

            double amount = Double.parseDouble(budgetText.substring(budgetText.indexOf("$")
                    + 1, budgetText.indexOf("/") - 1));

            return new Budget(category, description, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
