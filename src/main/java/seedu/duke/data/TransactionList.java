package seedu.duke.data;

import seedu.duke.utilities.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionList implements Iterable<Transaction> {

    private final List<Transaction> internalTransactionList;

    public TransactionList() {
        this.internalTransactionList = new ArrayList<>();
    }

    public TransactionList(List<Transaction> listOfTransaction) {
        this.internalTransactionList = listOfTransaction;
    }

    public List<Transaction> getTransactionList() {
        List<Transaction> list = internalTransactionList.stream().collect(Collectors.toList());
        return list;
    }

    public void addTransaction(Transaction transaction) {
        assert transaction != null : "transaction should not be null object";
        internalTransactionList.add(transaction);
    }

    public void removeTransaction(ReadOnlyTransaction toRemove) throws TransactionNotFound {
        assert toRemove != null : "ReadOnlyTransaction should not be null object";
        internalTransactionList.remove(toRemove);
    }

    public void updateTransaction(ReadOnlyTransaction toUpdate, int index) throws TransactionNotFound {
        assert toUpdate != null : "Transaction should not be null object";

        ReadOnlyTransaction target = internalTransactionList.get(index - 1);
        if (toUpdate.getAmount() > 0.0) {
            target.setAmount(toUpdate.getAmount());
        }
        if (!toUpdate.getDescription().isEmpty()) {
            target.setDescription(toUpdate.getDescription());
        }
        if (toUpdate.getDate() != null) {
            target.setDate(toUpdate.getDate());
        }
        if (toUpdate.getCategory() !=null && !toUpdate.getCategory().isEmpty()) {
            target.setCategory(ReadOnlyTransaction.CategoryType.valueOf(toUpdate.getCategory()));
        }
    }

    public List<Transaction> getTransactionsByCategory(String category) {
        List<Transaction> transactionsAccordingToCategory = new ArrayList<>();
        for (Transaction transaction: internalTransactionList) {
            if (transaction.getCategory().equalsIgnoreCase(category)) {
                transactionsAccordingToCategory.add(transaction);
            }
        }
        return transactionsAccordingToCategory;
    }

    public List<Transaction> getTransactionsWithinPeriod(Date startDate, Date endDate) {
        List<Transaction> requiredPeriodTransaction = new ArrayList<>();
        if (startDate != null || endDate != null) {
            for (Transaction transaction : internalTransactionList) {
                if (startDate != null && endDate != null && transaction.getDate() != null) {
                    if (transaction.getDate().after(startDate) && transaction.getDate().before(endDate)) {
                        requiredPeriodTransaction.add(transaction);
                    }
                } else if (startDate != null && transaction.getDate() != null) {
                    if (transaction.getDate().after(startDate)) {
                        requiredPeriodTransaction.add(transaction);
                    }

                } else if (endDate != null && transaction.getDate() != null) {
                    if (transaction.getDate().before(endDate)) {
                        requiredPeriodTransaction.add(transaction);
                    }
                }
            }
        } else {
            requiredPeriodTransaction = internalTransactionList;
        }
        return requiredPeriodTransaction;
    }

    public String getDatePeriodString(Date startDate, Date endDate) {
        String timePeriod = "";
        Date earlyStartDate = new Date();
        List<Transaction> requiredPeriodTransaction = getTransactionsWithinPeriod(startDate, endDate);
        for (Transaction transaction : requiredPeriodTransaction) {
            if (startDate != null && endDate != null && transaction.getDate() != null) {
                timePeriod = Parser.sdf.format(startDate) + " - " + Parser.sdf.format(endDate);
            } else if (startDate != null && transaction.getDate() != null) {
                if (earlyStartDate.after(transaction.getDate())) {
                    earlyStartDate = transaction.getDate();
                }
                timePeriod = "Every Transaction After " + Parser.sdf.format(startDate);
            } else if (endDate != null && transaction.getDate() != null) {

                if (earlyStartDate.after(transaction.getDate())) {
                    earlyStartDate = transaction.getDate();
                }
                timePeriod = "Every Transaction Before " + Parser.sdf.format(endDate);
            }
        }
        if (startDate == null && endDate == null) {
            timePeriod = "All Time Transactions";
        }
        return timePeriod;
    }

    public void generateReport(String exportPath, Date startDate, Date endDate) {
        Parser parser = new Parser();
        List<Transaction> requiredPeriodTransaction = getTransactionsWithinPeriod(startDate, endDate);
        String timePeriod = "";
        Date earlyStartDate = new Date();
        Date lateEndDate = new Date();
        try {

            timePeriod = getDatePeriodString(startDate, endDate);

            parser.generateReport(requiredPeriodTransaction, getTotalAmount(requiredPeriodTransaction), timePeriod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public double getTotalAmount(List<Transaction> transactions) {
        assert transactions != null;
        double totalAmount = 0.0;
        for (Transaction transaction : transactions) {
            totalAmount = totalAmount + transaction.getAmount();
        }
        return totalAmount;
    }

    @Override
    public Iterator<Transaction> iterator() {
        return internalTransactionList.iterator();
    }

    public static class TransactionNotFound extends Exception {

    }
}
