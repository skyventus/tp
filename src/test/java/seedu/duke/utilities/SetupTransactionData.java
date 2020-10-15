package seedu.duke.utilities;

import seedu.duke.data.Transaction;
import seedu.duke.data.TransactionList;

/**
 * Class to generate typical test test
 */
public class SetupTransactionData {

    private Transaction transactionData1;
    private Transaction transactionData2;
    private Transaction transactionData3;
    private Transaction transactionData4;
    private TransactionList transactionList = new TransactionList();

    public SetupTransactionData(){

        this.transactionData1 = new Transaction("Chicken Rice", 4.00, "");
        this.transactionData2 = new Transaction("Fried Rice", 15.00, "");
        this.transactionData3 = new Transaction("MSG Rice", 20.00, "");
        this.transactionData4 = new Transaction("Fish Curry Noodle", 23.00, "");

    }

    public TransactionList loadTransactionData(){
        try{
                for(Transaction t: getTransactionData()){
                    this.transactionList.addTransaction(t);
                }
                return this.transactionList;

        }catch(Exception e){
            assert false: "Error encountered";
        }
        return this.transactionList;
    }

    public Transaction[] getTransactionData(){
            return new Transaction[]{transactionData1, transactionData2, transactionData3, transactionData4};
    }
}
