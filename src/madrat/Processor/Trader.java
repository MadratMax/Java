package madrat.Processor;

import madrat.Empire.*;

import java.util.Map;

public class Trader {

    public static void manageTrade(Trade trade) {
        for (Map.Entry<ResourceType, Land> entry : trade.getTrading().entrySet()) {

            ResourceType res = entry.getKey();
            Land trader = entry.getValue();

            if (res.equals(ResourceType.FOOD) && trader != null) {
                if (trade.owner().resources.GOLD <= 0) {
                    MessageBox.pushMessage(trade.owner().name + " failed to trade due to lack of gold");
                    return;
                }
                trade.owner().resources.GOLD =
                    trade.owner().resources.GOLD - GameSettings.foodTradeCommission;
                trade.owner().resources.FOOD += GameSettings.foodTradeProfit;
                trader.resources.GOLD += GameSettings.traderProfit;
            }

            if (res.equals(ResourceType.WOOD) && trader != null) {
                if (trade.owner().resources.GOLD <= 0) {
                    MessageBox.pushMessage(trade.owner().name + " failed to trade due to lack of gold");
                    return;
                }
                trade.owner().resources.GOLD =
                        trade.owner().resources.GOLD - GameSettings.woodTradeCommission;
                trade.owner().resources.WOOD += GameSettings.woodTradeProfit;
                trader.resources.GOLD += GameSettings.traderProfit;
            }
        }
    }

    public void stopTrading(Land land) {
        for (ResourceType res: land.trades.keySet()) {
            if (land.trades.get(res) != null) {
                String traderName = land.trades.get(res).name;
                land.trades.put(res, null);
                MessageBox.pushMessage(land.name + " stopped trading with " + traderName);
            }
        }
    }
}
