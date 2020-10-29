package seedu.duke.commands;

import seedu.duke.data.Transaction;

import java.util.Date;

public class ReportCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Report exported successfully.";

    public static final String COMMAND_WORD = "report";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Generate transactions summary report and export as excel.\n"
            + "Parameters: STARTDATE ENDDATE...\n"
            + "Example: " + COMMAND_WORD
            + " /sd 2020-09-28 /ed 2020-09-30";

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
