package madrat.Empire;

import madrat.Empire.DataStructures.Queue;
import madrat.Empire.DataStructures.Stack;

public class MessageBox {

    private static Queue box = new Queue();

    public MessageBox() {

    }

    public static void pushErrorMessage(String message) {
        box.add("    =====================================\n" + "=== "
                + "[ERROR] " + message +
                " ===\n" + "    =====================================");
    }

    public static void pushMessage(Land land, String message) {
        if (land == null || land.ai)
            pushLogMessage(land, message);
        else
            box.add(land.name + " " + message);
    }

    public static void pushLogMessage(Land land, String message) {
        if (land == null)
            box.add("[LOG] " + message);
        else
            box.add("[LOG] " + land.name + " " + message);
    }

    public static void pushFightMessage(Land land, String message) {
        box.add("[FIGHT] " + land.name + " " + message);
    }

    public static String getMessage() {
        return (String) box.remove();
    }

    public static String getLogMessage() {
        return (String) box.remove();
    }

    public static boolean isEmpty() {
        return box.isEmpty();
    }

    public static void pushFactoryMessage(Land land, String message) {
        box.add("[FACTORY] " + land.name + " " + message);
    }

    public static void pushBarracksMessage(Land land, String message) {
        box.add("[ARMY] " + land.name + " " + message);
    }
}
