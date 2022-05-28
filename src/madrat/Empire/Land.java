package madrat.Empire;

import java.util.*;

public class Land {

    private boolean ai;
    private final String name;
    private int size;
    private final String id;
    private final String kingName;
    private final WarManager warManager;
    private List<Land> allies;
    private Army assaultArmy;
    private Army homeArmy;
    private Map<ResourceType, Land> trades;
    private Civils civils;
    private Resources resources;
    private List<Land> neighbours;
    private boolean isOnWar;
    private boolean isUnderAttack;
    private Land enemyLandInWar;
    private int capacity;
    private List<Land> landsConquered;
    private int loses;
    private int foodWorkers;
    private int woodWorkers;
    private int goldWorkers;
    private Land possibleTarget;

    public Land(String name, int size, WarManager warManager) {
        this.ai = true;
        this.name = name;
        this.kingName = "TODO";
        this.size = size;
        this.id = UUID.randomUUID().toString();

        this.resources = new Resources();

        neighbours = new ArrayList<>();

        this.trades = new HashMap<>();
        this.trades.put(ResourceType.FOOD, null);
        this.trades.put(ResourceType.GOLD, null);
        this.trades.put(ResourceType.WOOD, null);

        this.assaultArmy = new Army(this);
        this.homeArmy = new Army(this);
        this.civils = new Civils(this);

        this.warManager = warManager;
    }

    public String name() {
        return name;
    }

    public String kingName() {
        return kingName;
    }

    public int size() { return size; }

    public Army getHomeArmy() { return this.homeArmy; }

    public Army getAssaultArmy() { return this.assaultArmy; }

    public Civils getCivils() { return this.civils; }

    public Resources getResources() {
        return this.resources;
    }

    public void setUnderAttackStatus(boolean status) {
        this.isUnderAttack = status;
        if (this.isUnderAttack)
            MessageBox.pushMessage(name + " is under attack!");
        else
            MessageBox.pushMessage(name + " is no longer under attack");
    }

    public void setUserOwned() {
        ai = false;
    }

    public void setPossibleTarget(Land target) {
        this.possibleTarget = target;
    }

    public void setNeighbour(Land neighbour) {
        if (!neighbours.contains(neighbour))
            neighbours.add(neighbour);
    }

    public void extend(Land neighbour) {
        if (neighbours.contains(neighbour)) {
            neighbours.remove(neighbour);
            MessageBox.pushMessage(name + " occupied " + neighbour.name);
            size += neighbour.size();
            for (int i = 0; i < neighbour.neighbours.size(); i++) {
                if (!neighbour.neighbours.get(i).id.equals(this.id)) {
                    setNeighbour(neighbour.neighbours.get(i));
                    MessageBox.pushMessage(name + " now borders with " + neighbour.neighbours.get(i).name);
                }
            }
        }
    }

    public void addSoldierToHomeArmy() {
        Soldier soldier = new Soldier();
        homeArmy.addSoldier(soldier);
    }

    public void revokeToHomeArmy() {
        while (!assaultArmy.isEmpty()) {
            homeArmy.addSoldier(assaultArmy.getSoldier());
        }
    }

    public void setTrade(Land land, ResourceType resourceType) {
        if (neighbours.contains(land) &&
            (trades.get(resourceType) == null)) {
            trades.put(resourceType, land);
        } else {
            if (!trades.get(resourceType).id.equals(land.id)) {
                unsetTrade(land, resourceType);
                trades.put(resourceType, land);
            }
        }
    }

    public void unsetTrade(Land land, ResourceType resourceType) {
        if (neighbours.contains(land) &&
            (trades.get(resourceType) != null &&
                trades.get(resourceType).id.equals(land.id))) {
            trades.replace(resourceType, null);
        }
    }

    public void attack(Land enemyLand, int soldiersToAttack) {
        if (neighbours.contains(enemyLand)) {
            setAssaultArmy(soldiersToAttack);
            if (!assaultArmy.isEmpty()) {
                isOnWar = warManager.startWar(this.assaultArmy, enemyLand.homeArmy);
                // TODO message
            } else {
                // TODO message
            }
        }
    }

    private void setAssaultArmy(int soldiersCount) {
        for (int i = 0; i <= soldiersCount; i++) {
            if (homeArmy.isEmpty())
                return;
            assaultArmy.addSoldier(homeArmy.getSoldier());
        }
    }
}
