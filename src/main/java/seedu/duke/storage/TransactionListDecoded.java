package seedu.duke.storage;

import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.IllegalValueException;

import java.util.ArrayList;
import java.util.List;

public class TransactionListDecoded {

    public static TransactionList decodeTransactions(List<String> encodedTransactions)
            throws IllegalValueException {
        final List<Transaction> decodedTransaction = new ArrayList<>();
        for (String encodedTransaction : encodedTransactions) {
            decodedTransaction.add(decodeTransactionFromString(encodedTransaction));
        }
        return new TransactionList(decodedTransaction);
    }

    public static Transaction decodeTransactionFromString(String transactionText) {
        String[] transactionSplit = transactionText.split(" ");
        String date = transactionSplit[0].equals("-") ? "" : transactionSplit[0].trim();
        String description = transactionText.substring(
                transactionText.indexOf(transactionSplit[1]), transactionText.indexOf("$") - 1).trim();
        double d = Double.parseDouble(transactionText.substring(transactionText.indexOf("$") + 1));
        double amount = d;
        return new Transaction(description, amount, date);
    }
}
