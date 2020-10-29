package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;
import seedu.duke.exception.IllegalValueException;
import seedu.duke.utilities.Parser;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionDecodedTest {

    @Test
    public void decodeTransactionsTest() throws IllegalValueException, ParseException {
        List<String> decodeListString = new ArrayList<String>();
        decodeListString.add("2020-10-27 Chicken Rice $4.00 /c FOOD");
        TransactionList transactionsList = TransactionListDecoded.decodeTransactions(decodeListString);
        final DecimalFormat df2 = new DecimalFormat("#.00");

        var actualDescription = transactionsList.getTransactionList().get(0).getDescription();
        var actualAmount = transactionsList.getTransactionList().get(0).getAmount();
        var actualDate = transactionsList.getTransactionList().get(0).getDate();

        assertEquals("Chicken Rice", actualDescription);
        assertEquals("4.00", df2.format(actualAmount));
        assertEquals(Parser.sdf.parse("2020-10-27"), actualDate);


    }
}
