package madrat.Processor;

import madrat.Empire.Land;

public interface IPlayer {

    public String name();
    public boolean isAI();
    boolean isLost();
    void setLost();
    boolean canMove();
    void canMove(boolean res);
    public IPlayer act();
    public boolean yes();
    public Land land();
}
