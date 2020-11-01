package seedu.duke.data;

import org.junit.jupiter.api.Test;
import seedu.duke.utilities.SetupBudgetData;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BudgetListTest {
    private SetupBudgetData setupData = new SetupBudgetData();
    private BudgetList budgetList = setupData.loadBudgetData();

    //Test list index 0 and 1 to get its description
    @Test
    public void budget_getList_getDescription() {
        assertEquals("MISC", budgetList.getBudgetList().get(0).getDescription());
        assertEquals("FOOD", budgetList.getBudgetList().get(1).getDescription());
        assertEquals("RENT", budgetList.getBudgetList().get(2).getDescription());
        assertEquals("ENTERTAINMENT", budgetList.getBudgetList().get(3).getDescription());
    }

    //Test list index 0 and 1 to get its amount.
    @Test
    public void transactionList_getList_getAmount() {
        assertEquals(300.00, budgetList.getBudgetList().get(0).getAmount());
        assertEquals(500.00, budgetList.getBudgetList().get(1).getAmount());
        assertEquals(300.00, budgetList.getBudgetList().get(2).getAmount());
        assertEquals(250.00, budgetList.getBudgetList().get(3).getAmount());
    }



}
