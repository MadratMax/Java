package madrat.Empire;

import java.util.*;

public class Land {

    private boolean ai;
    private String name;
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
    private boolean conquered;
    private Land conquerorLand;

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

    public String id() {
        return id;
    }

    public String kingName() {
        return kingName;
    }

    public void setOnWar(boolean onWarStatus) {
        isOnWar = onWarStatus;
    }

    public boolean isOnWar() {
        return isOnWar;
    }

    public Land conqueredBy() {
        return conquerorLand;
    }

    public int size() { return size; }

    public void setSize(int newSize) {
        this.size = this.size + newSize;
    }

    public Army getHomeArmy() { return this.homeArmy; }

    public Army getAssaultArmy() { return this.assaultArmy; }

    public Civils getCivils() { return this.civils; }

    public void setCivils(Civils newCivils) {
        this.civils = null;
        this.civils = newCivils;

        for (int i = 0; i < civils.size(); i++) {
            MessageBox.pushMessage(this.name + " civil: " + civils.getCivil().name());
        }
    }

    public void reformHomeArmy(Army newHomeArmy) {
        this.homeArmy = null;
        this.homeArmy = newHomeArmy;
    }

    public void reformAssaultArmy(Army newAssaultArmy) {
        this.assaultArmy = null;
        this.assaultArmy = newAssaultArmy;
    }

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

    public List<Land> getNeighbours() {
        return neighbours;
    }

    public void addSoldierToHomeArmy() {
        Soldier soldier = new Soldier();
        homeArmy.addSoldier(soldier);
    }

    public void revokeToHomeArmy(boolean capitulation) {
        while (!assaultArmy.isEmpty()) {
            Soldier soldier = assaultArmy.getSoldier();
            if (capitulation)
                soldier.setSpirit(Spirit.LOW);
            homeArmy.addSoldier(soldier);
            MessageBox.pushMessage(this.name + " | soldier " + soldier.name() + " returned to home army");
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
        } else {
            MessageBox.pushMessage(this.name() + " is too far from " + enemyLand.name() + " to attack");
        }
    }

    private void setAssaultArmy(int soldiersCount) {
        for (int i = 0; i <= soldiersCount; i++) {
            if (homeArmy.isEmpty())
                return;

            Soldier soldier = homeArmy.getSoldier();

            if (soldier != null) {
                assaultArmy.addSoldier(soldier);
                MessageBox.pushMessage("soldier " + soldier.name() + " was mobilized to the assault army of " + this.name);
                MessageBox.pushMessage(this.name + " home army contains " + homeArmy.size());
                MessageBox.pushMessage(this.name + " assault army contains " + assaultArmy.size());
            }
        }
    }

    public void changeName(String newName) {
        name = newName;
    }

    public void setConquered(boolean res, Land conquerorLand) {
        this.conquered = res;
        this.conquerorLand = conquerorLand;
    }
}
