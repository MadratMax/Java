package madrat;

import madrat.DataStructures.Graph;
import madrat.Empire.*;
import madrat.Empire.ResourceManager.Factory;
import madrat.Empire.TrainingStation.*;

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
        publisher.publish();

        WarManager warManager = new WarManager();


        Land land1 = new Land("land1", Randomizator.getRandomMapSize(), warManager);
        Land land2 = new Land("land2", Randomizator.getRandomMapSize(), warManager);
        Land land3 = new Land("land3", Randomizator.getRandomMapSize(), warManager);
        Land land4 = new Land("land4", Randomizator.getRandomMapSize(), warManager);
        Land land5 = new Land("land5", Randomizator.getRandomMapSize(), warManager);
        Land land6 = new Land("land6", Randomizator.getRandomMapSize(), warManager);
        Land land7 = new Land("land7", Randomizator.getRandomMapSize(), warManager);
        Land land8 = new Land("land8", Randomizator.getRandomMapSize(), warManager);
        Land land9 = new Land("land9", Randomizator.getRandomMapSize(), warManager);
        Land land10 = new Land("land10", Randomizator.getRandomMapSize(), warManager);
        Land land11 = new Land("land11", Randomizator.getRandomMapSize(), warManager);
        Land land12 = new Land("land12", Randomizator.getRandomMapSize(), warManager);
        Land land13 = new Land("land13", Randomizator.getRandomMapSize(), warManager);
        Land land14 = new Land("land14", Randomizator.getRandomMapSize(), warManager);

        List<Land> lands = new ArrayList<>();
        lands.add(land1);
        //lands.add(land2);
        lands.add(land3);
        //lands.add(land4);
        //lands.add(land5);
        //lands.add(land6);
        //lands.add(land7);
        //lands.add(land8);
        //lands.add(land9);
        //lands.add(land10);
        lands.add(land11);
        //lands.add(land12);
        //lands.add(land13);
        //lands.add(land14);

        Graph<Integer> g = new Graph<Integer>();

        boolean setUserOwned = false;

        for (int i = 0; i<lands.size(); i++) {
            Random rand = new Random();
            Land randomLand = lands.get(rand.nextInt(lands.size()));
            if (randomLand != lands.get(i))
                if (!setUserOwned) {
                    randomLand.setUserOwned();
                    setUserOwned = true;
                }
            if (!lands.get(i).equals(randomLand) && !g.hasEdge(lands.get(i), randomLand)) {
                g.addEdge(lands.get(i), randomLand, true);
            }
        }

        g.setNeighbours();

        land11.getHomeArmy().addSoldier(new Soldier());
        land11.getHomeArmy().addSoldier(new Soldier());
        land11.getHomeArmy().addSoldier(new Soldier());
        land11.getHomeArmy().addSoldier(new Soldier());
        land11.getHomeArmy().addSoldier(new Soldier());

        land3.getHomeArmy().addSoldier(new Soldier());
        land3.getHomeArmy().addSoldier(new Soldier());
        land3.getHomeArmy().addSoldier(new Soldier());
        //land3.getHomeArmy().addSoldier(new Soldier());

        //land3.getHomeArmy().addSoldier(new Soldier());
        //land3.getHomeArmy().addSoldier(new Soldier());
        land1.getHomeArmy().addSoldier(new Soldier());
        land1.getHomeArmy().addSoldier(new Soldier());
        land1.getHomeArmy().addSoldier(new Soldier());

        land1.getResources().GOLD += 100;
        land3.getResources().GOLD += 100;

        Barracks.trainSoldier(land1.getHomeArmy(), 10, land1.getResources());
        Barracks.trainSoldier(land1.getHomeArmy(), 10, land1.getResources());
        Barracks.trainSoldier(land1.getHomeArmy(), 10, land1.getResources());

        land1.attack(land11, 5);
        land3.attack(land11, 5);
        for (int i = 0; i < 76; i++) {
            warManager.process();
            Thread.sleep(20);
        }

        /*land3.attack(land4, 3);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }

        land3.attack(land4, 3);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }*/

        /*land3.attack(land4, 2);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }

        land3.attack(land5, 2);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }

        land3.attack(land10, 2);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }

        land3.getCivils().addCivil(new Civil());
        land3.getCivils().addCivil(new Civil());
        land3.getCivils().addCivil(new Civil());
        //Barracks.trainCivil(land3.getCivils(), 2, land3.getResources());

        land3.getCivils().setTo(ResourceType.FOOD);
        land3.getCivils().setTo(ResourceType.GOLD);
        land3.getCivils().setTo(ResourceType.WOOD);
        land3.getCivils().setTo(ResourceType.FOOD);

        Factory.process(land3.getCivils(), land3.getResources());
        Factory.process(land3.getCivils(), land3.getResources());
        Factory.process(land3.getCivils(), land3.getResources());

        land1.attack(land2, 3);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }

        land1.attack(land5, 3);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }

        land1.attack(land4, 1);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }

        land11.attack(land3, 3);
        for (int i = 0; i < 46; i++) {
            warManager.process();
        }*/

        //for (int i = 0; i < 10; i++) {
        //    lands.get(Randomizator.getRandomIndex(lands.size())).attack(lands.get(Randomizator.getRandomIndex(lands.size())), 5);
        //    for (int j = 0; j < 30; j++)
        //        warManager.process();
        //}

        g.setNeighbours();
        publisher.showFights(true);
        publisher.publish();
    }
}
