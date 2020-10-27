package seedu.duke.storage;

import seedu.duke.commands.IncorrectCommand;
import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.utilities.Parser;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionListDecoded {

    private static Logger logger = Logger.getLogger("Foo");

    public static TransactionList decodeTransactions(List<String> encodedTransactions)
            throws IllegalValueException {
        logger.log(Level.INFO, "Decoding your transaction from the expenses file.");
        final List<Transaction> decodedTransaction = new ArrayList<>();
        for (String encodedTransaction : encodedTransactions) {
            decodedTransaction.add(decodeTransactionFromString(encodedTransaction));
        }
        logger.log(Level.INFO, "Decoding completed.");

        return new TransactionList(decodedTransaction);
    }

    public static Transaction decodeTransactionFromString(String transactionText) {
        try {
            String[] transactionSplit = transactionText.split(" ");
            String date = transactionSplit[0].equals("-") ? "" : transactionSplit[0].trim();

            String description = transactionText.substring(
                    transactionText.indexOf(transactionSplit[1]), transactionText.indexOf("$") - 1).trim();
            double amount = Double.parseDouble(transactionText.substring(transactionText.indexOf("$")
                    + 1, transactionText.indexOf("/c") - 1));
            String category = transactionText.substring(transactionText.indexOf("/c") + 2).trim();

            //Return null if date is null or empty.
            Date formattedDate = (date != null && !date.isEmpty()) ? Parser.sdf.parse(date) : null;

            return new Transaction(description, amount, formattedDate, category);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
