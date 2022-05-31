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

        //processor.runActionQueue();
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

        userPlayer.land().attack(userPlayer.land().neighbours.get(0), 1);
        Barracks.createSoldier(userPlayer.land(), 2);
        userPlayer.land().attack(userPlayer.land().neighbours.get(0), 1);

        processor.processProgress();


        userPlayer.land().attack(userPlayer.land().neighbours.get(0), 1);

        for (int i = 0; i < 76; i++) {
            processor.processProgress();
            Thread.sleep(10);
        }

        publisher.publish();

        publisher.showLogs(true);
        Barracks.createSoldier(userPlayer.land().neighbours.get(0), 20);
        userPlayer.land().neighbours.get(0).attack(userPlayer.land(), 4);

        for (int i = 0; i < 36; i++) {
            processor.processProgress();
            Thread.sleep(10);
        }

        worldManager.resetBorders();

        publisher.publish();

        for (Land land: worldManager.getLands()) {
            System.out.println("===========");
            System.out.println(land.name);
            System.out.println("gold: " + land.resources.GOLD);
            System.out.println("food: " + land.resources.FOOD);
            System.out.println("wood: " + land.resources.WOOD);
            System.out.println("home army: " + land.homeArmy.size());
            System.out.println("assault army: " + land.assaultArmy.size());
            System.out.println("civils: " + land.civils.size());
            if (land.conqueredBy() != null)
                System.out.println("conquered by: " + land.conqueredBy().name);
            System.out.println("food workers: " + land.foodWorkers);
            System.out.println("gold workers: " + land.goldWorkers);
            System.out.println("wood workers: " + land.woodWorkers);
        }
    }
}
