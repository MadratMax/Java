package madrat.Processor;

import madrat.Empire.Land;
import madrat.Empire.Randomizator;
import madrat.Empire.ResourceType;

public class User implements IPlayer{

    private final Land land;
    private final boolean ai;

    public User(Land land) {
        this.land = land;
        this.ai = false;
    }

    public boolean isAI() { return ai; }

    @Override
    public IPlayer act() {
        land.setTrade(land.neighbours.get(0), ResourceType.FOOD);
        return this;
    }

    @Override
    public boolean yes() {
        return Randomizator.getYesNo() == 1;
    }

    @Override
    public Land land() {
        return land;
    }
}
