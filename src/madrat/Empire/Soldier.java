package madrat.Empire;

import java.util.UUID;

public class Soldier {

    private String name;
    private final String id;
    private int health;
    private Spirit spirit;
    private int kills;
    private boolean onWar;
    private int skill;
    private int gold;

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

    public void train() {
        skill++;
        MessageBox.pushLogMessage(id +  " has been skilled | " + " skill: " + skill);
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
