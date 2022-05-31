package madrat.Processor;

import madrat.Empire.Land;
import madrat.Empire.Logic.StandardLogic;
import madrat.Empire.LogicTemplates.*;
import madrat.Empire.Randomizator;

import java.util.ArrayList;
import java.util.List;

public class AI implements IPlayer {

    private final Land land;
    private final boolean ai;
    private final StandardLogic logic;
    private final ITemplate template;
    private final String name;
    private boolean isLost;
    private boolean canMove;

    public AI(Land land) {
        this.land = land;
        this.ai = true;
        this.logic = new StandardLogic(this);
        this.name = this.land.name;
        this.canMove = true;

        List<ITemplate> templateTypes = new ArrayList<>();
        templateTypes.add(new AggressiveTemplate());
        templateTypes.add(new NormalTemplate());
        templateTypes.add(new WarriorTemplate());

        int random = Randomizator.getRandomIndex(templateTypes.size());

        this.template = templateTypes.get(random);

        this.logic.setTemplate(this.template);
    }

    @Override
    public String name() { return this.name; }

    @Override
    public boolean isAI() { return ai; }

    @Override
    public void setLost() {
        isLost = true;
    }

    @Override
    public boolean isLost() {
        return isLost;
    }

    @Override
    public void canMove(boolean res) {
        canMove = res;
    }

    @Override
    public boolean canMove() {
        return canMove;
    }

    @Override
    public IPlayer act() {
        logic.processPopulation();
        logic.processWorkers();
        logic.processTrading();
        logic.processWar();
        logic.processNextSteps();
        logic.updateTemplate();
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
