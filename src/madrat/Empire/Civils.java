package madrat.Empire;

import madrat.Empire.DataStructures.Stack;

public class Civils {

    private int size = 0;
    private Stack civils;
    private Land owner;

    public Civils(Land owner) {
        this.owner = owner;
        this.civils = new Stack(owner.size());
    }

    public Land owner() {
        return owner;
    }

    public Civil addCivil(Civil civil) {
        if (size >= owner.size())
            return null;

        if (civil != null) {
            civils.push(civil);
            size++;
        }

        return civil;
    }

    public Civil getCivil() {
        Civil civ = (Civil) civils.pop();

        if (civ != null)
            size--;

        return civ;
    }

    public void setTo(ResourceType resources, int count) {
        Civil civil = getCivil();

        if (civil == null) {
            MessageBox.pushMessage("no free civils to produce " + resources);
            return;
        }

        if (civil.getWorkOnResourceType().equals(ResourceType.UNSET)) {
            civil.setToWork(resources);
            MessageBox.pushMessage(civil.name() + " is set to produce " + resources);
        } else {
            setTo(resources, count-1);
        }

        addCivil(civil);
    }

    public boolean isEmpty() {
        return civils.isEmpty();
    }

    public int size() { return civils.getSize(); }
}
