package madrat.max;

public class Main {

    public static void main(String[] args) {

        var list = new LinkedList(1);

        list.add(2).add(3).add(4).add(5).add(6).add(7).add(8).add(9).add(10);

        var first = list.head().data();
        var last = list.last().data();
        var index = list.indexOf(10);

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("first: " + first);
        System.out.println("last: " + last);
        System.out.println("index of 10: " + index);

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("list: " + list.getItems());
        System.out.println("first: " + list.head().data());
        System.out.println("last: " + list.last().data());
        System.out.println("length: " + list.length());
        System.out.println("is empty: " + list.isEmpty());
        System.out.println("------------------------------------------------------------------------------------------------");

        System.out.println("remove 5. index: " + list.remove(5));

        System.out.println("------------------------------------------------------------------------------------------------");
        System.out.println("list: " + list.getItems());
        System.out.println("first: " + list.head().data());
        System.out.println("last: " + list.last().data());
        System.out.println("length: " + list.length());
        System.out.println("is empty: " + list.isEmpty());
        System.out.println("------------------------------------------------------------------------------------------------");

        System.out.println("clear elements. total removed: " + list.clear());
        System.out.println("cleared list. list (head): " + list.head());
        System.out.println("is empty: " + list.isEmpty());
        System.out.println("------------------------------------------------------------------------------------------------");
    }
}
