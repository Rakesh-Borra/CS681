package umbcs681.hw15;

public class ThreeDObserver  implements Observer<StockEvent>{
	
	private StockEvent event;
	
	@Override
    public void update(Observable<StockEvent> sender, StockEvent event) {
		System.out.println("3D updated: "+event.ticker()+ " -> "+event.quote());
    }


}