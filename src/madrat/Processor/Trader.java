package madrat.Processor;

import madrat.Empire.*;

import java.util.Map;

public class Trader {

    public void manageTrade(Trade trade) {
        for (Map.Entry<ResourceType, Land> entry : trade.getTrading().entrySet()) {

            ResourceType res = entry.getKey();
            Land trader = entry.getValue();

            if (res.equals(ResourceType.FOOD) && trader != null) {
                if (trade.owner().resources.GOLD <= 0) {
                    MessageBox.pushMessage(trade.owner(), "failed to trade due to lack of gold");
                    stopTrading(trade.owner());
                    return;
                }
                trade.owner().resources.GOLD =
                    trade.owner().resources.GOLD - GameSettings.foodTradeCommission;
                trade.owner().resources.FOOD += GameSettings.foodTradeProfit;
                trader.resources.GOLD += GameSettings.traderProfit;

                MessageBox.pushMessage(trade.owner(), "trading + " + GameSettings.foodTradeProfit + " food | - " + GameSettings.foodTradeCommission + " gold");
            }

            if (res.equals(ResourceType.WOOD) && trader != null) {
                if (trade.owner().resources.GOLD <= 0) {
                    MessageBox.pushMessage(trade.owner(), "failed to trade due to lack of gold");
                    return;
                }
                trade.owner().resources.GOLD =
                        trade.owner().resources.GOLD - GameSettings.woodTradeCommission;
                trade.owner().resources.WOOD += GameSettings.woodTradeProfit;
                trader.resources.GOLD += GameSettings.traderProfit;

                MessageBox.pushMessage(trade.owner(), "trading + " + GameSettings.woodTradeProfit + " wood | - " + GameSettings.woodTradeCommission + " gold");
            }
        }
    }

    public void stopTrading(Land land) {
        for (ResourceType res: land.trade.getTrading().keySet()) {
            if (land.trade.getTrading().get(res) != null) {
                String traderName = land.trade.getTrading().get(res).name;
                land.trade.getTrading().put(res, null);
                MessageBox.pushMessage(land, "stopped trading with " + traderName);
            }
        }
    }
}
