package seedu.duke.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionList implements Iterable<Transaction> {

    private final List<Transaction> internalTransactionList;

    public TransactionList() {
        this.internalTransactionList = new ArrayList<>();
    }

    public List<Transaction> getTransactionList() {
        return internalTransactionList;
    }

    public void addTransaction(Transaction transaction) {
        internalTransactionList.add(transaction);
    }

    @Override
    public Iterator<Transaction> iterator() {
        return internalTransactionList.iterator();
    }
}
