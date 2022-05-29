package madrat.Empire;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WarManager {

    private final LandManager landManager;
    private ConcurrentHashMap<Army, Army> enemies;

    public WarManager() {
        this.landManager = new LandManager();
        enemies = new ConcurrentHashMap<Army, Army>();
    }

    public boolean startWar(Army attacker, Army army2) {
        if (attacker.owner().name().equals(army2.owner().name())) {
            MessageBox.pushMessage(attacker.owner().name() + " cannot attack own army");
            return false;
        }

        if (!attacker.owner().getNeighbours().contains(army2.owner())) {
            MessageBox.pushMessage(attacker.owner().name() + " is too far from " + army2.owner().name() + " to attack");
            return false;
        }

        if (!enemies.containsKey(attacker)) {
            // TODO army1 attacks army2 !message!
            if (attacker.size() > 0) {

                Land conqueror = army2.owner().conqueredBy();
                if (conqueror != null) {
                    MessageBox.pushMessage("army " + army2.owner().name() + " belongs to " + conqueror.name());
                    army2 = conqueror.getHomeArmy();
                }

                enemies.put(attacker, army2);
                MessageBox.pushMessage(attacker.owner().name() + " attacks " + army2.owner().name());
                army2.owner().setUnderAttackStatus(true);
                enemies.get(attacker).owner().setOnWar(true);
                attacker.owner().setOnWar(true);
                return true;
            } else {
                MessageBox.pushMessage(attacker.owner().name() + " has no soldiers to attack " + army2.owner().name());
            }
        } else {
            MessageBox.pushMessage("cannot start new war since " + attacker.owner().name() + " is already in war with " + enemies.get(attacker).owner().name());
        }
        return false;
    }

    public void finishWar(Army attacker, Army army2) {
        // TODO merge lands
        // TODO set progress
        // TODO message

        army2.owner().setUnderAttackStatus(false);

        if (attacker.isEmpty()) {
            army2.owner().setPossibleTarget(attacker.owner());

            MessageBox.pushMessage("army " + attacker.owner().name() + " is lost the battle");
            enemies.remove(attacker);
        }

        if (army2.isEmpty()) {
            //attacker.owner().extend(army2.owner());
            Army assaultEnemyArmy = army2.owner().getAssaultArmy();
            Civils civils = army2.owner().getCivils();
            if (assaultEnemyArmy.equals(attacker.owner().getAssaultArmy()) ||
                civils.equals(attacker.owner().getCivils())) {
                MessageBox.pushLogMessage("army " + army2.owner().name() + " is lost the battle");
                enemies.get(attacker).owner().setOnWar(false);
                attacker.owner().setOnWar(false);
                enemies.remove(attacker);
                return;
            }
            landManager.extend(attacker.owner(), army2.owner());
            while (assaultEnemyArmy.size() > 0) {
                Soldier newSoldier = assaultEnemyArmy.getSoldier();
                attacker.addSoldier(newSoldier);
                if (newSoldier != null) {
                    MessageBox.pushMessage(attacker.owner().name() + " | new soldier added: " + newSoldier.name());
                }
            }
            while (civils.size() > 0) {
                Civil civil = civils.getCivil();
                attacker.owner().getCivils().addCivil(civil);
                if (civil != null) {
                    MessageBox.pushMessage(attacker.owner().name() + " | new civil added: " + civil.name());
                }
            }

            MessageBox.pushLogMessage("army " + army2.owner().name() + " is lost the battle");
            enemies.get(attacker).owner().setOnWar(false);
            attacker.owner().setOnWar(false);
            enemies.remove(attacker);
        }

        attacker.owner().revokeToHomeArmy(false);

        // TODO message

    }

    public void process() {
        // TODO

        if (enemies != null) {

            for (Army army: enemies.keySet()) {
                if (army.isEmpty()) {
                    WarStat stat = new WarStat();
                    stat.conquered = army.owner();
                    stat.winner = enemies.get(army).owner();
                    finishWar(army, enemies.get(army));
                    // TODO update message box

                } else if (enemies.get(army).isEmpty()) {
                    WarStat stat = new WarStat();
                    stat.conquered = enemies.get(army).owner();
                    stat.winner = army.owner();
                    finishWar(army, enemies.get(army));
                    // TODO message

                } else {
                    int first = Randomizator.getRandomIndex(2);
                    if (first == 0) {
                        FightLogic.fight(army, enemies.get(army));
                    } else {
                        FightLogic.fight(enemies.get(army), army);
                    }
                }
            }
        }
    }
}
