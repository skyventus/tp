package seedu.duke.utilities;


import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.commands.TotalCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    //public static final Pattern BASIC_COMMAND_FORMAT =
    // Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)",Pattern.CASE_INSENSITIVE);
    //public static final Pattern ADD_COMMAND_FORMAT =
    // Pattern.compile("(?<usage>\\S+)(?<arguments>.*)");

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)", Pattern.CASE_INSENSITIVE);
    public static final Pattern ADD_COMMAND_FORMAT =
            Pattern.compile("(?<description>\\w+) (?<amount>\\${1}\\d+\\.\\d{2}) (?<date>.*)");


    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("parseCommand");
            //return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case DeleteCommand.COMMAND_WORD:
            return createDeleteCommand(arguments);

        case SearchCommand.COMMAND_WORD:
            return createSearchCommand(arguments);

        case TotalCommand.COMMAND_WORD:
            return createTotalCommand(arguments);

        case ViewCommand.COMMAND_WORD:
            return createViewCommand(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    //    private Command createAddCommand(String args) {
    //        Command finalCommand;
    //        String usage = "";
    //        Double amount = 0.0;
    //        String date = "";
    //        try {
    //            usage = args.split("\\$")[0];
    //            String amount1 = args.substring(args.indexOf("$") + 1);
    //            String everythingAfterSign = amount1.trim();
    //            if (everythingAfterSign.indexOf(" ") != -1) {
    //                amount1 = everythingAfterSign.split(" ")[0];
    //                date = everythingAfterSign.split(" ")[1];
    //            }
    //            amount = Double.parseDouble(amount1);
    //            finalCommand = new AddCommand(usage, amount, date);
    //        } catch (Exception e) {
    //            e.printStackTrace();
    //            finalCommand = new IncorrectCommand("createAddCommand");
    //        }
    //        return finalCommand;
    //  }

    private Command prepareAdd(String args) {
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand("prepareAdd");
        }
        try {
            return new AddCommand(
                    matcher.group("description"),

                    Double.parseDouble(matcher.group("amount").replace("$", "")),

                    matcher.group("date")

            );
        } catch (Exception e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command createDeleteCommand(String args) {
        Command finalCommand;
        try {
            int index = Integer.parseInt(args.trim());
            finalCommand = new DeleteCommand(index);
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("createDeleteCommand");
        }
        return finalCommand;
    }

    private Command createSearchCommand(String args) {
        Command finalCommand;
        try {
            finalCommand = new SearchCommand();
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("createSearchCommand");
        }
        return finalCommand;
    }

    private Command createTotalCommand(String args) {
        Command finalCommand;
        try {
            finalCommand = new TotalCommand();
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("createTotalCommand");
        }
        return finalCommand;
    }

    private Command createViewCommand(String args) {
        Command finalCommand;
        try {
            finalCommand = new ViewCommand();
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("createViewCommand");
        }
        return finalCommand;
    }

}
