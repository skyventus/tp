package seedu.duke.commands;

import seedu.duke.data.Budget;
import seedu.duke.data.Transaction;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ViewBudgetCommand extends Command {

    public static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static final Date today = new Date();
    Calendar cal = Calendar.getInstance();
    public static final String COMMAND_WORD = "budgetview";
    public static final String MESSAGE_SUCCESS = "Above are all budgets entered.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": View a list of budgets added to the NUS Expenses Tracker.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {

        double total = 0.00;
        List<Budget> allBudgets = budgetList.getBudgetList();
        List<Transaction> allTransactions = transactionList.getTransactionList();;

        for (Budget budget: allBudgets) {
            System.out.println("[\\\\]CATEGORY: " + budget.getCategory()
                    + " || DESCRIPTION: " + budget.getDescription()
                    + " || Budget : $" + budget.getAmount());

            total = 0.0;
            switch (budget.getCategory()) {
                case ("DAILY") :
                    for (Transaction transaction: allTransactions) {
                        if(transaction.getDate() != null
                                && sdf.format(transaction.getDate()).compareTo(sdf.format(today)) == 0
                                && transaction.getCategory().equals(budget.getDescription())) {
                            total = total + transaction.getAmount();
                        }

                    }
                    System.out.println("[\\\\] Current Spending TODAY for CATEGORY "
                        + budget.getDescription() + " is $" + total);
                    break;

                case("WEEKLY") :
                    cal.setTime(today);
                    int todayweek = cal.get(Calendar.WEEK_OF_YEAR);
                    int week = 0;
                    for (Transaction transaction: allTransactions) {
                        if (transaction.getDate() != null) {
                            cal.setTime(transaction.getDate());
                            week = cal.get(Calendar.WEEK_OF_YEAR);
                            if (todayweek == week &&
                                    transaction.getCategory().equals(budget.getDescription())) {
                                total = total + transaction.getAmount();
                            }
                        }
                    }
                    System.out.println("[\\\\] Current Spending MONTHLY for CATEGORY "
                            + budget.getDescription() + " is $" + total);
                    break;

                case("MONTHLY") :
                    cal.setTime(today);
                    int todaymonth = cal.get(Calendar.MONTH);
                    int month = 0;
                    for (Transaction transaction: allTransactions) {
                        if (transaction.getDate() != null) {
                            cal.setTime(transaction.getDate());
                            month = cal.get(Calendar.MONTH);
                            if (todaymonth == month &&
                                    transaction.getCategory().equals(budget.getDescription())) {
                                total = total + transaction.getAmount();
                            }
                        }

                    }
                    new CommandResult("Test");
                    System.out.println("[\\\\] Current Spending MONTHLY for CATEGORY "
                            + budget.getDescription() + " is $" + total);
                    break;

                default :
                    System.out.println("Nothing happened");
                    break;
            }

            if (total <= budget.getAmount()) {
                System.out.println("[\\\\] You expenses is on track. Good Job!" + "\n[\\\\]");
            } else {
                System.out.println("[\\\\] You have overspent for this category!" + "\n[\\\\]");
            }
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS));
    }
}
