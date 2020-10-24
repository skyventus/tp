package seedu.duke.data;

import java.util.ArrayList;
import java.util.Collections;
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

    @Override
    public Iterator<Transaction> iterator() {
        return internalTransactionList.iterator();
    }

    public static class TransactionNotFound extends Exception {

    }
}
