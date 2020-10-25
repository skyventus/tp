package seedu.duke.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class BudgetList implements Iterable<Budget> {

    private final List<Budget> internalBudgetList;

    public BudgetList() {
        this.internalBudgetList = new ArrayList<>();
    }

    public BudgetList(List<Budget> listOfBudget) {
        this.internalBudgetList = listOfBudget;
    }

    public List<Budget> getBudgetList() {
        List<Budget> list = internalBudgetList.stream().collect(Collectors.toList());
        return list;
    }

    public void addBudget(Budget budget) {
        internalBudgetList.add(budget);
    }

    public void removeBudget(ReadOnlyBudget toRemove) throws BudgetList.BudgetNotFound {
        internalBudgetList.remove(toRemove);
    }

    @Override
    public Iterator<Budget> iterator() {
        return internalBudgetList.iterator();
    }

    public static class BudgetNotFound extends Exception {

    }

}
