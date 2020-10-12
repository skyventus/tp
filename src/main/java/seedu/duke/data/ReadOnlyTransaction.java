package seedu.duke.data;

public interface ReadOnlyTransaction {

    double getAmount();

    String getDescription();

    String getDate();

    default String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getDate())
                .append(" Date: ");

        builder.append(getDescription())
                .append(" Description: ");

        builder.append(getAmount())
                .append(" Amount: ");

        return builder.toString();
    }
}
