package seedu.duke.data;

import java.util.Date;

public interface ReadOnlyTransaction {

    double getAmount();

    String getDescription();

    Date getDate();

    String getCategory();

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

    String getAsTextShowAll();

}
