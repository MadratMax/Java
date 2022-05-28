package madrat.Empire;

import madrat.Empire.DataStructures.Stack;

public class Army {

    private int size = 0;
    private final int maxCapacity;
    private Stack army;
    private Land owner;

    public Army(Land owner) {
        this.owner = owner;
        this.army = new Stack(owner.size());
        this.maxCapacity = owner.size();
    }

    public Land owner() {
        return owner;
    }

    public Soldier addSoldier(Soldier soldier) {
        if (size >= maxCapacity)
            return null;

        if (soldier != null && soldier.health() > 0) {
            army.push(soldier);
            size++;
        }

        return soldier;
    }

    public Soldier getSoldier() {
        Soldier sol = (Soldier) army.pop();

        if (sol != null)
            size--;

        return sol;
    }

    public boolean isEmpty() {
        return army.isEmpty();
    }

    public int size() { return army.getSize(); }
}
