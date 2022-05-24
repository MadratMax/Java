package madrat.Tasks;

import java.util.ArrayList;

public class BestLocation {

    // find best location
    private static ArrayList<Block> locations;

    private static void buildBlocks() {
        Block b1 = new Block(false, true, true, "b1");
        Block b2 = new Block(false, false, false, "b2");
        Block b3 = new Block(false, false, true, "b3");
        Block b4 = new Block(true, false, false,"b4");
        Block b5 = new Block(true, true, false, "b5");
        Block b6 = new Block(true, true, false, "b6");

        locations = new ArrayList<>();
        locations.add(b1);
        locations.add(b2);
        locations.add(b3);
        locations.add(b4);
        locations.add(b5);
        locations.add(b6);
    }

    public static void findBestLocation(boolean schoolReq, boolean libReq, boolean officeReq) {
        buildBlocks();

        int maxScore = 3;
        int lastScore = 0;
        int score = 0;
        int position = 0;

        for (int i = 0; i < locations.size(); i++) {
            score = 0;
            if (locations.get(i).school() == true)
                if (schoolReq)
                    score+=2;
                else
                    score++;
            if (locations.get(i).library() == true)
                if (libReq)
                    score+=2;
                else
                    score++;
            if (locations.get(i).office() == true)
                if (officeReq)
                    score+=2;
                else
                    score++;

            if (score > lastScore) {
                lastScore = score;
                position = i;
            }
        }

        System.out.println("Best: " + locations.get(position).name());
    }
}
