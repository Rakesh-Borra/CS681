package umbcs681.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
	private volatile boolean done = false;

	public RunnableCancellablePrimeGenerator(long from, long to) {
		super(from, to);
	}
	
	public void setDone(){
		done=true;
	}

	public void generatePrimes(){
		for (long n = from; n <= to; n++) {
			// Stop generating prime numbers if done==true
			if(done){
				System.out.println("Stopped generating prime numbers.");
				this.primes.clear();
				break;
			}
			if( isPrime(n) ){ this.primes.add(n); }
		}
	}

	public static void main(String[] args) {

	    RunnableCancellablePrimeGenerator g1=new RunnableCancellablePrimeGenerator(1,20000);
	    RunnableCancellablePrimeGenerator g2=new RunnableCancellablePrimeGenerator(1,20000);
	    RunnableCancellablePrimeGenerator g3=new RunnableCancellablePrimeGenerator(1,20000);
	    Thread t1 = new Thread(() -> {
	        System.out.println("Thread-1 started");
	        g1.run();
	        System.out.println("Thread-1 result:"+g1.getPrimes());
	    });
	    Thread t2 = new Thread(() -> {
	        System.out.println("Thread-2 started");
	        g2.run();
	        System.out.println("Thread-2 result:"+g2.getPrimes());
	    });
	    Thread t3 = new Thread(() -> {
	        System.out.println("Thread-3 started");
	        g3.run();
	        System.out.println("Thread-3 result:"+g3.getPrimes());
	    });
	    System.out.println("Starting 3 threads...");
	    t1.start();
	    t2.start();
	    t3.start();
	    try { Thread.sleep(40); } catch (InterruptedException e) {}
	    System.out.println("Sending cancellation to all threads...");
	    g1.setDone();
	    g2.setDone();
	    g3.setDone();
	    t1.interrupt();
        t2.interrupt();
        t3.interrupt();
	    try {
	        t1.join();
	        t2.join();
	        t3.join();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
	    System.out.println("All threads stopped safely.");
	}



}
