package madrat.Empire.LogicTemplates;

public interface ITemplate {

    public int allSoldiersToWar();
    public int makeMoreWorkerIfGold();
    public int maxLostSoldiersToRevoke();

    // percent of civils amount
    public int maxSoldiers();

    // need train soldiers? 0 - no, 1 - yes
    public int trainSoldiersPerDay();

    // ask a neighbor for money
    public int askForMoney();

    // how many soldiers are need to be treated
    public int treatSoldiersCount();
}
