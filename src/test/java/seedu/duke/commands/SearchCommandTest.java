package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.TransactionList;
import seedu.duke.utilities.SetupTransactionData;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchCommandTest {
    private SetupTransactionData setupData = new SetupTransactionData();
    private TransactionList transactionList = setupData.loadTransactionData();
    //Expenses list shown to the user recently.
    private List<? extends ReadOnlyTransaction> lastShownList = Collections.emptyList();

    @Test
    public void searchCommand_Test() {
        SearchCommand command = new SearchCommand("chicken");
        command.setData(transactionList, lastShownList);

        assertEquals("1 transactions listed!", command.execute().feedbackToUser);

        SearchCommand command2 = new SearchCommand("rice");
        command2.setData(transactionList, lastShownList);

        assertEquals("2 transactions listed!", command2.execute().feedbackToUser);

    }
}