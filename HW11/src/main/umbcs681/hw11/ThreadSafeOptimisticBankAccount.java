package umbcs681.hw11;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSafeOptimisticBankAccount implements BankAccount{
	private double balance = 0;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

	public void deposit(double amount){
		rwLock.writeLock().lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (d): " + balance);
			balance = balance + amount;
			System.out.println(", New balance (d): " + balance);
		}finally{
			rwLock.writeLock().unlock();
			System.out.println("Lock released");
		}
	}
	
	public void withdraw(double amount){
		rwLock.writeLock().lock();
		try{
			System.out.println("Lock obtained");
			System.out.print("Current balance (w): " + balance);
			balance = balance - amount;
			System.out.println(", New balance (w): " + balance);
		}finally{
			rwLock.writeLock().unlock();
			System.out.println("Lock released");
		}
	}

	public double getBalance() { 
		rwLock.readLock().lock();
	    try {
	        return this.balance;
	    } finally {
	    	rwLock.readLock().unlock();
	    } 
    }
	
	public static void main(String[] args){
		ThreadSafeOptimisticBankAccount account = new ThreadSafeOptimisticBankAccount();
	    DepositRunnable[] drunnable = new DepositRunnable[10];
	    WithdrawRunnable[] wrunnable = new WithdrawRunnable[10];
	    GetBalanceRunnable[] brunnable = new GetBalanceRunnable[10];
	    Thread[] threads = new Thread[30];
	    int idx = 0;
	    for (int i = 0; i < 10; i++) {
	    	drunnable[i] = new DepositRunnable(account);
	        threads[idx] = new Thread(drunnable[i]);
	        threads[idx].start();
	        idx++;
	    }
	    for (int i = 0; i < 10; i++) {
	    	wrunnable[i] = new WithdrawRunnable(account);
	        threads[idx] = new Thread(wrunnable[i]);
	        threads[idx].start();
	        idx++;
	    }
	    for (int i = 0; i < 10; i++) {
	    	brunnable[i] = new GetBalanceRunnable(account);
	        threads[idx] = new Thread(brunnable[i]);
	        threads[idx].start();
	        idx++;
	    }
	    try { Thread.sleep(2000); } catch (InterruptedException e) {}
	    
	    for (DepositRunnable r : drunnable) r.setDone();
	    for (WithdrawRunnable r : wrunnable) r.setDone();
	    for (GetBalanceRunnable r : brunnable) r.setDone();
	    for (Thread t : threads) t.interrupt();
	    for (Thread t : threads) {
	        try { t.join(); } catch (InterruptedException e) {}
	    }
	    System.out.println("Final balance = " + account.getBalance());	
		
	}

}
