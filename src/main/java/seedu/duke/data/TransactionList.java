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
        internalTransactionList.add(transaction);
    }

    public void removeTransaction(ReadOnlyTransaction toRemove) throws TransactionNotFound {
        internalTransactionList.remove(toRemove);
    }

    public void updateTransaction(ReadOnlyTransaction toUpdate, int index) throws TransactionNotFound {
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
    }

    public void generateReport(String exportPath, Date startDate, Date endDate) {
        Parser parser = new Parser();
        List<Transaction> requiredPeriodTransaction = new ArrayList<>();
        String timePeriod = "";
        Date earlyStartDate = new Date();
        Date lateEndDate = new Date();
        try {

            if (startDate != null || endDate != null) {
                for (Transaction transaction : internalTransactionList) {
                    if (startDate != null && endDate != null && transaction.getDate() != null) {
                        if (transaction.getDate().after(startDate) && transaction.getDate().before(endDate)) {
                            requiredPeriodTransaction.add(transaction);
                        }
                        timePeriod = Parser.sdf.format(startDate) + " - " + Parser.sdf.format(endDate);
                    } else if (startDate != null && transaction.getDate() != null) {
                        if (transaction.getDate().after(startDate)) {
                            requiredPeriodTransaction.add(transaction);
                        }
                        if (earlyStartDate.after(transaction.getDate())) {
                            earlyStartDate = transaction.getDate();
                        }

                        timePeriod = "Every Transaction After " + Parser.sdf.format(startDate);
                    } else if (endDate != null && transaction.getDate() != null) {
                        if (transaction.getDate().before(endDate)) {
                            requiredPeriodTransaction.add(transaction);
                        }

                        if (earlyStartDate.after(transaction.getDate())) {
                            earlyStartDate = transaction.getDate();
                        }
                        timePeriod = "Every Transaction Until " + Parser.sdf.format(endDate);
                    }
                }
            } else {
                requiredPeriodTransaction = internalTransactionList;
            }
            parser.generateReport(requiredPeriodTransaction, getTotalAmount(requiredPeriodTransaction), timePeriod);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


    }

    public double getTotalAmount(List<Transaction> transactions) {
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
