package seedu.duke.utilities;

import seedu.duke.data.Budget;
import seedu.duke.data.BudgetList;
import seedu.duke.data.TransactionList;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SetupBudgetData {

    private Budget budgetData1;
    private Budget budgetData2;
    private Budget budgetData3;
    private Budget budgetData4;
    private BudgetList budgetList = new BudgetList();

    public SetupBudgetData() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        this.budgetData1 = new Budget("Monthly", "MISC", 300.0);
        this.budgetData2 = new Budget("Monthly", "FOOD", 500.0);
        this.budgetData3 = new Budget("Monthly", "RENT", 300.0);
        this.budgetData4 = new Budget("Daily", "ENTERTAINMENT", 250.);
    }

    public BudgetList loadBudgetData() {
        try {
            for (Budget t : getBudgetData()) {
                this.budgetList.addBudget(t);
            }
            return this.budgetList;

        } catch (Exception e) {
            assert false : "Error encountered";
        }
        return this.budgetList;
    }

    public Budget[] getBudgetData() {
        return new Budget[]{budgetData1, budgetData2, budgetData3, budgetData4};
    }
}
