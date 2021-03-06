package seedu.duke.utilities;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import seedu.duke.commands.AddCommand;
import seedu.duke.commands.DeleteCommand;
import seedu.duke.commands.DeleteBudgetCommand;
import seedu.duke.commands.Command;
import seedu.duke.commands.IncorrectCommand;
import seedu.duke.commands.ReportCommand;
import seedu.duke.commands.SearchCommand;
import seedu.duke.commands.TotalCommand;
import seedu.duke.commands.UpdateCommand;
import seedu.duke.commands.ViewCommand;
import seedu.duke.commands.ExitCommand;
import seedu.duke.commands.HelpCommand;
import seedu.duke.commands.AddBudgetCommand;
import seedu.duke.commands.ViewBudgetCommand;
import seedu.duke.common.Constants;
import seedu.duke.data.Transaction;


import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
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
            Pattern.compile("(?<description>\\s?[a-zA-Z\\s\\S]+)(?<amount>\\s\\${1}\\d+\\.?\\d{0,2}){1}(?<date>\\s"
                            + "\\d{4}-{1}\\d{2}-{1}\\d{2})?(?<category>\\s\\/{1}c{1}\\s{1}[a-zA-Z]+)?",
                            Pattern.CASE_INSENSITIVE);
    public static final Pattern UPDATE_COMMAND_FORMAT =
            Pattern.compile("(?<index>^\\d$)(?<usage>^\\\\d$)(?<date>.*)",
                    Pattern.CASE_INSENSITIVE);
    public static final Pattern SEARCH_COMMAND_FORMAT =
            Pattern.compile("(?<keyword>^[a-zA-Z0-9_]+$)", Pattern.CASE_INSENSITIVE);
    public static final Pattern ADDBUDGET_COMMAND_FORMAT =
            Pattern.compile("(?<category>[^/]*)(?<description>[^$]*)(?<amount>\\${1}\\d+\\.?\\d{0,2})",
                    Pattern.CASE_INSENSITIVE);

    public static final Pattern VIEW_COMMAND_CATEGORY_FILTER_FORMAT = Pattern.compile("(?<categoryFilter>[a-zA-Z]+$)");


    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat sdfFull = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

        case DeleteBudgetCommand.COMMAND_WORD :
            return createDeleteBudgetCommand(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(arguments);

        case ReportCommand.COMMAND_WORD:
            return prepareReportCommand(arguments);

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
            String temp = "";
            Date startDate = null;
            Date endDate = null;

            if (args.indexOf(Constants.VIEW_COMMAND_START_DATE_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.VIEW_COMMAND_START_DATE_PARAM) + 3);
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                startDate = sdf.parse(temp.trim());
                assert startDate != null : "Date cannot be null after parse";
            }

            if (args.indexOf(Constants.VIEW_COMMAND_END_DATE_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.VIEW_COMMAND_END_DATE_PARAM) + 3);
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                endDate = sdf.parse(temp.trim());
                assert endDate != null : "Date cannot be null after parse";
            }
            if (startDate == null && endDate == null) {
                finalCommand = new TotalCommand();
            } else {
                finalCommand = new TotalCommand(startDate,endDate);
            }
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("Incorrect Total Command");
        }
        return finalCommand;
    }

    private Command createViewCommand(String args) {
        final Matcher categoryMatcher = VIEW_COMMAND_CATEGORY_FILTER_FORMAT.matcher(args.trim());
        Command finalCommand;
        try {
            if (categoryMatcher.matches()) {

                return new ViewCommand(categoryMatcher.group("categoryFilter").trim());

            } else {
                String temp = "";
                Date startDate = null;
                Date endDate = null;
                if (args.indexOf(Constants.VIEW_COMMAND_START_DATE_PARAM) > 0) {
                    temp = args.substring(args.indexOf(Constants.VIEW_COMMAND_START_DATE_PARAM) + 3);
                    if (temp.indexOf("/") > 0) {
                        temp = temp.substring(0, temp.indexOf("/"));
                    }
                    startDate = sdf.parse(temp.trim());
                    assert startDate != null : "Date cannot be null after parse";
                }

                if (args.indexOf(Constants.VIEW_COMMAND_END_DATE_PARAM) > 0) {
                    temp = args.substring(args.indexOf(Constants.VIEW_COMMAND_END_DATE_PARAM) + 3);
                    if (temp.indexOf("/") > 0) {
                        temp = temp.substring(0, temp.indexOf("/"));
                    }
                    endDate = sdf.parse(temp.trim());
                    assert endDate != null : "Date cannot be null after parse";
                }

                return new ViewCommand(startDate, endDate);
            }
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("Incorrect View Command");
        }
        return new ViewCommand(null, null);
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
                usage = temp.trim();
            }
            if (args.indexOf(Constants.UPDATE_COMMAND_CATEGORY_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.UPDATE_COMMAND_CATEGORY_PARAM) + 2).trim();
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                category = temp.trim().toUpperCase();
            }

            return new UpdateCommand(index, usage, amount, date, category);

        } catch (IllegalArgumentException argumentException) {
            return new IncorrectCommand("Incorrect update command: " + args.trim());
        } catch (Exception e) {
            return new IncorrectCommand("Incorrect update command: " + e.getMessage());
        }
    }

    private Command createAddBudgetCommand(String args) {
        final Matcher matcher = ADDBUDGET_COMMAND_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand("Incorrect Budget Add Command");
        }
        if (!matcher.group("category").toUpperCase().startsWith("D")
                && !matcher.group("category").toUpperCase().startsWith("W")
                && !matcher.group("category").toUpperCase().startsWith("M")) {
            return new IncorrectCommand("Incorrect Budget Category");
        }
        if (!args.contains("/")) {
            return new IncorrectCommand("Incorrect Budget Description");
        }
        try {
            return new AddBudgetCommand(
                    matcher.group("category").trim(),
                    matcher.group("description").trim().replace("/", ""),

                    Double.parseDouble(matcher.group("amount").replace("$", ""))
            );
        } catch (Exception e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command createDeleteBudgetCommand(String args) {
        Command finalCommand;
        try {
            int index = Integer.parseInt(args.trim());
            finalCommand = new DeleteBudgetCommand(index);
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("Incorrect Delete Budget Command");
        }
        return finalCommand;
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

    private Command prepareReportCommand(String args) {
        Command finalCommand;
        try {
            String temp = "";
            Date startDate = null;
            Date endDate = null;
            if (args.indexOf(Constants.REPORT_COMMAND_START_DATE_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.REPORT_COMMAND_START_DATE_PARAM) + 3);
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                startDate = sdf.parse(temp.trim());
                assert startDate != null : "Date cannot be null after parse";
            }

            if (args.indexOf(Constants.REPORT_COMMAND_END_DATE_PARAM) > 0) {
                temp = args.substring(args.indexOf(Constants.REPORT_COMMAND_END_DATE_PARAM) + 3);
                if (temp.indexOf("/") > 0) {
                    temp = temp.substring(0, temp.indexOf("/"));
                }
                endDate = sdf.parse(temp.trim());
                assert endDate != null : "Date cannot be null after parse";
            }

            finalCommand = new ReportCommand(null, startDate, endDate);
        } catch (Exception e) {
            finalCommand = new IncorrectCommand("Incorrect Report Command");
        }
        return finalCommand;
    }

    public void generateReport(List<Transaction> transactionList, double totalAmount, String timePeriod) {
        String excelFilePath = "TransactionReportSummary.xlsx";
        try {


            Workbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = (XSSFSheet) workbook.createSheet("Summary");
            writeHeaderLine(sheet);
            writeDataLines(transactionList, workbook, sheet, totalAmount, timePeriod);

            FileOutputStream outputStream = new FileOutputStream(excelFilePath);
            workbook.write(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeHeaderLine(XSSFSheet sheet) {

        Row headerRow = sheet.createRow(0);

        Cell headerCell = headerRow.createCell(0);
        headerCell.setCellValue("Date");

        headerCell = headerRow.createCell(1);
        headerCell.setCellValue("Usage");

        headerCell = headerRow.createCell(2);
        headerCell.setCellValue("Category");

        headerCell = headerRow.createCell(3);
        headerCell.setCellValue("Amount");

    }

    private void writeDataLines(List<Transaction> transactions, Workbook workbook,
                                XSSFSheet sheet, double totalAmount, String timePeriod) {
        int rowCount = 1;

        Row row = null;
        Cell cell = null;

        for (Transaction transaction : transactions) {
            assert transactions != null : "Cannot export empty transactions";
            String date = "";
            final String usage = transaction.getDescription();
            final double amount = transaction.getAmount();
            final String category = transaction.getCategory();
            if (transaction.getDate() != null) {
                date = sdf.format(transaction.getDate());
            }
            row = sheet.createRow(rowCount++);

            int columnCount = 0;
            cell = row.createCell(columnCount++);
            cell.setCellValue(date);

            cell = row.createCell(columnCount++);
            cell.setCellValue(usage);

            cell = row.createCell(columnCount++);
            cell.setCellValue(category);

            cell = row.createCell(columnCount++);
            cell.setCellValue("$" + amount);
        }
        rowCount++;
        row = sheet.createRow(rowCount++);
        cell = row.createCell(2);
        cell.setCellValue("Total :");
        cell = row.createCell(3);
        cell.setCellValue("$" + totalAmount);

        row = sheet.createRow(rowCount++);
        cell = row.createCell(0);
        cell.setCellValue("Generated on :" + sdfFull.format(new Date()));

        row = sheet.createRow(rowCount++);
        cell = row.createCell(0);
        cell.setCellValue("Generate Period : " + timePeriod);

    }
}
