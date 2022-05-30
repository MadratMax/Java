package madrat.Empire;

import java.util.UUID;

public class Soldier {

    public String name;
    public final String id;
    public int health;
    public Spirit spirit;
    public int kills;
    public boolean onWar;
    public int skill;
    public int gold;

    public Soldier(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name + "_" + this.id;
        this.spirit = Spirit.MEDIUM;
        this.gold = 5;
        this.skill = Randomizator.getRandomSkill();
        this.health = Randomizator.getRandomHealth();
    }

    public Soldier() {
        this.id = UUID.randomUUID().toString();
        this.name = "_" + this.id;
        this.spirit = Spirit.MEDIUM;
        this.gold = 5;
        this.skill = Randomizator.getRandomSkill();
        this.health = Randomizator.getRandomHealth();
    }

    public String name() {
        return name;
    }

    public void setName(String setName) {
        name = setName + name;
    }

    public int skill() {
        return skill;
    }

    public int health() {
        return health;
    }

    public void injure(int power) {
        health = health - power;
    }

    public int kills() {
        return kills;
    }

    public int gold() {
        return gold;
    }

    public Spirit spirit() {
        return spirit;
    }

    public boolean isOnWar() {
        return onWar;
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void setSpirit(Spirit updateSpirit) {
        spirit = updateSpirit;
    }
}
