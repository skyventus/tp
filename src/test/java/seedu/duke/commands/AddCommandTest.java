package seedu.duke.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.duke.data.ReadOnlyTransaction;
import seedu.duke.data.TransactionList;
import seedu.duke.utilities.Parser;
import seedu.duke.utilities.SetupTransactionData;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddCommandTest {
    public static final String commandString = "Add lunch $4.00 2020-09-20";

    private SetupTransactionData setupData = new SetupTransactionData();
    private TransactionList transactionList = setupData.loadTransactionData();
    //Expenses list shown to the user recently.
    private List<? extends ReadOnlyTransaction> lastShownList = Collections.emptyList();

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void execute_add() {
        Command command = new Parser().parseCommand(commandString);

        command.setData(transactionList, lastShownList);

        String result = command.execute().feedbackToUser;

        assertEquals("New transaction added.", result);


    }

}
