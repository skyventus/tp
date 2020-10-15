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

    @Override
    public Iterator<Transaction> iterator() {
        return internalTransactionList.iterator();
    }

    public static class TransactionNotFound extends Exception {

    }
}
