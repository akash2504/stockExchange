package main.java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

enum TradeType {
    buy,
    sell
}
public class Exchange {
    ConcurrentHashMap<String, Tradebook> exchange;
    Exchange(){
        this.exchange = new ConcurrentHashMap<String, Tradebook>();
    }

    public List<String> addTrade(String orderId, Date tradeTime, String symbol, TradeType tradeType, double price, int quantity){
        if(null == exchange.get(symbol)){
            exchange.put(symbol, new Tradebook(new Stock(symbol)));
        }
        return exchange.get(symbol).addTrade(orderId, tradeTime, tradeType, price, quantity);
    }

    public List<String> addTrades(List<String> inputTrades) throws ParseException {
        List<String> result = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
        for(int i=0;i<inputTrades.size();i++){
            String[] input = inputTrades.get(i).split("\\s+");
            List<String> tempResult =  addTrade(input[0], sdf.parse(input[1]), input[2], TradeType.valueOf(input[3]), Double.parseDouble(input[5]), Integer.parseInt(input[4]));
            result.addAll(tempResult);
        }
        return result;
    }

}
