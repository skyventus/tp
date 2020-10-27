package seedu.duke.commands;

import seedu.duke.data.Transaction;

import java.util.Date;

public class ReportCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Report exported successfully.";

    public static final String COMMAND_WORD = "report";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a transaction to the NUS Expenses Tracker.\n"
            + "Parameters: DESCRIPTION $AMOUNT DATE...\n"
            + "Example: " + COMMAND_WORD
            + " Lunch at Com2 $4.50 14-Oct-2020";

    private Date startDate;
    private Date endDate;
    private String filePath;

    public ReportCommand(String filePath, Date startDate,
                         Date endDate) {
        this.filePath = filePath;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public CommandResult execute() {
        try {
            transactionList.generateReport(this.filePath, this.startDate, this.endDate);
            return new CommandResult(String.format(MESSAGE_SUCCESS));
        } catch (Exception e) {
            return new CommandResult(e.getMessage());
        }
    }
}
