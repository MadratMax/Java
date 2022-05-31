package madrat.Empire.Logic;

import madrat.Empire.GameSettings;
import madrat.Empire.Land;
import madrat.Empire.LogicTemplates.*;
import madrat.Empire.Randomizator;
import madrat.Empire.ResourceType;
import madrat.Empire.TrainingStation.Barracks;
import madrat.Empire.TrainingStation.University;
import madrat.Processor.IPlayer;

public class StandardLogic {

    private final IPlayer aiPlayer;
    private ITemplate template;

    public StandardLogic(IPlayer aiPlayer) {
        this.aiPlayer = aiPlayer;
    }

    public void setTemplate(ITemplate template) {
        this.template = template;
    }

    public void processPopulation() {
        if (!aiPlayer.land().isOnWar && !aiPlayer.land().isUnderAttack) {
            if (aiPlayer.land().homeArmy.size() == 0) {
                if (aiPlayer.land().resources.FOOD > GameSettings.createSoldierFoodFee * 2 &&
                        aiPlayer.land().resources.WOOD > GameSettings.createSoldierWoodFee * 2 &&
                        aiPlayer.land().resources.GOLD > GameSettings.createSoldierGoldFee * 2) {
                    Barracks.createSoldier(aiPlayer.land(), 1);
                } else {
                    University.createCivil(aiPlayer.land(), 1);
                }
            }
            if (template.type().equals(TemplateType.AGGRESSIVE)) {
                if (aiPlayer.land().foodWorkers >= 2 && aiPlayer.land().goldWorkers >= 2 && aiPlayer.land().woodWorkers >= 1) {
                    Barracks.createSoldier(aiPlayer.land(), 3);
                }
            }
        }
        if (aiPlayer.land().isOnWar && !aiPlayer.land().isUnderAttack) {
            if (template.type().equals(TemplateType.AGGRESSIVE)) {
                aiPlayer.land().setAssaultArmy(aiPlayer.land().homeArmy.size() / 2);
            }
        }

        University.createCivil(aiPlayer.land(), 1);
    }

    public void processWorkers() {
        if (aiPlayer.land().foodWorkers == 0)
            aiPlayer.land().civils.setTo(ResourceType.FOOD, 1);
        if (aiPlayer.land().woodWorkers == 0)
            aiPlayer.land().civils.setTo(ResourceType.WOOD, 1);
        if (aiPlayer.land().goldWorkers == 0)
            aiPlayer.land().civils.setTo(ResourceType.GOLD, 1);

        if (aiPlayer.land().foodWorkers > 1 && aiPlayer.land().foodWorkers > aiPlayer.land().goldWorkers * 2) {
            aiPlayer.land().civils.unsetFrom(ResourceType.FOOD, 1);
            aiPlayer.land().civils.setTo(ResourceType.GOLD, 1);
        }
        if (aiPlayer.land().woodWorkers > 1 && aiPlayer.land().woodWorkers > aiPlayer.land().goldWorkers * 2) {
            aiPlayer.land().civils.unsetFrom(ResourceType.WOOD, 1);
            aiPlayer.land().civils.setTo(ResourceType.GOLD, 1);
        }
        if (aiPlayer.land().foodWorkers > 1 && aiPlayer.land().foodWorkers > aiPlayer.land().woodWorkers * 2) {
            aiPlayer.land().civils.unsetFrom(ResourceType.FOOD, 1);
            aiPlayer.land().civils.setTo(ResourceType.WOOD, 1);
        }
        if (aiPlayer.land().woodWorkers > 1 &&
                aiPlayer.land().woodWorkers > aiPlayer.land().foodWorkers) {
            aiPlayer.land().civils.unsetFrom(ResourceType.WOOD, 1);
            aiPlayer.land().civils.setTo(ResourceType.FOOD, 1);
        }
        if (aiPlayer.land().woodWorkers > 1 &&
                aiPlayer.land().woodWorkers > aiPlayer.land().goldWorkers) {
            aiPlayer.land().civils.unsetFrom(ResourceType.WOOD, 1);
            aiPlayer.land().civils.setTo(ResourceType.GOLD, 1);
        }

        if (template.type().equals(TemplateType.AGGRESSIVE)) {
            if (aiPlayer.land().civils.size() < aiPlayer.land().capacity / 3) {
                aiPlayer.land().civils.setTo(ResourceType.GOLD, 1);
                aiPlayer.land().civils.setTo(ResourceType.FOOD, 1);
                aiPlayer.land().civils.setTo(ResourceType.WOOD, 1);
            }
        } else if (template.type().equals(TemplateType.NORMAL)) {
            if (aiPlayer.land().civils.size() < aiPlayer.land().capacity / 2) {
                aiPlayer.land().civils.setTo(ResourceType.GOLD, 1);
                aiPlayer.land().civils.setTo(ResourceType.FOOD, 1);
                aiPlayer.land().civils.setTo(ResourceType.WOOD, 1);
            }
        } else if (template.type().equals(TemplateType.WARRIOR)) {
            if (aiPlayer.land().civils.size() < aiPlayer.land().homeArmy.size()) {
                aiPlayer.land().civils.setTo(ResourceType.GOLD, 1);
                aiPlayer.land().civils.setTo(ResourceType.FOOD, 1);
                aiPlayer.land().civils.setTo(ResourceType.WOOD, 1);
            }
        }

        aiPlayer.land().civils.setTo(ResourceType.GOLD, 1);
        aiPlayer.land().civils.setTo(ResourceType.FOOD, 1);
        aiPlayer.land().civils.setTo(ResourceType.WOOD, 1);
    }

    public void processTrading() {
        if (!aiPlayer.land().isOnWar &&
            !aiPlayer.land().isUnderAttack &&
            aiPlayer.land().resources.GOLD > GameSettings.foodTradeCommission) {
            for (Land land: aiPlayer.land().neighbours) {
                if (aiPlayer.land().trade.getTrading().get(ResourceType.FOOD) != null &&
                    !aiPlayer.land().trade.getTraderLand(ResourceType.FOOD).equals(land) &&
                    aiPlayer.land().trade.getTraderLand(ResourceType.FOOD).attacker != null &&
                    !aiPlayer.land().trade.getTraderLand(ResourceType.FOOD).attacker.equals(aiPlayer.land())){
                    aiPlayer.land().setTrade(land, ResourceType.FOOD);
                }
                if (aiPlayer.land().trade.getTrading().get(ResourceType.WOOD) != null &&
                    !aiPlayer.land().trade.getTraderLand(ResourceType.WOOD).equals(land)  &&
                        aiPlayer.land().trade.getTraderLand(ResourceType.FOOD).attacker != null &&
                    !aiPlayer.land().trade.getTraderLand(ResourceType.WOOD).attacker.equals(aiPlayer.land())) {
                    aiPlayer.land().setTrade(land, ResourceType.WOOD);
                }
                if (aiPlayer.land().trade.getTrading().get(ResourceType.WOOD) == null) {
                    if (land.attacker != null && !land.attacker.equals(aiPlayer.land())) {
                        aiPlayer.land().setTrade(land, ResourceType.WOOD);
                    }
                }
                if (aiPlayer.land().trade.getTrading().get(ResourceType.FOOD) == null) {
                    if (land.attacker != null && !land.attacker.equals(aiPlayer.land())) {
                        aiPlayer.land().setTrade(land, ResourceType.FOOD);
                    }
                }
            }
        }
    }

    public void processWar() {

    }

    public void processNextSteps() {
        if (!aiPlayer.land().isOnWar &&
                !aiPlayer.land().isUnderAttack &&
                aiPlayer.land().possibleTarget != null &&
                !aiPlayer.land().name.equals(aiPlayer.land().possibleTarget.name)) {
            if (template.type().equals(TemplateType.AGGRESSIVE)) {
                if (aiPlayer.land().homeArmy.size() > aiPlayer.land().civils.size() / 4) {
                    aiPlayer.land().attack(aiPlayer.land().possibleTarget, aiPlayer.land().homeArmy.size() / 2);
                }
            }
            if (template.type().equals(TemplateType.WARRIOR)) {
                if (aiPlayer.yes()) {
                    if (aiPlayer.land().homeArmy.size() > aiPlayer.land().civils.size() / 3) {
                        aiPlayer.land().attack(aiPlayer.land().possibleTarget, aiPlayer.land().homeArmy.size() / 3);
                    }
                }
            }
            if (template.type().equals(TemplateType.NORMAL)) {
                if (aiPlayer.yes()) {
                    if (aiPlayer.land().homeArmy.size() > aiPlayer.land().civils.size() / 2) {
                        if (aiPlayer.yes())
                            aiPlayer.land().attack(aiPlayer.land().possibleTarget, aiPlayer.land().homeArmy.size() / 3);
                    }
                }
            }
        }
        if (template.type().equals(TemplateType.AGGRESSIVE)) {
            if (aiPlayer.land().homeArmy.size() > aiPlayer.land().civils.size() / 4) {
                if (aiPlayer.yes()) {
                    if (aiPlayer.land().neighbours.size() > 0)
                        aiPlayer.land().attack(aiPlayer.land().neighbours.get(Randomizator.getRandomIndex(aiPlayer.land().neighbours.size())), aiPlayer.land().homeArmy.size() / 2);
                }
            }
        }
    }

    public void updateTemplate() {
        if (aiPlayer.land().attacker != null && template.type().equals(TemplateType.NORMAL)) {
            template = new WarriorTemplate();
        } else if (aiPlayer.land().attacker != null && template.type().equals(TemplateType.WARRIOR)) {
            template = new AggressiveTemplate();
        }
        if (!aiPlayer.land().isOnWar &&
            !aiPlayer.land().isUnderAttack &&
            aiPlayer.land().resources.GOLD > 100) {
            template = new NormalTemplate();
        }
        if (!aiPlayer.land().isOnWar &&
            !aiPlayer.land().isUnderAttack &&
            aiPlayer.land().civils.size() > 10 &&
            aiPlayer.land().homeArmy.size() > 5) {
            template = new NormalTemplate();
        }
        if (!aiPlayer.land().isOnWar &&
            !aiPlayer.land().isUnderAttack &&
            aiPlayer.land().homeArmy.size() > aiPlayer.land().civils.size()) {
            template = new WarriorTemplate();
        }
    }
}
