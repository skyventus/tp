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

public class ViewCommandTest {
    public static final String commandString = "view";

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
    public void execute_view() {
        Command command = new Parser().parseCommand(commandString);

        command.setData(transactionList, lastShownList);

        String result = command.execute().feedbackToUser;

        assertEquals("Above are all transaction entered.", result);
//        assertEquals("1. Chicken Rice 4.0"
//                        + "2. Fried Rice 15.0"
//                        + "3. MSG Rice 20.0"
//                        + "4. Fish Curry Noodle 23.0".replaceAll("\r\n", ""),
//                outputStreamCaptor.toString().trim().replaceAll("\r\n", ""));

    }

}
