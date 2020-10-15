package seedu.duke.data;

public class Transaction implements ReadOnlyTransaction {

    private double amount;
    private String description;
    private String date;

    public Transaction(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
