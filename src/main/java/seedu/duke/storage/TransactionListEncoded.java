package seedu.duke.storage;

import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;
import seedu.duke.utilities.Parser;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TransactionListEncoded {

    private static Logger logger = Logger.getLogger("Foo");

    /**
     * Encodes all the {@code Person} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static List<String> encodeTransactionList(TransactionList toSave) {
        final List<String> encodedTransactions = new ArrayList<>();
        toSave.getTransactionList().forEach(transaction ->
                encodedTransactions.add(encodedTransactionToString(transaction)));
        return encodedTransactions;
    }

    /**
     * Encodes the {@code person} into a decodable and readable string representation.
     */
    private static String encodedTransactionToString(Transaction transaction) {

        final StringBuilder encodedTransactionBuilder = new StringBuilder();
        final DecimalFormat df2 = new DecimalFormat("#.00");
        if (transaction.getDate() != null) {
            encodedTransactionBuilder.append(Parser.sdf.format(transaction.getDate()));
        }
        encodedTransactionBuilder.append(" ");
        encodedTransactionBuilder.append(transaction.getDescription());
        encodedTransactionBuilder.append(" ");
        encodedTransactionBuilder.append("$" + df2.format(transaction.getAmount()));
        encodedTransactionBuilder.append(" ");
        encodedTransactionBuilder.append("/c");
        encodedTransactionBuilder.append(" ");
        encodedTransactionBuilder.append(transaction.getCategory());
        encodedTransactionBuilder.append("\n");
        return encodedTransactionBuilder.toString();
    }
}
