package seedu.duke.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TransactionList implements Iterable<Transaction> {

    private List<Transaction> transactionList;

    private final List<Transaction> internalTransactionList;


    public TransactionList(List<Transaction> transactionList) {
        this.internalTransactionList = new ArrayList<>();
    }

    public List<Transaction> getTransactionList() {
        return internalTransactionList;
    }

    @Override
    public Iterator<Transaction> iterator() {
        return internalTransactionList.iterator();
    }
}
