package madrat.Empire;

public class LandManager {

    public LandManager() {

    }

    public void mergeLands(Land land1, Land land2) {

    }

    public void extend(Land fromLand, Land neighbour) {
        if (fromLand.getNeighbours().contains(neighbour)) {
            fromLand.getNeighbours().remove(neighbour);
            int oldSize = fromLand.size();
            MessageBox.pushMessage(fromLand.name() + " occupied " + neighbour.name());
            fromLand.setSize(neighbour.size());
            MessageBox.pushMessage(fromLand.name() + " size extended from " + oldSize + " to " + fromLand.size());
            neighbour.setConquered(true, fromLand);
            neighbour.assaultArmy = fromLand.assaultArmy;
            neighbour.homeArmy = fromLand.homeArmy;
            for (int i = 0; i < neighbour.getNeighbours().size(); i++) {
                if (!neighbour.getNeighbours().get(i).id().equals(fromLand.id()) &&
                    !neighbour.getNeighbours().get(i).id().equals(neighbour.id())) {
                    fromLand.setNeighbour(neighbour.getNeighbours().get(i));
                    neighbour.getNeighbours().get(i).setNeighbour(fromLand);
                    MessageBox.pushMessage(fromLand.name() + " now borders with " + neighbour.getNeighbours().get(i).name());
                }
            }
        }

        mergeArmy(fromLand, neighbour);
    }

    private void mergeArmy(Land fromLand, Land neighbour) {
        neighbour.setCivils(fromLand.getCivils());
        neighbour.reformHomeArmy(fromLand.getHomeArmy());
        neighbour.reformAssaultArmy(fromLand.getAssaultArmy());
    }
}
