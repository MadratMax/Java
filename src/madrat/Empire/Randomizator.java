package madrat.Empire;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizator {

    private static Random random = new Random();

    public static int getRandomIntInRange(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static int getRandomSkill() {
        return random.nextInt(10);
    }

    public static int getRandomMapSize() {
        return ThreadLocalRandom.current().nextInt(150, 1000);
    }

    public static int getRandomHealth() {
        return ThreadLocalRandom.current().nextInt(8, 10);
    }

    public static int getRandomIndex(int arraySize) {
        if (arraySize == 0)
            return 0;
        return ThreadLocalRandom.current().nextInt(0, arraySize);
    }

    public static int getYesNo() {
        int res = random.nextInt(2);
        return res;
    }
}
