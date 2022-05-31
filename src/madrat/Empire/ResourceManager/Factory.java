package madrat.Empire.ResourceManager;

import madrat.Empire.*;

public class Factory {

    public static void process(Civils civils, Resources resources) {
        Civil civil = civils.getCivil();

        if (civil == null) {
            MessageBox.pushFactoryMessage(civils.owner(), "FOOD:  " + resources.FOOD);
            MessageBox.pushFactoryMessage(civils.owner(),"WOOD:  " + resources.WOOD);
            MessageBox.pushFactoryMessage(civils.owner(),"GOLD:  " + resources.GOLD);
            return;
        }

        if (civil.getWorkOnResourceType().equals(ResourceType.WOOD)) {
            resources.WOOD = resources.WOOD + GameSettings.woodGain;
            MessageBox.pushFactoryMessage(civils.owner(),"wood profit + " + GameSettings.woodGain);
        }
        else if (civil.getWorkOnResourceType().equals(ResourceType.FOOD)) {
            resources.FOOD = resources.FOOD + GameSettings.foodGain;
            MessageBox.pushFactoryMessage(civils.owner(),"food profit + " + GameSettings.foodGain);
        }
        else if (civil.getWorkOnResourceType().equals(ResourceType.GOLD)) {
            resources.GOLD = resources.GOLD + GameSettings.goldGain;
            MessageBox.pushFactoryMessage(civils.owner(),"gold profit + " + GameSettings.goldGain);
        }

        process(civils, resources);
        civils.addCivil(civil);
    }
}
