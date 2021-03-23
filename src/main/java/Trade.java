package main.java;

import java.util.Comparator;
import java.util.Date;

public class Trade {
    private String orderId;
    private Date tradeTime;
    private double price;
    private int quantity;

    Trade(String orderId, Date tradeTime, double price, int quantity){
        this.orderId = orderId;
        this.tradeTime = tradeTime;
        this.price = price;
        this.quantity = quantity;
    }
    public String getOrderId(){
        return this.orderId;
    }
    public Date getTradeTime(){
        return this.tradeTime;
    }
    public double getPrice(){
        return this.price;
    }
    public int getQuantity(){
        return this.quantity;
    }
    public void deductShares(int quantity){
        this.quantity-=quantity;
    }

}
class TradeComparator implements Comparator<Trade> {
    //descending order
    private int desc;
    TradeComparator(int desc){
     this.desc=desc;
    }
    @Override
    public int compare(Trade t1, Trade t2) {
        if (t1.getPrice() < t2.getPrice())
            return 1*desc;
        else if (t1.getPrice() > t2.getPrice())
            return -1*desc;
        else if (t1.getTradeTime().compareTo(t2.getTradeTime())>0)
            return -1*desc;
        else if (t1.getTradeTime().compareTo(t2.getTradeTime())<0)
            return 1*desc;
        return 1*desc;
    }
}