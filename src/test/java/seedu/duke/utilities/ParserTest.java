package seedu.duke.utilities;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.commands.TotalCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    private Command addCommand = new AddCommand("lunch", 4.00, "2020-09-09");
    private Command deleteCommand = new DeleteCommand(1);
    private Command viewCommand = new ViewCommand();
    private Command totalCommand = new TotalCommand();

    private static final String ADD_COMMAND_STRING = "Add lunch $4.00 2020-09-09";
    private static final String DELETE_COMMAND_STRING = "delete 1";
    private static final String VIEW_COMMAND_STRING = "view";
    private static final String TOTAL_COMMAND_STRING = "total";
    private static final String EXIT_COMMAND_STRING = "exit";
    private static final String HELP_COMMAND_STRING = "help";
    private static final String SEARCH_COMMAND_STRING = "search test";


    @Test
    public void execute_parser() {
        final Command getAddCommand = new Parser().parseCommand(ADD_COMMAND_STRING);
        final Command getDeleteCommand = new Parser().parseCommand(DELETE_COMMAND_STRING);
        final Command getViewCommand = new Parser().parseCommand(VIEW_COMMAND_STRING);
        final Command getTotalCommand = new Parser().parseCommand(TOTAL_COMMAND_STRING);
        final Command getExitCommand = new Parser().parseCommand(EXIT_COMMAND_STRING);
        final Command getHelpCommand = new Parser().parseCommand(HELP_COMMAND_STRING);
        final Command getSearchCommand = new Parser().parseCommand(SEARCH_COMMAND_STRING);

        assertTrue(getAddCommand instanceof AddCommand);
        assertTrue(getDeleteCommand instanceof DeleteCommand);
        assertTrue(getViewCommand instanceof ViewCommand);
        assertTrue(getTotalCommand instanceof TotalCommand);
        assertTrue(getExitCommand instanceof ExitCommand);
        assertTrue(getHelpCommand instanceof HelpCommand);
        assertTrue(getSearchCommand instanceof SearchCommand);
    }

}
