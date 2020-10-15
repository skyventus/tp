package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.TransactionList;
import seedu.duke.utilities.SetupTransactionData;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeleteCommandTest {
    private SetupTransactionData setupData = new SetupTransactionData();
    private TransactionList transactionList = setupData.loadTransactionData();
    //Expenses list shown to the user recently.
    private List<? extends ReadOnlyTransaction> lastShownList = Collections.emptyList();

    @Test
    public void execute_delete() {
        DeleteCommand command = new DeleteCommand(2);
        command.setData(transactionList, lastShownList);
        command.execute();
        assertEquals(3, command.transactionList.getTransactionList().size());

        DeleteCommand command2 = new DeleteCommand(1);
        command.setData(transactionList, lastShownList);

        command.execute();
        assertEquals(2, command.transactionList.getTransactionList().size());

    }

    @Test
    public void execute_delete_withWrongIndex() {
        DeleteCommand command = new DeleteCommand(5);
        command.setData(transactionList, lastShownList);


        assertEquals("Out of index", command.execute().feedbackToUser);

    }

}
