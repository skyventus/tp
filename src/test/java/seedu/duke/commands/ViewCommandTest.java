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
    public static final String commandString2 = "view /sd 2020-09-28";
    public static final String commandString3 = "view /ed 2020-09-28";


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

        assertEquals("Displaying : All Time Transactions \nAbove are all transaction entered.", result);

        Command command2 = new Parser().parseCommand(commandString2);

        command2.setData(transactionList, lastShownList);

        String result2 = command2.execute().feedbackToUser;

        assertEquals("Displaying : Every Transaction After 2020-09-28 \nAbove are all transaction entered.", result2);

        Command command3 = new Parser().parseCommand(commandString3);

        command3.setData(transactionList, lastShownList);

        String result3 = command3.execute().feedbackToUser;

        assertEquals("Displaying :  \nAbove are all transaction entered.", result3);
    }

}
