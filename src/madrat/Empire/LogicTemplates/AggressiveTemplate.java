package madrat.Empire.LogicTemplates;

public class AggressiveTemplate implements ITemplate{

    private final TemplateType type;
    private int allSoldiersToWar = 100;
    private int makeMoreWorkerIfGold = 100;
    private int maxLostSoldiersToRevoke = 1;

    public AggressiveTemplate() {
        this.type = TemplateType.AGGRESSIVE;
    }

    @Override
    public TemplateType type() {
        return type;
    }

    @Override
    public int allSoldiersToWar() {
        return allSoldiersToWar;
    }

    @Override
    public int makeMoreWorkerIfGold() {
        return makeMoreWorkerIfGold;
    }

    @Override
    public int maxLostSoldiersToRevoke() {
        return maxLostSoldiersToRevoke;
    }

    @Override
    public int maxSoldiers() {
        return 0;
    }

    @Override
    public int trainSoldiersPerDay() {
        return 0;
    }

    @Override
    public int askForMoney() {
        return 0;
    }

    @Override
    public int treatSoldiersCount() {
        return 0;
    }
}
