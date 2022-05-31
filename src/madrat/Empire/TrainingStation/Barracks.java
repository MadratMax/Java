package madrat.Empire.TrainingStation;

import madrat.Empire.*;

public class Barracks {

    public static void createSoldier(Land land, int count) {
        if (count <= 0)
            return;

        createSoldier(land.homeArmy, land.resources);
        createSoldier(land, count-1);
    }

    private static void createSoldier(Army army, Resources resources) {
        if (resources.GOLD < GameSettings.createSoldierGoldFee ||
                resources.FOOD < GameSettings.createSoldierFoodFee ||
                resources.WOOD < GameSettings.createSoldierWoodFee) {
            MessageBox.pushBarracksMessage(army.owner(), "insufficient funds to create a soldier");
            return;
        }

        Soldier soldier = new Soldier(army.owner().name());
        resources.GOLD = resources.GOLD - GameSettings.createSoldierGoldFee;
        resources.FOOD = resources.FOOD - GameSettings.createSoldierFoodFee;
        resources.WOOD = resources.WOOD - GameSettings.createSoldierWoodFee;

        army.addSoldier(soldier);
        MessageBox.pushBarracksMessage(army.owner(), "new soldier added to home army");
    }

    public static void trainSoldiers(Land land) {
        if (land.resources.GOLD < GameSettings.trainSoldierFee) {
            MessageBox.pushBarracksMessage(land, "insufficient funds to train a soldier");
            return;
        }

        Soldier soldier = land.homeArmy.getSoldier();

        if (soldier == null)
            return;

        land.resources.GOLD = land.resources.GOLD - GameSettings.trainSoldierFee;
        soldier.skill++;
        MessageBox.pushBarracksMessage(land, soldier.id +  " has been skilled | " + " skill: " + soldier.skill);

        trainSoldiers(land);
        land.homeArmy.addSoldier(soldier);
    }

    public static void treatSoldier(Land land, int count) {
        if (land.resources.GOLD < GameSettings.treatSoldierFee) {
            MessageBox.pushBarracksMessage(land, "insufficient funds to treat a soldier");
            return;
        }

        Soldier soldier = land.homeArmy.getSoldier();

        if (count == 0 || soldier == null)
            return;

        if (soldier.health < 10) {
            while (soldier.health < 10) {
                land.resources.GOLD = land.resources.GOLD - GameSettings.treatSoldierFee;
                soldier.health++;
            }

            MessageBox.pushBarracksMessage(land, soldier.id +  " was treated | " + "health: " + soldier.health);
            treatSoldier(land, count-1);
        } else {
            treatSoldier(land, count);
        }

        land.homeArmy.addSoldier(soldier);
    }

    public static void trainCivil(Civils civils, int count, Resources resources) {
        if (resources.GOLD < GameSettings.trainCivilFee) {
            MessageBox.pushBarracksMessage(civils.owner(), "insufficient funds to train a civil");
            return;
        }

        Civil civil = civils.getCivil();

        if (count <= 0 || civil == null)
            return;

        Soldier soldier = new Soldier();
        resources.GOLD = resources.GOLD - GameSettings.trainCivilFee;
        trainCivil(civils, count-1, resources);
        MessageBox.pushBarracksMessage(civils.owner(), "trained new soldier: " + soldier.name());
        civils.owner().getHomeArmy().addSoldier(soldier);
    }
}
