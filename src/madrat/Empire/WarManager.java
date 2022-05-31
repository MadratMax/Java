package madrat.Empire;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class WarManager {

    private final LandManager landManager;
    private ConcurrentHashMap<Army, Army> enemies;

    public WarManager() {
        this.landManager = new LandManager();
        enemies = new ConcurrentHashMap<Army, Army>();
    }

    public boolean startWar(Army attacker, Army army2) {

        if (army2.owner().conquerorLand != null) {
            army2 = army2.owner().conquerorLand.getHomeArmy();
        }

        if (attacker.owner().name().equals(army2.owner().name())) {
            MessageBox.pushMessage(attacker.owner(),"cannot attack own army");
            return false;
        }

        if (!attacker.owner().getNeighbours().contains(army2.owner())) {
            MessageBox.pushMessage(attacker.owner(), "is too far from " + army2.owner().name() + " to attack");
            return false;
        }

        if (!enemies.containsKey(attacker)) {

            if (attacker.size() > 0) {

                Land conqueror = army2.owner().conqueredBy();
                if (conqueror != null) {
                    MessageBox.pushMessage(army2.owner(), "army belongs to " + conqueror.name());
                    army2 = conqueror.getHomeArmy();
                }

                enemies.put(attacker, army2);
                enemies.get(attacker).owner().setOnWar(true);

                return true;
            } else {
                MessageBox.pushMessage(attacker.owner(),"has no soldiers to attack " + army2.owner().name());
            }
        } else {
            MessageBox.pushMessage(attacker.owner(), "cannot start new war since " + attacker.owner().name() + " is already in war with " + enemies.get(attacker).owner().name());
        }
        return false;
    }

    public void finishWar(Army attacker, Army army2) {

        if (attacker.isEmpty()) {
            army2.owner().setPossibleTarget(attacker.owner());
            MessageBox.pushMessage(attacker.owner(), "army is lost the battle");
            attacker.owner().setUnderAttackStatus(army2.owner(), false);

            enemies.remove(attacker);
        }

        if (army2.isEmpty()) {
            String loser = army2.owner().name();
            MessageBox.pushMessage(army2.owner(), "army is lost the battle");
            army2.owner().setUnderAttackStatus(attacker.owner() ,false);
            boolean unsetUnderAttack = true;
            for (Army attackerArmy: enemies.keySet()) {
                if (enemies.get(attackerArmy).owner().name().equals(attacker.owner().name())) {
                    unsetUnderAttack = false;
                }
            }
            if (unsetUnderAttack) {
                attacker.owner().setOnWar(false);
                attacker.owner().setUnderAttackStatus(army2.owner(),false);
            }

            army2.owner().setOnWar(false);
            Army assaultEnemyArmy = army2.owner().getAssaultArmy();
            Civils civils = army2.owner().getCivils();
            if (assaultEnemyArmy.equals(attacker.owner().getAssaultArmy()) ||
                civils.equals(attacker.owner().getCivils())) {
                enemies.remove(attacker);
                return;
            }

            landManager.extend(attacker.owner(), army2.owner());

            MessageBox.pushLogMessage(army2.owner(),"army is lost the battle");
            while (assaultEnemyArmy.size() > 0) {
                Soldier newSoldier = assaultEnemyArmy.getSoldier();
                attacker.addSoldier(newSoldier);
                if (newSoldier != null) {
                    MessageBox.pushMessage(attacker.owner(),"| new surrendered soldier added: " + newSoldier.name());
                }
            }
            while (civils.size() > 0) {
                Civil civil = civils.getCivil();
                attacker.owner().getCivils().addCivil(civil);
                if (civil != null) {
                    MessageBox.pushMessage(attacker.owner(), "| new surrendered civil added: " + civil.name());
                }
            }

            enemies.get(attacker).owner().setOnWar(false);
            enemies.remove(attacker);
        }

        attacker.owner().revokeToHomeArmy(false);

        boolean unsetUnderAttack = true;
        for (Army attackerArmy: enemies.keySet()) {
            if (enemies.get(attackerArmy).owner().name().equals(attacker.owner().name())) {
                unsetUnderAttack = false;
            }
        }
        if (unsetUnderAttack) {
            attacker.owner().setOnWar(false);
            attacker.owner().setUnderAttackStatus(army2.owner(), false);
        }
    }

    public void process() {

        if (enemies != null) {

            for (int i=enemies.size() - 1; i >= 0; i--) {
                Army army = (Army) enemies.keySet().toArray()[i];
                Army enemyArmy = enemies.get(army);

                if (enemyArmy.owner().conquerorLand != null) {
                    enemyArmy = enemyArmy.owner().conquerorLand.homeArmy;
                }

                if (army.owner().name().equals(enemyArmy.owner().name())) {
                    return;
                }

                MessageBox.pushMessage(army.owner(),"attacks " + enemyArmy.owner().name());
                enemyArmy.owner().setUnderAttackStatus(army.owner(),true);
                army.owner().setOnWar(true);

                if (army.isEmpty()) {
                    finishWar(army, enemyArmy);

                } else if (enemyArmy.isEmpty()) {
                    finishWar(army, enemyArmy);

                } else {
                    int first = Randomizator.getRandomIndex(2);
                    if (first == 0) {
                        FightLogic.fight(army, enemyArmy);
                    } else {
                        FightLogic.fight(enemyArmy, army);
                    }
                }
            }
        }
    }
}
