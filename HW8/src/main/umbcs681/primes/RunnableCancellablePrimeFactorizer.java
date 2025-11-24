package umbcs681.primes;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeFactorizer extends RunnablePrimeFactorizer {
    private volatile boolean done = false;

    public RunnableCancellablePrimeFactorizer(long dividend, long from, long to) {
        super(dividend, from, to);
    }

    public void setDone() {
            done = true;
    }

    @Override
    public void generatePrimeFactors() {
		long divisor = from;
	    while( dividend != 1 && divisor <= to ){
	      if(done) {
	          System.out.println("Stopped generating prime factors.");
	          this.factors.clear();
	          break; 
	       }  
	    	if( divisor > 2 && isEven(divisor)) {
	    		divisor++;
	    		continue;
	    	}
		    if(dividend % divisor == 0) {
		        factors.add(divisor);
		        dividend /= divisor;
		    }else {
		    	if(divisor==2){ divisor++; }
		    	else{ divisor += 2; }
		    }
		}
	}

    public static void main(String[] args){
        RunnableCancellablePrimeFactorizer f1=new RunnableCancellablePrimeFactorizer(36,2,(long)Math.sqrt(36));
        RunnableCancellablePrimeFactorizer f2=new RunnableCancellablePrimeFactorizer(36,2,(long)Math.sqrt(36));
        RunnableCancellablePrimeFactorizer f3=new RunnableCancellablePrimeFactorizer(36,2,(long)Math.sqrt(36));
        Thread t1 = new Thread(() -> {
            System.out.println("Thread-1 started");
            f1.run();
            System.out.println("Thread-1 result:"+f1.getPrimeFactors());
        });
        Thread t2 = new Thread(() -> {
            System.out.println("Thread-2 started");
            f2.run();
            System.out.println("Thread-2 result:"+f2.getPrimeFactors());
        });
        Thread t3 = new Thread(() -> {
            System.out.println("Thread-3 started");
            f3.run();
            System.out.println("Thread-3 result:"+f3.getPrimeFactors());
        });
        t1.start();
        t2.start();
        t3.start();
        try {Thread.sleep(10);} catch (InterruptedException e) {}
        System.out.println("Sending cancellation to all threads...");
        f1.setDone();
        f2.setDone();
        f3.setDone();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("All threads finished safely.");
    }




}
