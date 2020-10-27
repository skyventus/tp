package seedu.duke.data;

import java.util.Date;

public interface ReadOnlyTransaction {

    double getAmount();

    String getDescription();

    Date getDate();

    enum CategoryType {
        ENTERTAINMENT,
        FITNESS,
        FOOD,
        GIFT,
        MEDICAL,
        MISC,
        RECURRING,
        SHOPPING,
        TRANSPORT
    }

    void setAmount(double amount);

    void setDescription(String description);

    void setDate(Date date);

    void setCategory(ReadOnlyTransaction.CategoryType categoryType);

    default String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Description: ")
                .append(getDescription());

        builder.append(" Amount: $")
                .append(getAmount());

        builder.append(" Date: ")
                .append(getDate());

        return builder.toString();
    }
}
