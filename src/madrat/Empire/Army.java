package madrat.Empire;

import madrat.Empire.DataStructures.Stack;

public class Army {

    private int size = 0;
    private Stack army;
    private Land owner;

    public Army(Land owner) {
        this.owner = owner;
        this.army = new Stack(owner.size());
    }

    public Land owner() {
        return owner;
    }

    public Soldier addSoldier(Soldier soldier) {
        if (size >= owner.size()) {
            MessageBox.pushMessage(owner, "has reached max population size: " + size + ". cannot create a soldier");
            return null;
        }


        if (soldier != null && soldier.health() > 0) {
            if (soldier.name().startsWith("_")) {
                soldier.setName(owner.name());
            }
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
