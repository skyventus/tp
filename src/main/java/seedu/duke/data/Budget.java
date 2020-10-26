package seedu.duke.data;

public class Budget implements ReadOnlyBudget {

    private double amount;
    private String description;
    private String category;

    public Budget(String category, String description, double amount) {
        this.category = category;
        this.description = description;
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setDCategory(String category) {
        this.category = category;
    }

}
