package seedu.duke.data;

import java.util.Date;

public interface ReadOnlyTransaction {

    double getAmount();

    String getDescription();

    Date getDate();

    void setAmount(double amount);

    void setDescription(String description);

    void setDate(Date date);

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
