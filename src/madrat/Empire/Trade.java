package madrat.Empire;

import java.util.HashMap;
import java.util.Map;

public class Trade {

    private final Land owner;
    private Map<ResourceType, Land> trades;

    public Trade(Land owner) {
        this.trades = new HashMap<>();
        this.trades.put(ResourceType.FOOD, null);
        this.trades.put(ResourceType.WOOD, null);
        this.owner = owner;
    }

    public Land owner() {
        return owner;
    }

    public Map<ResourceType, Land> getTrading() {
        return trades;
    }

    public void setTrade(ResourceType resourceType, Land land) {
        if (!owner.neighbours.contains(land)) {
            MessageBox.pushMessage(owner, "cannot trade with " + land.name());
            return;
        }

        if (trades.get(resourceType) != null) {
            if (trades.get(resourceType).name.equals(owner.name)) {
                unsetTrade(resourceType, owner);
                return;
            }

            if (!owner.ai) {
                MessageBox.pushMessage(owner, "has set trade type " + resourceType + " with " + trades.get(resourceType).name);
                MessageBox.pushMessage(owner, "need to unset current trade first");
            }

            return;
        }



        trades.put(resourceType, land);
        MessageBox.pushMessage(owner, "sets trade type " + resourceType + " with " + land.name);
    }

    public void unsetTrade(ResourceType resourceType, Land land) {
        if (trades.get(resourceType) != null) {
            if (trades.get(resourceType).name.equals(land.name)) {
                trades.put(resourceType, null);
                MessageBox.pushMessage(owner, "unsets trade type " + resourceType + " with " + land.name);
            }
        }
    }

    public void unsetTrade(ResourceType resourceType) {
        if (trades.get(resourceType) != null) {
            String traderName = trades.get(resourceType).name;
            trades.put(resourceType, null);
            MessageBox.pushMessage(owner, "unsets trade type " + resourceType + " with " + traderName);
        }
    }

    public Land getTraderLand(ResourceType resourceType) {
        return trades.get(resourceType);
    }
}
