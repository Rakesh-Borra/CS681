package umbcs681.hw15;

public class DataHandlerRunnable implements Runnable{
	
	private volatile boolean done = false;
	private StockQuoteObservable observable;
	
	public DataHandlerRunnable(StockQuoteObservable observable) {
		this.observable=observable;
	}
	
	public void setDone() {
		this.done= true;
	}

	@Override
    public void run() {
        while (!done) {
            double quote = Math.random();
            observable.changeQuote("xyz", quote);
            try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }


}
