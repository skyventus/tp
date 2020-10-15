package seedu.duke.commands;

import seedu.duke.data.ReadOnlyTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CommandResult {

    /**
     * The feedback message to be shown to the user. Contains a description of the execution result.
     */
    public final String feedbackToUser;

    private final List<? extends ReadOnlyTransaction> transaction;


    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        transaction = null;
    }

    public CommandResult(String feedbackToUser, List<? extends ReadOnlyTransaction> transaction) {
        this.feedbackToUser = feedbackToUser;
        this.transaction = transaction;
    }

    public Optional<List<? extends ReadOnlyTransaction>> getRelevantTransactions() {
        return Optional.ofNullable(transaction);
    }
}
