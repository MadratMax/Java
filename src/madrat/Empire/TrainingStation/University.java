package madrat.Empire.TrainingStation;

import madrat.Empire.*;

public class University {

    public static void createCivil(Civils civils, int count, Resources resources) {
        if (count <= 0 || civils == null)
            return;

        Civil civil = new Civil();
        civils.addCivil(civil);
        resources.FOOD = resources.FOOD - GameSettings.createCivilFee;
        createCivil(civils, count-1, resources);
    }
}
