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
        if (size >= owner.size()) {
            MessageBox.pushMessage(owner, "has reached max population size: " + size + ". cannot create a civil");
            return null;
        }

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

    public void setTo(ResourceType resourceType, int count) {

        if (count <= 0)
            return;

        Civil civil = getCivil();

        if (civil == null) {
            MessageBox.pushMessage(owner, "no free civils to produce " + resourceType);
            return;
        }

        if (civil.getWorkOnResourceType().equals(ResourceType.UNSET)) {
            civil.setToWork(resourceType);

            if (resourceType.equals(ResourceType.FOOD))
                owner.foodWorkers++;
            if (resourceType.equals(ResourceType.WOOD))
                owner.woodWorkers++;
            if (resourceType.equals(ResourceType.GOLD))
                owner.goldWorkers++;

            MessageBox.pushMessage(owner,  civil.name() + " is set to produce " + resourceType);
        } else {
            setTo(resourceType, count-1);
        }

        addCivil(civil);
    }

    public void unsetFrom(ResourceType resourceType, int count) {

        if (count <= 0)
            return;

        Civil civil = getCivil();

        if (civil == null) {
            MessageBox.pushMessage(owner, "no free civils");
            return;
        }

        if (civil.getWorkOnResourceType().equals(resourceType)) {
            civil.freeFromWork(resourceType);

            if (resourceType.equals(ResourceType.FOOD))
                owner.foodWorkers--;
            if (resourceType.equals(ResourceType.WOOD))
                owner.woodWorkers--;
            if (resourceType.equals(ResourceType.GOLD))
                owner.goldWorkers--;

            MessageBox.pushMessage(owner, civil.name() + " is released from producing " + resourceType);
        } else {
            unsetFrom(resourceType, count-1);
        }

        addCivil(civil);
    }

    public boolean isEmpty() {
        return civils.isEmpty();
    }

    public int size() { return civils.getSize(); }
}
