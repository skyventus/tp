package seedu.duke.data;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements ReadOnlyTransaction {

    private double amount;
    private String description;
    private Date date;
    private CategoryType category;
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Transaction(String description, double amount, Date date,String categoryType) {
        this.description = description;
        this.amount = amount;
        this.date = date;

        if (categoryType.isEmpty()) {
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


    public String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Description: ")
                .append(getDescription());

        builder.append(" Amount: $")
                .append(getAmount());

        if (!(getDate() == null)) {
            builder.append(" Date: ")
                    .append(sdf.format(getDate()));
        }

        builder.append(" Category: ")
                .append(getCategory());

        return builder.toString();
    }

    public String getCategory() {
        return this.category.toString().toUpperCase();
    }

    public static boolean enumContains(String test) {

        for (CategoryType categoryType : CategoryType.values()) {
            if (categoryType.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
