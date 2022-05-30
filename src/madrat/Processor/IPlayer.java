package madrat.Processor;

import madrat.Empire.Land;

public interface IPlayer {

    public boolean isAI();
    public IPlayer act();
    public boolean yes();
    public Land land();
}
