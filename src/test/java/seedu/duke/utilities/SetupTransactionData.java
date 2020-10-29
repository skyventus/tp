package seedu.duke.utilities;

import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Class to generate typical test test.
 */
public class SetupTransactionData {

    private Transaction transactionData1;
    private Transaction transactionData2;
    private Transaction transactionData3;
    private Transaction transactionData4;
    private TransactionList transactionList = new TransactionList();

    public SetupTransactionData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {

            this.transactionData1 = new Transaction("Chicken Rice", 4.00, sdf.parse("2020-10-27"), "FOOD");
            this.transactionData2 = new Transaction("Fried Rice", 15.00, sdf.parse("2020-10-28"), "FOOD");
            this.transactionData3 = new Transaction("Corsair Keyboard", 200.00, null, "ENTERTAINMENT");
            this.transactionData4 = new Transaction("Menthol Mints", 2.10, null, "");



        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public TransactionList loadTransactionData() {
        try {
            for (Transaction t : getTransactionData()) {
                this.transactionList.addTransaction(t);
            }
            return this.transactionList;

        } catch (Exception e) {
            assert false : "Error encountered";
        }
        return this.transactionList;
    }

    public Transaction[] getTransactionData() {
        return new Transaction[]{transactionData1, transactionData2, transactionData3, transactionData4};
    }
}
