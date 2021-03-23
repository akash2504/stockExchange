package main.java;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;

public class Tradebook {
    private PriorityQueue<Trade> buyTrades;
    private PriorityQueue<Trade> sellTrades;
    private double lastTradedPrice;
    private Stock stock;

    Tradebook(Stock stock){
        this.buyTrades = new PriorityQueue<Trade>(new TradeComparator(1));
        this.sellTrades = new PriorityQueue<Trade>(new TradeComparator(-1));
        this.stock = stock;
    }
    public synchronized void createBuyOrder(Trade t){
        buyTrades.add(t);
    }
    public synchronized void createSellOrder(Trade t){
        sellTrades.add(t);
    }

    public List<String> addTrade(String orderId, Date tradeTime, TradeType tradeType, double price, int quantity){
        //matchTrade based on buy and sell list and correspondingly add trade
        Trade t = new Trade(orderId, tradeTime, price, quantity);
        if(tradeType==TradeType.buy){
           createBuyOrder(t);
        }
        else if(tradeType==TradeType.sell){
            createSellOrder(t);
        }
        return matchTrade();
    }

    public synchronized List<String> matchTrade(){
        List<String> trades = new ArrayList<>();
        while(sellTrades.size()>0 && buyTrades.size()>0 &&
                (sellTrades.peek().getPrice() <= buyTrades.peek().getPrice())){
            if(buyTrades.peek().getQuantity() >= sellTrades.peek().getQuantity()){
                int sharesToSell = sellTrades.peek().getQuantity();
                String sellOrderIdExecuted = sellTrades.peek().getOrderId();
                this.lastTradedPrice = sellTrades.peek().getPrice();
                String buyOrderIdExecuted = buyTrades.peek().getOrderId();
                buyTrades.peek().deductShares(sharesToSell);
                sellTrades.remove();
                trades.add(sellOrderIdExecuted + " " + sharesToSell + " " + String.format("%.2f", this.lastTradedPrice) + " " + buyOrderIdExecuted);
            }else{
                int sharesToSell = buyTrades.peek().getQuantity();
                String sellOrderIdExecuted = sellTrades.peek().getOrderId();
                this.lastTradedPrice = sellTrades.peek().getPrice();
                String buyOrderIdExecuted = buyTrades.peek().getOrderId();
                sellTrades.peek().deductShares(sharesToSell);
                buyTrades.remove();
                trades.add(sellOrderIdExecuted + " " + sharesToSell + " " + String.format("%.2f", this.lastTradedPrice) + " " + buyOrderIdExecuted);
            }
        }
        return trades;
    }
}
