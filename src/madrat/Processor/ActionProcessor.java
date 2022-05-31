package madrat.Processor;

import madrat.Empire.Configurator.WorldManager;
import madrat.Empire.Land;
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
            if (player.isLost() && player.canMove()) {
                MessageBox.pushErrorMessage("Player " + player.name() + " is lost his land");
                player.canMove(false);
                continue;
            }

            if (!player.isAI()) {
                // provide user interface
                player.act();
            } else {
                if (!player.land().conquered)
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
        MessageBox.pushMessage(null,"------------- ------------- ------------- ------------- ");
        MessageBox.pushMessage(null,"day " + day++);

        runActionQueue();

        for (IPlayer player: players) {

            if (player.land().conquered) {
                player.setLost();
                continue;
            }

            trader.manageTrade(player.land().trade);
            Factory.process(player.land().getCivils(), player.land().resources);

        }

        worldManager.getWarManager().process();


        MessageBox.pushMessage(null,"------------- ------------- ------------- ------------- ");


    }

    private void showGameOver(IPlayer player) {
        System.out.println("Player " + player.name() + " has lost the game");
    }

    // 3rd
    // process conflicts

    // 4th
}
