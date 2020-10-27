package seedu.duke.data;

import java.util.Date;

public class Transaction implements ReadOnlyTransaction {

    private double amount;
    private String description;
    private Date date;
    private CategoryType category;

    public Transaction(String description, double amount, Date date,String categoryType) {
        this.description = description;
        this.amount = amount;
        this.date = date;

        if(categoryType.isEmpty()) {
            categoryType = "MISC";
        }

        this.category = CategoryType.valueOf(categoryType);
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

    public void setCategory(CategoryType categoryType) {
        this.category = categoryType;
    }
    public String getCategory(){
        return this.category.toString().toUpperCase();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
