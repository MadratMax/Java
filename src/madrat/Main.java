package madrat;

import madrat.DataStructures.Graph;
import madrat.Empire.*;
import madrat.Empire.Configurator.WorldManager;
import madrat.Empire.ResourceManager.Factory;
import madrat.Empire.TrainingStation.*;
import madrat.Processor.ActionProcessor;
import madrat.Processor.IPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //Examples.usingBubbleSort();
        //Examples.usingList();
        //Examples.usingStack();

        //Examples.usingBinSearchTree();

        //Examples.usingMergeSort();

        IPublisher publisher = new ConsolePublisher();
        publisher.showFights(true);
        publisher.showFactory(false);

        WorldManager worldManager = new WorldManager(10, 30, 50);
        worldManager.generateWorld().assignLands().resetBorders();

        ActionProcessor processor = new ActionProcessor(worldManager);

        // hack to operate as user
        IPlayer userPlayer = worldManager.getUserPlayer();

        processor.runActionQueue();
        processor.processProgress();
        processor.processProgress();
        processor.processProgress();

        userPlayer.land().getCivils().setTo(ResourceType.FOOD, 1);
        University.createCivil(userPlayer.land(), 2);
        userPlayer.land().resources.GOLD += 2000;
        userPlayer.land().resources.WOOD += 3000;
        userPlayer.land().resources.FOOD += 3000;
        University.createCivil(userPlayer.land(), 3);
        userPlayer.land().setTrade(userPlayer.land().neighbours.get(0), ResourceType.WOOD);

        processor.processProgress();

        userPlayer.land().attack(userPlayer.land().neighbours.get(0), 2);
        Barracks.createSoldier(userPlayer.land(), 10);
        userPlayer.land().attack(userPlayer.land().neighbours.get(0), 2);

        processor.processProgress();

        userPlayer.land().attack(userPlayer.land().neighbours.get(0), 2);
        processor.processProgress();

        worldManager.resetBorders();

        publisher.publish();
    }
}
