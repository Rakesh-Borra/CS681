package umbcs681.hw15;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

public class StockQuoteObservable  extends Observable<StockEvent>{
	private final ConcurrentHashMap<String, Double> tickerQuotes =new ConcurrentHashMap<>();	
	
	public void changeQuote(String ticker, double quote) {
		 tickerQuotes.put(ticker, quote);
		 StockEvent event = new StockEvent(ticker, quote);
        notifyObservers(event);
    }
	
	public Double getQuote(String ticker) {
        return tickerQuotes.get(ticker);
    }



}