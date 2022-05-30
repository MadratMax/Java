package madrat.Empire.Configurator;

import madrat.DataStructures.Graph;
import madrat.Empire.*;
import madrat.Empire.TrainingStation.Barracks;
import madrat.Processor.AI;
import madrat.Processor.IPlayer;
import madrat.Processor.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class WorldManager {

    private final int landsCount;
    private Graph<Integer> world;
    private WarManager warManager;
    private List<Land> lands;
    private List<IPlayer> players;

    public WorldManager(int landsCount, int minLandSize, int maxLandSize) {
        this.warManager = new WarManager();
        this.lands = new ArrayList<>(landsCount);
        this.landsCount = landsCount;
    }

    public List getPlayers() {
        return players;
    }

    public List getLands() {
        return lands;
    }

    public IPlayer getUserPlayer() {
        return players.stream().filter(player -> player.isAI() == false).findAny().get();
    }

    public WarManager getWarManager() {
        return warManager;
    }

    public WorldManager generateWorld() {

        for (int i = 0; i < landsCount; i++) {
            int landIndex = i+1;
            lands.add(new Land("Land_" + landIndex, Randomizator.getRandomMapSize(), warManager));
        }

        world = new Graph<Integer>();

        boolean setUserOwned = false;

        for (int i = 0; i<lands.size(); i++) {
            Random rand = new Random();
            Land randomLand = lands.get(rand.nextInt(lands.size()));
            if (randomLand != lands.get(i))
                if (!setUserOwned) {
                    randomLand.setUserOwned();
                    randomLand.name = GameSettings.userLandName;
                    setUserOwned = true;
                }
            if (!lands.get(i).equals(randomLand) && !world.hasEdge(lands.get(i), randomLand)) {
                world.addEdge(lands.get(i), randomLand, true);
            }
        }

        //world.setNeighbours();

        /*land11.getHomeArmy().addSoldier(new Soldier());
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

        Barracks.trainSoldiers(land1.getHomeArmy(), land1.getResources());
        Barracks.trainSoldiers(land1.getHomeArmy(), land1.getResources());
        Barracks.trainSoldiers(land1.getHomeArmy(), land1.getResources());

        land1.attack(land11, 5);
        land3.attack(land11, 5);
        for (int i = 0; i < 76; i++) {
            warManager.process();
            Thread.sleep(20);
        }*/

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

        return this;
    }

    public WorldManager resetBorders() {
        world.setNeighbours();
        return this;
    }

    public WorldManager assignLands() {
        players = new ArrayList<>(lands.size());

        Collections.shuffle(lands);

        for (Land land:lands) {
            if (land.ai)
                players.add(new AI(land));
            else
                players.add(new User(land));
        }

        return this;
    }
}
