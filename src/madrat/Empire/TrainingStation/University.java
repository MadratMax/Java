package madrat.Empire.TrainingStation;

import madrat.Empire.*;

public class University {

    public static void createCivil(Civils civils, int count, Resources resources) {
        if (resources.GOLD < GameSettings.createCivilGoldFee ||
                resources.FOOD < GameSettings.createCivilFoodFee ||
                resources.WOOD < GameSettings.createCivilWoodFee) {
            MessageBox.pushMessage("insufficient funds to create a civil");
            return;
        }

        if (count <= 0 || civils == null)
            return;

        Civil civil = new Civil();
        civils.addCivil(civil);
        resources.FOOD = resources.FOOD - GameSettings.createCivilFoodFee;
        resources.GOLD = resources.GOLD - GameSettings.createCivilGoldFee;
        resources.WOOD = resources.WOOD - GameSettings.createCivilWoodFee;
        createCivil(civils, count-1, resources);
    }
}
