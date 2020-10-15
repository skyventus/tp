package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.utilities.SetupTransactionData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionListTest {

    private SetupTransactionData setupData = new SetupTransactionData();
    private TransactionList transactionList = setupData.loadTransactionData();


    //Test list index 0 and 1 to get its description
    @Test
    public void transaction_getList_getDescription() {
        assertEquals("Chicken Rice", transactionList.getTransactionList().get(0).getDescription());
        assertEquals("Fried Rice", transactionList.getTransactionList().get(1).getDescription());
    }

    //Test list index 0 and 1 to get its amount.
    @Test
    public void transactionList_getList_getAmount() {
        assertEquals(4.00, transactionList.getTransactionList().get(0).getAmount());
        assertEquals(15.00, transactionList.getTransactionList().get(1).getAmount());
    }

    //Test removing index 0 transaction and assert the size.
    @Test
    public void transactionList_getList_removeTransaction() {
        ReadOnlyTransaction transactionToRemove = transactionList.getTransactionList().get(0);

        try {
            transactionList.removeTransaction(transactionToRemove);
            assertEquals(3, transactionList.getTransactionList().size());
        } catch (TransactionList.TransactionNotFound transactionNotFound) {
            assertEquals("", transactionNotFound.getMessage());
        }

    }

}
