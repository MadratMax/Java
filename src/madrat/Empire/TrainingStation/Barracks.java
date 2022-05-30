package madrat.Empire.TrainingStation;

import madrat.Empire.*;

public class Barracks {

    public static void createSoldier(Army army, int count, Resources resources) {
        if (count <= 0)
            return;

        createSoldier(army, resources);
        createSoldier(army, count-1, resources);
    }

    public static void createSoldier(Army army, Resources resources) {
        if (resources.GOLD < GameSettings.createSoldierGoldFee ||
                resources.FOOD < GameSettings.createSoldierFoodFee ||
                resources.WOOD < GameSettings.createSoldierWoodFee) {
            MessageBox.pushLogMessage("insufficient funds to create a soldier");
            return;
        }

        Soldier soldier = new Soldier(army.owner().name());
        resources.GOLD = resources.GOLD - GameSettings.createSoldierGoldFee;
        resources.FOOD = resources.FOOD - GameSettings.createSoldierFoodFee;
        resources.WOOD = resources.WOOD - GameSettings.createSoldierWoodFee;

        army.addSoldier(soldier);
    }

    public static void trainSoldiers(Army army, Resources resources) {
        if (resources.GOLD < GameSettings.trainSoldierFee) {
            MessageBox.pushMessage("insufficient funds to train a soldier");
            return;
        }

        Soldier soldier = army.getSoldier();

        if (soldier == null)
            return;

        resources.GOLD = resources.GOLD - GameSettings.trainSoldierFee;
        soldier.skill++;
        MessageBox.pushLogMessage(soldier.id +  " has been skilled | " + " skill: " + soldier.skill);

        trainSoldiers(army, resources);
        army.addSoldier(soldier);
    }

    public static void treatSoldier(Army army, int count, Resources resources) {
        if (resources.GOLD < GameSettings.treatSoldierFee) {
            MessageBox.pushMessage("insufficient funds to treat a soldier");
            return;
        }

        Soldier soldier = army.getSoldier();

        if (count == 0 || soldier == null)
            return;

        if (soldier.health < 10) {
            while (soldier.health < 10) {
                resources.GOLD = resources.GOLD - GameSettings.treatSoldierFee;
                soldier.health++;
            }

            MessageBox.pushLogMessage(soldier.id +  " was treated | " + "health: " + soldier.health);
            treatSoldier(army, count-1, resources);
        } else {
            treatSoldier(army, count, resources);
        }

        army.addSoldier(soldier);
    }

    public static void trainCivil(Civils civils, int count, Resources resources) {
        if (resources.GOLD < GameSettings.trainCivilFee) {
            MessageBox.pushLogMessage("insufficient funds to train a civil");
            return;
        }

        Civil civil = civils.getCivil();

        if (count <= 0 || civil == null)
            return;

        Soldier soldier = new Soldier();
        resources.GOLD = resources.GOLD - GameSettings.trainCivilFee;
        trainCivil(civils, count-1, resources);
        MessageBox.pushLogMessage("trained new soldier: " + soldier.name());
        civils.owner().getHomeArmy().addSoldier(soldier);
    }
}
