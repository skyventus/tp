package seedu.duke.utilities;


import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.commands.TotalCommand;
import seedu.duke.commands.UpdateCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.AddBudgetCommand;
import seedu.duke.commands.ViewBudgetCommand;
import seedu.duke.common.Constants;

import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.duke.common.Constants.MESSAGE_INVALID_COMMAND_FORMAT;

public class Parser {

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)", Pattern.CASE_INSENSITIVE);
    public static final Pattern ADD_COMMAND_FORMAT =
            Pattern.compile("(?<description>\\s?[\\w+]*)(?<amount>\\s\\${1}\\d+\\.?\\d{0,2}){1}(?<date>\\s\\d{4}-{1}"
                            + "\\d{2}-{1}\\d{2})?(?<category>\\s\\/{1}c{1}\\s{1}[a-zA-Z]+)?",
                    Pattern.CASE_INSENSITIVE);
    public static final Pattern UPDATE_COMMAND_FORMAT =
            Pattern.compile("(?<index>^\\d$)(?<usage>^\\\\d$)(?<date>.*)",
                    Pattern.CASE_INSENSITIVE);
    public static final Pattern SEARCH_COMMAND_FORMAT =
            Pattern.compile("(?<keyword>^[a-zA-Z0-9_]+$)",Pattern.CASE_INSENSITIVE);
    public static final Pattern ADDBUDGET_COMMAND_FORMAT =
            Pattern.compile("(?<category>[^/]*)(?<description>[^$]*)(?<amount>\\${1}\\d+\\.?\\d{0,2})",
                    Pattern.CASE_INSENSITIVE);

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, userInput,
                    HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case DeleteCommand.COMMAND_WORD:
            return createDeleteCommand(arguments);

        case SearchCommand.COMMAND_WORD:
            return prepareSearchCommand(arguments);

        case TotalCommand.COMMAND_WORD:
            return createTotalCommand(arguments);

        case ViewCommand.COMMAND_WORD:
            return createViewCommand(arguments);

        case AddBudgetCommand.COMMAND_WORD:
            return createAddBudgetCommand(arguments);

        case ViewBudgetCommand.COMMAND_WORD:
            return createViewBudgetCommand(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(arguments);

        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    private Command prepareAdd(String args) {
        final Matcher matcher = ADD_COMMAND_FORMAT.matcher(args.trim());

        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, args.trim(),
                    HelpCommand.MESSAGE_USAGE));
        }
        try {
            String dateString = matcher.group("date");
            Date date;
            if (!(dateString == null || dateString.isEmpty())) {
                date = sdf.parse(dateString);
            } else {
                date = null;
            }
            String categoryString = matcher.group("category");
            String category;
            if (!(categoryString == null || categoryString.isEmpty())) {
                categoryString = categoryString.substring(categoryString.indexOf("/") + 2).trim();
                category = categoryString;
            } else {
                category = "";
            }

            return new AddCommand(
                    matcher.group("description").trim(),

                    Double.parseDouble(matcher.group("amount").replace("$", "")),

                    date,

                    category.toUpperCase()

            );
        } catch (Exception e) {
            System.out.println("Inside PrepareAdd");
            e.printStackTrace();
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, args.trim(),
                    HelpCommand.MESSAGE_USAGE));
        }
    }

    private Command createDeleteCommand(String args) {
        Command finalCommand;
        try {
            int index = Integer.parseInt(args.trim());
            finalCommand = new DeleteCommand(index);
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("Incorrect Delete Command");
        }
        return finalCommand;
    }

    private Command prepareSearchCommand(String args) {
        final Matcher matcher = SEARCH_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Incorrect Search Command");
        }
        try {
            return new SearchCommand(
                    matcher.group("keyword").trim()
            );
        } catch (Exception e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command createTotalCommand(String args) {
        Command finalCommand;
        try {
            finalCommand = new TotalCommand();
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("Incorrect Total Command");
        }
        return finalCommand;
    }

    private Command createViewCommand(String args) {
        Command finalCommand;
        try {
            finalCommand = new ViewCommand();
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("Incorrect View Command");
        }
        return finalCommand;
    }

    private Command prepareUpdate(String args) {

        String temp = "";
        String usage = "";
        double amount = 0.0;
        Date date = null;
        String category = "";
        try {
            final Integer index = Integer.parseInt(args.trim().split(" ")[0]);
            if (args.indexOf(Constants.UPDATE_COMMAND_AMOUNT_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.UPDATE_COMMAND_AMOUNT_PARAM) + 2);
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                amount = Double.parseDouble(temp.trim());
            }

            if (args.indexOf(Constants.UPDATE_COMMAND_DATE_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.UPDATE_COMMAND_DATE_PARAM) + 2);
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                date = sdf.parse(temp.trim());
            }

            if (args.indexOf(Constants.UPDATE_COMMAND_USAGE_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.UPDATE_COMMAND_USAGE_PARAM) + 2);
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                usage = temp;
            }
            if (args.indexOf(Constants.UPDATE_COMMAND_CATEGORY_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.UPDATE_COMMAND_CATEGORY_PARAM) + 2);
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                category = temp;
            }

            return new UpdateCommand(index, usage, amount, date,category);

        } catch (Exception e) {
            e.printStackTrace();
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command createAddBudgetCommand(String args) {
        final Matcher matcher = ADDBUDGET_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand("Incorrect Add Command");
        }
        try {
            return new AddBudgetCommand(
                    matcher.group("category").trim(),
                    matcher.group("description").trim(),

                    Double.parseDouble(matcher.group("amount").replace("$", ""))

            );
        } catch (Exception e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command createViewBudgetCommand(String args) {
        Command finalCommand;
        try {
            finalCommand = new ViewBudgetCommand();
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("Incorrect View Budget Command");
        }
        return finalCommand;
    }
}
