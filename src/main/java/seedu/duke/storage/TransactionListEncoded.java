package seedu.duke.storage;

import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class TransactionListEncoded {
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
        DecimalFormat df2 = new DecimalFormat("#.00");
        encodedTransactionBuilder.append(transaction.getDate()
                .equalsIgnoreCase("") ? "-" : transaction.getDate().trim());
        encodedTransactionBuilder.append(" ");
        encodedTransactionBuilder.append(transaction.getDescription());
        encodedTransactionBuilder.append(" ");
        encodedTransactionBuilder.append("$" + df2.format(transaction.getAmount()));
        encodedTransactionBuilder.append("\n");
        return encodedTransactionBuilder.toString();
    }
}
