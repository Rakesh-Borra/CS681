package umbcs681.hw10;

import java.util.Random;

public class WithdrawRunnable implements Runnable {

	private BankAccount account;
    public volatile boolean done = false;
    private Random random = new Random();

    public WithdrawRunnable(BankAccount account) {
        this.account = account;
    }
    
    public void setDone() {
        done = true;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                if (done) break;
                account.withdraw(100);
                Thread.sleep(random.nextInt(20));
            }
        } catch (InterruptedException e) {
        	System.out.println(e);
        }
    }



}

