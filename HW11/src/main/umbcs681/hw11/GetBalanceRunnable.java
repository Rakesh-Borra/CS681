package umbcs681.hw11;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class GetBalanceRunnable implements Runnable {

    private BankAccount account;
    private AtomicBoolean done=new AtomicBoolean(false);
    private Random random = new Random();

    public GetBalanceRunnable(BankAccount account) {
        this.account = account;
    }
    
    public void setDone() {
        done.set(true);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                if (done.get()) break;
                account.getBalance();
                Thread.sleep(random.nextInt(20));
            }
        } catch (InterruptedException e) {
        	System.out.println(e);
        }
    }


}

