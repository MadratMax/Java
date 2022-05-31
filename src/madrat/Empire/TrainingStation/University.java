package madrat.Empire.TrainingStation;

import madrat.Empire.*;

public class University {

    public static void createCivil(Land land, int count) {
        if (count <= 0 || land.civils == null)
            return;

        if (land.resources.GOLD < GameSettings.createCivilGoldFee ||
                land.resources.FOOD < GameSettings.createCivilFoodFee ||
                land.resources.WOOD < GameSettings.createCivilWoodFee) {
            MessageBox.pushMessage(land,  "insufficient funds to create a civil");
            return;
        }

        Civil civil = new Civil();
        land.civils.addCivil(civil);
        land.resources.FOOD = land.resources.FOOD - GameSettings.createCivilFoodFee;
        land.resources.GOLD = land.resources.GOLD - GameSettings.createCivilGoldFee;
        land.resources.WOOD = land.resources.WOOD - GameSettings.createCivilWoodFee;
        MessageBox.pushMessage(land, "new civil created");
        createCivil(land, count-1);
    }
}
