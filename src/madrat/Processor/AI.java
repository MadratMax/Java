package madrat.Processor;

import madrat.Empire.Land;
import madrat.Empire.Randomizator;

public class AI implements IPlayer {

    private final Land land;
    private final boolean ai;

    public AI(Land land) {
        this.land = land;
        this.ai = true;
    }

    public boolean isAI() { return ai; }

    public IPlayer act() {
        return this;
    }

    public boolean yes() {
        return Randomizator.getYesNo() == 1;
    }

    @Override
    public Land land() {
        return land;
    }
}
