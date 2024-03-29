package madrat.Empire;

public class FightLogic {

    public static void fight(Army army1, Army army2) {
        // TODO
        Soldier soldier1 = army1.getSoldier();
        Soldier soldier2 = army2.getSoldier();

        // TODO
        // FIGHT
        MessageBox.pushFightMessage(army1.owner(), "Soldier " + soldier1.name() + " [land: " + army1.owner().name() + "] will fight " + soldier2.name());
        MessageBox.pushFightMessage(army1.owner(),"Soldier " + soldier1.name() + " health: " + soldier1.health());
        MessageBox.pushFightMessage(army2.owner(),"Soldier " + soldier2.name() + " health: " + soldier2.health());

        int sol1Score = 0;

        if (soldier1.skill() > soldier2.skill())
            sol1Score++;
        else
            sol1Score--;
        if (soldier1.health() > soldier2.health())
            sol1Score++;
        else
            sol1Score--;
        if (soldier1.kills() > soldier2.kills())
            sol1Score++;
        else
            sol1Score--;
        if (soldier2.spirit().equals(Spirit.LOW) && !soldier1.spirit().equals(Spirit.LOW))
            sol1Score++;
        else if (soldier1.spirit().equals(Spirit.LOW) && !soldier2.spirit().equals(Spirit.LOW))
            sol1Score--;

        if (sol1Score > 0) {
            MessageBox.pushFightMessage(army1.owner(),"Soldier " + soldier1.name() + " [land: " + army1.owner().name() + "] harmed " + soldier2.name() + " with power " + sol1Score);
            soldier2.injure(sol1Score);
        }
        else if (sol1Score < 0) {
            MessageBox.pushFightMessage(army2.owner(),"Soldier " + soldier2.name() + " [land: " + army2.owner().name() + "] harmed " + soldier1.name() + " with power " + Math.abs(sol1Score));
            soldier1.injure(Math.abs(sol1Score));
        }

        if (soldier1.health() > 0) {
            army1.addSoldier(soldier1);
        } else {
            // TODO message if my L -> your sold. was killed
            MessageBox.pushFightMessage(army1.owner(),"Soldier " + soldier1.name() + " [land: " + army1.owner().name() + "] was killed by " + soldier2.name());

            if (soldier2.spirit().equals(Spirit.LOW))
                soldier2.setSpirit(Spirit.MEDIUM);
            else
                soldier2.setSpirit(Spirit.HIGH);

            MessageBox.pushMessage(army1.owner(),"army " + army1.owner().name() + " lost its soldier");
        }

        if (soldier2.health() > 0) {
            army2.addSoldier(soldier2);
        } else {
            // TODO message if my L -> your sold. was killed
            MessageBox.pushFightMessage(army2.owner(),"Soldier " + soldier2.name() + " [land: " + army2.owner().name() + "] was killed by " + soldier1.name());

            if (soldier1.spirit().equals(Spirit.LOW))
                soldier1.setSpirit(Spirit.MEDIUM);
            else
                soldier1.setSpirit(Spirit.HIGH);

            MessageBox.pushMessage(army2.owner(),"army lost its soldier");
        }
    }
}
