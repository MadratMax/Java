package madrat.Processor;

import madrat.Empire.Land;
import madrat.Empire.MessageBox;
import madrat.Empire.Randomizator;
import madrat.Empire.ResourceType;

public class User implements IPlayer{

    private final Land land;
    private final boolean ai;
    private String name;
    private boolean isLost;
    private boolean canMove;

    public User(Land land) {
        this.land = land;
        this.ai = false;
        this.name = land.name;
        this.canMove = true;
    }
    @Override
    public String name() { return this.name; }

    @Override
    public boolean isAI() { return ai; }

    @Override
    public boolean isLost() { return isLost; }

    @Override
    public void setLost() {
        isLost = true;
    }

    @Override
    public void canMove(boolean res) {
        canMove = res;
    }

    @Override
    public boolean canMove() { return canMove; }

    @Override
    public IPlayer act() {
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
