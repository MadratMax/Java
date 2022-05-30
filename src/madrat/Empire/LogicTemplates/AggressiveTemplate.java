package madrat.Empire.LogicTemplates;

public class AggressiveTemplate implements ITemplate{

    private int allSoldiersToWar = 100;
    private int makeMoreWorkerIfGold = 100;
    private int maxLostSoldiersToRevoke = 1;

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
