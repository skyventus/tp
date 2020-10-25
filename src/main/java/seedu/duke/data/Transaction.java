package seedu.duke.data;

import java.util.Date;

public class Transaction implements ReadOnlyTransaction {

    private double amount;
    private String description;
    private Date date;

    public Transaction(String description, double amount, Date date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }



    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
