package seedu.duke.storage;

import org.junit.jupiter.api.Test;
import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;
import seedu.duke.utilities.Parser;

import java.text.ParseException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionEncodedTest {

    @Test
    public void transactionEncodedTest() throws ParseException {
        TransactionList transactionList = new TransactionList();

        Transaction transaction = new Transaction("Chicken Rice", 4.00, Parser.sdf.parse("2020-10-27"), "FOOD");
        transactionList.addTransaction(transaction);

        List<String> encodedTransactionList = TransactionListEncoded.encodeTransactionList(transactionList);

        assertEquals("2020-10-27 Chicken Rice $4.00 /c FOOD", encodedTransactionList.get(0).trim());
    }
}
