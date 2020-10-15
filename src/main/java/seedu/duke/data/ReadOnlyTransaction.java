package seedu.duke.data;

public interface ReadOnlyTransaction {

    double getAmount();

    String getDescription();

    String getDate();

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
