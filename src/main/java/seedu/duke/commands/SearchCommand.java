package seedu.duke.commands;

import seedu.duke.data.ReadOnlyTransaction;

import java.util.ArrayList;
import java.util.List;

public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Search for transaction(s) in the NUS Expenses Tracker using a single keyword\n"
            + "Parameters: KEYWORD...\n"
            + "Example: " + COMMAND_WORD
            + " lunch";

    private final String keyword;

    public SearchCommand(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword() {
        return new String(keyword);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyTransaction> transactionsFound = getTransactionsWithNameContainingKeyword(keyword);
        return new CommandResult(getMessageForTransactionListShownSummary(transactionsFound), transactionsFound);
    }

    private List<ReadOnlyTransaction> getTransactionsWithNameContainingKeyword(String keyword) {
        final List<ReadOnlyTransaction> matchedTransactions = new ArrayList<>();
        for (ReadOnlyTransaction transaction : transactionList.getTransactionList()) {
            if (transaction.getDescription().contains(keyword)) {
                matchedTransactions.add(transaction);
            }
        }
        return matchedTransactions;
    }
}
