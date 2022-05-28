package madrat.Empire.ResourceManager;

import madrat.Empire.*;

public class Factory {

    public static void process(Civils civils, Resources resources) {
        Civil civil = civils.getCivil();

        if (civil == null) {
            MessageBox.pushMessage("FOOD:  " + resources.FOOD);
            MessageBox.pushMessage("WOOD:  " + resources.WOOD);
            MessageBox.pushMessage("GOLD:  " + resources.GOLD);
            return;
        }

        if (civil.getWorkOnResourceType().equals(ResourceType.WOOD)) {
            resources.WOOD = resources.WOOD + GameSettings.woodGain;
            MessageBox.pushMessage("wood profit + " + GameSettings.woodGain);
        }
        else if (civil.getWorkOnResourceType().equals(ResourceType.FOOD)) {
            resources.FOOD = resources.FOOD + GameSettings.foodGain;
            MessageBox.pushMessage("food profit + " + GameSettings.foodGain);
        }
        else if (civil.getWorkOnResourceType().equals(ResourceType.GOLD)) {
            resources.GOLD = resources.GOLD + GameSettings.goldGain;
            MessageBox.pushMessage("gold profit + " + GameSettings.goldGain);
        }

        process(civils, resources);
        civils.addCivil(civil);
    }
}
