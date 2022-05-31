package madrat.Empire;

import java.util.*;

public class Land {

    public boolean ai;
    public String name;
    public int size;
    public final String id;
    public final String kingName;
    public final WarManager warManager;
    public List<Land> allies;
    public Army assaultArmy;
    public Army homeArmy;
    public Trade trade;
    public Civils civils;
    public Resources resources;
    public List<Land> neighbours;
    public boolean isOnWar;
    public boolean isUnderAttack;
    public Land enemyLandInWar;
    public int capacity;
    public List<Land> landsConquered;
    public int loses;
    public int foodWorkers;
    public int woodWorkers;
    public int goldWorkers;
    public Land possibleTarget;
    public boolean conquered;
    public Land conquerorLand;
    public Land attacker;

    public Land(String name, int size, WarManager warManager) {
        this.ai = true;
        this.name = name;
        this.kingName = "TODO";
        this.size = size;
        this.capacity = size;
        this.id = UUID.randomUUID().toString();

        this.resources = new Resources();

        neighbours = new ArrayList<>();

        this.trade = new Trade(this);

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
        this.capacity = this.size;
    }

    public Army getHomeArmy() { return this.homeArmy; }

    public Army getAssaultArmy() { return this.assaultArmy; }

    public Civils getCivils() { return this.civils; }

    public void setCivils(Civils newCivils) {
        this.civils = newCivils;
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

    public void setUnderAttackStatus(Land attacker, boolean status) {

        if (!this.isUnderAttack && status) {
            MessageBox.pushMessage(this,"is under attack!");
            this.isUnderAttack = status;
            this.attacker = attacker;
        }

        else if (this.isUnderAttack && !status) {
            MessageBox.pushMessage(this, "is no longer under attack");
            this.attacker = null;
            this.isUnderAttack = status;
        }
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

    public void revokeToHomeArmy(boolean capitulation) {
        while (!assaultArmy.isEmpty()) {
            Soldier soldier = assaultArmy.getSoldier();
            if (capitulation)
                soldier.setSpirit(Spirit.LOW);
            homeArmy.addSoldier(soldier);
            MessageBox.pushMessage(this, "| soldier " + soldier.name() + " returned to home army");
        }
    }

    public void setTrade(Land land, ResourceType resourceType) {
        trade.setTrade(resourceType, land);
    }

    public void unsetTrade(ResourceType resourceType) {
        trade.unsetTrade(resourceType);
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
            MessageBox.pushMessage(this, "is too far from " + enemyLand.name() + " to attack");
        }
    }

    public void setAssaultArmy(int soldiersCount) {
        if (soldiersCount == 0) {
            MessageBox.pushMessage(this, "no soldiers to mobilize. add soldiers to home army");
            return;
        }
        for (int i = 0; i < soldiersCount; i++) {
            if (homeArmy.isEmpty()) {
                MessageBox.pushMessage(this, "no soldiers to mobilize. add soldiers to home army");
                return;
            }

            Soldier soldier = homeArmy.getSoldier();

            if (soldier != null) {
                assaultArmy.addSoldier(soldier);
                MessageBox.pushMessage(this, "soldier " + soldier.name() + " was mobilized to the assault army");
                MessageBox.pushMessage(this, "home army contains " + homeArmy.size());
                MessageBox.pushMessage(this, "assault army contains " + assaultArmy.size());
            }
        }
    }

    public void changeName(String newName) {
        name = newName;
    }

    public void setConquered(boolean res, Land conquerorLand) {
        this.conquered = res;
        this.conquerorLand = conquerorLand;
        if (res) {
            MessageBox.pushMessage(this, "has been conquered by " + conquerorLand.name);
            changeName(conquerorLand.name);
        }
    }
}
