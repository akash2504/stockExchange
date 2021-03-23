package main.java;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;



public class ExchangeTest {
    private Exchange exchange;

    @BeforeEach
    public void setUp() throws Exception {
        exchange = new Exchange();
    }

    @Test
    @DisplayName("Sample Input")
    public void testSampleInput() throws ParseException {
        List<String> inputTrades = new ArrayList<>();
        inputTrades.add("#7 09:52 TCS buy 10 1001.10");
        inputTrades.add("#8 10:01 TCS sell 20 240.10");
        List<String> expected = new ArrayList<>();
        expected.add("#8 10 240.1 #7");
        List<String> result = exchange.addTrades(inputTrades);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("Sample Input2")
    public void testSampleInput2() throws ParseException {
        List<String> inputTrades = new ArrayList<>();
        inputTrades.add("#1 09:45 BAC sell 100 240.10");
        inputTrades.add("#2 09:45 BAC sell 90 237.45");
        inputTrades.add("#3 09:47 BAC buy 80 238.10");
        inputTrades.add("#5 09:48 BAC sell 220 241.50");
        inputTrades.add("#6 09:49 BAC buy 50 238.50");
        inputTrades.add("#7 09:52 TCS buy 10 1001.10");
        inputTrades.add("#8 10:01 BAC sell 20 240.10");
        inputTrades.add("#9 10:02 BAC buy 150 242.70");
        List<String> expected = new ArrayList<>();
        expected.add("#2 80 237.45 #3");
        expected.add("#2 10 237.45 #6");
        expected.add("#1 100 240.10 #9");
        expected.add("#8 20 240.10 #9");
        expected.add("#5 30 241.50 #9");
        List<String> result = exchange.addTrades(inputTrades);
        assertEquals(expected, result);
    }

}
