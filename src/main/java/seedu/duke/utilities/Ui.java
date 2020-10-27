package seedu.duke.utilities;

import seedu.duke.commands.CommandResult;
import seedu.duke.data.ReadOnlyTransaction;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static seedu.duke.common.Constants.MESSAGE_GOODBYE;
import static seedu.duke.common.Constants.MESSAGE_WELCOME;

/**
 * Display / Show to user stuffs.
 */
public class Ui {

    //Decorative prefix.
    private static final String LINE_PREFIX = "[\\\\]";

    // A platform independent line separator.
    private static final String LS = System.lineSeparator();

    //Offset to use 1-indexing.
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    // Format of indexed list item.
    private static final String MESSAGE_INDEXED_LIST_ITEM = " %1$d. %2$s";

    private static final String DIVIDER = "***************************************************";

    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showWelcomeMessage(String version) {
        //String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        showToUser(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                DIVIDER);
    }

    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    /**
     * Read in the next input line.
     *
     * @return
     */
    public String readUserCommand() {
        out.print(LINE_PREFIX + "Input command: ");
        String fullLineInput = in.nextLine().trim();

        showToUser("[Command entered:" + fullLineInput + "]");
        return fullLineInput;
    }

    /**
     * Close the inputstream.
     */
    public void endInputFeed() {
        in.close();
    }


    public void showToUser(String... message) {
        out.println(LINE_PREFIX);
        for (String m : message) {
            out.println(LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX));
        }
    }

    public void showResultToUser(CommandResult result) {
        final Optional<List<? extends ReadOnlyTransaction>> resultTransaction = result.getRelevantTransactions();
        if (resultTransaction.isPresent()) {
            showTransactionListView(resultTransaction.get());
        }
        showToUser(DIVIDER, result.feedbackToUser, DIVIDER);
    }

    /**
     * Shows a list of expenses to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void showTransactionListView(List<? extends ReadOnlyTransaction> transactions) {
        final List<String> formattedTransaction = new ArrayList<>();
        for (ReadOnlyTransaction transaction : transactions) {
            formattedTransaction.add(transaction.getAsTextShowAll());
        }
        showToUserAsIndexedList(formattedTransaction);
    }

    /**
     * Shows a list of strings to the user, formatted as an indexed list.
     */
    private void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    /**
     * Formats a list of strings as a viewable indexed list.
     */
    private static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
