package umbcs681.hw15;

import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		StockQuoteObservable observable = new StockQuoteObservable();
		Observer line=new LineChartObserver();
		Observer table=new TableObserver();
		Observer Threed=new ThreeDObserver();
		observable.addObserver(line);
		observable.addObserver(table);
		observable.addObserver(Threed);
		
	
	    List<DataHandlerRunnable> handlers = new ArrayList<>();
	    List<Thread> threads = new ArrayList<>();
	
	    for (int i = 0; i < 10; i++) {
	    	DataHandlerRunnable handler = new DataHandlerRunnable(observable);
	        Thread t = new Thread(handler);
	        handlers.add(handler);
	        threads.add(t);
	        t.start();
	    }
	    Thread.sleep(1000);
	    for (DataHandlerRunnable h : handlers) {
	        h.setDone();
	    }
	    for (Thread t : threads) {
	        t.interrupt();
	    }
	    for (Thread t : threads) {
	        t.join();
	    }
	
	    System.out.println("All data handler threads terminated.");

	}

	
}
