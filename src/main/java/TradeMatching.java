package main.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TradeMatching {
    public static void main(String[] args) {
        //Read from the file
        try {
            File myObj = new File(args[0]);
            Scanner myReader = new Scanner(myObj);
            Exchange ex = new Exchange();
            List<String> inputTrades = new ArrayList<>();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputTrades.add(data);
            }
            List<String> result = ex.addTrades(inputTrades);
            for(int i=0;i<result.size();i++){
                System.out.println(result.get(i));
            }
            myReader.close();
        } catch (FileNotFoundException | ParseException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
