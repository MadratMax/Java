package madrat.Empire.TrainingStation;

import madrat.Empire.*;

public class Barracks {

    public static void trainSoldier(Army army, int count, Resources resources) {
        if (resources.GOLD < GameSettings.trainSoldierFee) {
            MessageBox.pushMessage("insufficient funds to train a soldier");
            return;
        }

        Soldier soldier = army.getSoldier();

        if (count == 0 || soldier == null)
            return;

        soldier.train();
        resources.GOLD = resources.GOLD - GameSettings.trainSoldierFee;
        trainSoldier(army, count-1, resources);
        army.addSoldier(soldier);
    }

    public static void trainCivil(Civils civils, int count, Resources resources) {
        if (resources.GOLD < GameSettings.trainCivilFee) {
            MessageBox.pushMessage("insufficient funds to train a civil");
            return;
        }

        Civil civil = civils.getCivil();

        if (count <= 0 || civil == null)
            return;

        Soldier soldier = new Soldier();
        resources.GOLD = resources.GOLD - GameSettings.trainCivilFee;
        trainCivil(civils, count-1, resources);
        MessageBox.pushMessage("trained new soldier: " + soldier.name());
        civils.owner().getHomeArmy().addSoldier(soldier);
    }
}
