package seedu.duke.commands;

public class HelpCommand extends Command {
    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE
                        + System.lineSeparator() + "\n" + AddCommand.MESSAGE_USAGE
                        + System.lineSeparator() + "\n" + DeleteCommand.MESSAGE_USAGE
                        + System.lineSeparator() + "\n" + SearchCommand.MESSAGE_USAGE
                        + System.lineSeparator() + "\n" + ViewCommand.MESSAGE_USAGE
                        + System.lineSeparator() + "\n" + TotalCommand.MESSAGE_USAGE
                        + System.lineSeparator() + "\n" + HelpCommand.MESSAGE_USAGE
                        + System.lineSeparator() + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
