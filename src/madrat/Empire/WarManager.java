package madrat.Empire;

import java.util.HashMap;
import java.util.Map;

public class WarManager {

    private Map<Army, Army> enemies;

    public WarManager() {
        enemies = new HashMap<>();
    }

    public boolean startWar(Army attacker, Army army2) {
        if (!enemies.containsKey(attacker)) {
            enemies.put(attacker, army2);
            // TODO army1 attacks army2 !message!
            MessageBox.pushMessage(attacker.owner().name() + " attacks " + army2.owner().name());
            army2.owner().setUnderAttackStatus(true);
            return true;
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

            // no need to get home army since the attackers is lost the battle on the foreign land
            //while (!attacker.owner().getHomeArmy().isEmpty()) {
            //    army2.addSoldier(attacker.getSoldier());
            //}

            MessageBox.pushMessage("army " + attacker.owner().name() + " is lost the battle");
            enemies.remove(attacker);
        }

        if (army2.isEmpty()) {
            attacker.owner().extend(army2.owner());
            while (!army2.owner().getAssaultArmy().isEmpty()) {
                Soldier newSoldier = attacker.addSoldier(army2.owner().getAssaultArmy().getSoldier());
                if (newSoldier != null) {
                    MessageBox.pushMessage(attacker.owner().name() + " | new soldier added: " + newSoldier.name());
                }
            }

            MessageBox.pushLogMessage("army " + army2.owner().name() + " is lost the battle");
            enemies.remove(attacker);
        }

        // TODO message

    }

    public WarStat process() {
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

        return null;
    }
}
