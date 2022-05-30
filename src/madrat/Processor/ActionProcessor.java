package madrat.Processor;

import madrat.Empire.Configurator.WorldManager;
import madrat.Empire.MessageBox;
import madrat.Empire.ResourceManager.Factory;

import java.util.LinkedList;
import java.util.List;

public class ActionProcessor {

    private int day;
    private final List<IPlayer> players;
    private final WorldManager worldManager;
    private Trader trader;

    public ActionProcessor(WorldManager worldManager) {
        this.worldManager = worldManager;
        this.players = this.worldManager.getPlayers();
        trader = new Trader();
        this.day = 1;
    }

    // first action
    // 1.1 user actions (real)

    // process AI
    // 1.2 AI actions (real)
    public void runActionQueue() {
        for (IPlayer player: players) {
            if (!player.isAI()) {
                // provide user interface
                player.act();
            } else {
                player.act();
            }
        }

        // wait user starts next move
    }

    // 2nd
    // calculate trades
    // calc profits
    // calc ...
    public void processProgress() {
        MessageBox.pushMessage("------------- ------------- ------------- ------------- ");
        MessageBox.pushMessage("day " + day++);

        for (IPlayer player: players) {

            trader.manageTrade(player.land().trade);
            Factory.process(player.land().getCivils(), player.land().resources);

        }

        worldManager.getWarManager().process();

        MessageBox.pushMessage("------------- ------------- ------------- ------------- ");
    }

    // 3rd
    // process conflicts

    // 4th
}
