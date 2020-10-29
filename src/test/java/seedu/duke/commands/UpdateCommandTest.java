package seedu.duke.commands;

import org.junit.jupiter.api.Test;
import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.TransactionList;
import seedu.duke.utilities.SetupTransactionData;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdateCommandTest {

    private SetupTransactionData setupData = new SetupTransactionData();
    private TransactionList transactionList = setupData.loadTransactionData();
    //Expenses list shown to the user recently.
    private List<? extends ReadOnlyTransaction> lastShownList = Collections.emptyList();

    @Test
    public void execute_update(){
        UpdateCommand command = new UpdateCommand(4,"Menthol Mints",2.10,null,"Food");
        command.setData(transactionList, lastShownList);
        assertEquals("Transaction updated.", command.execute().feedbackToUser);
    }
}
