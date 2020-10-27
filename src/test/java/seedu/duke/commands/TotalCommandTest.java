package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.TransactionList;
import seedu.duke.utilities.Parser;
import seedu.duke.utilities.SetupTransactionData;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TotalCommandTest {

    private SetupTransactionData setupData = new SetupTransactionData();
    private TransactionList transactionList = setupData.loadTransactionData();
    //Expenses list shown to the user recently.
    private List<? extends ReadOnlyTransaction> lastShownList = Collections.emptyList();


    @Test
    public void execute_total() {
        TotalCommand command = new TotalCommand();
        command.setData(transactionList, lastShownList);
        assertEquals("The total amount you have spent so far is $221.10", command.execute().feedbackToUser);
    }

}
