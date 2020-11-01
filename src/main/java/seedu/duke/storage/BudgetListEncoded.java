package seedu.duke.storage;

import seedu.duke.data.Budget;
import seedu.duke.data.BudgetList;
import seedu.duke.utilities.Parser;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BudgetListEncoded {

    private static Logger logger = Logger.getLogger("Foo");

    /**
     * Encodes all the {@code Person} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static List<String> encodeBudgetList(BudgetList toSave) {
        final List<String> encodedTransactions = new ArrayList<>();
        toSave.getBudgetList().forEach(budget ->
                encodedTransactions.add(encodedBudgetToString(budget)));
        return encodedTransactions;
    }

    /**
     * Encodes the {@code person} into a decodable and readable string representation.
     */
    private static String encodedBudgetToString(Budget budget) {

        final StringBuilder encodedTransactionBuilder = new StringBuilder();
        final DecimalFormat df2 = new DecimalFormat("#.00");

        encodedTransactionBuilder.append(budget.getCategory());
        encodedTransactionBuilder.append(" ");
        encodedTransactionBuilder.append(budget.getDescription());
        encodedTransactionBuilder.append("\\");
        encodedTransactionBuilder.append("$" + df2.format(budget.getAmount()));
        encodedTransactionBuilder.append("/");

        encodedTransactionBuilder.append("\n");
        return encodedTransactionBuilder.toString();
    }
}
