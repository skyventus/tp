package seedu.duke.data;

public interface ReadOnlyBudget {

    double getAmount();

    String getDescription();

    String getCategory();

    default String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Category: ")
                .append(getCategory());

        builder.append(" Description: ")
                .append(getDescription());

        builder.append(" Amount: $")
                .append(getAmount());

        return builder.toString();
    }
}
