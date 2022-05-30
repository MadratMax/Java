package madrat.Empire;

import madrat.Empire.DataStructures.Queue;
import madrat.Empire.DataStructures.Stack;

public class MessageBox {

    private static Queue box = new Queue();

    public MessageBox() {

    }

    public static void pushMessage(String message) {
        box.add(message);
    }

    public static void pushLogMessage(String message) {
        box.add("[LOG] " + message);
    }

    public static void pushFightMessage(String message) {
        box.add("[FIGHT] " + message);
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

    public static void pushFactoryMessage(String message) {
        box.add("[FACTORY] " + message);
    }

    public static void pushBarracksMessage(String message) {
        box.add("[ARMY] " + message);
    }
}
